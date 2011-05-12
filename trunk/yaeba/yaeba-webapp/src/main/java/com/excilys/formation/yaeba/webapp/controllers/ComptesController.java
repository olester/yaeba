package com.excilys.formation.yaeba.webapp.controllers;

import java.util.List;
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
import com.excilys.formation.yaeba.webapp.DateBean;

@Controller
@RequestMapping("user/comptes")
public class ComptesController {

	@Autowired
	private OperationService operationService;
	@Autowired
	private CompteService compteService;
	@Autowired
	private DateBean dateBean;

	@RequestMapping("/comptes.html")
	public String redirectComptes(ModelMap model) {
		model.put("bouton", "bouton_comptes");

		DateTime dt = new DateTime();
		dateBean.setAnnee(dt.getYear());
		dateBean.setMois(dt.getMonthOfYear());
		model.put("dateBean", dateBean);

		Utilisateur u = ((CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUtilisateur();
		List<Compte> comptes = compteService.getComptesByUtilisateur(u);
		model.put("comptes", comptes);

		model.put("utilisateur", u);
		return "comptes";
	}

	@RequestMapping("/{numeroCompte}/{annee}/{mois}/{page}/details.html")
	public String redirectDetailsCompte(@PathVariable("numeroCompte") String numeroCompte, @PathVariable("annee") String annee,
			@PathVariable("mois") String mois, @PathVariable("page") String page, ModelMap model) {
		int anneeInt;
		int moisInt;
		int pageInt;

		try {
			anneeInt = Integer.parseInt(annee);
			moisInt = Integer.parseInt(mois);
			pageInt = Integer.parseInt(page);
			if (anneeInt < 0 || anneeInt > new DateTime().getYear()) throw new NumberFormatException();
			if (moisInt < 0 || moisInt > 12) throw new NumberFormatException();
			if (pageInt <= 0) throw new NumberFormatException();
		} catch (NumberFormatException e) {
			return "redirect:/error-404.html";
		}

		Utilisateur u = ((CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUtilisateur();
		Compte c = compteService.getCompteByNumeroCompte(u, numeroCompte);

		if (c != null) {
			model.put("bouton", "bouton_comptes");
			model.put("numero", numeroCompte);

			dateBean.setAnnee(anneeInt);
			dateBean.setMois(moisInt);
			model.put("dateBean", dateBean);

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

			if ((anneeInt < Math.max(c.getDateCreation().getYear(), auj.minusMonths(36).getYear()))
					|| (anneeInt == Math.max(c.getDateCreation().getYear(), auj.minusMonths(36).getYear()) && moisInt < Math.max(c.getDateCreation()
							.getMonthOfYear(), auj.minusMonths(36).getMonthOfYear()))) {
				model.clear();
				return "redirect:/error-404.html";
			}
			System.out.println(Math.max(c.getDateCreation().getYear(), auj.minusMonths(36).getYear()));
			System.out.println(Math.max(c.getDateCreation().getMonthOfYear(), auj.minusMonths(36).getMonthOfYear()));

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

			model.put("utilisateur", u);
			return "detailsCompte";
		}

		return "redirect:/error-404.html";
	}

	@RequestMapping(value = "/{numeroCompte}/choix.html", method = RequestMethod.POST)
	public String redirectChoixDate(HttpServletRequest request, @PathVariable("numeroCompte") String numeroCompte, ModelMap model) {
		String annee = request.getParameter("annee");
		String anneeEx = request.getParameter("anneeEx");
		String mois = request.getParameter("mois");

		if (annee != anneeEx) {
			try {
				int anneeInt = Integer.parseInt(annee);
				if (anneeInt == new DateTime().getYear()) mois = "1";
				else
					mois = "12";
			} catch (NumberFormatException e) {
				return "redirect:/error-404.html";
			}
		}

		return "redirect:/user/comptes/" + numeroCompte + "/" + annee + "/" + mois + "/1/details.html";
	}

}
