package com.excilys.formation.yaeba.webapp.controllers;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.excilys.formation.yaeba.model.Utilisateur;
import com.excilys.formation.yaeba.webapp.CustomUser;

@Controller
public class DefaultController {

	@RequestMapping("/welcome.html")
	public String redirectWelcome(ModelMap model) {
		model.put("bouton", "bouton_welcome");

		Utilisateur u = ((CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUtilisateur();
		model.put("nom", u.getNom());
		model.put("prenom", u.getPrenom());

		return "welcome";
	}

	@RequestMapping("/login.html")
	public String redirectLogin(ModelMap model) {
		model.put("bouton", "bouton_login");
		return "login";
	}

	@RequestMapping("/error-403.html")
	public String redirectError403(ModelMap model) {
		return "error-403";
	}

	@RequestMapping("/error-404.html")
	public String redirectError404(ModelMap model) {
		return "error-404";
	}

	@RequestMapping("/error-500.html")
	public String redirectError500(ModelMap model) {
		return "error-500";
	}

}
