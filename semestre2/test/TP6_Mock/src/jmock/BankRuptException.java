package jmock;

/**
 * Classe GameIsCloseException qui etend Exception pour gerer l'exception de la banque en faillite.
 * @author Folabi
 *
 */
public class BankRuptException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BankRuptException(String message){
		super(message);
	}
}
