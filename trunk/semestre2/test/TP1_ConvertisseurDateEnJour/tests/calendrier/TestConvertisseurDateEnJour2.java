package calendrier;


import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestConvertisseurDateEnJour2 {
	
	private static Zeller z = null;
	
	@Before
	public void setUp() throws Exception {
		z = Zeller.getInstance();
	}

	@After
	public void tearDown() throws Exception {
		z = null;
	}
	
	// bissextile : 1600, 2000, 2400
	/**
	 * Jeux de valeurs limites. 1 <= Jour <= 28, Mois = 2 et Annee non bissextile : 1582, 1700, 3000
	 * @throws InvalidGregorianDateException 
	 */
	@Test
	public void testDayIsValid1() throws InvalidGregorianDateException{
		assertTrue(1 == z.getDelay(28, 2, 1700));
	}
	
	/**
	 * Jeux de valeurs limites. 1 <= Jour <= 28, Mois = 2 et Annee bissextile : 1600, 2000, 2400
	 * @throws InvalidGregorianDateException 
	 */
	@Test
	public void testDayIsValid2() throws InvalidGregorianDateException{
		assertTrue(3 == z.getDelay(29, 2, 1600));
		assertTrue(3 == z.getDelay(29, 2, 2400));
	}
	
}
