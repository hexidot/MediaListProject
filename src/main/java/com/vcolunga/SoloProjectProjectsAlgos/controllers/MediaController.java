package com.vcolunga.SoloProjectProjectsAlgos.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.vcolunga.SoloProjectProjectsAlgos.models.Media;
import com.vcolunga.SoloProjectProjectsAlgos.models.User;
import com.vcolunga.SoloProjectProjectsAlgos.services.MediaListService;
import com.vcolunga.SoloProjectProjectsAlgos.services.MediaService;
import com.vcolunga.SoloProjectProjectsAlgos.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/media")
public class MediaController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private MediaService mediaService;
	
	@Autowired
	private MediaListService mediaListService;
	
	@Autowired
	private HttpSession session;
	
	@GetMapping("/{id}")
	public String mediaView(@PathVariable("id") Long id, Model model) {
		Long userId = (Long) session.getAttribute("userId");
		
		if(userId == null) {
			return "redirect:/";
		}
		
		User currentUser = userService.findById(userId);
		
		model.addAttribute("currentUser", currentUser);
		
		Media currentMedia = mediaService.findMedia(id);
		
		model.addAttribute("currentMedia", currentMedia);
		
		model.addAttribute("MLService", mediaListService);
		
		return "mediaView.jsp";
	}
	
	@DeleteMapping("/{id}/delete")
	public String deleteMedia(@PathVariable("id") Long id) {
		Media deletedMedia = mediaService.findMedia(id);
		mediaService.deleteMedia(id);
		return "redirect:/lists/" + deletedMedia.getParentList().getId();
	}
	
	@GetMapping("/{id}/edit")
	public String editPage(@PathVariable("id") Long id, Model model) {
		Long userId = (Long) session.getAttribute("userId");
		
		if(userId == null) {
			return "redirect:/";
		}
		
		User currentUser = userService.findById(userId);
		
		model.addAttribute("currentUser", currentUser);
		
		Media currentMedia = mediaService.findMedia(id);
		
		model.addAttribute("currentMedia", currentMedia);
		
		if(!mediaListService.containsUser(currentMedia.getParentList(), currentUser)) {
			return "redirect:/lists";
		}
		
		String[] types = {"Movie", "Game", "Book"};
		
		model.addAttribute("types", types);
		
		return "editMedia.jsp";
	}
	
	@PutMapping("/{id}/edit")
	public String editDB(@PathVariable("id") Long id, Model model, @Valid @ModelAttribute("currentMedia") Media currentMedia, BindingResult result) {
		
		if(result.hasErrors()) {
			Long userId = (Long) session.getAttribute("userId");
			
			if(userId == null) {
				return "redirect:/";
			}
			
			User currentUser = userService.findById(userId);
			
			model.addAttribute("currentUser", currentUser);
			
			String[] types = {"Movie", "Game", "Book"};
			
			model.addAttribute("types", types);
			
			return "editMedia.jsp";
		}
		
		mediaService.updateMedia(currentMedia);
		
		return "redirect:/media/" + currentMedia.getId();
	}
}
