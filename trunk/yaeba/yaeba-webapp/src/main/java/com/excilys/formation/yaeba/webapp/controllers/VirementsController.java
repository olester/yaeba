package com.excilys.formation.yaeba.webapp.controllers;

import java.util.Locale;
import java.util.ResourceBundle;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.excilys.formation.yaeba.model.Utilisateur;
import com.excilys.formation.yaeba.webapp.CustomUser;

@Controller
@RequestMapping("/user/virements")
public class VirementsController {

	@RequestMapping("/virements.html")
	public String redirectVirements(ModelMap model, Locale locale) {
		ResourceBundle bundle = ResourceBundle.getBundle("messages_" + locale.getLanguage());
		model.put("title", bundle.getString("transfers.title"));
		model.put("bouton", "bouton_comptes");
		model.put("bouton", "bouton_virements");

		Utilisateur u = ((CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUtilisateur();

		model.put("nom", u.getNom());
		model.put("prenom", u.getPrenom());

		return "virements";
	}
}
