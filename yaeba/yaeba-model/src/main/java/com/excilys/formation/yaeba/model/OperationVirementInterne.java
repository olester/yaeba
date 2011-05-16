package com.excilys.formation.yaeba.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "operationvirementinterne")
public class OperationVirementInterne extends Operation implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3815970073513291893L;
	private Compte compteDistant;

	public OperationVirementInterne() {

	}

	@ManyToOne(targetEntity = Compte.class)
	@JoinColumn(name = "comptedistant")
	public Compte getCompteDistant() {
		return compteDistant;
	}

	public void setCompteDistant(Compte compteDistant) {
		this.compteDistant = compteDistant;
	}

}
