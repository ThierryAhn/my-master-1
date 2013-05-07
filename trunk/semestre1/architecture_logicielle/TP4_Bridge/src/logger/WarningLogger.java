package logger;

/**
 * Class WarningLogger for the sending of warning message.
 * @author ahounfol
 * @date 23/10/12
 *
 */
public class WarningLogger extends Logger{

	public WarningLogger(ILogger logger, String message) {
		super(logger, message);
	}

	@Override
	public String send() {
		// TODO Auto-generated method stub
		return "Warning : " +logger.log(message);
	}

}
