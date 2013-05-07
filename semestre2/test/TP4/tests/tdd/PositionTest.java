package tdd;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import tdd.IPosition;
import tdd.Position;

public class PositionTest {

	private IPosition position1;
	private IPosition position2;
	
	@Before
	public void setUp() throws Exception {
		position1 = new Position();
		position2 = new Position(3,8);
	}

	@After
	public void tearDown() throws Exception {
		position1 = null;
		position2 = null;
	}
	
	/**
	 * Test constructeur par defaut.
	 */
	@Test
	public void testDefaultConstructor(){
		// contructeur par defaut.
		assertEquals(0, position1.getX());
		assertEquals(0, position1.getY());
	}
	
	
	/**
	 * Test du constructeur avec parametres.
	 */
	@Test
	public void testConstructorWithParam(){
		
		// Second constructor
		assertEquals(3, position2.getX());
		assertEquals(8, position2.getY());
		
		// test aux bornes sur x
		position2 = new Position(Integer.MIN_VALUE,8);
		assertEquals(Integer.MIN_VALUE, position2.getX());
		position2 = new Position(0,8);
		assertEquals(0, position2.getX());
		position2 = new Position(-1,8);
		assertEquals(-1, position2.getX());
		position2 = new Position(Integer.MAX_VALUE,8);
		assertEquals(Integer.MAX_VALUE, position2.getX());
		
		
		// test aux bornes sur y
		position2 = new Position(3,Integer.MIN_VALUE);
		assertEquals(Integer.MIN_VALUE, position2.getY());
		position2 = new Position(3,0);
		assertEquals(0, position2.getY());
		position2 = new Position(3,-1);
		assertEquals(-1, position2.getY());
		
		
		// test aleatoires random entre 0 et Integer.MAX_VALUE
		int random = (int)(Math.random() * (Integer.MAX_VALUE-0)) + 0;
		
		position2 = new Position(random,8);
		assertEquals(random, position2.getX());
		
		random = (int)(Math.random() * (Integer.MAX_VALUE-0)) + 0;
		position2 = new Position(0,random);
		assertEquals(random, position2.getY());
		
		random = (int)(Math.random() * (Integer.MAX_VALUE-0)) + 0;
		position2 = new Position(random,random);
		assertEquals(random, position2.getX());
		assertEquals(random, position2.getY());
		
		
		// test aleatoires random entre Integer.MIN_VALUE et 0
		random = (int)(Math.random() * (0-Integer.MIN_VALUE)) + Integer.MIN_VALUE;
		position2 = new Position(random,8);
		assertEquals(random, position2.getX());
		
		random = (int)(Math.random() * (0-Integer.MIN_VALUE)) + Integer.MIN_VALUE;
		position2 = new Position(0,random);
		assertEquals(random, position2.getY());
		
		random = (int)(Math.random() * (0-Integer.MIN_VALUE)) + Integer.MIN_VALUE;
		position2 = new Position(random,random);
		assertEquals(random, position2.getX());
		assertEquals(random, position2.getY());
	}
	
	/**
	 * Test du modificateur de x.
	 */
	@Test
	public void testSetX() {
		position1.setX(2);
		assertEquals(2, position1.getX());
		
		position2.setX(2);
		assertEquals(2, position2.getX());
		
		// test aleatoires random entre 0 et Integer.MAX_VALUE
		int random = (int)(Math.random() * (Integer.MAX_VALUE-0)) + 0;
		position1.setX(random);
		assertEquals(random, position1.getX());
		
		random = (int)(Math.random() * (Integer.MAX_VALUE-0)) + 0;
		position2.setX(random);
		assertEquals(random, position2.getX());
		
		// test aleatoires random entre Integer.MIN_VALUE et 0
		random = (int)(Math.random() * (0-Integer.MIN_VALUE)) + Integer.MIN_VALUE;
		position1.setX(random);
		assertEquals(random, position1.getX());
		
		random = (int)(Math.random() * (0-Integer.MIN_VALUE)) + Integer.MIN_VALUE;
		position2.setX(random);
		assertEquals(random, position2.getX());
		
		// test aux bornes
		position1.setX(Integer.MIN_VALUE);
		assertEquals(Integer.MIN_VALUE, position1.getX());
		position2.setX(Integer.MIN_VALUE);
		assertEquals(Integer.MIN_VALUE, position2.getX());
		position1.setX(0);
		assertEquals(0, position1.getX());
		position2.setX(0);
		assertEquals(0, position2.getX());
		position1.setX(Integer.MAX_VALUE);
		assertEquals(Integer.MAX_VALUE, position1.getX());
		position2.setX(Integer.MAX_VALUE);
		assertEquals(Integer.MAX_VALUE, position2.getX());
		
	}
	
	/**
	 * Test de l'accesseur de x.
	 */
	@Test
	public void testGetX() {
		assertEquals(0, position1.getX());
		assertEquals(3, position2.getX());
	}
	
	/**
	 * Test du modificateur de y.
	 */
	@Test
	public void testSetY() {
		position1.setY(2);
		assertEquals(2, position1.getY());
		
		position2.setY(2);
		assertEquals(2, position2.getY());
		
		// test aleatoires random entre 0 et Integer.MAX_VALUE
		int random = (int)(Math.random() * (Integer.MAX_VALUE-0)) + 0;
		position1.setY(random);
		assertEquals(random, position1.getY());
		
		random = (int)(Math.random() * (Integer.MAX_VALUE-0)) + 0;
		position2.setY(random);
		assertEquals(random, position2.getY());
		
		// test aleatoires random entre Integer.MIN_VALUE et 0
		random = (int)(Math.random() * (0-Integer.MIN_VALUE)) + Integer.MIN_VALUE;
		position1.setY(random);
		assertEquals(random, position1.getY());
		
		random = (int)(Math.random() * (0-Integer.MIN_VALUE)) + Integer.MIN_VALUE;
		position2.setY(random);
		assertEquals(random, position2.getY());
		
		// test aux bornes
		position1.setY(Integer.MIN_VALUE);
		assertEquals(Integer.MIN_VALUE, position1.getY());
		position2.setY(Integer.MIN_VALUE);
		assertEquals(Integer.MIN_VALUE, position2.getY());
		position1.setY(0);
		assertEquals(0, position1.getY());
		position2.setY(0);
		assertEquals(0, position2.getY());
		position1.setY(Integer.MAX_VALUE);
		assertEquals(Integer.MAX_VALUE, position1.getY());
		position2.setY(Integer.MAX_VALUE);
		assertEquals(Integer.MAX_VALUE, position2.getY());
	}
	
	/**
	 * Test de l'accesseur de y.
	 */
	@Test
	public void testGetY() {
		assertEquals(0, position1.getY());
		assertEquals(8, position2.getY());
	}

}
