package com.excilys.formation.yaeba.webapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.excilys.formation.yaeba.model.Utilisateur;
import com.excilys.formation.yaeba.webapp.StaticParam;
import com.excilys.formation.yaeba.webapp.UtilisateurUtils;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@RequestMapping("/admin.html")
	public String redirectComptes(ModelMap model) {
		Utilisateur u = UtilisateurUtils.getUtilisateur();
		model.put(StaticParam.UTILISATEUR_NAME, u);

		return "admin";
	}

}
