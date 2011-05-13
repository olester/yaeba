package com.excilys.formation.yaeba.webapp.controllers;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.excilys.formation.yaeba.model.Utilisateur;
import com.excilys.formation.yaeba.service.api.OperationService;
import com.excilys.formation.yaeba.webapp.CustomUser;

@Controller
@RequestMapping("/user/virements")
public class VirementsController {

	@Autowired
	private OperationService operationService;

	@RequestMapping("/virements.html")
	public String redirectVirements(ModelMap model) {
		Utilisateur u = ((CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUtilisateur();
		model.put("utilisateur", u);

		return "virements";
	}

	@RequestMapping("/historique.html")
	public String redirectHistorique(ModelMap model, Locale locale) {
		Utilisateur u = ((CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUtilisateur();
		model.put("utilisateur", u);

		model.put("listeVirements", operationService.getVirementsInternes(u));
		model.put("locale", locale.getLanguage());

		return "historique";
	}

}
