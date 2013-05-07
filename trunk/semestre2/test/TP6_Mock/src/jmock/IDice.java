package jmock;

/**
 * 
 * @author Folabi
 * @cons
 *
 */
public interface IDice {
	/**
	 * Lance un de.
	 * @post
	 * 	throwDice() >= 1
	 * 	throwDice() <= 6
	 * @return la valeur du lancer.
	 */
	public int throwDice();
	
}
