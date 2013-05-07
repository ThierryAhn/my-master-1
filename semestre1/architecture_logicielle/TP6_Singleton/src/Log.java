/**
 * Les deux types de Singleton sont : 
 * --> Le Singleton agressif non dérivable
 * --> Le Singleton laxiste non dérivable
 * J'ai choisi le laxiste parce qu'on aimerait avoir une instance de Log quand on utilise
 * le journal
 */

/**
 * Class Log, allow one instanciation.
 * @author ahounfol
 * @date 13/11/12
 *
 */
public final class Log {
	
	/**
	 * Private reference to the one and the only instance
	 */
	private static Log log = null;
	
	/**
	 * Private reference to the current class
	 */
	private static Object lock = Log.class;
	
	/**
	 * Private constructor.
	 * No client can instanciate Log object.
	 */
	private Log(){}
	
	/**
	 * Return a reference to the single instance, create it if does not exist
	 * @return log
	 */
	public static Log instance(){
		synchronized(lock){
			if(log == null){
				log = new Log();
			}
			return log;
		}
	}
	
	/**
	 * Print an information message
	 * @param text the information to print
	 * @return text
	 */
	public String information(String text){
		return "Info : " + text;
	}
	
	/**
	 * Print a warning message 
	 * @param text the warning to print
	 * @return text
	 */
	public String warning(String text){
		return "Warning : " + text;
	}
	
	/**
	 * Print an error message
	 * @param text the error to print
	 * @return text
	 */
	public String error(String text){
		return "Error : " +text;
	}
}
