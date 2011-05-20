package com.excilys.formation.yaeba.webapp.controllers;

import java.util.ArrayList;
import java.util.HashMap;
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
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.excilys.formation.yaeba.model.Compte;
import com.excilys.formation.yaeba.model.Operation;
import com.excilys.formation.yaeba.model.OperationCarteBancaire;
import com.excilys.formation.yaeba.model.Utilisateur;
import com.excilys.formation.yaeba.service.api.CompteService;
import com.excilys.formation.yaeba.service.api.OperationService;
import com.excilys.formation.yaeba.service.api.exception.NoCardException;
import com.excilys.formation.yaeba.webapp.DateBean;
import com.excilys.formation.yaeba.webapp.StaticParam;
import com.excilys.formation.yaeba.webapp.UtilisateurUtils;

@Controller
@RequestMapping("user/comptes")
public class ComptesController {

	Logger logger = LoggerFactory.getLogger(ComptesController.class);

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
		Utilisateur u = UtilisateurUtils.getUtilisateur();
		model.put(StaticParam.UTILISATEUR_NAME, u);

		// on recupere la liste des comptes.
		// et on calcule l'encours carte pour les comptes associes a une carte.
		List<Compte> comptes = compteService.getComptesByUtilisateur(u);
		for (Compte c : comptes) {
			try {
				if (c.isAssociatedWithCards()) c.setEncoursCarte(compteService.getEncoursCarte(c));
			} catch (NoCardException e) {
				logger.error(e.getMessage());
			}
		}
		model.put(StaticParam.COMPTE_NAME, comptes);

		return StaticParam.COMPTE_NAME;
	}

	@RequestMapping("/{numeroCompte}/{annee}/{mois}/{page}/details.html")
	public String redirectDetailsCompte(@PathVariable("numeroCompte") String numeroCompte, @PathVariable("annee") int annee, @PathVariable("mois") int mois,
			@PathVariable("page") int page, ModelMap model, Locale locale) {

		// on teste si les arguments de l'url sont corrects.
		if (page <= 0) return "redirect:/error-400.html";

		// on appelle la methode de calcul qui remplit le model
		HashMap<String, Object> map = remplirModel(numeroCompte, annee, mois, model, locale);
		model = (ModelMap) map.get("model");
		if (!((String) map.get("retour")).equals("")) return (String) map.get("retour");
		Compte c = (Compte) map.get("compte");

		// on ajoute diverses informations (locale, page courante)
		model.put("locale", locale.getLanguage());
		model.put("page", page);

		// on verifie si le compte est vide, et on appelle les services si celui-ci ne l'est pas.
		// on ajoute ensuite la liste des operations sauf CB et le nombre de pages
		boolean estVide = compteService.isEmpty(c);
		model.put("compteEstVide", estVide);
		if (!estVide) {
			model.put(StaticParam.OPERATIONS_LIST, operationService.getOperationsNoCBByMoisAnnee(c, annee, mois, page, StaticParam.NB_RESULTS));
			long nbRes = operationService.getNbOperationsNoCBByMoisAnnee(c, annee, mois);
			model.put(StaticParam.NB_PAGES, Math.ceil(nbRes / (float) StaticParam.NB_RESULTS));
		} else {
			model.put(StaticParam.OPERATIONS_LIST, new ArrayList<Operation>());
			model.put(StaticParam.NB_PAGES, 0);
		}

		return "detailsCompte";
	}

	@RequestMapping("/{numeroCompte}/{annee}/{mois}/excel.html")
	public String redirectExcel(@PathVariable("numeroCompte") String numeroCompte, @PathVariable("annee") int annee, @PathVariable("mois") int mois,
			ModelMap model, Locale locale) {

		// on appelle la methode de calcul qui remplit le model
		HashMap<String, Object> map = remplirModel(numeroCompte, annee, mois, model, locale);
		model = (ModelMap) map.get("model");
		if (!((String) map.get("retour")).equals("")) return (String) map.get("retour");
		Compte c = (Compte) map.get("compte");

		// on redirige vers l'excelBean si l'utilisateur a demandé a avoir une feuille excel
		model.put(StaticParam.OPERATIONS_LIST, operationService.getOperationsByMoisAnnee(c, annee, mois));
		return "ExcelBean";
	}

	private HashMap<String, Object> remplirModel(String numeroCompte, int annee, int mois, ModelMap model, Locale locale) {

		// on cree la hashmap qui servira a retourner le model modifie
		HashMap<String, Object> map = new HashMap<String, Object>();
		String retour = "";

		// on teste si les arguments de l'url sont corrects.
		if (annee < 0 || annee > new DateTime().getYear() || mois < 0 || mois > 12) retour = "redirect:/error-400.html";

		// on recupere l'utilisateur courant
		Utilisateur u = UtilisateurUtils.getUtilisateur();
		model.put(StaticParam.UTILISATEUR_NAME, u);

		// on recupere le compte dont on cherche a afficher les details.
		model.put("numero", numeroCompte);
		Compte c = compteService.getCompteByNumeroCompte(u, numeroCompte);
		if (c == null) {
			model.clear();
			retour = "redirect:/error-404.html";
		}
		model.put("libelle", c.getLibelle());

		// on ajoute la date courante
		DateBean dateBean = new DateBean();
		dateBean.setAnnee(annee);
		dateBean.setMois(mois);
		model.put("dateBean", dateBean);

		// on calcule les dates aujourd'hui, il y a 36 mois, etc...
		DateTime auj = new DateTime();
		DateTime max = auj.minusMonths(36).isAfter(c.getDateCreation().minusMonths(1)) ? auj.minusMonths(36) : c.getDateCreation().minusMonths(1);
		DateTime request = auj.monthOfYear().setCopy(mois).year().setCopy(annee);
		if (request.isBefore(max)) {
			model.clear();
			retour = "redirect:/error-404.html";
		}

		// on actualise les listes déroulantes
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
		} else
			model.put("sommeCB", 0f);

		map.put("model", model);
		map.put("retour", retour);
		map.put("compte", c);

		return map;
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