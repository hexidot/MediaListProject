package com.vcolunga.SoloProjectProjectsAlgos.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.vcolunga.SoloProjectProjectsAlgos.models.LoginUser;
import com.vcolunga.SoloProjectProjectsAlgos.models.User;
import com.vcolunga.SoloProjectProjectsAlgos.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class LoginController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private HttpSession session;
	
	@GetMapping("/")
	public String loginPage(@ModelAttribute("newUser") User newUser, @ModelAttribute("newLogin") LoginUser newLogin) {
		Long userId = (Long) session.getAttribute("userId");
		
		if(userId != null) {
			return "redirect:/home";
		}
		
		return "loginPage.jsp";
	}
	
	@PostMapping("/register")
	public String registerUserDB(@Valid @ModelAttribute("newUser") User newUser, BindingResult result, Model model) {
		
		//call register method in user service
		User possibleRegistration = userService.register(newUser, result); 
		
		//if register returns null (validations failed), show errors
		if (possibleRegistration == null) {
			model.addAttribute("newLogin", new LoginUser());
			return "loginPage.jsp";
		}
		
		//set userId as session attribute for validations
		session.setAttribute("userId", possibleRegistration.getId());
		
		//finally, redirect to home page
		return "redirect:/home";
		
	}
	
	@PostMapping("/login")
	public String loginUser(@Valid @ModelAttribute("newLogin") LoginUser newLogin, BindingResult result, Model model) {
		
		//call login for validation
		User possibleLogin = userService.login(newLogin, result);
		
		//if errors exist, return to login and display them
		if(possibleLogin == null) {
			model.addAttribute("newUser", new User());
			return "loginPage.jsp";
		}
		
		//else, set session userId and redirect to home page
		session.setAttribute("userId", possibleLogin.getId());
		
		return "redirect:/home";
	}
	
	@PostMapping("/logout")
	public String logout() {
		session.invalidate();
		return "redirect:/";
	}
	
}
