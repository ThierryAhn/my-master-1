package discovery;

import java.lang.management.ManagementFactory;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jws.WebService;
import javax.management.MBeanServer;
import javax.management.ObjectName;
import javax.xml.namespace.QName;
import javax.xml.ws.Endpoint;

import discovery.service.AvailableService;
import discovery.service.CalculatorWebService;
import discovery.service.ServiceDescription;
import discovery.service.ServiceNotFound;

/**
 * 
 * Gestionnaire de service.
 * Cette classe permet de gerer un ensemble de webservice et au besoin de les publier a la volee.
 *
 */
@WebService(endpointInterface = "discovery.RepositoryService", serviceName = "RepositoryService")
public class Repository implements RepositoryService, RepositoryMBean{
	
	static MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
	
	private Map<String, AvailableService> servicesDescription = new HashMap<String, AvailableService>();
	private List<String> listRepositoryAvailable = new ArrayList<String>();
	
	private String host;
	private int port;
	
	/**
	 * Cree un Repository
	 * @param host adresse du repository
	 * @param port port du repository
	 */
	public Repository(String host, int port) {
		this.host = host;
		this.port = port;
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
	
	public static void main(String[] args) throws Exception{
		Repository repository = new Repository("localhost", 8085);
		Endpoint.publish(repository.getBaseUrl()+"repository", repository);
		
		
		String operation = "CalculatorService";
		
		CalculatorWebService service = new CalculatorWebService();
		service.setUrl(repository.getBaseUrl()+operation);
		service.setServiceName(new QName("http://service.discovery/"));
		service.setPortName(new QName("http://service.discovery/"));
		
		repository.addService(operation, service);
		repository.publishService(service);
		repository.addRepository(repository.getBaseUrl()+operation);
		repository.addObjectInMBeanServer(repository);
		
	}
	
	
	public static void addObjectInMBeanServer(Repository mbean) throws Exception{
		ObjectName reference = new ObjectName("Repository:type=RepositoryMBean, name=Repository");
		mbs.registerMBean(mbean, reference);
	}
	
	
	@Override
	public ServiceDescription searchFor(String service) throws ServiceNotFound {
		ServiceDescription serviceDescription = new ServiceDescription();
		serviceDescription.setServiceName(new QName(service));
		
		return serviceDescription;
	}

	@Override
	public List<String> listAllServices() {
		return (List<String>) servicesDescription.keySet();
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
		return listRepositoryAvailable.toString();
	}
	
}