package com.excilys.formation.yaeba.dao.impl;

import java.io.File;

import javax.sql.DataSource;

import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.operation.DatabaseOperation;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring-context.xml" })
public class UtilisateurDaoImplDbTest {

	private DataSource dataSource;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Before
	public void init() throws Exception {
		// Avant le test on insère le dataSet
		DatabaseOperation.CLEAN_INSERT.execute((IDatabaseConnection) dataSource.getConnection(), getDataSet());
	}

	@After
	public void after() throws Exception {
		// Après le test on supprime le dataSet
		DatabaseOperation.DELETE_ALL.execute((IDatabaseConnection) dataSource.getConnection(), getDataSet());
	}

	private IDataSet getDataSet() throws Exception {
		return new FlatXmlDataSet(new File("src/test/resources/dataset.xml"));
	}

	@Test
	public void testSomethingWithDao() {
		// myDaoUnderTest.doSomething();

	}
}