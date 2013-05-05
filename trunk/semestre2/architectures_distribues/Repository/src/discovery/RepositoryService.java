package discovery;

import java.util.List;

import javax.jws.WebService;

import discovery.service.ServiceDescription;
import discovery.service.ServiceNotFound;

@WebService
public interface RepositoryService {
	
	/**
	 * Retourne une description du service qui correspond au nom demande
	 * @param service service dont il faut retourner la description.
	 * @return la description du service.
	 * @throws ServiceNotFound
	 * @throws Exception
	 */
	public ServiceDescription searchFor(String service) throws ServiceNotFound, Exception;
	
	/**
	 * Liste l'ensemble des noms des services disponibles dans le repository et ceux des repository associes.
	 * @return l'ensemble des noms des services.
	 * @throws Exception
	 */
	public List<String> listAllServices() throws Exception;
}
