package com.excilys.formation.yaeba.service.impl;

import java.util.List;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.formation.yaeba.dao.api.CompteDao;
import com.excilys.formation.yaeba.model.Compte;
import com.excilys.formation.yaeba.model.OperationCarteBancaire;
import com.excilys.formation.yaeba.model.Utilisateur;
import com.excilys.formation.yaeba.service.api.CompteService;
import com.excilys.formation.yaeba.service.api.OperationService;
import com.excilys.formation.yaeba.service.api.exception.NoCardException;

@Service
@Transactional(readOnly = true)
public class CompteServiceImpl implements CompteService {

	@Autowired
	private OperationService operationService;

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

	@Override
	public double getEncoursCarte(Compte c) throws NoCardException {
		if (!c.isCards()) throw new NoCardException(c);
		DateTime now = new DateTime();
		List<OperationCarteBancaire> operationsCB = operationService.getOperationsCBByMoisAnnee(c, now.getYear(), now.getMonthOfYear());
		double result = 0;
		for (OperationCarteBancaire o : operationsCB) {
			if (o.getDateEffective().isAfter(now)) result += o.getMontant();
		}
		return result;
	}

	@Override
	public boolean isApprovisionne(Compte c, double montant) {
		return c.getSoldeCourant() >= montant;
	}
}
