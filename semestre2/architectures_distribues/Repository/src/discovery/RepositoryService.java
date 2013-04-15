package discovery;

import java.util.List;

import javax.jws.WebService;

import discovery.service.ServiceDescription;
import discovery.service.ServiceNotFound;

@WebService
public interface RepositoryService {
	
	// Retourne une description du service qui correspond au nom demande
	public ServiceDescription searchFor(String service) throws ServiceNotFound;
	
	// Liste l'ensemble des noms des services disponibles
	public List<String> listAllServices();
}
