package rmi;

import java.util.LinkedList;
import java.util.List;

import javax.management.AttributeChangeNotification;
import javax.management.Notification;
import javax.management.NotificationBroadcasterSupport;

/**
 * MBean implementation for the RMIList MBean.
 * @author ahounfol
 *
 */
public class RMIList extends NotificationBroadcasterSupport implements RMIListMBean {
	
	/**
	 * Linked List of String.
	 */
	private List<String> list = new LinkedList<String>();
	
	/**
	 * Add a data in the list.
	 * @param data data to add.
	 * @return true if the data is well added, false if not.
	 */
	public boolean add(String data) {
		this.sendNotification(new Notification("Adding", data, System.currentTimeMillis(), "Adding of the value : "+data));
		return list.add(data);
	}

	/**
	 * Remove a given data from the list.
	 * @param data data to be removed.
	 * @return true if the data is well remove, false if not.
	 */
	public boolean remove(String data) {
		this.sendNotification(new Notification("Removing", data, System.currentTimeMillis(), "Value : "+data +" deleted "));
		return list.remove(data);
	}
	
	/**
	 * Print the data of the list.
	 */
	public String toString(){
		return list.toString();
	}

	@Override
	public void handleNotification(Notification notification, Object arg1) {
		System.out.println("\nReceived notification:");
        System.out.println("\tClassName: " + notification.getClass().getName());
        System.out.println("\tSource: " + notification.getSource());
        System.out.println("\tType: " + notification.getType());
        System.out.println("\tMessage: " + notification.getMessage());
        if (notification instanceof AttributeChangeNotification) {
            AttributeChangeNotification acn =
                (AttributeChangeNotification) notification;
            System.out.println("\tAttributeName: " + acn.getAttributeName());
            System.out.println("\tAttributeType: " + acn.getAttributeType());
            System.out.println("\tNewValue: " + acn.getNewValue());
            System.out.println("\tOldValue: " + acn.getOldValue());
        }
		
	}
}
