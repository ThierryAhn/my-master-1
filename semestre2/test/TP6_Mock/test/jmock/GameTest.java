package jmock;

import static org.junit.Assert.*;

import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Before;
import org.junit.Test;

/**
 * Classe GameTest
 * @author Folabi
 *
 */
public class GameTest {
	private JUnitRuleMockery context;
	IGame game;
	IBank bank;
	IDice dice;
	IPlayer player;
	
	@Before
	public void doBeforeEachTestCase(){
		context = new JUnitRuleMockery();
		bank = context.mock(IBank.class);
		dice = context.mock(IDice.class);
		player = context.mock(IPlayer.class, "MockPlayer1");
	}
	
	/**
	 * Scenario : le jeu est ferme, il ne doit donc y avoir aucune interaction entre les autres acteurs.
	 * @throws GameIsCloseException
	 * @throws BankRuptException 
	 */
	@Test(expected=GameIsCloseException.class)
	public void testGameIsClose() throws GameIsCloseException, BankRuptException{
		game = new Game(bank, dice, dice);
		// fermeture du jeu, open == false
		game.close();
		game.play(20);
	}
	
	/**
	 * Scenerio : test aux limites de la mise (Integer.MIN_VALUE)
	 * @throws GameIsCloseException 
	 * @throws BankRuptException 
	 */
	@Test(expected=IllegalArgumentException.class)
	public void testBetNegativeInfiniteValue() throws IllegalArgumentException, GameIsCloseException, 
													BankRuptException{
		game = new Game(bank, dice, dice);
		game.play(Integer.MIN_VALUE);
	}
	
	/**
	 * Scenerio : Mise (Mise negative) et mise negative comprise entre Integer.MIN_VALUE et 0
	 * @throws GameIsCloseException 
	 * @throws BankRuptException 
	 */
	@Test(expected=IllegalArgumentException.class)
	public void testBetNegativeValue() throws IllegalArgumentException, GameIsCloseException, BankRuptException{
		game = new Game(bank, dice, dice);
		game.play(-30);
		// random negative
		int random = (int)(Math.random() * (0-Integer.MIN_VALUE)) + Integer.MIN_VALUE;
		game.play(random);
		
	}
	
	/**
	 * Scenerio : test aux limites de la mise (0)
	 * @throws GameIsCloseException 
	 * @throws BankRuptException 
	 */
	@Test(expected=IllegalArgumentException.class)
	public void testBetBadValue() throws IllegalArgumentException, GameIsCloseException, BankRuptException{
		game = new Game(bank, dice, dice);
		game.play(0);
	}
	
	/**
	 * Scenerio : test aux limites de la mise (Integer.MAX_VALUE + 1)
	 * @throws GameIsCloseException 
	 * @throws BankRuptException 
	 */
	@Test(expected=IllegalArgumentException.class)
	public void testBetPositiveInfiniteValue() throws IllegalArgumentException, GameIsCloseException, 
										BankRuptException{
		game = new Game(bank, dice, dice);
		game.play(Integer.MAX_VALUE + 1);
		
		int random = (int)(Math.random() * (Integer.MAX_VALUE-0)) + 0;
		game.play(Integer.MAX_VALUE + random);
	}
	
	/**
	 * Scenario : Test avec une mise correcte, on peut donc joueur
	 * @throws GameIsCloseException 
	 * @throws BankRuptException 
	 */
	@Test
	public void testGoodBet() throws GameIsCloseException, BankRuptException{
		game = new Game(bank, dice, dice);
		game.play(20);
		game.play(Integer.MAX_VALUE - 1);
	}
	
	/**
	 * Scenerion : Joueur perdant
	 * @throws GameIsCloseException
	 * @throws BankRuptException 
	 */
	@Test
	public void testPlayerLose() throws GameIsCloseException, BankRuptException{
		context.checking(new Expectations(){ {
			// player a 20€
			oneOf(player).getMoney(); will(returnValue(20));
			
			// lancer des des
			exactly(2).of(dice).throwDice();
			will(onConsecutiveCalls(returnValue(4), returnValue(1)));
			
			// le joueur a perdu, la banque le debite de 10€
			oneOf(bank).debit(player, 10);
			oneOf(bank).getMoney(); will(returnValue(50 + 10));
		} });
		
	
		game = new Game(bank, dice, dice);
		game.addPlayer(player);
		game.play(10);
		
		context.assertIsSatisfied();
		
		assertTrue(game.isOpen());
	}
	
	
	/**
	 * Scenerion : Joueur gagnant
	 * @throws GameIsCloseException
	 * @throws BankRuptException 
	 */
	@Test
	public void testPlayerWin() throws GameIsCloseException, BankRuptException{
		context.checking(new Expectations(){ {
			// player a 20€
			oneOf(player).getMoney(); will(returnValue(20));
			
			// lancer des des
			exactly(2).of(dice).throwDice();
			will(onConsecutiveCalls(returnValue(4), returnValue(3)));
			
			// le joueur a gagne, la banque le credite de 2 * 20€
			oneOf(bank).credit(player, 20*2);
			oneOf(bank).getMoney(); will(returnValue(980 - (20*2)));
		} });
		
	
		game = new Game(bank, dice, dice);
		game.addPlayer(player);
		game.play(20);
		
		context.assertIsSatisfied();
		assertTrue(game.isOpen());
	}
	
	/**
	 * Scenerion : La banque a saute
	 * @throws GameIsCloseException
	 * @throws BankRuptException 
	 */
	@Test(expected=BankRuptException.class)
	public void testBankRupt() throws GameIsCloseException, BankRuptException{
		context.checking(new Expectations(){ {
			// player a 20€
			oneOf(player).getMoney(); will(returnValue(20));
			
			// lancer des des
			exactly(2).of(dice).throwDice();
			will(onConsecutiveCalls(returnValue(4), returnValue(3)));
			
			// le joueur a gagne, la banque le credite de 2 * 20€
			oneOf(bank).credit(player, 50*2);
			oneOf(bank).getMoney(); will(returnValue(50 - (50*2)));
		} });
		
	
		game = new Game(bank, dice, dice);
		game.addPlayer(player);
		game.play(50);
		
		context.assertIsSatisfied();
	}
	
	
	/**
	 * Scenerion : Plusieurs joueurs gagnants et un perdant
	 * @throws GameIsCloseException
	 * @throws BankRuptException 
	 */
	@Test
	public void testPlayersWinOneLose() throws GameIsCloseException, BankRuptException{
		final IPlayer player2 = context.mock(IPlayer.class, "MockPlayer2");
		final IPlayer player3 = context.mock(IPlayer.class, "MockPlayer3");
		
		// player 1 joue et gagne
		context.checking(new Expectations(){ {
			// player1 a 20€
			oneOf(player).getMoney(); will(returnValue(20));
			
			// lancer des des
			exactly(2).of(dice).throwDice();
			will(onConsecutiveCalls(returnValue(4), returnValue(3)));
			
			// le joueur a gagne, la banque le credite de 2 * 5€
			oneOf(bank).credit(player, 5*2);
			oneOf(bank).getMoney(); will(returnValue(200 - (5*2)));
			
		} });
		
		// player 2 joue et gagne
		context.checking(new Expectations(){ {
			// player2 a 30€
			oneOf(player2).getMoney(); will(returnValue(30));
			
			// lancer des des
			exactly(2).of(dice).throwDice();
			will(onConsecutiveCalls(returnValue(4), returnValue(3)));
			
			// le joueur a gagne, la banque le credite de 2 * 5€
			oneOf(bank).credit(player2, 5*2);
			oneOf(bank).getMoney(); will(returnValue(190 - (5*2)));
			
			
		} });
		
		// player 3 joue et perd
		context.checking(new Expectations(){ {
			// player3 a 40€
			oneOf(player3).getMoney(); will(returnValue(40));
			
			// lancer des des
			exactly(2).of(dice).throwDice();
			will(onConsecutiveCalls(returnValue(4), returnValue(2)));
			
			// le joueur a perdu, la banque le debite de 5€
			oneOf(bank).debit(player3, 5);
			oneOf(bank).getMoney(); will(returnValue(180 + 5));
			
			
		} });
		
		
		game = new Game(bank, dice, dice);
		game.addPlayer(player);
		game.addPlayer(player2);
		game.addPlayer(player3);
		game.play(5);
		
		context.assertIsSatisfied();
		assertTrue(game.isOpen());
	}
	
	
	/**
	 * Scenerion : Plusieurs joueurs perdent
	 * @throws GameIsCloseException
	 * @throws BankRuptException 
	 */
	@Test
	public void testPlayersLose() throws GameIsCloseException, BankRuptException{
		final IPlayer player2 = context.mock(IPlayer.class, "MockPlayer2");
		
		// player 1 joue et perd
		context.checking(new Expectations(){ {
			// player1 a 20€
			oneOf(player).getMoney(); will(returnValue(20));
			
			// lancer des des
			exactly(2).of(dice).throwDice();
			will(onConsecutiveCalls(returnValue(4), returnValue(1)));
			
			// le joueur a perdu, la banque le debite de 5€
			oneOf(bank).debit(player, 5);
			oneOf(bank).getMoney(); will(returnValue(180 + 5));
			
		} });
		
		// player 2 joue et perd
		context.checking(new Expectations(){ {
			// player2 a 30€
			oneOf(player2).getMoney(); will(returnValue(30));
			
			// lancer des des
			exactly(2).of(dice).throwDice();
			will(onConsecutiveCalls(returnValue(4), returnValue(2)));
			
			// le joueur a perdu, la banque le debite de 5€
			oneOf(bank).debit(player2, 5);
			oneOf(bank).getMoney(); will(returnValue(185 + 5));
			
		} });
		
		
		game = new Game(bank, dice, dice);
		game.addPlayer(player);
		game.addPlayer(player2);
		game.play(5);
		
		context.assertIsSatisfied();
		assertTrue(game.isOpen());
	}
	
	
	/**
	 * Scenerion : Joueurs gagnent et font sauter la banque
	 * @throws GameIsCloseException
	 * @throws BankRuptException 
	 */
	@Test(expected=BankRuptException.class)
	public void testAllPlayersWin() throws GameIsCloseException, BankRuptException{
		final IPlayer player2 = context.mock(IPlayer.class, "MockPlayer2");
		final IPlayer player3 = context.mock(IPlayer.class, "MockPlayer3");
		
		context.checking(new Expectations(){ {
			// player a 20€
			oneOf(player).getMoney(); will(returnValue(20));
			
			// lancer des des
			exactly(2).of(dice).throwDice();
			will(onConsecutiveCalls(returnValue(4), returnValue(3)));
			
			// le joueur a gagne, la banque le credite de 2 * 20€
			oneOf(bank).credit(player, 10*2);
			oneOf(bank).getMoney(); will(returnValue(50 - (10*2)));
		} });
		
		context.checking(new Expectations(){ {
			// player a 20€
			oneOf(player2).getMoney(); will(returnValue(20));
			
			// lancer des des
			exactly(2).of(dice).throwDice();
			will(onConsecutiveCalls(returnValue(4), returnValue(3)));
			
			// le joueur a gagne, la banque le credite de 2 * 20€
			oneOf(bank).credit(player2, 10*2);
			oneOf(bank).getMoney(); will(returnValue(50 - (10*2)));
		} });
		
		context.checking(new Expectations(){ {
			// player a 20€
			oneOf(player3).getMoney(); will(returnValue(20));
			
			// lancer des des
			exactly(2).of(dice).throwDice();
			will(onConsecutiveCalls(returnValue(4), returnValue(3)));
			
			// le joueur a gagne, la banque le credite de 2 * 20€
			oneOf(bank).credit(player3, 10*2);
			oneOf(bank).getMoney(); will(returnValue(10 - (10*2)));
		} });
	
		game = new Game(bank, dice, dice);
		game.addPlayer(player);
		game.addPlayer(player2);
		game.addPlayer(player3);
		game.play(10);
		
		context.assertIsSatisfied();
	}
	
}
