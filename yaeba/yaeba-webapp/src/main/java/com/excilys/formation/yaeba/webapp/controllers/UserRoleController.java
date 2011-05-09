package com.excilys.formation.yaeba.webapp.controllers;

import java.util.Locale;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import org.joda.time.DateTime;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.excilys.formation.yaeba.model.Compte;
import com.excilys.formation.yaeba.model.Utilisateur;
import com.excilys.formation.yaeba.webapp.CustomUser;

@Controller
@RequestMapping("/user")
public class UserRoleController {

	// @Autowired
	// private CompteService compteService;

	@RequestMapping("/comptes.html")
	public String redirectComptes(ModelMap model, Locale locale) {
		ResourceBundle bundle = ResourceBundle.getBundle("messages_" + locale.getLanguage());
		model.put("title", bundle.getString("accounts.title"));
		model.put("bouton", "bouton_comptes");

		DateTime dt = new DateTime();
		model.put("annee", dt.getYear());
		model.put("mois", dt.getMonthOfYear());

		Utilisateur u = ((CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUtilisateur();
		model.put("utilisateur", u);

		return "comptes";
	}

	@RequestMapping("/comptes/{numeroCompte}/{annee}/{mois}/details.html")
	public String redirectDetailsCompte(@PathVariable("numeroCompte") String numeroCompte, @PathVariable("annee") String annee,
			@PathVariable("mois") String mois, ModelMap model, Locale locale) {
		ResourceBundle bundle = ResourceBundle.getBundle("messages_" + locale.getLanguage());

		int anneeInt = Integer.parseInt(annee);
		int moisInt = Integer.parseInt(mois);

		Utilisateur u = ((CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUtilisateur();

		for (Compte c : u.getComptes()) {
			if (c.getNumeroCompte().equals(numeroCompte)) {
				model.put("title", bundle.getString("details.title"));
				model.put("bouton", "bouton_comptes");
				model.put("numero", numeroCompte);
				model.put("compte", c);
				model.put("annee", anneeInt);
				model.put("mois", moisInt);
				model.put("locale", locale.getLanguage());

				// List<Integer> anneesDispo = new ArrayList<Integer>();
				// List<Integer> moisDispo = new ArrayList<Integer>();
				//
				// Months d = Months.monthsBetween(new DateTime(), c.getDateCreation());
				// int monthsDiff = d.getMonths();
				//
				// int maxMois = Math.min(36, d.getYear() * d.getMonth());
				//
				// for (int i = 0; i < 36; i++) {
				//
				// }
				// int maxMois = Math.min(36, cal.MONTH);

				return "detailsCompte";
			}
		}

		model.put("title", bundle.getString("welcome.title"));
		model.put("error_text", bundle.getString("error-404.text"));
		model.put("error_code", "404");
		return "error";
	}

	@RequestMapping(value = "/comptes/{numeroCompte}/choix.html", method = RequestMethod.POST)
	public String redirectChoixDate(HttpServletRequest request, @PathVariable("numeroCompte") String numeroCompte, ModelMap model, Locale locale) {
		String annee = request.getParameter("annee");
		String mois = request.getParameter("mois");
		return "redirect:/user/comptes/" + numeroCompte + "/" + annee + "/" + mois + "/details.html";
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
