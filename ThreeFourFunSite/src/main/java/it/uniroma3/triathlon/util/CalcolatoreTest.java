package it.uniroma3.triathlon.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;

public class CalcolatoreTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testCalcolaCategoria() {
		assertEquals("M7", Calcolatore.calcolaCategoria(71));
		assertNull(Calcolatore.calcolaCategoria(10));
	}

}
