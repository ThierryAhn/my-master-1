package rmi;

import java.util.LinkedList;
import java.util.List;

import javax.management.JMX;
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
		
		CityManagerDMBean client = JMX.newMBeanProxy(mbsc, reference, CityManagerDMBean.class);
		
		// print all cities
		client.getCities();
		
		// remove all cities
		client.removeAllCities();
		
		// print all cities
		client.getCities();
		
		// add city rouen in france
		client.addCity(new City("Rouen", 49.443889, 1.103333, "France"));
		// add city mogadiscio in somalie
		client.addCity(new City("Mogadiscio", 2.333333, 48.85, "Somalie"));
		// add city rouen in france
		client.addCity(new City("Rouen", 49.443889, 1.103333, "France"));
		// add city bihorel in france
		client.addCity(new City("Bihorel", 49.455278, 1.116944, "France"));
		// add city londres in england
		client.addCity(new City("Londres", 51.504872, -0.07857, "Angleterre"));
		// add city paris in france
		client.addCity(new City("Paris", 48.856578, 2.351828, "France"));
		// add city paris in canada
		client.addCity(new City("Paris", 43.2, -80.38333, "Canada"));
		
		// print all cities
		client.getCities();
		
		// add city villers-bocage in france
		client.addCity(new City("Villers-Bocage", 49.083333, -0.65, "France"));
		client.addCity(new City("Villers-Bocage", 50.021858, 2.326126, "France"));
		
		// print all cities
		client.getCities();
		
		// remove city villers-bocage in france
		client.removeCity(new City("Villers-Bocage", 49.083333, -0.65, "France"));
		
		// print all cities
		client.getCities();
		
		// remove city londres twice in france
		client.removeCity(new City("Londres", 51.504872, -0.07857, "Angleterre"));
		client.removeCity(new City("Londres", 51.504872, -0.07857, "Angleterre"));
		
		// search cities at exacts positions
		client.searchExactPosition(new Position(49.443889, 1.103333));
		client.searchExactPosition(new Position(49.083333, -0.65));
		client.searchExactPosition(new Position(43.2, -80.38));
		
		// search near cities at exacts positions
		client.searchNear(new Position(48.85, 2.34));
		client.searchNear(new Position(42, 64));
		client.searchNear(new Position(49.45, 1.11));
		
		// search cities with specific name
		client.searchFor("Mogadiscio");
		client.searchFor("Paris");
		client.searchFor("Hyrule");
		
		// remove all cities
		client.removeAllCities();
		
		// print all cities
		client.getCities();
		
		// setCities
		List<City> cities = new LinkedList<City>();
		cities.add(new City("Rouen", 49.443889, 1.103333, "France"));
		client.setCities(cities);
		
		// print all cities
		client.getCities();
		
		// remove all cities
		client.removeAllCities();
		
		// print all cities
		client.getCities();
	}
}

