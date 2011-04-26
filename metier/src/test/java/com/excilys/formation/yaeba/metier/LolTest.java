package com.excilys.formation.yaeba.metier;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class LolTest {

	Lol lol;

	@Before
	public void setUp() throws Exception {
		lol = new Lol();
	}

	@Test
	public void testBadPassword() throws Exception {
		assertTrue(lol.getLol().equals("lol"));
	}

	@After
	public void tearDown() throws Exception {
	}

}
