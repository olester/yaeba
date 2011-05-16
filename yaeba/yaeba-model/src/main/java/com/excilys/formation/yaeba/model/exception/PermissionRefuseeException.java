package com.excilys.formation.yaeba.model.exception;

import com.excilys.formation.yaeba.model.Compte;

public class PermissionRefuseeException extends BusinessException {

	private Compte credite;

	private Compte debite;

	/**
	 * 
	 */
	private static final long serialVersionUID = 2431706452366547249L;

	public PermissionRefuseeException(Compte credite, Compte debite) {
		super("les comptes " + credite.getLibelle() + " " + credite.getNumeroCompte() + " et " + debite.getLibelle() + " " + debite.getNumeroCompte()
				+ " n'ont pas le même propriétaire.");
		this.credite = credite;
		this.debite = debite;
	}

	public Compte getCredite() {
		return credite;
	}

	public void setCredite(Compte credite) {
		this.credite = credite;
	}

	public Compte getDebite() {
		return debite;
	}

	public void setDebite(Compte debite) {
		this.debite = debite;
	}

}
