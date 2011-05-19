package com.excilys.formation.yaeba.service.api.exception;

/**
 * Cette exception est levee si on essaie d'obtenir un compte avec un id n'etant pas stocke en base. Cette exception conserve l'id fautif, et celuici est
 * accessible.
 * 
 * @author excilys
 * 
 */
public class NumeroCompteNotFoundException extends CompteException {

	private static final long serialVersionUID = -2947740001357875475L;

	private String numero;

	public NumeroCompteNotFoundException(String numeroNotFound) {
		super("le numero de compte " + numeroNotFound + " n'a pas ete trouve");
		this.numero = numeroNotFound;
	}

	/**
	 * Un accesseur pour recuperer le numero du compte qui n'a pas ete trouve en base.
	 * 
	 * @return l'id
	 */
	public String getNumero() {
		return numero;
	}

}
