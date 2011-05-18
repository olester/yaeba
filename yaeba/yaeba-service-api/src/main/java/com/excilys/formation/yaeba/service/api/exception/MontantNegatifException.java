package com.excilys.formation.yaeba.service.api.exception;

public class MontantNegatifException extends VirementException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5741081113214346317L;

	public MontantNegatifException(double montant) {
		super("le montant entré est invalide (" + montant + ")");
	}

}
