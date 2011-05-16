package com.excilys.formation.yaeba.dao.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.joda.time.DateTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import com.excilys.formation.yaeba.dao.api.CompteDao;
import com.excilys.formation.yaeba.dao.api.OperationDao;
import com.excilys.formation.yaeba.dao.api.UtilisateurDao;
import com.excilys.formation.yaeba.model.Compte;
import com.excilys.formation.yaeba.model.Operation;
import com.excilys.formation.yaeba.model.OperationCarteBancaire;
import com.excilys.formation.yaeba.model.OperationVirementInterne;
import com.excilys.formation.yaeba.model.Utilisateur;
import com.excilys.utils.spring.log.logback.test.Logback;
import com.excilys.utils.spring.log.logback.test.LogbackConfigurerTestExecutionListener;
import com.excilys.utils.spring.test.dbunit.DataSet;
import com.excilys.utils.spring.test.dbunit.DataSetTestExecutionListener;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/context/test-applicationContext.xml", "classpath:/context/test-applicationContext-HSQL.xml" })
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, DataSetTestExecutionListener.class, LogbackConfigurerTestExecutionListener.class })
@DataSet("classpath:dataset.xml")
@Logback("classpath:logback-test-dao.xml")
public class OperationDaoImplDbTest {

	@Autowired
	private OperationDao operationDao;
	@Autowired
	private CompteDao compteDao;
	@Autowired
	private UtilisateurDao utilisateurDao;

	@Test
	public void testGetOperationById() {
		Operation o = operationDao.getOperationById(98);
		assertNull(o);

		o = operationDao.getOperationById(99);
		assertEquals("testcb", o.getLibelle());
	}

	@Test
	public void testGetOperationsByDate() {
		Compte c = compteDao.getCompteById(99);
		List<Operation> operations = operationDao.getOperationsByDate(c, new DateTime(2002, 12, 1, 0, 0, 0, 0), new DateTime(2003, 1, 31, 0, 0, 0, 0));
		assertEquals(6, operations.size());

		operations = operationDao.getOperationsByDate(c, new DateTime(2002, 12, 1, 0, 0, 0, 0), new DateTime(2003, 1, 1, 0, 0, 0, 0));
		assertEquals(1, operations.size());
	}

	@Test
	public void testGetOperationsNoCBByDate() {
		Compte c = compteDao.getCompteById(99);
		List<Operation> operations = operationDao.getOperationsNoCBByDate(c, new DateTime(2002, 12, 1, 0, 0, 0, 0), new DateTime(2003, 1, 31, 0, 0, 0, 0));
		assertEquals(4, operations.size());

		assertEquals("testcheque", operations.get(0).getLibelle());
		assertEquals("testcheque2", operations.get(1).getLibelle());
		assertEquals("testcheque3", operations.get(2).getLibelle());
		assertEquals("vir 99 -> 100", operations.get(3).getLibelle());
	}

	@Test
	public void testGetOperationsNoCBByDatePagination() {
		Compte c = compteDao.getCompteById(99);
		List<Operation> operations = operationDao.getOperationsNoCBByDate(c, new DateTime(2002, 12, 1, 0, 0, 0, 0), new DateTime(2003, 1, 31, 0, 0, 0, 0), 1,
				10);

		assertTrue(operations.size() <= 10);
		assertEquals(4, operations.size());

		operations = operationDao.getOperationsNoCBByDate(c, new DateTime(2002, 12, 1, 0, 0, 0, 0), new DateTime(2003, 1, 31, 0, 0, 0, 0), 2, 2);
		assertEquals(2, operations.size());
		assertEquals("testcheque3", operations.get(0).getLibelle());
	}

	@Test
	public void testGetNbOperationsNoCBByDate() {
		Compte c = compteDao.getCompteById(99);
		assertEquals(4, operationDao.getNbOperationsNoCBByDate(c, new DateTime(2002, 12, 1, 0, 0, 0, 0), new DateTime(2003, 1, 31, 0, 0, 0, 0)));
	}

	@Test
	public void testGetOperationsCBByDate() {
		Compte c = compteDao.getCompteById(99);
		List<OperationCarteBancaire> operations = operationDao.getOperationsCBByDate(c, new DateTime(2002, 12, 1, 0, 0, 0, 0), new DateTime(2003, 1, 31, 0, 0,
				0, 0));

		assertEquals(2, operations.size());
		assertEquals("testcb2", operations.get(0).getLibelle());
		assertEquals("testcb", operations.get(1).getLibelle());
	}

	@Test
	public void testGetVirementsInternes() {
		// Virements sans doublons (que les negatifs attaches au compte emetteur)
		Utilisateur u = utilisateurDao.getUtilisateurById(99);
		List<OperationVirementInterne> virements = operationDao.getVirementsInternes(u);

		assertEquals(1, virements.size());
		assertEquals("vir 99 -> 100", virements.get(0).getLibelle());
	}

	@Test
	public void testSave() {
		OperationCarteBancaire o = new OperationCarteBancaire();
		o.setDateCreation(new DateTime());
		o.setMontant(13246487);
		o.setLibelle("test");
		o.setDateEffective(new DateTime());
		operationDao.create(o);
		Operation o1 = operationDao.getOperationById(o.getId());
		assertNotNull(o1);
	}
}
