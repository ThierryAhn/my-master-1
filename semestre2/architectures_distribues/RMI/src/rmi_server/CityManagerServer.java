package rmi_server;

import java.io.IOException;
import java.lang.management.ManagementFactory;

import javax.management.InstanceNotFoundException;
import javax.management.MBeanServer;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;

public class CityManagerServer {
	static MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
	
	
	public static void main(String ...args) throws InterruptedException, InstanceNotFoundException{
		ObjectName reference = null;
		
		try {
			reference = new ObjectName("CityManager:type=DynamicMBean, name=CityManager");
		
		} catch (MalformedObjectNameException e) {
			e.printStackTrace();
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
		waitForEnterPressed();
	}
	
	/**
	 * Save MBean in the server.
	 * @param reference
	 * @throws InstanceNotFoundException 
	 */
	public static void addObjectInMBeanServer(ObjectName reference) throws InstanceNotFoundException{
		/*RMIList mbean = new RMIList();
		try {
			mbs.registerMBean(mbean, reference);
			mbs.addNotificationListener(reference, reference, null, null);
		} catch (InstanceAlreadyExistsException e) {
			e.printStackTrace();
		} catch (MBeanRegistrationException e) {
			e.printStackTrace();
		} catch (NotCompliantMBeanException e) {
			e.printStackTrace();
		}
		mbean.add("item0");*/
	}
	
	/**
	 * Stop the server.
	 */
	private static void waitForEnterPressed() {
        try {
            System.out.println("\nPress <Enter> to stop the server !");
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
