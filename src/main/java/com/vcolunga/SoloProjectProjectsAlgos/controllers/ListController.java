package com.vcolunga.SoloProjectProjectsAlgos.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.vcolunga.SoloProjectProjectsAlgos.models.Media;
import com.vcolunga.SoloProjectProjectsAlgos.models.MediaList;
import com.vcolunga.SoloProjectProjectsAlgos.models.User;
import com.vcolunga.SoloProjectProjectsAlgos.services.MediaListService;
import com.vcolunga.SoloProjectProjectsAlgos.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/lists")
public class ListController {
	@Autowired
	private HttpSession session;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private MediaListService mediaListService;
	
	@GetMapping("")
	public String listsPage(
			@ModelAttribute("newList") MediaList newList,
			Model model) {
		Long userId = (Long) session.getAttribute("userId");
		
		if(userId == null) {
			return "redirect:/";
		}
		
		User currentUser = userService.findById(userId);
		
		model.addAttribute("currentUser", currentUser);
		
		model.addAttribute("MLService", mediaListService);
		
		return "lists.jsp";
	}
	
	@PostMapping("")
	public String listsNew(@Valid @ModelAttribute("newList") MediaList newList, BindingResult result, Model model) {
		Long userId = (Long) session.getAttribute("userId");
		
		if(userId == null) {
			return "redirect:/";
		}
		
		User currentUser = userService.findById(userId);
		
		model.addAttribute("currentUser", currentUser);
		
		User friend = userService.findById(newList.getUserId());
		
		mediaListService.createMediaList(newList);
		
		userService.addList(currentUser, friend, newList);
		
		return "redirect:/lists";
		
	}
	
	@GetMapping("/{id}")
	public String listViewPage(@PathVariable("id") Long listId, Model model) {
		Long userId = (Long) session.getAttribute("userId");
		
		if(userId == null) {
			return "redirect:/";
		}
		
		User currentUser = userService.findById(userId);
		
		MediaList currentList = mediaListService.findList(listId);
		
		model.addAttribute("currentUser", currentUser);
		
		model.addAttribute("currentList", currentList);
		
		model.addAttribute("friend", mediaListService.findOppositeUser(currentList, currentUser).getUserName());
		
		return "listView.jsp";
	}
	
	@GetMapping("/{id}/add")
	public String newMediaPage(@PathVariable("id") Long listId, @ModelAttribute("newMedia") Media newMedia, Model model) {
		return "newMedia.jsp";
	}
}
