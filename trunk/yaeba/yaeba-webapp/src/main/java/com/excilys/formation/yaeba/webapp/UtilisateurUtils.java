package com.excilys.formation.yaeba.webapp;

import org.springframework.security.core.context.SecurityContextHolder;

import com.excilys.formation.yaeba.model.Utilisateur;

public final class UtilisateurUtils {

	private static final Utilisateur utilisateur = ((CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUtilisateur();

	public static Utilisateur getUtilisateur() {
		return utilisateur;
	}
}
