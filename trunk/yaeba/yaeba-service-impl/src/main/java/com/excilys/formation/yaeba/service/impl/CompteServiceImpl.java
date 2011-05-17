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
import com.excilys.formation.yaeba.service.api.exception.IdCompteNotFoundException;
import com.excilys.formation.yaeba.service.api.exception.NoCardException;

/**
 * @author excilys
 * 
 */
@Service
@Transactional(readOnly = true)
public class CompteServiceImpl implements CompteService {

	/**
	 * 
	 */
	@Autowired
	private OperationService operationService;

	/**
	 * 
	 */
	@Autowired
	private CompteDao compteDao;

	/* (non-Javadoc)
	 * @see com.excilys.formation.yaeba.service.api.CompteService#getCompteById(int)
	 */
	@Override
	public Compte getCompteById(int id) throws IdCompteNotFoundException {
		Compte result = compteDao.getCompteById(id);
		if (result == null) throw new IdCompteNotFoundException(id);
		return result;
	}

	/* (non-Javadoc)
	 * @see com.excilys.formation.yaeba.service.api.CompteService#getCompteByNumeroCompte(com.excilys.formation.yaeba.model.Utilisateur, java.lang.String)
	 */
	@Override
	public Compte getCompteByNumeroCompte(Utilisateur u, String numeroCompte) {
		return compteDao.getCompteByNumeroCompte(u, numeroCompte);
	}

	/* (non-Javadoc)
	 * @see com.excilys.formation.yaeba.service.api.CompteService#getComptesByUtilisateur(com.excilys.formation.yaeba.model.Utilisateur)
	 */
	@Override
	public List<Compte> getComptesByUtilisateur(Utilisateur u) {
		return compteDao.getComptes(u);
	}

	/* (non-Javadoc)
	 * @see com.excilys.formation.yaeba.service.api.CompteService#isEmpty(com.excilys.formation.yaeba.model.Compte)
	 */
	@Override
	public boolean isEmpty(Compte c) {
		return compteDao.isEmpty(c);
	}

	/* (non-Javadoc)
	 * @see com.excilys.formation.yaeba.service.api.CompteService#getEncoursCarte(com.excilys.formation.yaeba.model.Compte)
	 */
	@Override
	public double getEncoursCarte(Compte c) throws NoCardException {
		if (!c.isAssociatedWithCards()) throw new NoCardException(c);
		DateTime now = new DateTime();
		List<OperationCarteBancaire> operationsCB = operationService.getOperationsCBByMoisAnnee(c, now.getYear(), now.getMonthOfYear());
		double result = 0;
		for (OperationCarteBancaire o : operationsCB) {
			if (o.getDateEffective().isAfter(now)) result += o.getMontant();
		}
		return result;
	}

	/* (non-Javadoc)
	 * @see com.excilys.formation.yaeba.service.api.CompteService#isApprovisionne(com.excilys.formation.yaeba.model.Compte, double)
	 */
	@Override
	public boolean isApprovisionne(Compte c, double montant) {
		return c.getSoldeCourant() >= montant;
	}
}
