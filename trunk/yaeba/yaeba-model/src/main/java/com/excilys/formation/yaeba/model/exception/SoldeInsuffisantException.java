package com.excilys.formation.yaeba.model.exception;

import com.excilys.formation.yaeba.model.Compte;
import com.excilys.formation.yaeba.model.OperationVirementInterne;

public class SoldeInsuffisantException extends BusinessException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2198496134126679260L;

	private Compte compte;

	private OperationVirementInterne virement;

	public SoldeInsuffisantException(Compte c, OperationVirementInterne v) {
		super("le compte " + c.getLibelle() + " " + c.getNumeroCompte() + " n'as pas un solde suffisant pour le virement demandé.");
		compte = c;
		virement = v;
	}

	public Compte getCompte() {
		return compte;
	}

	public void setCompte(Compte compte) {
		this.compte = compte;
	}

	public OperationVirementInterne getVirement() {
		return virement;
	}

	public void setVirement(OperationVirementInterne virement) {
		this.virement = virement;
	}

}
