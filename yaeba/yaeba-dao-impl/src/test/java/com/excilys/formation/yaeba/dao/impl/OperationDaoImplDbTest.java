package com.excilys.formation.yaeba.dao.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.joda.time.DateTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import com.excilys.formation.yaeba.dao.api.OperationDao;
import com.excilys.formation.yaeba.model.Operation;
import com.excilys.formation.yaeba.model.OperationCarteBancaire;
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

	@Test
	public void testGetOperationById() {
		Operation o = operationDao.getOperationById(98);
		assertNull(o);

		o = operationDao.getOperationById(99);
		assertEquals("test", o.getLibelle());
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
