package jmock;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe Game qui implemente l'interface Game.
 * @author Folabi
 *
 */
public class Game implements IGame{
	/**
	 * 
	 */
	private boolean open;
	/**
	 * La banque du jeu.
	 */
	private IBank bank;
	/**
	 * Les deux des du jeu.
	 */
	private IDice firstDice, secondDice;
	/**
	 * Liste des joueurs du jeu.
	 */
	private List<IPlayer> listPlayers;
	
	/**
	 * Constructeur. 
	 * Cree le jeu avec une liste de joueurs vide, une banque et les deux des.
	 * Le jeu est automatiquement ouvert lors de sa creation.
	 */
	public Game(IBank bank, IDice firstDice, IDice secondDice){
		this.bank = bank;
		this.firstDice = firstDice;
		this.secondDice = secondDice;
		listPlayers = new ArrayList<IPlayer>();
		open = true;
	}
	
	@Override
	public boolean isOpen() {
		return open;
	}

	@Override
	public void play(int bet) throws GameIsCloseException, BankRuptException {
		// si le jeu est ouvert
		if(open){
			// si la mise est inferieure ou egale a 0
			if(bet <= 0){
				throw new IllegalArgumentException("Argument bet de play() invalide");
			}
			for(int i = 0; i < listPlayers.size(); i++){
				IPlayer currentPlayer = listPlayers.get(i);
				// si le joueur a assez pour miser
				if(currentPlayer.getMoney() >= bet){
					// lancer de des
					int tempDice1 = firstDice.throwDice();;
					int tempDice2 = secondDice.throwDice();;
					if((tempDice1 + tempDice2) == 7){
						int amount = bet * 2;
						// la banque credite le joueur
						bank.credit(currentPlayer, amount);
					}else{
						bank.debit(currentPlayer, bet);
					}
				}else{
					this.removePlayer(currentPlayer);
				}
				// si le fond de la banque est inferieur ou egale a 0, elle saute
				if(bank.getMoney() <= 0){
					open = false;
					throw new BankRuptException("Faillite de la banque");
				}
			}
		}else{
			throw new GameIsCloseException("Le Jeu est ferme");
		}
	}

	@Override
	public void close() {
		open = false;
	}

	@Override
	public IBank getBank() {
		return bank;
	}
	
	@Override
	public IDice getFirstDice() {
		return firstDice;
	}

	@Override
	public IDice getSecondDice() {
		return secondDice;
	}

	@Override
	public List<IPlayer> getPlayers() {
		return listPlayers;
	}

	@Override
	public boolean addPlayer(IPlayer player) {
		return listPlayers.add(player);
	}

	@Override
	public boolean removePlayer(IPlayer player) {
		return listPlayers.remove(player);
	}


}
