package discovery.service;

import javax.jws.WebService;

@WebService(endpointInterface = "discovery.service.Calculator", serviceName = "Calculator")
public class CalculatorWebService extends AvailableService implements Calculator{

	@Override
	public double executeOperation(double number1, double number2) {
		// TODO Auto-generated method stub
		return 0;
	}

}
