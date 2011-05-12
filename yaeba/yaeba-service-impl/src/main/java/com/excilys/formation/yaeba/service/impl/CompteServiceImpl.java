package com.excilys.formation.yaeba.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.formation.yaeba.dao.api.CompteDao;
import com.excilys.formation.yaeba.model.Compte;
import com.excilys.formation.yaeba.model.Utilisateur;
import com.excilys.formation.yaeba.service.api.CompteService;

@Service
@Transactional(readOnly = true)
public class CompteServiceImpl implements CompteService {

	@Autowired
	private CompteDao compteDao;

	@Override
	public Compte getCompteById(int id) {
		return compteDao.getCompteById(id);
	}

	@Override
	public Compte getCompteByNumeroCompte(Utilisateur u, String numeroCompte) {
		return compteDao.getCompteByNumeroCompte(u, numeroCompte);
	}

	@Override
	public List<Compte> getComptesByUtilisateur(Utilisateur u) {
		return compteDao.getComptes(u);
	}

	@Override
	public boolean isEmpty(Compte c) {
		return compteDao.isEmpty(c);
	}
}
