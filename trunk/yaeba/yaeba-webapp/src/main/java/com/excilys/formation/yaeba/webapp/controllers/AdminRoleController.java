package com.excilys.formation.yaeba.webapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminRoleController {

	@RequestMapping("/panel.html")
	public String redirectComptes(ModelMap model) {
		model.put("title", "Index Admin");
		model.put("bouton", "bouton_admin");
		return "panel";
	}

}
