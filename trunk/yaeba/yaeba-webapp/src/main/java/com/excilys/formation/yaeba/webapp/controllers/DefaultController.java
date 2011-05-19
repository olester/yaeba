package com.excilys.formation.yaeba.webapp.controllers;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.excilys.formation.yaeba.model.Utilisateur;
import com.excilys.formation.yaeba.webapp.CustomUser;
import com.excilys.formation.yaeba.webapp.StaticParam;

@Controller
public class DefaultController {

	@RequestMapping("/welcome.html")
	public String redirectWelcome(ModelMap model) {
		Utilisateur u = ((CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUtilisateur();
		model.put(StaticParam.UTILISATEUR_NAME, u);

		return "welcome";
	}

	@RequestMapping("/login.html")
	public String redirectLogin(ModelMap model) {
		return "login";
	}

	/**
	 * Cette methode gere les cas d'erreur. Seules les erreurs 500, 403, 400 et 404 sont gerees. Dans le cas d'une autre erreur, une 404 sera generee.
	 * 
	 * @param code
	 *            le code d'erreur recupere dans l'url (route par spring)
	 * @param model
	 *            la hashmap passee par spring
	 * @return la redirection spring vers tiles
	 */
	@RequestMapping("/error-{code}.html")
	public String redirectError(@PathVariable("code") String code, ModelMap model) {
		int codeInt;
		try {
			codeInt = Integer.parseInt(code);
		} catch (NumberFormatException e) {
			return "error-404";
		}
		if (codeInt == 403 || codeInt == 404 || codeInt == 500 || codeInt == 400) return "error-" + codeInt;
		return "error-404";
	}

}
