package calendrier;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import calendrier.Zeller;

public class TestConvertisseurDateEnJour {
	
	private static Zeller z = null;
	
	@Before
	public void setUp() throws Exception {
		z = Zeller.getInstance();
	}

	@After
	public void tearDown() throws Exception {
		z = null;
	}
	
	/**
	 * Test du constructeur, on verifie que z est different de null.
	 */
	@Test
	public void constructorTest1(){
		assertNotNull(z);
	}
	
	/**
	 * Test du singleton, on verifie que deux instances de z sont égales.
	 */
	@Test
	public void constructorTest2(){
		Zeller z2 = Zeller.getInstance();
		assertTrue(z.equals(z2));
	}
	
	/**
	 * Test limites du parametre jour, mois et annee sont des parametres valides.
	 * @throws InvalidGregorianDateException 
	 */
	@Test(expected=InvalidGregorianDateException.class)
	public void testDayIsValid1() throws InvalidGregorianDateException{
		int n = z.getDelay(0, 2, 2013);
		n = z.getDelay(32, 3, 2014);
		n = z.getDelay(31, 4, 2015);
	}
	
	/**
	 * Test limites du parametre jour, mois et annee sont des parametres valides. 
	 * @throws InvalidGregorianDateException 
	 */
	@Test
	public void testDayIsValid2() throws InvalidGregorianDateException{
		int n = z.getDelay(1, 5, 2015);
		assertTrue(n >= 1);
		assertTrue(n <= 7);
	}
	
	/**
	 * Test limites du parametre jour, mois = 2 et annee bissextile. 
	 * @throws InvalidGregorianDateException 
	 */
	@Test
	public void testDayIsValid3() throws InvalidGregorianDateException{
		int n = z.getDelay(29, 2, 2016);
		assertTrue(n >= 1);
		assertTrue(n <= 7);
	}
	
	/**
	 * Test limites du parametre jour, mois = 2 et annee non bissextile. 
	 * @throws InvalidGregorianDateException 
	 */
	@Test(expected=InvalidGregorianDateException.class)
	public void testDayIsValid4() throws InvalidGregorianDateException{
		int n = z.getDelay(29, 2, 2015);
	}
	
	/**
	 * Test limites du parametre mois, jour et annee valides. 
	 * @throws InvalidGregorianDateException 
	 */
	@Test(expected=InvalidGregorianDateException.class)
	public void testMonthIsValid1() throws InvalidGregorianDateException{
		int n = z.getDelay(15, 0, 2013);
		n = z.getDelay(16, 13, 2014);
	}
	
	/**
	 * Test parametre mois, jour, mois et annee valides. 
	 * @throws InvalidGregorianDateException 
	 */
	@Test
	public void testMonthIsValid2() throws InvalidGregorianDateException{
		int n = z.getDelay(17, 10, 2015);
		assertTrue(n >= 1);
		assertTrue(n <= 7);
	}
	
	/**
	 * Test du parametre annee, jour, mois valides. 
	 * @throws InvalidGregorianDateException 
	 */
	@Test(expected=InvalidGregorianDateException.class)
	public void testYearIsValid1() throws InvalidGregorianDateException{
		int n = z.getDelay(15, 1, 1581);
		n = z.getDelay(16, 2, Integer.MAX_VALUE);
	}
	
	/**
	 * Test du parametre annee, jour, mois valides. 
	 * @throws InvalidGregorianDateException 
	 */
	@Test
	public void testYearIsValid2() throws InvalidGregorianDateException{
		int n = z.getDelay(17, 3, 2015);
		assertTrue(n >= 1);
		assertTrue(n <= 7);
	}
	
	/**
	 * Test des arrondis dans la formule de Zeller.
	 * @throws InvalidGregorianDateException
	 */
	@Test
	public void testZellerFormula() throws InvalidGregorianDateException{
		assertTrue(2 == Math.floor(2.01));
		assertTrue(2 == Math.floor(2.99));
		assertTrue(0 == Math.floor(0/4));
		assertTrue(24 == Math.floor(99/4));
	}
	
}
