package com.excilys.formation.yaeba.service.api.exception;

public class IdCompteNotFoundException extends CompteException {

	private int id;

	/**
	 * 
	 */
	private static final long serialVersionUID = 7573275401873798967L;

	public IdCompteNotFoundException(int idNotFound) {
		super("l'id " + idNotFound + " n'a pas ete trouve");
		this.id = idNotFound;
	}

	public int getId() {
		return id;
	}

}
