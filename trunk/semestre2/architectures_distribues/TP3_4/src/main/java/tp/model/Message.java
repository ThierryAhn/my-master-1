package tp.model;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * This class represents a message.
 * 
 * @author Folabi.
 * @date 20/02/2013.
 *
 */
@XmlRootElement
public class Message {
	
	private String message;
	
	/**
	 * Default Constructor.
	 */
	public Message(){
	}
	
	/**
	 * Create a message.
	 * @param message text of the message.
	 */
	public Message(String message){
		this.message = message;
	}
	
	/**
	 * Return the message text.
	 * @return the message text.
	 */
	public String getMessage() {
		return message;
	}
	
	/**
	 * Set a new message text.
	 * @param message new message text.
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * Print the message.
	 */
	public String toString() {
		return "Message [message=" + message + "]";
	}
	
	
}
