package com.excilys.formation.yaeba.service.api.exception;

/**
 * Cette exception est une exception generique concernant les comptes, elle n'est pas destinee a etre jetee.
 * 
 * @author excilys
 * 
 */
public abstract class CompteException extends Exception {

	private static final long serialVersionUID = 8710596249446850851L;

	public CompteException(String message) {
		super(message);
	}

	public CompteException(Throwable cause) {
		super(cause);
	}
}
