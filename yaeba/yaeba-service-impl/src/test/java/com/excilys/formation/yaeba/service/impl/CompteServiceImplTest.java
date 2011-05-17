package com.excilys.formation.yaeba.service.impl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.excilys.formation.yaeba.dao.api.CompteDao;
import com.excilys.formation.yaeba.model.Compte;
import com.excilys.formation.yaeba.model.OperationCarteBancaire;
import com.excilys.formation.yaeba.service.api.CompteService;
import com.excilys.formation.yaeba.service.api.OperationService;
import com.excilys.formation.yaeba.service.api.exception.IdCompteNotFoundException;
import com.excilys.formation.yaeba.service.api.exception.NoCardException;

@RunWith(MockitoJUnitRunner.class)
public class CompteServiceImplTest {

	@Mock
	private OperationService operationService;
	@Mock
	private CompteDao compteDao;

	@InjectMocks
	private CompteService compteService = new CompteServiceImpl();

	@Test
	public void testGetCompteById() throws IdCompteNotFoundException {
		Compte c = new Compte();
		c.setLibelle("testcoucou");
		when(compteDao.getCompteById(98)).thenReturn(c);
		assertEquals("testcoucou", compteService.getCompteById(98).getLibelle());
	}

	// Id qui n'existe pas
	@Test(expected = IdCompteNotFoundException.class)
	public void testGetCompteByIdNotFound() throws IdCompteNotFoundException {
		when(compteDao.getCompteById(98)).thenReturn(null);
		compteService.getCompteById(98);
	}

	// Compte sans carte
	@Test(expected = NoCardException.class)
	public void testGetEncoursCarteNoCard() throws NoCardException {
		Compte c = new Compte();
		c.setAssociatedWithCards(false);
		compteService.getEncoursCarte(c);
	}

	// Compte sans operation
	@Test
	public void testGetEncoursCarteSansOperation() throws NoCardException {
		Compte c = new Compte();
		c.setAssociatedWithCards(true);

		DateTime now = new DateTime();
		List<OperationCarteBancaire> operationsCB = new ArrayList<OperationCarteBancaire>();
		when(operationService.getOperationsCBByMoisAnnee(c, now.getYear(), now.getMonthOfYear())).thenReturn(operationsCB);

		assertEquals(0, compteService.getEncoursCarte(c), 0);
	}

	// Compte avec operations
	@Test
	public void testGetEncoursCarte() throws NoCardException {
		Compte c = new Compte();
		c.setAssociatedWithCards(true);

		DateTime now = new DateTime();
		List<OperationCarteBancaire> operationsCB = new ArrayList<OperationCarteBancaire>();
		// Effective le 1 du mois
		OperationCarteBancaire o = new OperationCarteBancaire();
		o.setDateEffective(now.withDayOfMonth(1));
		o.setMontant(-1000);
		operationsCB.add(o);
		// Effective le 30/31 du mois
		o = new OperationCarteBancaire();
		o.setDateEffective(now.dayOfMonth().withMaximumValue().secondOfDay().withMaximumValue());
		o.setMontant(-100);
		operationsCB.add(o);

		when(operationService.getOperationsCBByMoisAnnee(c, now.getYear(), now.getMonthOfYear())).thenReturn(operationsCB);

		assertEquals(-100, compteService.getEncoursCarte(c), 0);
	}

}
