package com.excilys.formation.yaeba.service.impl;

import static org.junit.Assert.assertEquals;

import java.util.HashSet;
import java.util.Set;

import org.joda.time.DateTime;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.excilys.formation.yaeba.dao.api.OperationDao;
import com.excilys.formation.yaeba.model.Compte;
import com.excilys.formation.yaeba.model.Operation;
import com.excilys.formation.yaeba.model.OperationCheque;
import com.excilys.formation.yaeba.model.OperationVirementInterne;
import com.excilys.formation.yaeba.service.api.OperationService;

@RunWith(MockitoJUnitRunner.class)
public class OperationServiceImplTest {

	@Mock
	private OperationDao operationDao;

	@InjectMocks
	private OperationService operationService = new OperationServiceImpl();

	// TODO : TESTER si on essaie de faire un virement av ec un identifiant ne correspondant a rien (mock renvoyant null)

	@Test
	@Ignore
	public void testVirement() {
		// assertTrue(true);
		OperationVirementInterne o1 = new OperationVirementInterne();
		OperationVirementInterne o2 = new OperationVirementInterne();

		// Mockito.when(operationDao.getOperationById(2)).thenReturn(o1);
		// operationService.createVirement(o1, o2);

		Mockito.verify(operationDao, Mockito.times(1)).create(o1);
		Mockito.verify(operationDao, Mockito.times(1)).create(o2);
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