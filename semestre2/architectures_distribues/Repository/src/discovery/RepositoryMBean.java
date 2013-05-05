package discovery;

import java.util.List;

public interface RepositoryMBean {
	/**
	 * Retourne toutes les services disponibles dans le repository courant.
	 * @return toutes les services disponibles dans le repository courant.
	 */
	public List<String> listServicesAvailable();
	/**
	 * Ajoute un repository.
	 * @param url url du repository a ajouter.
	 * @return true si le repository a ete bien ajoute, faux sinon.
	 */
	public boolean addRepository(String url);
	/**
	 * Supprime un repository.
	 * @param url url du repository a supprimer.
	 * @return true si le repository a ete bien supprime, faux sinon.
	 */
	public boolean removeRepository(String url);
	
	public String getExposedURL();
}
