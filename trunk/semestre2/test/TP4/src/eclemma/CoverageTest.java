package eclemma;

public class CoverageTest {
	
	public static void methodIf(boolean a, boolean b){
		if(a || b)
			b = true;
	}
	
	private static void lanceException() {
		throw new IllegalArgumentException();
	}
	
	public static void methodException() throws IllegalArgumentException {
		lanceException();
	}
}
