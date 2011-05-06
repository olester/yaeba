package com.excilys.formation.yaeba.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "compte")
public class Compte {

	private Integer id;
	private String numeroCompte;
	private String libelle;
	private Set<Operation> operations;
	private Date dateCreation;
	private float soldeCourant;

	public Compte() {
		operations = new HashSet<Operation>();
	}

	public Compte(String numeroCompte, String libelle, Set<Operation> operations, Date dateCreation, float soldeCourant) {
		this.numeroCompte = numeroCompte;
		this.operations = operations;
		this.dateCreation = dateCreation;
		this.libelle = libelle;
		this.soldeCourant = soldeCourant;
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

	@Column(name = "numerocompte", unique = true, nullable = false)
	public String getNumeroCompte() {
		return numeroCompte;
	}

	public void setNumeroCompte(String numeroCompte) {
		this.numeroCompte = numeroCompte;
	}

	@Column(name = "libelle", unique = false, nullable = false)
	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	@OneToMany
	@JoinColumn(name = "compte_id")
	public Set<Operation> getOperations() {
		return operations;
	}

	public void setOperations(Set<Operation> operations) {
		this.operations = operations;
	}

	@Column(name = "datecreation", nullable = false)
	public Date getDatecreation() {
		return dateCreation;
	}

	public void setDatecreation(Date datecreation) {
		this.dateCreation = datecreation;
	}

	@Column(name = "soldecourant", nullable = false)
	public float getSoldeCourant() {
		return soldeCourant;
	}

	public void setSoldeCourant(float soldeCourant) {
		this.soldeCourant = soldeCourant;
	}

}
