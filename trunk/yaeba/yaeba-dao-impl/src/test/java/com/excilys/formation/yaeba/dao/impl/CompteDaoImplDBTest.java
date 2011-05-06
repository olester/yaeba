package com.excilys.formation.yaeba.dao.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.io.File;
import java.sql.Connection;

import javax.sql.DataSource;

import org.dbunit.database.DatabaseConfig;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.operation.DatabaseOperation;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.excilys.formation.yaeba.model.Compte;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/context/test-applicationContext.xml" })
public class CompteDaoImplDBTest {

	@Autowired
	private CompteDaoImpl compteDaoImpl;

	@Autowired
	private DataSource dataSource;

	private IDatabaseConnection dbUnitCon;
	private Connection con;

	@Before
	public void init() throws Exception {
		// Avant le test on insère le dataSet
		con = DataSourceUtils.getConnection(dataSource);
		dbUnitCon = new DatabaseConnection(con, "yaeba");

		DatabaseConfig config = dbUnitCon.getConfig();
		config.setFeature(DatabaseConfig.FEATURE_CASE_SENSITIVE_TABLE_NAMES, true);

		DatabaseOperation.INSERT.execute(dbUnitCon, getDataSet());
	}

	@After
	public void after() throws Exception {
		DatabaseOperation.DELETE.execute(dbUnitCon, getDataSet());
		con.close();
	}

	// Chargement d'une bdd de test
	private IDataSet getDataSet() throws Exception {
		FlatXmlDataSet f = new FlatXmlDataSet(new File("src/test/resources/dataset.xml"));
		return f;
	}

	@Test
	public void testGetCompteById() {
		Compte c = compteDaoImpl.getCompteById("98");
		assertNull(c);

		c = compteDaoImpl.getCompteById("99");
		assertEquals("citron", c.getNumeroCompte());
	}

	// @Test
	// public void testGetCompteByLogin() {
	// Compte c = compteDaoImpl.getCompteByNumeroCompte("riendutout");
	// assertNull(c);
	//
	// c = compteDaoImpl.getCompteByNumeroCompte("citron");
	// assertEquals("citron", c.getNumeroCompte());
	// }
	//
	// @Test
	// public void testUpdate() {
	// Compte c = compteDaoImpl.getCompteByNumeroCompte("citron");
	// c.setNumeroCompte("fraise");
	// compteDaoImpl.update(c);
	// assertEquals("fraise", c.getNumeroCompte());
	// }
	//
	// @Test
	// public void testSave() {
	// Compte c = new Compte("login2", "login2", null, new Date(), 10.2f);
	// compteDaoImpl.save(c);
	// Compte c2 = compteDaoImpl.getCompteByNumeroCompte("login2");
	// assertNotNull(c2);
	// }
	//
	// @Test
	// public void testDelete() {
	// Compte c = compteDaoImpl.getCompteByNumeroCompte("login2");
	// compteDaoImpl.delete(c);
	// Compte c2 = compteDaoImpl.getCompteByNumeroCompte("login2");
	// assertNull(c2);
	// }
}
