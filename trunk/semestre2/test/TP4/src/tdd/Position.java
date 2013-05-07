package tdd;

public class Position implements IPosition {
	/**
	 * Abscisse.
	 * @see Position#Position(int, int)
	 * @see Position#setX(int)
	 */
	private int x;
	/**
	 * Ordonnee.
	 * @see Position#Position(int, int)
	 * @see Position#setY(int)
	 */
	private int y;
	
	/**
	 * Constructeur par defaut.
	 */
	public Position(){ 
	}
	
	/**
	 * Constructeur qui initialise x et y avec les valeurs en parametres.
	 * @param x
	 * @param y
	 */
	public Position(int x, int y) {
		this.x = x;
		this.y = y;
	}	

	/**
	 * Modifie la valeur de x.
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * Retourne la valeur de x.
	 */
	public int getX() {
		return x;
	}

	/**
	 * Modifie la valeur de y.
	 */
	public void setY(int y) {
		this.y = y;
	}

	/**
	 * Modifie la valeur de y.
	 */
	public int getY() {
		return y;
	}
	
}
