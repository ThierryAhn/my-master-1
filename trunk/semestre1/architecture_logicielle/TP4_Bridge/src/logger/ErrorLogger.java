package logger;

/**
 * Class ErrorLogger for the sending of error message.
 * @author ahounfol
 * @date 23/10/12
 *
 */
public class ErrorLogger extends Logger{

	public ErrorLogger(ILogger logger, String message) {
		super(logger, message);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String send() {
		// TODO Auto-generated method stub
		return "Error : " +logger.log(message);
	}

}
