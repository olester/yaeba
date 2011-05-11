package com.excilys.formation.yaeba.dao.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import com.excilys.formation.yaeba.dao.api.OperationDao;
import com.excilys.formation.yaeba.model.Operation;
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
		Operation o = operationDao.getOperationById("98");
		assertNull(o);

		o = operationDao.getOperationById("99");
		assertEquals("test", o.getLibelle());
	}

	@Test
	public void testUpdate() {
		Operation c = operationDao.getOperationById("99");
		c.setLibelle("saved");
		operationDao.update(c);
		assertEquals("saved", c.getLibelle());
	}

	// @Test
	// public void testSave() {
	// Operation o = new Operation("opé2", 750, new DateTime());
	// operationDao.save(o);
	// Operation o1 = operationDao.getOperationById("opé2");
	// assertNotNull(o1);
	// }

	@Test
	public void testDelete() {
		Operation c = operationDao.getOperationById("99");
		operationDao.delete(c);
		c = operationDao.getOperationById("99");
		assertNull(c);
	}
}
