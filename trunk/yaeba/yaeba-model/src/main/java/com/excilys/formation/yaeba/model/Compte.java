package com.excilys.formation.yaeba.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "compte")
public class Compte {

	private Integer id;
	private String numeroCompte;
	private Utilisateur utilisateur;
	private Set<Operation> operation;
	private Date datecreation;

	public Compte() {

	}

	public Compte(String numeroCompte, Utilisateur utilisateur, Set<Operation> operations, Date datecreation) {
		this.numeroCompte = numeroCompte;
		this.utilisateur = utilisateur;
		this.operation = operations;
		this.datecreation = datecreation;
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

	@Column(name = "numeroCompte", unique = true, nullable = false)
	public String getNumeroCompte() {
		return numeroCompte;
	}

	public void setNumeroCompte(String numeroCompte) {
		this.numeroCompte = numeroCompte;
	}

	@OneToOne
	@JoinColumn(name = "utilisateur_id")
	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

	@OneToMany
	public Set<Operation> getOperations() {
		return operation;
	}

	public void setOperations(Set<Operation> operations) {
		this.operation = operations;
	}

	@Column(name = "datecreation", nullable = false)
	public Date getDatecreation() {
		return datecreation;
	}

	public void setDatecreation(Date datecreation) {
		this.datecreation = datecreation;
	}

}
