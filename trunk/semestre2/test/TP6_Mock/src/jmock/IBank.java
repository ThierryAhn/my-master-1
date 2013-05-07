package jmock;

/**
 * Interface qui represente la banque.
 * @cons
 * $ARGS$ int money
 * 
 * $PRE$
 * 	money > 0
 * 
 * $POST$
 * 	getMoney() == money
 * 
 * @author Folabiget
 *
 */
public interface IBank {
	
	/**
	 * Debite le montant du pari du joueur.
	 * @pre 
	 * 	amount > 0
	 * @post
	 * 	getMoney() == money + amount
	 * @param player joueur a debiter.
	 * @param amount montant a ajouter sur le compte du joueur.
	 */
	public void debit(IPlayer player, int amount);
	
	/**
	 * Paye deux fois la mise du joueur.
	 * @pre
	 * 	amount > 0
	 * @post
	 * 	getMoney() == money - (2*amount)
	 * @param player joueur a payer.
	 * @param amount montant a ajouter sur le compte du joueur.
	 */
	public void credit(IPlayer player, int amount);
	
	/**
	 * Retourne le solde d'argent de la banque.
	 * @return le solde d'argent de la banque.
	 */
	public int getMoney();
}
