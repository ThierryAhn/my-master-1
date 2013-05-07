package logger;

/**
 * Abstract class Logger
 * @author ahounfol
 * @date 23/10/12
 * 
 */
public abstract class Logger {
	
	/**
	 * Interface ILogger
	 */
	protected ILogger logger;
	
	/**
	 * The message to be send
	 */
	protected String message;
	
	public Logger(ILogger logger, String message){
		this.logger = logger;
		this.message = message;
	}
	
	public abstract String send();
	
}
