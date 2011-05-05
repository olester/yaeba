package com.excilys.formation.yaeba.dao.api;

import com.excilys.formation.yaeba.model.Compte;

public interface CompteDao {

	public Compte getCompteById(String id);

	public Compte getCompteByNumeroCompte(String numeroCompte);

	public void update(Compte c);

	public void save(Compte c);

	public void delete(Compte c);
}
