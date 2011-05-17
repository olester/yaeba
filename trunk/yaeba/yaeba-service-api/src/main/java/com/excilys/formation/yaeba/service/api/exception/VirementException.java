package com.excilys.formation.yaeba.service.api.exception;

/**
 * Cette exception est une exception generique concernant les virements, elle n'est pas destinee a etre jetee.
 * 
 * @author excilys
 * 
 */
public abstract class VirementException extends Exception {

	private static final long serialVersionUID = -3882927630801182931L;

	public VirementException(String message) {
		super(message);
	}

	public VirementException(Throwable cause) {
		super(cause);
	}
}
