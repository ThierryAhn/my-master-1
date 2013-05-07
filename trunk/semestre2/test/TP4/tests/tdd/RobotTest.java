package tdd;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import tdd.CannotGoThereException;
import tdd.IRobot;
import tdd.Orientation;
import tdd.Robot;

public class RobotTest {
	
	private IRobot robot;
	
	@Before
	public void setUp() throws Exception {
		robot = new Robot(2, 2);
	}

	@After
	public void tearDown() throws Exception {
		robot = null;
	}
	
	/**
	 * Test du constructeur.
	 */
	@Test
	public void testConstructor(){
		assertEquals(2, robot.getHeight());
		assertEquals(2, robot.getWidth());
		
		// test aleatoires random entre 0 et Integer.MAX_VALUE
		int random = (int)(Math.random() * (Integer.MAX_VALUE-0)) + 0;
		robot = new Robot(random, 2);
		assertEquals(random, robot.getHeight());
		robot = new Robot(2, random);
		assertEquals(random, robot.getWidth());
		random = (int)(Math.random() * (Integer.MAX_VALUE-0)) + 0;
		robot = new Robot(random, random);
		assertEquals(random, robot.getHeight());
		assertEquals(random, robot.getWidth());
		
		// test aleatoires random entre Integer.MIN_VALUE et 0
		random = (int)(Math.random() * (0-Integer.MIN_VALUE)) + Integer.MIN_VALUE;
		robot = new Robot(random, 2);
		assertEquals(random, robot.getHeight());
		robot = new Robot(2, random);
		assertEquals(random, robot.getWidth());
		random = (int)(Math.random() * (0-Integer.MIN_VALUE)) + Integer.MIN_VALUE;
		robot = new Robot(random, random);
		assertEquals(random, robot.getHeight());
		assertEquals(random, robot.getWidth());
		
		
		// test aux bornes sur height
		robot = new Robot(Integer.MIN_VALUE, 2);
		assertEquals(Integer.MIN_VALUE, robot.getHeight());
		
		robot = new Robot(0, 2);
		assertEquals(0, robot.getHeight());
		
		robot = new Robot(-1, 2);
		assertEquals(-1, robot.getHeight());
		
		// test aux bornes sur width
		robot = new Robot(2, Integer.MIN_VALUE);
		assertEquals(Integer.MIN_VALUE, robot.getWidth());
		
		robot = new Robot(2, 0);
		assertEquals(0, robot.getWidth());
		
		robot = new Robot(2, -1);
		assertEquals(-1, robot.getWidth());
		
		assertEquals(0, robot.getPosition().getX());
		assertEquals(0, robot.getPosition().getY());
		
		assertEquals(Orientation.EST, robot.getOrientation());
	}
	
	/**
	 * Test cas d'exception sur position X, en avancant vers l'ouest.
	 * @throws CannotGoThereException 
	 */
	@Test(expected=CannotGoThereException.class)
	public void testAvanceOuest() throws CannotGoThereException{
		robot.tourne(); robot.tourne(); // ouest
		// exception robot.x < width
		robot.avance();
	}
	
	/**
	 * Test cas d'exception sur position X, en avancant vers l'est.
	 * @throws CannotGoThereException 
	 */
	@Test(expected=CannotGoThereException.class)
	public void testAvanceEst() throws CannotGoThereException{
		// boucle pour avoir robots.x = width
		for(int i = 0; i < robot.getWidth(); i++)
			robot.avance();
		// exception robots.x > width
		robot.avance();
	}
	
	/**
	 * Test cas d'exception sur position Y, en avancant vers le sud.
	 * @throws CannotGoThereException 
	 */
	@Test(expected=CannotGoThereException.class)
	public void testAvanceSud() throws CannotGoThereException{
		robot.tourne(); // sud
		// exception robots.y < 0
		robot.avance();
	}
	
	/**
	 * Test cas d'exception sur position Y, en avancant vers le nord.
	 * @throws CannotGoThereException 
	 */
	@Test(expected=CannotGoThereException.class)
	public void testAvanceNord() throws CannotGoThereException{
		robot.tourne(); robot.tourne(); robot.tourne(); // nord
		
		// boucle pour avoir robots.y = height
		for(int i = 0; i < robot.getHeight(); i++)
			robot.avance();
		
		// exception robot.y > height
		robot.avance();
	}
	
	/**
	 * Test des avance sans levee d'exception.
	 * @throws CannotGoThereException
	 */
	@Test
	public void testAvance3() throws CannotGoThereException{
		// robots.x = 1
		robot.avance();
		assertEquals(1, robot.getPosition().getX());
		
		robot.tourne(); // sud
		robot.tourne(); // ouest
		
		// robots.x = 0
		robot.avance();
		assertEquals(0, robot.getPosition().getX());
		
		// robots.y = 1
		robot.tourne(); // nord
		robot.avance();
		assertEquals(1, robot.getPosition().getY());
		
		// robots.y = 0
		robot.tourne(); // est
		robot.tourne(); // sud
		robot.avance();
		assertEquals(0, robot.getPosition().getY());
	}
	
	/**
	 * Test des orientations(orientation par defaut = EST)
	 */
	@Test
	public void testTourne() {
		// orientation = SUD
		robot.tourne();
		assertEquals(Orientation.SUD, robot.getOrientation());
		
		// orientation = OUEST
		robot.tourne();
		assertEquals(Orientation.OUEST, robot.getOrientation());
		
		// orientation = NORD
		robot.tourne();
		assertEquals(Orientation.NORD, robot.getOrientation());
		
		// orientation = EST
		robot.tourne();
		assertEquals(Orientation.EST, robot.getOrientation());
	}

}
