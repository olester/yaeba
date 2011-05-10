package com.excilys.formation.yaeba.webapp.controllers;

import java.util.Locale;
import java.util.ResourceBundle;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.excilys.formation.yaeba.model.Utilisateur;
import com.excilys.formation.yaeba.webapp.CustomUser;

@Controller
public class DefaultController {

	@RequestMapping("/welcome.html")
	public String redirectWelcome(ModelMap model, Locale locale) {
		ResourceBundle bundle = ResourceBundle.getBundle("messages_" + locale.getLanguage());
		model.put("title", bundle.getString("welcome.title"));
		model.put("bouton", "bouton_welcome");

		Utilisateur u = ((CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUtilisateur();
		model.put("utilisateur", u);

		return "welcome";
	}

	@RequestMapping("/login.html")
	public String redirectLogin(ModelMap model, Locale locale) {
		ResourceBundle bundle = ResourceBundle.getBundle("messages_" + locale.getLanguage());
		model.put("title", bundle.getString("login.title"));
		model.put("bouton", "bouton_login");
		return "login";
	}

	@RequestMapping("/error-403.html")
	public String redirectError403(ModelMap model, Locale locale) {
		ResourceBundle bundle = ResourceBundle.getBundle("messages_" + locale.getLanguage());
		model.put("title", bundle.getString("welcome.title"));
		model.put("error_text", bundle.getString("error-403.text"));
		model.put("error_code", "403");
		return "error";
	}

	@RequestMapping("/error-404.html")
	public String redirectError404(ModelMap model, Locale locale) {
		ResourceBundle bundle = ResourceBundle.getBundle("messages_" + locale.getLanguage());
		model.put("title", bundle.getString("welcome.title"));
		model.put("error_text", bundle.getString("error-404.text"));
		model.put("error_code", "404");
		return "error";
	}

	@RequestMapping("/error-500.html")
	public String redirectError500(ModelMap model, Locale locale) {
		ResourceBundle bundle = ResourceBundle.getBundle("messages_" + locale.getLanguage());
		model.put("title", bundle.getString("welcome.title"));
		model.put("error_text", bundle.getString("error-500.text"));
		model.put("error_code", "500");
		return "error";
	}

}
