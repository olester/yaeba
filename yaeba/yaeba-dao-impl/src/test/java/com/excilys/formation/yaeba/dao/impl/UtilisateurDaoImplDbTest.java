package com.excilys.formation.yaeba.dao.impl;

import static org.junit.Assert.assertTrue;

import java.io.File;

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

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/context/test-applicationContext.xml" })
public class UtilisateurDaoImplDbTest {

	@Autowired
	private DataSource dataSource;

	private UtilisateurDaoImpl utilisateurDaoImpl;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Autowired
	public void setUtilisateurDaoImpl(UtilisateurDaoImpl utilisateurDaoImpl) {
		this.utilisateurDaoImpl = utilisateurDaoImpl;
	}

	@Before
	public void init() throws Exception {
		// Avant le test on insère le dataSet
		java.sql.Connection con = DataSourceUtils.getConnection(dataSource);
		IDatabaseConnection dbUnitCon = new DatabaseConnection(con, "yaeba");

		DatabaseConfig config = dbUnitCon.getConfig();
		config.setFeature(DatabaseConfig.FEATURE_CASE_SENSITIVE_TABLE_NAMES, true);

		DatabaseOperation.INSERT.execute(dbUnitCon, getDataSet());
	}

	@After
	public void after() throws Exception {
		java.sql.Connection con = DataSourceUtils.getConnection(dataSource);
		IDatabaseConnection dbUnitCon = new DatabaseConnection(con, "yaeba");

		DatabaseConfig config = dbUnitCon.getConfig();
		config.setFeature(DatabaseConfig.FEATURE_CASE_SENSITIVE_TABLE_NAMES, true);

		DatabaseOperation.DELETE.execute(dbUnitCon, getDataSet());
	}

	private IDataSet getDataSet() throws Exception {
		FlatXmlDataSet f = new FlatXmlDataSet(new File("src/test/resources/dataset.xml"));
		return f;
	}

	@Test
	public void testSomethingWithDao() {
		// myDaoUnderTest.doSomething();
		assertTrue(true);
		// commentaire
	}

	// @Test
	// public void testGetUtilisateurById() {
	// Utilisateur u = utilisateurDaoImpl.getUtilisateurById("99");
	// System.out.println(u);
	// assertTrue(u.getNom().equals("citron"));
	// }

}