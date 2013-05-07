package logger;

public class TestLogger {
	public static void main(String ... args){
		
		ILogger fileLogger = new FileLogger();
		ILogger dbLogger = new DataBaseLogger();
		
		Logger warningLoggerFile = new WarningLogger(fileLogger, "File");
		Logger warningLoggerDb = new WarningLogger(dbLogger, "DB");
		
		Logger errorLoggerFile = new ErrorLogger(fileLogger, "File");
		Logger errorLoggerDb = new ErrorLogger(dbLogger, "DB");
		
		System.out.println(warningLoggerFile.send());
		System.out.println(errorLoggerFile.send());
		System.out.println(warningLoggerDb.send());
		System.out.println(errorLoggerDb.send());
		
	}
}
