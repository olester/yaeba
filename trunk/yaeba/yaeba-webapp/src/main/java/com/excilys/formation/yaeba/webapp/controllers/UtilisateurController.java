package com.excilys.formation.yaeba.webapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.excilys.formation.yaeba.model.Utilisateur;
import com.excilys.formation.yaeba.service.api.UtilisateurService;

@Controller
public class UtilisateurController {
	
	@Autowired
	private UtilisateurService utilisateurService;
	
	@RequestMapping(value="/save/utilisateur.html")
	public void save(Utilisateur u){
		utilisateurService.save(u);
	}

}
