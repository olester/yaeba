package com.excilys.formation.yaeba.webapp.controllers;


import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.excilys.formation.yaeba.model.Compte;
import com.excilys.formation.yaeba.service.api.CompteService;

@Controller
@RequestMapping("/user")
public class UserRoleController {

	@RequestMapping("/comptes.html")
	public String redirectComptes(ModelMap model, Locale locale) {
		ResourceBundle bundle = ResourceBundle.getBundle("messages_"+locale.getLanguage());
		model.put("title", bundle.getString("accounts.title"));
		model.put("bouton", "bouton_comptes");

		// Pour le test d'affichage de la page jsp
		Compte c1 = new Compte();
		c1.setNumeroCompte("789 123 456");
		c1.setDatecreation(new Date(2011, 04, 30));
		Compte c2 = new Compte();
		c2.setNumeroCompte("123 456 789");
		c2.setDatecreation(new Date(2011, 05, 05));
		List<Compte> comptes = new ArrayList<Compte>();
		comptes.add(c1);
		comptes.add(c2);
		// -------------

		model.put("comptes", comptes);
		return "comptes";
	}

	@RequestMapping("/virements.html")
	public String redirectVirements(ModelMap model, Locale locale) {
		ResourceBundle bundle = ResourceBundle.getBundle("messages_"+locale.getLanguage());
		model.put("title", bundle.getString("transfers.title"));
		model.put("bouton", "bouton_comptes");
		model.put("bouton", "bouton_virements");
		return "virements";
	}

	@Autowired
	private CompteService compteService;

	@RequestMapping("/comptes/{numeroCompte}/details.html")
	public String redirectDetailsCompte(
			@PathVariable("numeroCompte") String numeroCompte, ModelMap model, Locale locale) {
		ResourceBundle bundle = ResourceBundle.getBundle("messages_"+locale.getLanguage());
		model.put("title", bundle.getString("details.title"));
		model.put("bouton", "bouton_comptes");
		Compte compte = compteService.getCompteByNumeroCompte(numeroCompte);
		model.put("numero", numeroCompte);
		model.put("compte", compte);
		return "detailsCompte";
	}
}
