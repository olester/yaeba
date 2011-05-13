package com.excilys.formation.yaeba.model.exception;

import com.excilys.formation.yaeba.model.Compte;

public class NoCardException extends BusinessException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9011569264229535888L;

	public NoCardException(Compte c) {
		super("le compte " + c.getLibelle() + " " + c.getNumeroCompte() + " n'est associé a aucune carte.");
	}

}
