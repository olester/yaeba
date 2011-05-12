package com.excilys.formation.yaeba.webapp.controllers;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.excilys.formation.yaeba.model.Utilisateur;
import com.excilys.formation.yaeba.webapp.CustomUser;

@Controller
public class DefaultController {

	@RequestMapping("/welcome.html")
	public String redirectWelcome(ModelMap model) {
		model.put("bouton", "bouton_welcome");

		Utilisateur u = ((CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUtilisateur();
		model.put("utilisateur", u);

		return "welcome";
	}

	@RequestMapping("/login.html")
	public String redirectLogin(ModelMap model) {
		model.put("bouton", "bouton_login");
		return "login";
	}

	@RequestMapping("/error-{code}.html")
	public String redirectError(@PathVariable("code") String code, ModelMap model) {
		int codeInt;
		try {
			codeInt = Integer.parseInt(code);
		} catch (NumberFormatException e) {
			return "error-404";
		}
		if (codeInt == 403 || codeInt == 404 || codeInt == 500) return "error-" + codeInt;
		return "error-404";
	}

}
