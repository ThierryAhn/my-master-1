package token_vehicule;

/**
 * Interface Mailing
 * @author Nicart
 * @date 16/10/12
 * 
 */
public interface Mailing {
	/* send a documentation with a given print id at a given address and return a confirmation message */
	public String send(int printid, String address);
}

/**
 * Implementation of the interface Mailing
 * @author Nicart
 * @date 16/10/12
 *
 */
class MailingImpl implements Mailing {
	public String send(int printid, String address) {
		return "The documentation order no " + printid + " will be sent at " + address;
	}
}
