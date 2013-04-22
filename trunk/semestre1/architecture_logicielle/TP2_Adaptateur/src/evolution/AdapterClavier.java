package evolution;

import evolution.Clavier.Key;

/**
 * Adapter for the class Clavier
 * @author ahounfol
 * @date 9/10/2012
 *
 */
public class AdapterClavier implements Manette{
	
	/**
	 * Attribute clavier.
	 */
	private Clavier clavier;
	
	public AdapterClavier(Clavier clavier){
		this.clavier = clavier;
	}
	
	@Override
	public boolean isLeft() {
		// TODO Auto-generated method stub
		return clavier.keyPressed().equals(Key.ARROW_LEFT);
	}

	@Override
	public boolean isRight() {
		// TODO Auto-generated method stub
		return clavier.keyPressed().equals(Key.ARROW_RIGHT);
	}

	@Override
	public boolean isPush() {
		// TODO Auto-generated method stub
		return clavier.keyPressed().equals(Key.SPACEBAR);
	}

}
