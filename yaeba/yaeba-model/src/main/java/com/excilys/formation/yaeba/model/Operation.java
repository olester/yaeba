package com.excilys.formation.yaeba.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

@Entity
@Table(name = "operation")
public class Operation {

	private Integer id;
	private String libelle;
	private float montant;
	private DateTime dateCreation;

	public Operation() {

	}

	public Operation(String libelle, float montant, DateTime dateCreation) {
		this.libelle = libelle;
		this.montant = montant;
		this.dateCreation = dateCreation;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", unique = true, nullable = false, updatable = false)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "libelle", nullable = false)
	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	@Column(name = "montant", nullable = false)
	public float getMontant() {
		return montant;
	}

	public void setMontant(float montant) {
		this.montant = montant;
	}

	@Column(name = "datecreation", nullable = false)
	@Type(type = "jodaDateTime")
	public DateTime getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(DateTime dateCreation) {
		this.dateCreation = dateCreation;
	}
}
