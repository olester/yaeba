package com.excilys.formation.yaeba.service.api.exception;

import com.excilys.formation.yaeba.model.Compte;

/**
 * Cette exception eest levee si on essaie de creer un virement entre deux comptes n'appartenant pas a la meme personne. Elle embarque les comptes fautifs.
 * 
 * @author excilys
 * 
 */
public class PermissionRefuseeException extends VirementException {

	private Compte credite;

	private Compte debite;

	private static final long serialVersionUID = 2431706452366547249L;

	public PermissionRefuseeException(Compte credite, Compte debite) {
		super("les comptes " + credite.getLibelle() + " " + credite.getNumeroCompte() + " et " + debite.getLibelle() + " " + debite.getNumeroCompte()
				+ " n'ont pas le même propriétaire.");
		this.credite = credite;
		this.debite = debite;
	}

	/**
	 * Un accesseur pour le compte credite
	 * 
	 * @return le compte
	 */
	public Compte getCredite() {
		return credite;
	}

	/**
	 * Un accesseur pour le compte debite
	 * 
	 * @return le compte
	 */
	public Compte getDebite() {
		return debite;
	}

}
