package com.excilys.formation.yaeba.webapp.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.excilys.formation.yaeba.model.Compte;

@Controller
@RequestMapping("/user")
public class UserRoleController {

	@RequestMapping("/comptes.html")
	public String redirectComptes(ModelMap model) {
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
	public String redirectVirements(ModelMap model) {
		model.put("bouton", "bouton_virements");
		return "virements";
	}
}
