package rmi;

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
			reference = new ObjectName("CityManager:type=CityManagerDMBean, name=CityManager");
			addObjectInMBeanServer(reference);
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
	private static void addObjectInMBeanServer(ObjectName reference){
		CityManager cityManager = new CityManager();
		try {
			mbs.registerMBean(cityManager,reference);
			mbs.addNotificationListener(reference,reference,null, null);
		} catch (Exception e) {
			e.printStackTrace();
		}
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
