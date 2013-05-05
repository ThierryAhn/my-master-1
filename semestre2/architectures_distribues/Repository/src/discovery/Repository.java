package discovery;

import java.lang.management.ManagementFactory;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jws.WebService;
import javax.management.JMX;
import javax.management.MBeanServer;
import javax.management.MBeanServerConnection;
import javax.management.ObjectName;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;
import javax.xml.namespace.QName;
import javax.xml.ws.Endpoint;

import discovery.service.AvailableService;
import discovery.service.CityManagerWebService;
import discovery.service.ServiceDescription;
import discovery.service.ServiceNotFound;

/**
 * 
 * Gestionnaire de service.
 * Cette classe permet de gerer un ensemble de webservice et au besoin de les publier a la volee.
 *
 */
@WebService
public class Repository implements RepositoryService, RepositoryMBean{
	
	static MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
	
	private Map<String, AvailableService> servicesDescription;
	private List<String> listRepositoryAvailable;
	
	private String host;
	private int port;
	
	/**
	 * Constructeur
	 * @param host adresse du repository
	 * @param port port du repository
	 */
	public Repository(String host, int port) {
		this.host = host;
		this.port = port;
		
		servicesDescription = new HashMap<String, AvailableService>();
		listRepositoryAvailable = new ArrayList<String>();
	}

	/**
	 * Ajouter un service au repository
	 * @param name reference au service
	 * @param service description du service
	 */
	private void addService(String name, AvailableService service) {
		servicesDescription.put(name, service);
	}
	
	/**
	 * URL de base du repository
	 * @return l'url d'acces a le repository publie des services
	 */
	private String getBaseUrl(){
		return "http://"+host+":"+port+"/";
	}
	
	/**
	 * Publie un service
	 * @param service service a  publier.
	 */
	private void publishService(AvailableService service){
		Endpoint.publish(service.getUrl(), service);
		service.setAvailable(true);
	}
	
	/**
	 * Enregistre le repository en parametre.
	 * @param mbean repository a enregistrer.
	 * @throws Exception
	 */
	private static void addObjectInMBeanServer(Repository mbean) throws Exception{
		ObjectName reference = new ObjectName("Repository:type=RepositoryMBean, name=Repository");
		mbs.registerMBean(mbean, reference);
	}
	
	/**
	 * Retourne un repository publie a une url donnee.
	 * @param url url du repository.
	 * @return le repository publie a l'url donnee.
	 * @throws Exception
	 */
	private RepositoryMBean getRepositoryByUrl(String url) throws Exception {
		JMXServiceURL serviceUrl = new JMXServiceURL("service:jmx:rmi:///jndi/rmi://"+ url +"/jmxrmi");
		JMXConnector jmxc = JMXConnectorFactory.connect(serviceUrl);
		MBeanServerConnection mbsc = jmxc.getMBeanServerConnection();
		ObjectName reference = new ObjectName("Repository:type=RepositoryMBean,name=Repository");
		return JMX.newMBeanProxy(mbsc, reference, RepositoryMBean.class);
	}
	
	
	@Override
	public ServiceDescription searchFor(String service) throws Exception {
		// si on retrouve le nom du service dans le map
		if(servicesDescription.containsKey(service)){
			AvailableService availableService = servicesDescription.get(service);
			if(availableService.isAvailable())
				return availableService;
			
			publishService(availableService);
			return availableService;
		}else{
			for(String url : listRepositoryAvailable) {
				RepositoryMBean repository = getRepositoryByUrl(url);
				if(repository.listServicesAvailable().contains(service)) {
					ServiceDescription serviceDescription = new ServiceDescription();
					serviceDescription.setUrl(repository.getExposedURL()+service);
					serviceDescription.setServiceName(new QName("http://service.discovery/", "CityManagerService"));
					serviceDescription.setPortName(new QName("http://service.discovery/", "CityManagerPort"));
					return serviceDescription;
				}
			}
		}
		
		throw new ServiceNotFound();
	}

	@Override
	public List<String> listAllServices() throws Exception {
		List<String> arrayServices = new ArrayList<String>();
		// ajout des services du repository courant
		arrayServices.addAll(this.listServicesAvailable());
		
		// ajout des services des repository associes
		for(String temp : listRepositoryAvailable){
			RepositoryMBean repository = getRepositoryByUrl(temp);
			arrayServices.addAll(repository.listServicesAvailable());
		}
		
		return arrayServices;
	}
	
	@Override
	public List<String> listServicesAvailable() {
		return listRepositoryAvailable;
	}
	
	@Override
	public boolean addRepository(String url) {
		return listRepositoryAvailable.add(url);
	}
	
	
	@Override
	public boolean removeRepository(String url) {
		return listRepositoryAvailable.remove(url);
	}

	@Override
	public String getExposedURL() {
		return getBaseUrl();
	}
	
	/**
	 * Cree un repository et le publie.
	 * @param port port ou publier le repository
	 * @return un repository cree et publie
	 * @throws Exception
	 */
	public static Repository createRepository(int port) throws Exception{
		Repository repository = new Repository("127.0.0.1", port);
		Endpoint.publish(repository.getBaseUrl()+"repository", repository);
		
		addObjectInMBeanServer(repository);
		
		return repository;
	}
	
	/**
	 * Ajoute un service de type CityManager au repository a la demande.
	 * @param name nom du service CityManager.
	 */
	public void addCityManagerService(String name){
		AvailableService service = new CityManagerWebService(this);
		
		service.setUrl(this.getBaseUrl()+name);
		service.setServiceName(new QName("http://service.discovery/", "CityManagerService"));
		service.setPortName(new QName("http://service.discovery/", "CityManagerPort"));
		addService(name, service);
	}
}