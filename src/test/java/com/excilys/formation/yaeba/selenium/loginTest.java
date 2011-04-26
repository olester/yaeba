package com.excilys.formation.yaeba.selenium;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.thoughtworks.selenium.DefaultSelenium;
import com.thoughtworks.selenium.Selenium;

public class loginTest {

	private Selenium selenium;

	@Before
	public void setUp() throws Exception {
		selenium = new DefaultSelenium("localhost", 4444, "*chrome", "http://localhost:8080/");
		selenium.start();
	}

	@Test
	public void testBadPassword() throws Exception {
		selenium.open("/yaeba/index.jsp");
		selenium.click("link=Authentifiez-vous");
		selenium.waitForPageToLoad("30000");
		selenium.type("login", "login");
		selenium.type("password", "badpassword");
		selenium.click("//input[@value='login']");
		selenium.waitForPageToLoad("30000");
		assertEquals("YAEBA - Yet Another E-Banking Application - mauvais login", selenium.getTitle());
	}

	@Test
	public void testGoodLogin() throws Exception {
		selenium.open("/yaeba/");
		selenium.click("link=Authentifiez-vous");
		selenium.waitForPageToLoad("30000");
		selenium.type("login", "login");
		selenium.type("password", "password");
		selenium.click("//input[@value='login']");
		selenium.waitForPageToLoad("30000");
		assertEquals("YAEBA - Yet Another E-Banking Application - page d'accueil", selenium.getTitle());
	}

	@After
	public void tearDown() throws Exception {
		selenium.stop();
	}

}
