package com.excilys.formation.yaeba.service.impl;

import java.util.List;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.formation.yaeba.dao.api.OperationDao;
import com.excilys.formation.yaeba.dao.api.UtilisateurDao;
import com.excilys.formation.yaeba.model.Compte;
import com.excilys.formation.yaeba.model.Operation;
import com.excilys.formation.yaeba.model.OperationCarteBancaire;
import com.excilys.formation.yaeba.model.OperationVirementInterne;
import com.excilys.formation.yaeba.model.Utilisateur;
import com.excilys.formation.yaeba.service.api.CompteService;
import com.excilys.formation.yaeba.service.api.OperationService;
import com.excilys.formation.yaeba.service.api.exception.PermissionRefuseeException;
import com.excilys.formation.yaeba.service.api.exception.SoldeInsuffisantException;

/**
 * @author excilys
 * 
 */
@Service
@Transactional(readOnly = true)
public class OperationServiceImpl implements OperationService {

	/**
	 * 
	 */
	@Autowired
	private OperationDao operationDao;

	/**
	 * 
	 */
	@Autowired
	private CompteService compteService;

	/**
	 * 
	 */
	@Autowired
	private UtilisateurDao utilisateurDao;

	/* (non-Javadoc)
	 * @see com.excilys.formation.yaeba.service.api.OperationService#getOperationById(int)
	 */
	@Override
	public Operation getOperationById(int id) {
		return operationDao.getOperationById(id);
	}

	/* (non-Javadoc)
	 * @see com.excilys.formation.yaeba.service.api.OperationService#getOperationsByMoisAnnee(com.excilys.formation.yaeba.model.Compte, int, int)
	 */
	@Override
	public List<Operation> getOperationsByMoisAnnee(Compte c, int annee, int mois) {
		DateTime dt = new DateTime(annee, mois, 1, 0, 0, 0, 0);
		return operationDao.getOperationsByDate(c, dt, dt.plusMonths(1));
	}

	/* (non-Javadoc)
	 * @see com.excilys.formation.yaeba.service.api.OperationService#getOperationsNoCBByMoisAnnee(com.excilys.formation.yaeba.model.Compte, int, int, int, int)
	 */
	@Override
	public List<Operation> getOperationsNoCBByMoisAnnee(Compte c, int annee, int mois, int page, int nbResultats) {
		DateTime dt = new DateTime(annee, mois, 1, 0, 0, 0, 0);
		return operationDao.getOperationsNoCBByDate(c, dt, dt.plusMonths(1), page, nbResultats);
	}

	/* (non-Javadoc)
	 * @see com.excilys.formation.yaeba.service.api.OperationService#getNbOperationsNoCBByMoisAnnee(com.excilys.formation.yaeba.model.Compte, int, int)
	 */
	@Override
	public long getNbOperationsNoCBByMoisAnnee(Compte c, int annee, int mois) {
		DateTime dt = new DateTime(annee, mois, 1, 0, 0, 0, 0);
		return operationDao.getNbOperationsNoCBByDate(c, dt, dt.plusMonths(1));
	}

	/* (non-Javadoc)
	 * @see com.excilys.formation.yaeba.service.api.OperationService#getOperationsCBByMoisAnnee(com.excilys.formation.yaeba.model.Compte, int, int)
	 */
	@Override
	public List<OperationCarteBancaire> getOperationsCBByMoisAnnee(Compte c, int annee, int mois) {
		DateTime dt = new DateTime(annee, mois, 1, 0, 0, 0, 0);
		return operationDao.getOperationsCBByDate(c, dt, dt.plusMonths(1));
	}

	/* (non-Javadoc)
	 * @see com.excilys.formation.yaeba.service.api.OperationService#getVirementsInternes(com.excilys.formation.yaeba.model.Utilisateur)
	 */
	@Override
	public List<OperationVirementInterne> getVirementsInternes(Utilisateur u) {
		return operationDao.getVirementsInternes(u);
	}

	/**
	 * @param o
	 */
	// XXX cette fonction est elle utile?
	@Transactional(readOnly = false)
	private void create(Operation o) {
		operationDao.create(o);
	}

	/* (non-Javadoc)
	 * @see com.excilys.formation.yaeba.service.api.OperationService#createVirement(int, int, double)
	 */
	@Override
	@Transactional(readOnly = false)
	public void createVirement(int idCompteEmetteur, int idCompteRecepteur, double montant) throws SoldeInsuffisantException, PermissionRefuseeException {

		// XXX il faut tester si les id correspondent a quelque chose: getCompteById renvoie NULL:
		// ce qui fait quand meme une eventuelle 500 dans ce cas.

		Compte em = compteService.getCompteById(idCompteEmetteur);

		if (!compteService.isApprovisionne(em, montant)) {
			throw new SoldeInsuffisantException(em, montant);
		}

		Compte rcpt = compteService.getCompteById(idCompteRecepteur);

		if (!utilisateurDao.getOwner(em).equals(utilisateurDao.getOwner(rcpt))) {
			throw new PermissionRefuseeException(em, rcpt);
		}

		DateTime dt = new DateTime();

		// Creation des 2 virements
		OperationVirementInterne operation = new OperationVirementInterne();
		OperationVirementInterne operationInverse = new OperationVirementInterne();

		// Operation compte emetteur
		operationInverse.setCompte(em);
		operationInverse.setCompteDistant(rcpt);
		operationInverse.setDateCreation(dt);
		operationInverse.setLibelle("Virement emis Compte n°" + operationInverse.getCompteDistant().getNumeroCompte());
		operationInverse.setMontant(-(montant));

		// Operation compte recu
		operation.setCompte(rcpt);
		operation.setCompteDistant(em);
		operation.setDateCreation(dt);
		operation.setLibelle("Virement recu Compte n°" + operation.getCompteDistant().getNumeroCompte());
		operation.setMontant(montant);

		em.setSoldeCourant(em.getSoldeCourant() - montant);
		rcpt.setSoldeCourant(rcpt.getSoldeCourant() + montant);

		create(operation);
		create(operationInverse);

	}

}
