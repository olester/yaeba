package com.excilys.formation.yaeba.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.joda.time.DateTime;
import org.joda.time.Months;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import com.excilys.formation.yaeba.service.api.exception.NumeroCompteNotFoundException;

/**
 * @author excilys
 * 
 */
@Service
@Transactional(readOnly = true)
public class CompteServiceImpl implements CompteService {

	private final static Logger logger = LoggerFactory.getLogger(CompteServiceImpl.class);

	@Autowired
	private OperationService operationService;

	@Autowired
	private CompteDao compteDao;

	/* (non-Javadoc)
	 * @see com.excilys.formation.yaeba.service.api.CompteService#getCompteById(int)
	 */
	@Override
	public Compte getCompteById(int id) throws IdCompteNotFoundException {
		Compte result = compteDao.getCompteById(id);
		if (result == null) {
			StringBuffer sb = new StringBuffer();
			sb.append("On ne trouve pas l'id compte : ").append(id);
			logger.warn(sb.toString());
			throw new IdCompteNotFoundException(id);
		}
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
		if (!c.isAssociatedWithCards()) {
			StringBuffer sb = new StringBuffer();
			sb.append("On recherche l'encours carte du compte sans carte : ").append(c.getId()).append(" (n°").append(c.getNumeroCompte()).append(")");
			logger.warn(sb.toString());
			throw new NoCardException(c);
		}
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

	@Override
	public Compte getCompteByNumeroCompte(String numeroCompte) throws NumeroCompteNotFoundException {
		Compte result = compteDao.getCompteByNumeroCompte(numeroCompte);
		if (result == null) {
			StringBuffer sb = new StringBuffer();
			sb.append("On ne trouve pas le numero de compte : ").append(numeroCompte);
			logger.warn(sb.toString());
			throw new NumeroCompteNotFoundException(numeroCompte);
		}
		return result;
	}

	@Override
	public Map<String, Set<Integer>> calculAnneeMoisDispo(Compte c, int mois, int annee) {

		// Calcul des dates aujourd'hui, il y a 36 mois, etc...
		DateTime auj = new DateTime();
		DateTime max = auj.minusMonths(36).isAfter(c.getDateCreation().minusMonths(1)) ? auj.minusMonths(36) : c.getDateCreation().minusMonths(1);
		DateTime request = auj.monthOfYear().setCopy(mois).year().setCopy(annee);
		if (request.isBefore(max)) {
			return null;
		}

		// -- Actualisation des listes déroulantes
		Set<Integer> anneesDispo = new TreeSet<Integer>();
		Set<Integer> moisDispo = new TreeSet<Integer>();
		int maxMois = Months.monthsBetween(max, auj).getMonths();
		for (int i = maxMois; i >= 0; i--) {
			DateTime dateI = auj.minusMonths(i);
			if (dateI.getYear() == annee) moisDispo.add(dateI.getMonthOfYear());
			if (!anneesDispo.contains(dateI.getYear())) anneesDispo.add(dateI.getYear());
		}
		Map<String, Set<Integer>> map = new HashMap<String, Set<Integer>>();
		map.put("moisDispo", moisDispo);
		map.put("anneesDispo", anneesDispo);

		return map;
	}
}
