package com.excilys.formation.yaeba.webapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("admin")
public class AdminController {

	@RequestMapping(value = "/index.html")
	public String acceuil() {
		return "hello";
	}

}
