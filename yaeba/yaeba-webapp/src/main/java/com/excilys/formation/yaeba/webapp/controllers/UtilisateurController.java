package com.excilys.formation.yaeba.webapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("user")
public class UtilisateurController {

	@RequestMapping(value = "/index.html")
	public String acceuil() {
		return "welcome";
	}

}
