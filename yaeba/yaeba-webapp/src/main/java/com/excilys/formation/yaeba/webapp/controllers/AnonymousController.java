package com.excilys.formation.yaeba.webapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AnonymousController {

	@RequestMapping("/welcome.html")
	public String redirectWelcome(ModelMap model) {
		model.put("bouton", "bouton_welcome");
		return "welcome";
	}

	@RequestMapping("/login.html")
	public String redirectLogin(ModelMap model) {
		model.put("bouton", "bouton_login");
		return "login";
	}

	@RequestMapping("/error-403.html")
	public String redirectError403(ModelMap model) {
		model.put("title", "Acces interdit");
		model.put("bouton", "");
		model.put("error_code", "403");
		return "error";
	}

	@RequestMapping("/error-404.html")
	public String redirectError404(ModelMap model) {
		model.put("title", "Page perdue");
		model.put("bouton", "");
		model.put("error_code", "404");
		return "error";
	}

	@RequestMapping("/error-500.html")
	public String redirectError500(ModelMap model) {
		model.put("title", "Erreur interne au serveur");
		model.put("bouton", "");
		model.put("error_code", "500");
		return "error";
	}

}
