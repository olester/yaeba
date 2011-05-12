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

	public OperationCheque() {

	}

	@Column(name = "numerocheque", nullable = false)
	public int getNumeroCheque() {
		return numeroCheque;
	}

	public void setNumeroCheque(int numeroCheque) {
		this.numeroCheque = numeroCheque;
	}

}
