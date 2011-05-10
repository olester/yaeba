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

		// EN FRANCAIS

		selenium.open("/yaeba-webapp/?lang=fr");
		assertEquals("Authentification", selenium.getTitle());

		selenium.type("j_username", "user");
		selenium.type("j_password", "user");
		selenium.click("//input[@value='Valider']");
		selenium.waitForPageToLoad("30000");
		assertEquals("Comptes", selenium.getTitle());

		selenium.click("link=Virements");
		selenium.waitForPageToLoad("30000");
		assertEquals("Virements", selenium.getTitle());

		selenium.click("link=Accueil");
		selenium.waitForPageToLoad("30000");
		assertEquals("Bienvenue", selenium.getTitle());

		selenium.click("link=Comptes");
		selenium.waitForPageToLoad("30000");
		assertEquals("Comptes", selenium.getTitle());

		selenium.click("link=Yaeba");
		selenium.waitForPageToLoad("30000");
		assertEquals("Bienvenue", selenium.getTitle());

		selenium.click("link=Déconnexion");
		selenium.waitForPageToLoad("30000");
		assertEquals("Authentification", selenium.getTitle());

		selenium.type("j_username", "admin");
		selenium.type("j_password", "admin");
		selenium.click("//input[@value='Valider']");
		selenium.waitForPageToLoad("30000");
		assertEquals("Administration", selenium.getTitle());

		selenium.click("link=Virements");
		selenium.waitForPageToLoad("30000");
		assertEquals("Virements", selenium.getTitle());

		selenium.click("link=Admin");
		selenium.waitForPageToLoad("30000");
		assertEquals("Administration", selenium.getTitle());

		selenium.click("link=Comptes");
		selenium.waitForPageToLoad("30000");
		assertEquals("Comptes", selenium.getTitle());

		selenium.click("link=Accueil");
		selenium.waitForPageToLoad("30000");
		assertEquals("Bienvenue", selenium.getTitle());

		selenium.click("link=Comptes");
		selenium.waitForPageToLoad("30000");
		assertEquals("Comptes", selenium.getTitle());

		selenium.click("link=Yaeba");
		selenium.waitForPageToLoad("30000");
		assertEquals("Bienvenue", selenium.getTitle());

		// selenium.open("/yaeba-webapp/unepageinexistante");
		// assertEquals("Erreur", selenium.getTitle());

		// selenium.click("link=Revenir a la page d'accueil");
		// selenium.waitForPageToLoad("30000");
		// assertEquals("Bienvenue", selenium.getTitle());
		//
		selenium.click("link=Déconnexion");
		selenium.waitForPageToLoad("30000");
		assertEquals("Authentification", selenium.getTitle());

		// EN ANGLAIS

		selenium.click("//div[@id='footer-content']/span/a[1]/img");

		selenium.waitForPageToLoad("30000");
		assertEquals("Authentication", selenium.getTitle());

		selenium.type("j_username", "user");
		selenium.type("j_password", "user");
		selenium.click("//input[@value='Validate']");
		selenium.waitForPageToLoad("30000");
		assertEquals("Accounts", selenium.getTitle());

		selenium.click("link=Transfers");
		selenium.waitForPageToLoad("30000");
		assertEquals("Transfers", selenium.getTitle());

		selenium.click("link=Home");
		selenium.waitForPageToLoad("30000");
		assertEquals("Welcome", selenium.getTitle());

		selenium.click("link=Accounts");
		selenium.waitForPageToLoad("30000");
		assertEquals("Accounts", selenium.getTitle());

		selenium.click("link=Yaeba");
		selenium.waitForPageToLoad("30000");
		assertEquals("Welcome", selenium.getTitle());

		selenium.click("link=Logout");
		selenium.waitForPageToLoad("30000");
		assertEquals("Authentication", selenium.getTitle());

		selenium.type("j_username", "admin");
		selenium.type("j_password", "admin");
		selenium.click("//input[@value='Validate']");
		selenium.waitForPageToLoad("30000");
		assertEquals("Administration", selenium.getTitle());

		selenium.click("link=Transfers");
		selenium.waitForPageToLoad("30000");
		assertEquals("Transfers", selenium.getTitle());

		selenium.click("link=Admin");
		selenium.waitForPageToLoad("30000");
		assertEquals("Administration", selenium.getTitle());

		selenium.click("link=Accounts");
		selenium.waitForPageToLoad("30000");
		assertEquals("Accounts", selenium.getTitle());

		selenium.click("link=Home");
		selenium.waitForPageToLoad("30000");
		assertEquals("Welcome", selenium.getTitle());

		selenium.click("link=Accounts");
		selenium.waitForPageToLoad("30000");
		assertEquals("Accounts", selenium.getTitle());

		selenium.click("link=Yaeba");
		selenium.waitForPageToLoad("30000");
		assertEquals("Welcome", selenium.getTitle());

		selenium.click("link=Logout");
		selenium.waitForPageToLoad("30000");
		assertEquals("Authentication", selenium.getTitle());
	}

	@After
	public void tearDown() throws Exception {
		selenium.stop();
	}
}
