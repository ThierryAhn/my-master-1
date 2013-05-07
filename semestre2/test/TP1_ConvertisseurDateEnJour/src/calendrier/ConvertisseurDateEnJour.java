package calendrier;
/**
 * Interface qui permet de convertir une date en jour.
 * @author Folabi.
 * @date 11/02/2013.
 *
 */
public interface ConvertisseurDateEnJour {
	
	/**
	 * Convertit une date du calendirier gregorien en le jour de la semaine correspondante. 
	 * @param jour le jour dans le mois (entre 1 et 31).
	 * @param mois mois dans le mois (entre 1 et 12).
	 * @param annee l'annee.
	 * @return 	le jour de la semaine correspondant entre 1 et 7.
	 * 			1 correspondant a dimanche.
	 * @throws InvalidGregorianDateException
	 * 			si la date est avant le 15 octobre 1582 ou si les parametres ne correspondent pas a 
	 * 			une date.
	 */
	public int getDelay(int jour, int mois, int annee) throws InvalidGregorianDateException;
}
