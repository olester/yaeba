package com.excilys.formation.yaeba.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

@Entity
@Table(name = "compte")
public class Compte implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3782823641514579337L;
	private Integer id;
	private String numeroCompte;
	private String libelle;
	private Set<Operation> operations = new HashSet<Operation>();
	private DateTime dateCreation;
	private double soldeCourant;
	private Utilisateur utilisateur;
	private boolean cards; // TODO refactoré ce nom en isAssociatedWithCards
	transient private double encoursCarte;

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

	@OneToMany(mappedBy = "compte")
	public Set<Operation> getOperations() {
		return operations;
	}

	public void setOperations(Set<Operation> operations) {
		this.operations = operations;
	}

	@Column(name = "datecreation", nullable = false)
	@Type(type = "jodaDateTime")
	public DateTime getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(DateTime dateCreation) {
		this.dateCreation = dateCreation;
	}

	@Column(name = "soldecourant", nullable = false)
	public double getSoldeCourant() {
		return soldeCourant;
	}

	public void setSoldeCourant(double soldeCourant) {
		this.soldeCourant = soldeCourant;
	}

	@ManyToOne
	@JoinColumn(name = "utilisateur_id")
	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

	@Column(name = "cards", nullable = false)
	@Basic
	public boolean isCards() {
		return cards;
	}

	public void setCards(boolean cards) {
		this.cards = cards;
	}

	@Transient
	public double getEncoursCarte() {
		return encoursCarte;
	}

	public void setEncoursCarte(double encoursCarte) {
		this.encoursCarte = encoursCarte;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (cards ? 1231 : 1237);
		result = prime * result + ((dateCreation == null) ? 0 : dateCreation.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((libelle == null) ? 0 : libelle.hashCode());
		result = prime * result + ((numeroCompte == null) ? 0 : numeroCompte.hashCode());
		result = prime * result + ((operations == null) ? 0 : operations.hashCode());
		long temp;
		temp = Double.doubleToLongBits(soldeCourant);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((utilisateur == null) ? 0 : utilisateur.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		Compte other = (Compte) obj;
		if (cards != other.cards) return false;
		if (dateCreation == null) {
			if (other.dateCreation != null) return false;
		} else if (!dateCreation.equals(other.dateCreation)) return false;
		if (id == null) {
			if (other.id != null) return false;
		} else if (!id.equals(other.id)) return false;
		if (libelle == null) {
			if (other.libelle != null) return false;
		} else if (!libelle.equals(other.libelle)) return false;
		if (numeroCompte == null) {
			if (other.numeroCompte != null) return false;
		} else if (!numeroCompte.equals(other.numeroCompte)) return false;
		if (operations == null) {
			if (other.operations != null) return false;
		} else if (!operations.equals(other.operations)) return false;
		if (Double.doubleToLongBits(soldeCourant) != Double.doubleToLongBits(other.soldeCourant)) return false;
		if (utilisateur == null) {
			if (other.utilisateur != null) return false;
		} else if (!utilisateur.equals(other.utilisateur)) return false;
		return true;
	}

}
