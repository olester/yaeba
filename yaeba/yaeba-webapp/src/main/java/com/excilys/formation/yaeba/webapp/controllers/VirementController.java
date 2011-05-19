package com.excilys.formation.yaeba.webapp.controllers;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.excilys.formation.yaeba.model.Compte;
import com.excilys.formation.yaeba.model.Utilisateur;
import com.excilys.formation.yaeba.service.api.CompteService;
import com.excilys.formation.yaeba.service.api.OperationService;
import com.excilys.formation.yaeba.service.api.exception.IdCompteNotFoundException;
import com.excilys.formation.yaeba.service.api.exception.MontantNegatifException;
import com.excilys.formation.yaeba.service.api.exception.PermissionRefuseeException;
import com.excilys.formation.yaeba.service.api.exception.SoldeInsuffisantException;
import com.excilys.formation.yaeba.webapp.CustomUser;
import com.excilys.formation.yaeba.webapp.DateBean;
import com.excilys.formation.yaeba.webapp.StaticParam;
import com.excilys.formation.yaeba.webapp.VirementCommand;
import com.excilys.utils.web.flash.FlashScope;

@Controller
@RequestMapping("/user/virements")
public class VirementController {

	@Autowired
	private CompteService compteService;

	@Autowired
	private OperationService operationService;

	@RequestMapping(value = "/virements.html", method = RequestMethod.GET)
	public String redirectVirements(ModelMap model, HttpServletRequest request) {
		Utilisateur u = ((CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUtilisateur();
		List<Compte> comptes = compteService.getComptesByUtilisateur(u);
		model.put(StaticParam.COMPTE_NAME, comptes);
		model.put(StaticParam.UTILISATEUR_NAME, u);
		model.put(StaticParam.VIREMENT_COMMAND_NAME, new VirementCommand());

		return StaticParam.VIREMENT_COMMAND;
	}

	@RequestMapping(value = "/virements.html", method = RequestMethod.POST)
	public String redirectValidateur(ModelMap model, @ModelAttribute @Valid final VirementCommand virementCommand, final BindingResult result,
			HttpServletRequest request) {

		Utilisateur u = ((CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUtilisateur();
		List<Compte> comptes = compteService.getComptesByUtilisateur(u);
		model.put(StaticParam.COMPTE_NAME, comptes);
		model.put(StaticParam.UTILISATEUR_NAME, u);

		if (result.hasErrors()) {
			return StaticParam.VIREMENT_COMMAND;
		}

		Compte c = null;

		try {
			c = compteService.getCompteById(virementCommand.getCompteEmetteur());
		} catch (IdCompteNotFoundException e) {
			model.put("message", "transfers.error.notFoundAccount");
			return StaticParam.VIREMENT_COMMAND;
		}

		boolean isError = false;

		if (virementCommand.getMontant() <= 0) {
			model.put("messageAmount", "transfers.error.amount");
			isError = true;
		}
		if (virementCommand.getCompteRecepteur().equals(virementCommand.getCompteEmetteur())) {
			model.put("messageAccounts", "transfers.error.equals");
			isError = true;
		}
		if (!compteService.isApprovisionne(c, virementCommand.getMontant())) {
			model.put("messageInsufficient", "transfers.error.insufficient");
			isError = true;
		}
		if (isError) return StaticParam.VIREMENT_COMMAND;

		for (Compte compte : comptes) {
			if (compte.getId().equals(virementCommand.getCompteEmetteur())) virementCommand.setCompteEmetteurLibelle(compte.getLibelle());
			else if (compte.getId().equals(virementCommand.getCompteRecepteur())) virementCommand.setCompteRecepteurLibelle(compte.getLibelle());
		}

		FlashScope.bind(StaticParam.VIREMENT_COMMAND_NAME).to(virementCommand);

		return "redirect:confirmation.html";
	}

	@RequestMapping(value = "/confirmation.html")
	public String confirmation(ModelMap model, HttpServletRequest request) {
		String comingFrom = request.getHeader("referer");
		VirementCommand virementCommand = (VirementCommand) request.getAttribute(StaticParam.VIREMENT_COMMAND_NAME);
		if (comingFrom != null && virementCommand != null) {
			FlashScope.bind(StaticParam.VIREMENT_COMMAND_NAME).to(virementCommand);
			return "confirmation";
		}
		return "redirect:/error-404.html";
	}

	@RequestMapping("/save.html")
	public String save(ModelMap model, HttpServletRequest request) {

		VirementCommand virementCommand = (VirementCommand) request.getAttribute(StaticParam.VIREMENT_COMMAND_NAME);

		// On verifie que le bean est encore en session
		if (virementCommand == null) {
			return "redirect:/error-404.html";
		}
		Compte c = null;
		// Sauvegarde des operations
		try {
			operationService.createVirement(virementCommand.getCompteEmetteur(), virementCommand.getCompteRecepteur(), virementCommand.getMontant());
			c = compteService.getCompteById(virementCommand.getCompteEmetteur());

		} catch (SoldeInsuffisantException e) {
			model.put("messageTransferError", "transfers.error.amount");
			return StaticParam.VIREMENT_COMMAND;
		} catch (PermissionRefuseeException e) {
			model.put("messageTransferError", "transfers.error.deniedAccount");
			return StaticParam.VIREMENT_COMMAND;
		} catch (MontantNegatifException e) {
			model.put("message", "transfers.error.amount");
			return StaticParam.VIREMENT_COMMAND;
		} catch (IdCompteNotFoundException e) {
			model.put("message", "transfers.error.notFoundAccount");
			return StaticParam.VIREMENT_COMMAND;
		}

		FlashScope.bind("messageSuccess").to("transfers.success");

		DateTime dateTime = new DateTime();
		StringBuilder sb = new StringBuilder();
		sb.append("redirect:/user/comptes/").append(c.getNumeroCompte());
		sb.append("/").append(dateTime.getYear()).append("/").append(dateTime.getMonthOfYear()).append("/1/details.html");

		return sb.toString();
	}

	@RequestMapping("/historique.html")
	public String redirectHistorique(ModelMap model, Locale locale) {
		Utilisateur u = ((CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUtilisateur();
		model.put(StaticParam.UTILISATEUR_NAME, u);

		model.put("listeVirements", operationService.getVirementsInternes(u));
		model.put("locale", locale.getLanguage());

		DateBean dateBean = new DateBean(new DateTime());
		model.put("dateBean", dateBean);

		return "historique";
	}

}
