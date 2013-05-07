
public class TestLog {
	
	static int function1(int value){
		// reference to the single instance
		Log log = Log.instance();
		
		if(value > 1000)
			System.out.println(log.warning("warning"));
		
		if(value == 0)
			System.out.println(log.error("error"));
		
		return 12345/value;
			
		
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		// reference to the single instance
		Log log = Log.instance();
		
		// print info message
		System.out.println(log.information("Demarrage de l'application"));
		
		System.out.println(function1(1001));
		
		System.out.println(log.information("fin normale de l'application"));
		
		Log log2 = Log.instance();
		
		System.out.println("Is the same object? "+(log == log2));
		
	}

}
