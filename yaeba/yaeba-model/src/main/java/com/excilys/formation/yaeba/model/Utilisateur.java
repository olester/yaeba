package com.excilys.formation.yaeba.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "utilisateur")
public class Utilisateur implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6085668591967161033L;
	/**
	 * 
	 */
	private Integer id;
	private String login;
	private String nom;
	private String prenom;
	private String adresse;
	private String motDePasse;
	private Set<Role> roles;
	private Set<Compte> comptes;

	public Utilisateur() {
		roles = new HashSet<Role>();
		comptes = new HashSet<Compte>();
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

	@Column(name = "login", unique = true, nullable = false)
	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	@Column(name = "nom", nullable = false)
	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	@Column(name = "prenom", nullable = false)
	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	@Column(name = "adresse")
	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	@Column(name = "password", nullable = false)
	public String getMotDePasse() {
		return motDePasse;
	}

	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}

	@ManyToMany
	@JoinTable(name = "roleutilisateur", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	@OneToMany(mappedBy = "utilisateur")
	// @OrderBy("datecreation")
	public Set<Compte> getComptes() {
		return comptes;
	}

	public void setComptes(Set<Compte> comptes) {
		this.comptes = comptes;
	}

	public void addCompte(Compte compte) {
		this.comptes.add(compte);
	}

	public void removeCompte(Compte compte) {
		this.comptes.remove(compte);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		Utilisateur other = (Utilisateur) obj;
		if (adresse == null) {
			if (other.adresse != null) return false;
		} else if (!adresse.equals(other.adresse)) return false;
		if (id == null) {
			if (other.id != null) return false;
		} else if (!id.equals(other.id)) return false;
		if (login == null) {
			if (other.login != null) return false;
		} else if (!login.equals(other.login)) return false;
		if (motDePasse == null) {
			if (other.motDePasse != null) return false;
		} else if (!motDePasse.equals(other.motDePasse)) return false;
		if (nom == null) {
			if (other.nom != null) return false;
		} else if (!nom.equals(other.nom)) return false;
		if (prenom == null) {
			if (other.prenom != null) return false;
		} else if (!prenom.equals(other.prenom)) return false;
		if (roles == null) {
			if (other.roles != null) return false;
		} else if (!roles.equals(other.roles)) return false;
		return true;
	}

}
