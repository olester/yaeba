package com.excilys.formation.yaeba.service.api.exception;

import com.excilys.formation.yaeba.model.Compte;

/**
 * Cette exception est levee si on tente d'effectuer un virement avec un montant superieur au solde courant du compte. Elle embarque le compte dont le solde est
 * insuffisant, ainsi que le virement dont le montant est trop important.
 * 
 * @author excilys
 * 
 */
public class SoldeInsuffisantException extends CompteException {

	private static final long serialVersionUID = -2198496134126679260L;

	private Compte compte;

	private double montant;

	public SoldeInsuffisantException(Compte c, double m) {
		super("le compte " + c.getLibelle() + " " + c.getNumeroCompte() + " n'as pas un solde suffisant pour le virement demandé.");
		compte = c;
		montant = m;
	}

	/**
	 * Un accessseur vers le compte
	 * 
	 * @return le compte
	 */
	public Compte getCompte() {
		return compte;
	}

	/**
	 * Un accesseur vers le montant
	 * 
	 * @return le montant du virement
	 */
	public double getMontant() {
		return montant;
	}

}
