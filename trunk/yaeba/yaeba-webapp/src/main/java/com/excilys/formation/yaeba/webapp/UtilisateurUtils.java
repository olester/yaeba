package com.excilys.formation.yaeba.webapp;

import org.springframework.security.core.context.SecurityContextHolder;

import com.excilys.formation.yaeba.model.Utilisateur;

public class UtilisateurUtils {

	public static Utilisateur getUtilisateur() {
		return ((CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUtilisateur();
	}
}
