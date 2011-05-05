package com.excilys.formation.yaeba.selenium;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.thoughtworks.selenium.DefaultSelenium;
import com.thoughtworks.selenium.Selenium;

public class LoginTest {

	Selenium selenium;

	@Before
	public void setUp() throws Exception {
		selenium = new DefaultSelenium("localhost", 4444, "*firefox", "http://localhost:8080/");
		selenium.start();
	}

	@Test
	public void testTest() throws Exception {
		selenium.open("/yaeba/");
		assertEquals("", selenium.getTitle());
		//
		// selenium.type("j_username", "user");
		// selenium.type("j_password", "user");
		// selenium.click("//input[@value='Valider']");
		// selenium.waitForPageToLoad("30000");
		// assertEquals("Comptes", selenium.getTitle());
		//
		// selenium.click("link=Virements");
		// selenium.waitForPageToLoad("30000");
		// assertEquals("Virements", selenium.getTitle());
		//
		// selenium.click("link=Accueil");
		// selenium.waitForPageToLoad("30000");
		// assertEquals("Accueil", selenium.getTitle());
		//
		// selenium.click("link=Comptes");
		// selenium.waitForPageToLoad("30000");
		// assertEquals("Comptes", selenium.getTitle());
		//
		// selenium.click("link=Yaeba");
		// selenium.waitForPageToLoad("30000");
		// assertEquals("Accueil", selenium.getTitle());
		//
		// selenium.click("link=Déconnexion");
		// selenium.waitForPageToLoad("30000");
		// assertEquals("Login", selenium.getTitle());
		//
		// selenium.type("j_username", "admin");
		// selenium.type("j_password", "admin");
		// selenium.click("//input[@value='Valider']");
		// selenium.waitForPageToLoad("30000");
		// assertEquals("Admin", selenium.getTitle());
		//
		// selenium.click("link=Virements");
		// selenium.waitForPageToLoad("30000");
		// assertEquals("Virements", selenium.getTitle());
		//
		// selenium.click("link=Admin");
		// selenium.waitForPageToLoad("30000");
		// assertEquals("Admin", selenium.getTitle());
		//
		// selenium.click("link=Comptes");
		// selenium.waitForPageToLoad("30000");
		// assertEquals("Comptes", selenium.getTitle());
		//
		// selenium.click("link=Accueil");
		// selenium.waitForPageToLoad("30000");
		// assertEquals("Accueil", selenium.getTitle());
		//
		// selenium.click("link=Comptes");
		// selenium.waitForPageToLoad("30000");
		// assertEquals("Comptes", selenium.getTitle());
		//
		// selenium.click("link=Yaeba");
		// selenium.waitForPageToLoad("30000");
		// assertEquals("Accueil", selenium.getTitle());
		//
		// selenium.open("/yaeba-webapp/unepageinexistante");
		// assertEquals("Erreur", selenium.getTitle());
		//
		// selenium.click("link=Revenir a la page d'accueil");
		// selenium.waitForPageToLoad("30000");
		// assertEquals("Accueil", selenium.getTitle());
		//
		// selenium.click("link=Déconnexion");
		// selenium.waitForPageToLoad("30000");
		// assertEquals("Login", selenium.getTitle());

	}

	@After
	public void tearDown() throws Exception {
		selenium.stop();
	}
}
