package com.excilys.formation.yaeba.webapp.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.TreeSet;

import javax.servlet.http.HttpServletRequest;

import org.joda.time.DateTime;
import org.joda.time.Months;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.excilys.formation.yaeba.model.Compte;
import com.excilys.formation.yaeba.model.Operation;
import com.excilys.formation.yaeba.model.OperationCarteBancaire;
import com.excilys.formation.yaeba.model.Utilisateur;
import com.excilys.formation.yaeba.service.api.CompteService;
import com.excilys.formation.yaeba.service.api.OperationService;
import com.excilys.formation.yaeba.service.api.exception.NoCardException;
import com.excilys.formation.yaeba.webapp.CustomUser;
import com.excilys.formation.yaeba.webapp.DateBean;

@Controller
@RequestMapping("user/comptes")
public class ComptesController {

	public static final int NB_RESULTS = 7;

	Logger l = LoggerFactory.getLogger(ComptesController.class);

	@Autowired
	private OperationService operationService;
	@Autowired
	private CompteService compteService;

	@RequestMapping("/comptes.html")
	public String redirectComptes(ModelMap model) {

		// on recupere la date du jour
		DateBean dateBean = new DateBean(new DateTime());
		model.put("dateBean", dateBean);

		// on recupere l'utilisateur courant
		Utilisateur u = ((CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUtilisateur();
		model.put("utilisateur", u);

		// on recupere la liste des comptes.
		// et on calcule l'encours carte pour les comptes associes a une carte.
		List<Compte> comptes = compteService.getComptesByUtilisateur(u);
		for (Compte c : comptes) {
			try {
				if (c.isAssociatedWithCards()) c.setEncoursCarte(compteService.getEncoursCarte(c));
			} catch (NoCardException e) {
				l.error(e.getMessage());
			}
		}
		model.put("comptes", comptes);

		return "comptes";
	}

	@RequestMapping("/{numeroCompte}/{annee}/{mois}/{page}/details.html")
	public String redirectDetailsCompte(@PathVariable("numeroCompte") String numeroCompte, @PathVariable("annee") int annee, @PathVariable("mois") int mois,
			@PathVariable("page") int page, @RequestParam(value = "excel", defaultValue = "false", required = false) String excel, ModelMap model, Locale locale) {

		// TODO A refactorer

		// on teste si les arguments de l'url sont corrects.

		if (annee < 0 || annee > new DateTime().getYear() || mois < 0 || mois > 12 || page <= 0) return "redirect:/error-400.html";

		// on recupere l'utilisateur courant
		Utilisateur u = ((CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUtilisateur();
		model.put("utilisateur", u);

		// on recupere le compte dont on cherche a afficher les details.
		model.put("numero", numeroCompte);
		Compte c = compteService.getCompteByNumeroCompte(u, numeroCompte);
		if (c == null) {
			model.clear();
			return "redirect:/error-404.html";
		}
		model.put("libelle", c.getLibelle());

		// on ajoute la date courante
		DateBean dateBean = new DateBean();
		dateBean.setAnnee(annee);
		dateBean.setMois(mois);
		model.put("dateBean", dateBean);

		// Calcul des dates aujourd'hui, il y a 36 mois, etc...
		DateTime auj = new DateTime();
		DateTime max = auj.minusMonths(36).isAfter(c.getDateCreation()) ? auj.minusMonths(36) : c.getDateCreation();
		DateTime request = auj.monthOfYear().setCopy(mois).year().setCopy(annee);
		if (request.isBefore(max)) {
			model.clear();
			return "redirect:/error-404.html";
		}

		// -- Actualisation des listes déroulantes
		Set<Integer> anneesDispo = new TreeSet<Integer>();
		Set<Integer> moisDispo = new TreeSet<Integer>();
		int maxMois = Months.monthsBetween(max, auj).getMonths();
		for (int i = maxMois; i >= 0; i--) {
			DateTime dateI = auj.minusMonths(i);
			if (dateI.getYear() == annee) moisDispo.add(dateI.getMonthOfYear());
			if (!anneesDispo.contains(dateI.getYear())) anneesDispo.add(dateI.getYear());
		}
		model.put("anneesDispo", anneesDispo);
		model.put("moisDispo", moisDispo);

		// on charge les informations relatives aux cartes bancaires (nombre d'operations, liste des operations, somme des operations)
		List<OperationCarteBancaire> listeOperationsCB = operationService.getOperationsCBByMoisAnnee(c, annee, mois);
		model.put("nbCB", listeOperationsCB.size());
		if (!listeOperationsCB.isEmpty()) {
			float sommeCB = 0;
			for (OperationCarteBancaire o : listeOperationsCB)
				sommeCB += o.getMontant();
			model.put("sommeCB", sommeCB);
			model.put("listeOperationsCB", listeOperationsCB);
		}

		// on redirige vers l'excelBean si l'utilisateur a demandé a avoir une feuille excel
		if (excel.equals("true")) {
			model.put("listeOperations", operationService.getOperationsByMoisAnnee(c, annee, mois));
			return "ExcelBean";
		}

		// on ajoute diverses informations (locale, page courante,
		model.put("locale", locale.getLanguage());
		model.put("page", page);

		// on verifie si le compte est vide, et on appelle les services si celui-ci ne l'est pas.
		// on ajoute ensuite la liste des operations sauf CB et le nombre de pages
		boolean estVide = compteService.isEmpty(c);
		model.put("compteEstVide", estVide);
		if (!estVide) {
			model.put("listeOperations", operationService.getOperationsNoCBByMoisAnnee(c, annee, mois, page, NB_RESULTS));
			long nbRes = operationService.getNbOperationsNoCBByMoisAnnee(c, annee, mois);
			model.put("nbPages", Math.ceil(nbRes / (float) NB_RESULTS));
		} else {
			model.put("listeOperations", new ArrayList<Operation>());
			model.put("nbPages", 0);
		}

		return "detailsCompte";
	}

	/**
	 * Fonction servant a mapper le choix du mois et de l\'annee lors de la selection par l\'utilisateur sur le detail d\'un compte.
	 * 
	 * @param request
	 *            parametres passes en POST par le formulaire
	 * @param numeroCompte
	 *            Numero du compte courant dans l\'URL
	 * @param model
	 *            ModelMap
	 * @return redirection vers le detail du compte au mois et a l\'annee selectionne.
	 */
	@RequestMapping(value = "/{numeroCompte}/choix.html", method = RequestMethod.POST)
	public String redirectChoixDate(HttpServletRequest request, @PathVariable("numeroCompte") String numeroCompte, ModelMap model) {
		String annee = request.getParameter("annee");
		String anneeEx = request.getParameter("anneeEx");
		String mois = request.getParameter("mois");

		if (!annee.equals(anneeEx)) {
			try {
				int anneeInt = Integer.parseInt(annee);
				if (anneeInt == new DateTime().getYear()) mois = "1";
				else
					mois = "12";
			} catch (NumberFormatException e) {
				return "redirect:/error-404.html";
			}
		}

		StringBuilder sb = new StringBuilder();
		sb.append("redirect:/user/comptes/").append(numeroCompte);
		sb.append("/").append(annee).append("/").append(mois).append("/1/details.html");

		return sb.toString();
	}

}
