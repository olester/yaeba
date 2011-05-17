package com.excilys.formation.yaeba.service.api.exception;

/**
 * Cette exception est levee si on essaie d'obtenir un compte avec un id n'etant pas stocke en base. Cette exception conserve l'id fautif, et celuici est
 * accessible.
 * 
 * @author excilys
 * 
 */
public class IdCompteNotFoundException extends CompteException {

	private int id;

	private static final long serialVersionUID = 7573275401873798967L;

	public IdCompteNotFoundException(int idNotFound) {
		super("l'id " + idNotFound + " n'a pas ete trouve");
		this.id = idNotFound;
	}

	/**
	 * Un accesseur pour recuperer l'id qui n'a pas ete trouve en base.
	 * 
	 * @return l'id
	 */
	public int getId() {
		return id;
	}

}
