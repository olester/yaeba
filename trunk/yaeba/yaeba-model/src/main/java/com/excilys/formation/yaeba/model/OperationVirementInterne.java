package com.excilys.formation.yaeba.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "operationvirementinterne")
public class OperationVirementInterne extends Operation implements Serializable {

	private static final long serialVersionUID = -3815970073513291893L;

	private Compte compteDistant;

	@ManyToOne(targetEntity = Compte.class)
	@JoinColumn(name = "comptedistant")
	public Compte getCompteDistant() {
		return compteDistant;
	}

	public void setCompteDistant(Compte compteDistant) {
		this.compteDistant = compteDistant;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((compteDistant == null) ? 0 : compteDistant.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (!super.equals(obj)) return false;
		if (getClass() != obj.getClass()) return false;
		OperationVirementInterne other = (OperationVirementInterne) obj;
		if (compteDistant == null) {
			if (other.compteDistant != null) return false;
		} else if (!compteDistant.equals(other.compteDistant)) return false;
		return true;
	}

}
