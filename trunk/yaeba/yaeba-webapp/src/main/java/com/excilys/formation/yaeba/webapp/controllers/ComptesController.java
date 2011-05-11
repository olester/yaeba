package com.excilys.formation.yaeba.webapp.controllers;

import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.TreeSet;

import javax.servlet.http.HttpServletRequest;

import org.joda.time.DateTime;
import org.joda.time.Months;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.excilys.formation.yaeba.model.Compte;
import com.excilys.formation.yaeba.model.OperationCarteBancaire;
import com.excilys.formation.yaeba.model.Utilisateur;
import com.excilys.formation.yaeba.service.api.CompteService;
import com.excilys.formation.yaeba.service.api.OperationService;
import com.excilys.formation.yaeba.webapp.CustomUser;

@Controller
@RequestMapping("user/comptes")
public class ComptesController {

	@Autowired
	private OperationService operationService;
	@Autowired
	private CompteService compteService;

	@RequestMapping("/comptes.html")
	public String redirectComptes(ModelMap model, Locale locale) {
		ResourceBundle bundle = ResourceBundle.getBundle("messages_" + locale.getLanguage());
		model.put("title", bundle.getString("accounts.title"));
		model.put("bouton", "bouton_comptes");

		DateTime dt = new DateTime();
		model.put("annee", dt.getYear());
		model.put("mois", dt.getMonthOfYear());

		Utilisateur u = ((CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUtilisateur();
		List<Compte> comptes = compteService.getComptesByUtilisateur(u);
		model.put("comptes", comptes);
		model.put("nom", u.getNom());
		model.put("prenom", u.getPrenom());

		return "comptes";
	}

	@RequestMapping("/{numeroCompte}/{annee}/{mois}/{page}/details.html")
	public String redirectDetailsCompte(@PathVariable("numeroCompte") String numeroCompte, @PathVariable("annee") String annee,
			@PathVariable("mois") String mois, @PathVariable("page") String page, ModelMap model, Locale locale) {
		ResourceBundle bundle = ResourceBundle.getBundle("messages_" + locale.getLanguage());

		Utilisateur u = ((CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUtilisateur();

		int anneeInt;
		int moisInt;
		int pageInt;

		try {
			anneeInt = Integer.parseInt(annee);
			moisInt = Integer.parseInt(mois);
			pageInt = Integer.parseInt(page);
		} catch (NumberFormatException e) {

			model.put("nom", u.getNom());
			model.put("prenom", u.getPrenom());
			model.put("title", bundle.getString("welcome.title"));
			model.put("error_text", bundle.getString("error-404.text"));
			model.put("error_code", "404");
			return "error";
		}

		Compte c = compteService.getCompteByNumeroCompte(u, numeroCompte);

		if (c != null) {
			model.put("title", bundle.getString("details.title"));
			model.put("bouton", "bouton_comptes");
			model.put("numero", numeroCompte);
			model.put("annee", anneeInt);
			model.put("mois", moisInt);
			model.put("locale", locale.getLanguage());
			model.put("page", pageInt);
			model.put("libelle", c.getLibelle());
			model.put("compteEstVide", compteService.isEmpty(c));
			model.put("listeOperations", operationService.getOperationsByMoisAnnee(c, anneeInt, moisInt));

			// -- Actualisation des listes déroulantes
			Set<Integer> anneesDispo = new TreeSet<Integer>();
			Set<Integer> moisDispo = new TreeSet<Integer>();

			DateTime auj = new DateTime();
			DateTime dateCreation = c.getDateCreation();
			Months d = Months.monthsBetween(dateCreation, auj);
			int maxMois = Math.min(36, d.getMonths());

			if (anneeInt == dateCreation.getYear()) moisDispo.add(dateCreation.getMonthOfYear());

			for (int i = maxMois; i >= 0; i--) {
				DateTime dateI = auj.minusMonths(i);
				if (dateI.getYear() == anneeInt) moisDispo.add(dateI.getMonthOfYear());
				if (!anneesDispo.contains(dateI.getYear())) anneesDispo.add(dateI.getYear());
			}

			model.put("anneesDispo", anneesDispo);
			model.put("moisDispo", moisDispo);
			// ---------------------------------------

			float sommeCB = 0;
			for (OperationCarteBancaire o : operationService.getOperationsCBByMoisAnnee(c, anneeInt, moisInt))
				sommeCB += o.getMontant();
			model.put("sommeCB", sommeCB);

			model.put("nom", u.getNom());
			model.put("prenom", u.getPrenom());
			return "detailsCompte";

		}

		model.put("nom", u.getNom());
		model.put("prenom", u.getPrenom());
		model.put("title", bundle.getString("welcome.title"));
		model.put("error_text", bundle.getString("error-404.text"));
		model.put("error_code", "404");
		return "error";
	}

	@RequestMapping(value = "/{numeroCompte}/choix.html", method = RequestMethod.POST)
	public String redirectChoixDate(HttpServletRequest request, @PathVariable("numeroCompte") String numeroCompte, ModelMap model, Locale locale) {
		String annee = request.getParameter("annee");
		String mois = request.getParameter("mois");
		return "redirect:/user/comptes/" + numeroCompte + "/" + annee + "/" + mois + "/1/details.html";
	}

}
