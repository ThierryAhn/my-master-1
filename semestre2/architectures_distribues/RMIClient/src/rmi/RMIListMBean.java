package rmi;

import javax.management.NotificationListener;

/**
 * MBean Interface describing the management operations attributes for the RMIList MBean.
 * @author ahounfol
 *
 */
public interface RMIListMBean extends NotificationListener{
	public boolean add(String data);
	public boolean remove(String data);
	public String toString();
}

