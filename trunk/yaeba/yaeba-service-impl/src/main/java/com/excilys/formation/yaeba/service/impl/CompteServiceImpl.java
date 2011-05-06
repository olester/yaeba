package com.excilys.formation.yaeba.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.excilys.formation.yaeba.dao.api.CompteDao;
import com.excilys.formation.yaeba.model.Compte;
import com.excilys.formation.yaeba.model.Utilisateur;
import com.excilys.formation.yaeba.service.api.CompteService;

@Service
public class CompteServiceImpl implements CompteService {

	@Autowired
	private CompteDao compteDao;

	@Override
	public Compte getCompteById(String id) {
		return compteDao.getCompteById(id);
	}

	@Override
	public Compte getCompteByNumeroCompte(Utilisateur u, String numeroCompte) {
		return compteDao.getCompteByNumeroCompte(u, numeroCompte);
	}

	@Override
	public void update(Compte c) {
		compteDao.update(c);

	}

	@Override
	public void save(Compte c) {
		compteDao.save(c);

	}

	@Override
	public void delete(Compte c) {
		compteDao.delete(c);

	}

	@Override
	public List<Compte> getCompteByUtilisateur(Utilisateur u) {
		return compteDao.getComptes(u);
	}

}
