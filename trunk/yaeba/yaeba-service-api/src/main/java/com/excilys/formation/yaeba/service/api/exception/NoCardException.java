package com.excilys.formation.yaeba.service.api.exception;

import com.excilys.formation.yaeba.model.Compte;

/**
 * Cette exception est levee si on essaie d'acceder aux operations cartes d'un compte qui n'est pas associe a une carte.
 * 
 * @author excilys
 * 
 */
public class NoCardException extends CompteException {

	private static final long serialVersionUID = -9011569264229535888L;

	public NoCardException(Compte c) {
		super("le compte " + c.getLibelle() + " " + c.getNumeroCompte() + " n'est associé a aucune carte.");
	}

}
