package com.excilys.formation.yaeba.webapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WelcomeController {

	@RequestMapping("/welcome.html")
	public String redirect() {
		System.out.println("ok");
		return "welcome";
	}

}
