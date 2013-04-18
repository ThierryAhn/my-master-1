
import javax.management.JMX;
import javax.management.MBeanServerConnection;
import javax.management.ObjectName;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;

import rmi.RMIListMBean;

public class Client {
	
	public static void main(String [] args) throws Exception{
		// create a RMI connector client
		JMXServiceURL url = new JMXServiceURL("service:jmx:rmi:///jndi/rmi://localhost:9999/jmxrmi");
		// we connect the RMI connection client to the RMI connector server
		JMXConnector jmxc = JMXConnectorFactory.connect(url);
		MBeanServerConnection mbsc = jmxc.getMBeanServerConnection();
		
		ObjectName reference = new ObjectName("RMIList:type=RMIListMBean, name=RMIList");
		
		retrieveObjectFromBeanServer(mbsc, reference);
	}
	
	/**
	 * Get a MBean from the server and add the data(item1).
	 * @param reference
	 * @throws Exception
	 */
	public static void retrieveObjectFromBeanServer(MBeanServerConnection mbsc, ObjectName reference) throws Exception{
		RMIListMBean mbean = JMX.newMBeanProxy(mbsc, reference, RMIListMBean.class);
		mbsc.addNotificationListener(reference, reference, null, null);
		mbean.add("item1");
		System.out.println(mbean);
	}
	
}
