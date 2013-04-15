import javax.management.MBeanServerConnection;
import javax.management.ObjectName;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;


public class CityManagerClient {
	public static void main(String [] args) throws Exception{
		// create a RMI connector client
		JMXServiceURL url = new JMXServiceURL("service:jmx:rmi:///jndi/rmi://localhost:9998/jmxrmi");
		// we connect the RMI connection client to the RMI connector server
		JMXConnector jmxc = JMXConnectorFactory.connect(url);
		MBeanServerConnection mbsc = jmxc.getMBeanServerConnection();
		
		ObjectName reference = new ObjectName("CityManager:type=CityManagerDMBean, name=CityManager");
		
	}
}
