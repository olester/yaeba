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
@ContextConfiguration(locations = { "classpath:/context/applicationContext.xml" })
public class UtilisateurDaoImplDbTest {

	@Autowired
	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Before
	public void init() throws Exception {
		// Avant le test on insère le dataSet
		java.sql.Connection con = DataSourceUtils.getConnection(dataSource);
		IDatabaseConnection dbUnitCon = new DatabaseConnection(con, "yaeba");

		DatabaseConfig config = dbUnitCon.getConfig(); // new
		config.setFeature(DatabaseConfig.FEATURE_CASE_SENSITIVE_TABLE_NAMES, true); // new

		DatabaseOperation.CLEAN_INSERT.execute(dbUnitCon, getDataSet());
	}

	@After
	public void after() throws Exception {
		java.sql.Connection con = DataSourceUtils.getConnection(dataSource);
		IDatabaseConnection dbUnitCon = new DatabaseConnection(con, "yaeba");

		// DatabaseOperation.DELETE_ALL.execute(dbUnitCon, getDataSet());
	}

	private IDataSet getDataSet() throws Exception {
		FlatXmlDataSet f = new FlatXmlDataSet(new File("src/main/resources/dataset.xml"));
		return f;
	}

	@Test
	public void testSomethingWithDao() {
		// myDaoUnderTest.doSomething();
		assertTrue(true);
		// commentaire

	}
}