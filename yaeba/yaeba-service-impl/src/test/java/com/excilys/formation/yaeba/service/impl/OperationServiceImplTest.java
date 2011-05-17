package com.excilys.formation.yaeba.service.impl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Set;

import org.joda.time.DateTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.excilys.formation.yaeba.dao.api.OperationDao;
import com.excilys.formation.yaeba.dao.api.UtilisateurDao;
import com.excilys.formation.yaeba.model.Compte;
import com.excilys.formation.yaeba.model.Operation;
import com.excilys.formation.yaeba.model.OperationCheque;
import com.excilys.formation.yaeba.model.OperationVirementInterne;
import com.excilys.formation.yaeba.model.Utilisateur;
import com.excilys.formation.yaeba.service.api.CompteService;
import com.excilys.formation.yaeba.service.api.OperationService;
import com.excilys.formation.yaeba.service.api.exception.IdCompteNotFoundException;
import com.excilys.formation.yaeba.service.api.exception.PermissionRefuseeException;
import com.excilys.formation.yaeba.service.api.exception.SoldeInsuffisantException;

@RunWith(MockitoJUnitRunner.class)
public class OperationServiceImplTest {

	@Mock
	private OperationDao operationDao;
	@Mock
	private UtilisateurDao utilisateurDao;
	@Mock
	private CompteService compteService;

	@InjectMocks
	private OperationService operationService = new OperationServiceImpl();

	// TODO : TESTER si on essaie de faire un virement av ec un identifiant ne correspondant a rien (mock renvoyant null)

	@Test
	public void testVirement() throws IdCompteNotFoundException, SoldeInsuffisantException, PermissionRefuseeException {
		Compte em = new Compte();
		em.setLibelle("compte1");
		em.setNumeroCompte("0123");
		em.setId(98);
		Compte rcpt = new Compte();
		rcpt.setLibelle("compte2");
		rcpt.setNumeroCompte("4567");
		rcpt.setId(97);
		double montant = 10;
		when(compteService.getCompteById(98)).thenReturn(em);
		when(compteService.getCompteById(97)).thenReturn(rcpt);
		when(compteService.isApprovisionne(em, montant)).thenReturn(true);

		Utilisateur u1 = new Utilisateur();
		u1.setNom("nom1");
		when(utilisateurDao.getOwner(em)).thenReturn(u1);
		when(utilisateurDao.getOwner(rcpt)).thenReturn(u1);

		operationService.createVirement(98, 97, montant);

		DateTime dt = new DateTime();

		// Creation des 2 virements
		OperationVirementInterne operation = new OperationVirementInterne();
		OperationVirementInterne operationInverse = new OperationVirementInterne();

		// Operation compte emetteur
		operation.setCompte(em);
		operation.setCompteDistant(rcpt);
		operation.setDateCreation(dt);
		operation.setLibelle("Virement emis Compte n°4567");
		operation.setMontant(-(montant));

		// Operation compte recu
		operationInverse.setCompte(rcpt);
		operationInverse.setCompteDistant(em);
		operationInverse.setDateCreation(dt);
		operationInverse.setLibelle("Virement recu Compte n°0123");
		operationInverse.setMontant(montant);

		verify(operationDao, Mockito.times(1)).create(operation);
		// Mockito.verify(operationDao, Mockito.times(1)).create(operationInverse);
	}

	// Permission refusee
	@Test(expected = PermissionRefuseeException.class)
	public void testVirementPermissionRefusee() throws IdCompteNotFoundException, SoldeInsuffisantException, PermissionRefuseeException {
		Compte em = new Compte();
		em.setLibelle("compte1");
		Compte rcpt = new Compte();
		em.setLibelle("compte2");
		double montant = 10;
		when(compteService.getCompteById(98)).thenReturn(em);
		when(compteService.getCompteById(97)).thenReturn(rcpt);
		when(compteService.isApprovisionne(em, montant)).thenReturn(true);

		Utilisateur u1 = new Utilisateur();
		u1.setNom("nom1");
		Utilisateur u2 = new Utilisateur();
		u2.setNom("nom2");
		when(utilisateurDao.getOwner(em)).thenReturn(u1);
		when(utilisateurDao.getOwner(rcpt)).thenReturn(u2);

		operationService.createVirement(98, 97, montant);
	}

	// Solde insuffisant
	@Test(expected = SoldeInsuffisantException.class)
	public void testVirementNonApprovisionne() throws IdCompteNotFoundException, SoldeInsuffisantException, PermissionRefuseeException {
		Compte em = new Compte();
		Compte rcpt = new Compte();
		when(compteService.getCompteById(98)).thenReturn(em);
		when(compteService.getCompteById(97)).thenReturn(rcpt);
		when(compteService.isApprovisionne(em, 10)).thenReturn(false);
		operationService.createVirement(98, 97, 10);
	}

	// Id de compte inexistant
	@Test(expected = IdCompteNotFoundException.class)
	public void testVirementIdNotFound() throws IdCompteNotFoundException, SoldeInsuffisantException, PermissionRefuseeException {
		when(compteService.getCompteById(98)).thenThrow(new IdCompteNotFoundException(98));
		operationService.createVirement(98, 97, 10);
	}

	@Test
	public void testGetNbOperationsNoCBByMoisAnnee() {
		Compte c = new Compte();
		Set<Operation> operations = new HashSet<Operation>();

		// Operation Cheque 1
		OperationCheque opCh1 = new OperationCheque();
		opCh1.setDateCreation(new DateTime());
		operations.add(opCh1);

		// Operation Cheque 2
		OperationCheque opCh2 = new OperationCheque();
		opCh2.setDateCreation(new DateTime());
		operations.add(opCh2);

		c.setOperations(operations);

		DateTime now = new DateTime();
		DateTime dt = new DateTime(now.getYear(), now.getMonthOfYear(), now.dayOfMonth().withMinimumValue().getDayOfMonth(), 0, 0, 0, 0);

		Mockito.when(operationDao.getNbOperationsNoCBByDate(c, dt, dt.plusMonths(1))).thenReturn((long) operations.size());

		long res = operationService.getNbOperationsNoCBByMoisAnnee(c, now.getYear(), now.getMonthOfYear());

		assertEquals(res, operations.size());
	}

}