package com.excilys.formation.yaeba.webapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WelcomeController {

	@RequestMapping("/welcome.html")
	public String redirectWelcome(ModelMap model) {
		model.put("title", "welcome");
		model.put("bouton", "bouton_welcome");
		return "welcome";
	}

	@RequestMapping("/login.html")
	public String redirectLogin(ModelMap model) {
		model.put("title", "login");
		model.put("bouton", "bouton_login");
		return "login";
	}

}
