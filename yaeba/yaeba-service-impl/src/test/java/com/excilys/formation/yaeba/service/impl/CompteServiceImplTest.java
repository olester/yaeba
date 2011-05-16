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

import com.excilys.formation.yaeba.model.Compte;
import com.excilys.formation.yaeba.model.OperationCarteBancaire;
import com.excilys.formation.yaeba.model.exception.NoCardException;
import com.excilys.formation.yaeba.service.api.CompteService;
import com.excilys.formation.yaeba.service.api.OperationService;

@RunWith(MockitoJUnitRunner.class)
public class CompteServiceImplTest {

	@Mock
	private OperationService operationService;

	@InjectMocks
	private CompteService compteService = new CompteServiceImpl();

	// Compte sans carte
	@Test(expected = NoCardException.class)
	public void testGetEncoursCarteNoCard() throws NoCardException {
		Compte c = new Compte();
		c.setCards(false);
		compteService.getEncoursCarte(c);
	}

	// Compte sans operation
	@Test
	public void testGetEncoursCarteSansOperation() throws NoCardException {
		Compte c = new Compte();
		c.setCards(true);

		DateTime now = new DateTime();
		List<OperationCarteBancaire> operationsCB = new ArrayList<OperationCarteBancaire>();
		when(operationService.getOperationsCBByMoisAnnee(c, now.getYear(), now.getMonthOfYear())).thenReturn(operationsCB);

		assertEquals(0, compteService.getEncoursCarte(c), 0);
	}

	// Compte avec operations
	@Test
	public void testGetEncoursCarte() throws NoCardException {
		Compte c = new Compte();
		c.setCards(true);

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
