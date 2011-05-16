package com.excilys.formation.yaeba.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "operationcheque")
public class OperationCheque extends Operation implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -330978500644908892L;
	private int numeroCheque;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + numeroCheque;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (!super.equals(obj)) return false;
		if (getClass() != obj.getClass()) return false;
		OperationCheque other = (OperationCheque) obj;
		if (numeroCheque != other.numeroCheque) return false;
		return true;
	}

	@Column(name = "numerocheque", nullable = false)
	public int getNumeroCheque() {
		return numeroCheque;
	}

	public void setNumeroCheque(int numeroCheque) {
		this.numeroCheque = numeroCheque;
	}

}
