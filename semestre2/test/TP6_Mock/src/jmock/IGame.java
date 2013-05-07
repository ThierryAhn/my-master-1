package jmock;

import java.util.List;

/**
 * Interface representant une partie du jeu.
 * @cons
 * $ARGS$ IBank bank, IDice firstDice, IDice secondDice
 * 
 * $PRE$
 * 	bank != null
 * 	firstDice != null
 * 	secondDice != null
 * 
 * $POST$
 * 	getBank() = bank
 * 	getPlayers().size() == 0
 * 	isOpen() == true
 * 	getFirstDice() == firstDice
 * 	getSecondDice() == secondDice
 * 
 * @author Folabi
 *
 */
public interface IGame {
	
	/**
	 * Indique si le jeu est ouvert ou non.
	 * @return true si le jeu est ouvert, faux sinon.
	 */
	public boolean isOpen();
	
	/**
	 * Permet de jouer tant que le jeu est ouvert.
	 * @pre
	 * 	isOpen() == true get
	 * 	bet > 0
	 * @post
	 * 	
	 * @param bet mise courante.
	 * 
	 * @throws GameIsCloseException
	 * 	isOpen() == false
	 * @throws BankRuptException
	 * 	isOpen() == false
	 */
	public void play(int bet) throws GameIsCloseException, BankRuptException;
	
	/**
	 * Ferme le jeu.
	 * @pre
	 * 	isOpen() == true
	 * @post
	 * 	isOpen() == false
	 */
	public void close();
	
	/**
	 * Retourne la banque du jeu.
	 * @return la banque du jeu.
	 */
	public IBank getBank();

	/**
	 * Retourne le premier de du jeu.
	 * @return le premier de du jeu.
	 */
	public IDice getFirstDice();
	
	/**
	 * Retourne le second de du jeu.
	 * @return le second de du jeu.
	 */
	public IDice getSecondDice();
	
	/**
	 * Retourne la liste des joueurs.
	 * @return la liste des joueurs.
	 */
	public List<IPlayer> getPlayers();
	/**
	 * Permet d'ajouter un joueur a la partie.
	 * @pre
	 * 	player != null
	 * @post
	 * 	getPlayers().contains(player) == true
	 * @param player joueur a ajouter.
	 * @return true si le joueur a ete bien ajoute, faux sinon.
	 */
	public boolean addPlayer(IPlayer player);
	
	/**
	 * Permet de supprimer un joueur de la partie.
	 * @pre 
	 * 	player != null
	 * @post
	 * 	getPlayers().contains(player) == false
	 * @param player joueur a supprimer.
	 * @return true si le joueur a ete bien supprime, faux sinon.
	 */
	public boolean removePlayer(IPlayer player);
}
