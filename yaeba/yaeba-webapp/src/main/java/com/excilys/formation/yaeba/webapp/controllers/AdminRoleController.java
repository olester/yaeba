package com.excilys.formation.yaeba.webapp.controllers;

import java.util.Locale;
import java.util.ResourceBundle;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminRoleController {

	@RequestMapping("/admin.html")
	public String redirectComptes(ModelMap model, Locale locale) {
		ResourceBundle bundle = ResourceBundle.getBundle("messages_"+locale.getLanguage());
		model.put("title", bundle.getString("admin.title"));
		model.put("bouton", "bouton_comptes");
		model.put("bouton", "bouton_admin");
		return "admin";
	}

}
