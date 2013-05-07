package jmock;

/**
 * Classe GameIsCloseException qui etend Exception pour gerer l'exception quand la table est fermee.
 * @author Folabi
 *
 */
public class GameIsCloseException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 353131691959892052L;

	public GameIsCloseException(String message){
		super(message);
	}
}
