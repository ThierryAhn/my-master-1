package tdd;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TeleCommandeTest {
	
	private TeleCommande teleCommande;
	
	@Before
	public void setUp()throws Exception{
		teleCommande = new TeleCommande(new Robot(2,2));
	}
	
	@After
	public void tearDown() throws Exception {
		teleCommande = null;
	}
	
	/**
	 * Test du constructeur.
	 */
	@Test
	public void testConstructor(){
		Robot robot = new Robot(2,2);
		assertEquals(robot.getHeight(), teleCommande.getRobot().getHeight());
		assertEquals(robot.getWidth(), teleCommande.getRobot().getWidth());
		assertEquals(robot.getPosition().getX(), teleCommande.getRobot().getPosition().getX());
		assertEquals(robot.getPosition().getY(), teleCommande.getRobot().getPosition().getY());
		assertEquals(robot.getOrientation(), teleCommande.getRobot().getOrientation());
		robot = null;
	}
	
	/**
	 * Test de l'orientation vers le nord.
	 */
	@Test
	public void testOrienteToiAuNord() {
		teleCommande.orienteToiAuNord();
		assertEquals(Orientation.NORD, teleCommande.getRobot().getOrientation());
	}
	
	/**
	 * Test de l'orientation vers le sud.
	 */
	@Test
	public void testOrienteToiAuSud() {
		teleCommande.orienteToiAuSud();
		assertEquals(Orientation.SUD, teleCommande.getRobot().getOrientation());
	}
	
	/**
	 * Test de l'orientation vers l'est.
	 */
	@Test
	public void testOrienteToiALEst() {
		teleCommande.orienteToiALOuest();
		teleCommande.orienteToiALEst();
		assertEquals(Orientation.EST, teleCommande.getRobot().getOrientation());
	}
	
	/**
	 * Test de l'orientation vers l'ouest.
	 */
	@Test
	public void testOrienteToiALOuest() {
		teleCommande.orienteToiALOuest();
		assertEquals(Orientation.OUEST, teleCommande.getRobot().getOrientation());
	}
	
	/**
	 * Test avanceDe sans levee d'exception.
	 */
	@Test
	public void testAvanceDe() throws CannotGoThereException{
		teleCommande.avanceDe(1);
	}
	
	/**
	 * Test avanceDe avec levee d'exception.
	 */
	@Test(expected=CannotGoThereException.class)
	public void testAvanceDeException() throws CannotGoThereException{
		teleCommande.avanceDe(4);
	}

	@Test
	public void testAvanceEtRebonditDe() {
		teleCommande.avanceEtRebonditDe(9);
	}

}
