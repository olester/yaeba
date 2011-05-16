package com.excilys.formation.yaeba.webapp.controllers;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.excilys.formation.yaeba.model.Compte;
import com.excilys.formation.yaeba.model.Utilisateur;
import com.excilys.formation.yaeba.service.api.CompteService;
import com.excilys.formation.yaeba.service.api.OperationService;
import com.excilys.formation.yaeba.service.api.exception.PermissionRefuseeException;
import com.excilys.formation.yaeba.service.api.exception.SoldeInsuffisantException;
import com.excilys.formation.yaeba.webapp.CustomUser;
import com.excilys.formation.yaeba.webapp.DateBean;
import com.excilys.formation.yaeba.webapp.VirementBean;
import com.excilys.utils.web.flash.FlashScope;

@Controller
@RequestMapping("/user/virements")
public class VirementsController {

	@Autowired
	private CompteService compteService;

	@Autowired
	private OperationService operationService;

	@Autowired
	private DateBean dateBean;

	@ModelAttribute("virements")
	public VirementBean getVirementObject() {
		return new VirementBean();
	}

	@RequestMapping("/virements.html")
	public String redirectVirements(ModelMap model, HttpServletRequest request) {
		Utilisateur u = ((CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUtilisateur();
		List<Compte> comptes = compteService.getComptesByUtilisateur(u);
		model.put("comptes", comptes);
		model.put("utilisateur", u);

		return "virements";
	}

	@RequestMapping("/validateur.html")
	public String redirectValidateur(ModelMap model, VirementBean virementBean, HttpServletRequest request) {
		Utilisateur u = ((CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUtilisateur();
		List<Compte> comptes = compteService.getComptesByUtilisateur(u);
		model.put("comptes", comptes);
		model.put("utilisateur", u);

		if (virementBean.getCompteRecepteur().equals(virementBean.getCompteEmetteur())) {
			model.put("message", "transfers.error.equals");
			return "virements";
		} else if (virementBean.getMontant() <= 0) {
			model.put("message", "transfers.error.amount");
			return "virements";
		} else if (!compteService.isApprovisionne(compteService.getCompteById(virementBean.getCompteEmetteur()), virementBean.getMontant())) {
			model.put("message", "transfers.error.insufficient");
			return "virements";
		}

		FlashScope.bind("virementBean").to(virementBean);

		for (Compte compte : comptes) {
			if (compte.getId().equals(virementBean.getCompteEmetteur())) {
				model.put("em", compte.getLibelle());
			} else if (compte.getId().equals(virementBean.getCompteRecepteur())) {
				model.put("rcpt", compte.getLibelle());
			}
		}
		model.put("montant", virementBean.getMontant());

		return "confirmation";
	}

	@RequestMapping("/confirmation.html")
	public String confirmation(ModelMap model, HttpServletRequest request) {
		// VirementBean bean = (VirementBean) request.getAttribute("virementBean");
		// model.put("em", compteService.getCompteById(bean.getCompteEmetteur()));
		// model.put("rcpt", compteService.getCompteById(bean.getCompteRecepteur()));
		// model.put("montant", bean.getMontant());
		return "confirmation";
	}

	@RequestMapping("/save.html")
	public String save(ModelMap model, HttpServletRequest request) {

		VirementBean virementBean = (VirementBean) request.getAttribute("virementBean");

		// On verifie que le bean est encore en session
		if (virementBean == null) {
			return "redirect:/user/virements/virements.html";
		}

		// Sauvegarde des operations
		try {
			operationService.createVirement(virementBean.getCompteEmetteur(), virementBean.getCompteRecepteur(), virementBean.getMontant());
		} catch (SoldeInsuffisantException e) {
			model.put("message", "transfers.error.amount");
			return "virements";
		} catch (PermissionRefuseeException e) {
			model.put("message", "transfers.error.deniedAccount");
			return "virements";
		}

		DateTime dateTime = new DateTime();

		StringBuilder sb = new StringBuilder();
		sb.append("redirect:/user/comptes/").append(compteService.getCompteById(virementBean.getCompteEmetteur()).getNumeroCompte());
		sb.append("/").append(dateTime.getYear()).append("/").append(dateTime.getMonthOfYear()).append("/1/details.html");

		return sb.toString();
	}

	@RequestMapping("/historique.html")
	public String redirectHistorique(ModelMap model, Locale locale) {
		Utilisateur u = ((CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUtilisateur();
		model.put("utilisateur", u);

		model.put("listeVirements", operationService.getVirementsInternes(u));
		model.put("locale", locale.getLanguage());

		DateTime dt = new DateTime();
		dateBean.setAnnee(dt.getYear());
		dateBean.setMois(dt.getMonthOfYear());
		model.put("dateBean", dateBean);

		return "historique";
	}

}
