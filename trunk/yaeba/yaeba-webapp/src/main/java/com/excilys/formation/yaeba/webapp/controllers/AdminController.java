package com.excilys.formation.yaeba.webapp.controllers;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.excilys.formation.yaeba.model.Utilisateur;
import com.excilys.formation.yaeba.webapp.CustomUser;
import com.excilys.formation.yaeba.webapp.StaticParam;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@RequestMapping("/admin.html")
	public String redirectComptes(ModelMap model) {
		Utilisateur u = ((CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUtilisateur();
		model.put(StaticParam.UTILISATEUR_NAME, u);

		return "admin";
	}

}
