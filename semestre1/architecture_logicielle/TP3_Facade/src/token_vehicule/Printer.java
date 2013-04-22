package token_vehicule;

/**
 * Interface Printer
 * @author Nicart
 * @date 16/10/12
 *
 */
public interface Printer {
	/* print the documentation and return the job id */
	public int print(String doc);
}

/**
 * Implementation of the interface Printer
 * @author Nicart
 * @date 16/10/12
 *
 */
class PrinterImpl implements Printer {
	private static int jobid=0;

	public static Printer getDefaultPrinter() {
		return new PrinterImpl();
	}

	public int print(String doc) {
		return ++jobid;
	}
}
