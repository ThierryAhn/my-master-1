package jmock;

/**
 * Interface qui represente le joueur.
 * @cons
 * $ARGS$ int money
 * 
 * $PRE$
 * 	money > 0
 * 
 * $POST$
 * 	getMoney == money
 * 
 * @author Folabi
 */
public interface IPlayer {
	
	/**
	 * Retourne le solde d'argent du joueur.
	 * @return le solde d'argent du joueur.
	 */
	public int getMoney();
}
