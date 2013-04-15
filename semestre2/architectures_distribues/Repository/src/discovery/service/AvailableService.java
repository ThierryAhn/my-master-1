package discovery.service;

/**
 * Classe abstraite pour suivre la publication d'un service 
 *
 */
public abstract class AvailableService extends ServiceDescription {

	/**
	 * Représente l'état de disponibilité du service
	 */
	private boolean available = false;

	/**
	 * Indique si le service est publié
	 * @return vrai si le service est publié, faux sinon
	 */
	public boolean isAvailable() {
		return available;
	}

	/**
	 * Change la valeur de l'état de publication du service
	 * @param available nouvel état.
	 */
	public void setAvailable(boolean available) {
		this.available = available;
	}
	
}
