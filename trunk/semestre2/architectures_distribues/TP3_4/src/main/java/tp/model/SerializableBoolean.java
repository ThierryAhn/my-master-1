package tp.model;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * This class represents a serializable boolean.
 * 
 * @author Folabi.
 * @date 20/02/2013.
 *
 */
@XmlRootElement
public class SerializableBoolean {
	
	private boolean var;
	
	/**
	 * Default constructor.
	 */
	public SerializableBoolean(){
	}
	
	/**
	 * Creates a new serializable boolean with it var.
	 * @param var
	 */
	public SerializableBoolean(boolean var){
		this.var = var;
	}
	
	/**
	 * Set a new boolean variable.
	 * @param var new boolean variable.
	 */
	public void set(boolean var){
		this.var = var;
	}
	
	/**
	 * Return the boolean variable.
	 * @return var.
	 */
	public boolean get(){
		return var;
	}
}
