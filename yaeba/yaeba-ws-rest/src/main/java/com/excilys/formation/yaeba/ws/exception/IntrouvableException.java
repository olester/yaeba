package com.excilys.formation.yaeba.ws.exception;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Cette exception est levee si on execute une mauvaise requete REST.
 * 
 * @author excilys
 * 
 */
public class IntrouvableException extends WebApplicationException {

	private static final long serialVersionUID = -2838925475139612667L;

	public IntrouvableException(String message) {
		super(Response.status(400).entity(message).type(MediaType.TEXT_PLAIN).build());
	}

}
