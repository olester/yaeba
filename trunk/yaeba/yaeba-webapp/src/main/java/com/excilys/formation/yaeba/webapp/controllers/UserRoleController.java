package com.excilys.formation.yaeba.webapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserRoleController {

	@RequestMapping("/comptes.html")
	public String redirectComptes(ModelMap model) {
		model.put("bouton", "bouton_comptes");
		return "comptes";
	}

	@RequestMapping("/virements.html")
	public String redirectVirements(ModelMap model) {
		model.put("bouton", "bouton_virements");
		return "virements";
	}
}
