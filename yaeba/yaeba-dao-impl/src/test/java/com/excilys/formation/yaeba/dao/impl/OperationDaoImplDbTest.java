package com.excilys.formation.yaeba.dao.impl;

import static org.junit.Assert.assertTrue;

import java.sql.Connection;

import javax.sql.DataSource;

import org.dbunit.database.IDatabaseConnection;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/context/test-applicationContext.xml" })
public class OperationDaoImplDbTest {
	@Autowired
	private OperationDaoImpl operationDaoImpl;

	@Autowired
	private DataSource dataSource;

	private IDatabaseConnection dbUnitCon;
	private Connection con;

	@Test
	public void rien() {
		assertTrue(true);
	}

	// @Before
	// public void init() throws Exception {
	// // Avant le test on insère le dataSet
	// con = DataSourceUtils.getConnection(dataSource);
	// dbUnitCon = new DatabaseConnection(con, "yaeba");
	//
	// DatabaseConfig config = dbUnitCon.getConfig();
	// config.setFeature(DatabaseConfig.FEATURE_CASE_SENSITIVE_TABLE_NAMES, true);
	//
	// DatabaseOperation.INSERT.execute(dbUnitCon, getDataSet());
	// }
	//
	// @After
	// public void after() throws Exception {
	// DatabaseOperation.DELETE.execute(dbUnitCon, getDataSet());
	// con.close();
	// }
	//
	// // Chargement d'une bdd de test
	// private IDataSet getDataSet() throws Exception {
	// FlatXmlDataSet f = new FlatXmlDataSet(new File("src/test/resources/dataset.xml"));
	// return f;
	// }
	//
	// @Test
	// public void testGetOperationById() {
	// Operation o = operationDaoImpl.getOperationById("98");
	// assertNull(o);
	//
	// o = operationDaoImpl.getOperationById("99");
	// assertEquals("test", o.getLibelle());
	// }
	//
	// @Test
	// public void testUpdate() {
	// Operation c = operationDaoImpl.getOperationById("99");
	// c.setLibelle("saved");
	// operationDaoImpl.update(c);
	// assertEquals("saved", c.getLibelle());
	// }
	//
	// // @Test
	// // public void testSave() {
	// // Operation o = new Operation("opé2", 750, new Date());
	// // operationDaoImpl.save(o);
	// // List<Operation> l = operationDaoImpl.getOperationByLibelle("opé2");
	// // assertFalse(l.isEmpty());
	// // }
	//
	// @Test
	// public void testDelete() {
	// Operation c = operationDaoImpl.getOperationById("99");
	// operationDaoImpl.delete(c);
	// c = operationDaoImpl.getOperationById("99");
	// assertNull(c);
	// }
}
