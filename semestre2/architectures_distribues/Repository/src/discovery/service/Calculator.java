package discovery.service;

import javax.jws.WebService;

@WebService
public interface Calculator {
	/**
	 * Effectue une operation sur les nombres number1 et number2
	 * @param number1 
	 * @param number2
	 * @return le résultat de l'opétarion
	 */
	public double executeOperation(double number1, double number2);
}
