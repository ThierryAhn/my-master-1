package evolution;

/**
 * Class Clavier
 * @author ahounfol
 * @date 9/10/2012
 *
 */
public class Clavier {
	public enum Key {SPACEBAR, ARROW_LEFT, ARROW_RIGHT};
	
	/**
	 * Constructor
	 */
	public Clavier(){
	}
	
	/**
	 * Return the type of key pressed.
	 * @return
	 */
	public Key keyPressed(){
		return Key.ARROW_RIGHT;
	}
	
}
