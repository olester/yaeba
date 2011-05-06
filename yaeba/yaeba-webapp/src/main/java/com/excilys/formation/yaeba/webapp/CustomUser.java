package com.excilys.formation.yaeba.webapp;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.excilys.formation.yaeba.model.Utilisateur;

public class CustomUser extends User {

	private static final long serialVersionUID = 1L;
	private Utilisateur utilisateur;

	public CustomUser(Utilisateur utilisateur, Collection<GrantedAuthority> authorities) {
		super(utilisateur.getLogin(), utilisateur.getMotDePasse(), true, true, true, true, authorities);
		this.utilisateur = utilisateur;
	}

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

}
