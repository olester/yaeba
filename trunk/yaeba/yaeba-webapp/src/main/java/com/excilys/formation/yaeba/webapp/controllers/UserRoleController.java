package com.excilys.formation.yaeba.webapp.controllers;

import java.util.Locale;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.excilys.formation.yaeba.model.Compte;
import com.excilys.formation.yaeba.model.Utilisateur;
import com.excilys.formation.yaeba.service.api.CompteService;
import com.excilys.formation.yaeba.webapp.CustomUser;

@Controller
@RequestMapping("/user")
public class UserRoleController {

	@Autowired
	private CompteService compteService;

	@RequestMapping("/comptes.html")
	public String redirectComptes(ModelMap model, Locale locale) {
		ResourceBundle bundle = ResourceBundle.getBundle("messages_" + locale.getLanguage());
		model.put("title", bundle.getString("accounts.title"));
		model.put("bouton", "bouton_comptes");

		Utilisateur u = ((CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUtilisateur();
		model.put("utilisateur", u);

		return "comptes";
	}

	@RequestMapping("/comptes/{numeroCompte}/details.html")
	public String redirectDetailsCompte(@PathVariable("numeroCompte") String numeroCompte, ModelMap model, Locale locale) {
		ResourceBundle bundle = ResourceBundle.getBundle("messages_" + locale.getLanguage());

		Utilisateur u = ((CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUtilisateur();

		for (Compte c : u.getComptes()) {
			if (c.getNumeroCompte().equals(numeroCompte)) {
				model.put("title", bundle.getString("details.title"));
				model.put("bouton", "bouton_comptes");
				model.put("numero", numeroCompte);
				model.put("compte", c);
				return "detailsCompte";
			}
		}

		model.put("title", bundle.getString("welcome.title"));
		model.put("error_text", bundle.getString("error-404.text"));
		model.put("error_code", "404");
		return "error";
	}

	@RequestMapping("/virements.html")
	public String redirectVirements(ModelMap model, Locale locale) {
		ResourceBundle bundle = ResourceBundle.getBundle("messages_" + locale.getLanguage());
		model.put("title", bundle.getString("transfers.title"));
		model.put("bouton", "bouton_comptes");
		model.put("bouton", "bouton_virements");
		return "virements";
	}

}
