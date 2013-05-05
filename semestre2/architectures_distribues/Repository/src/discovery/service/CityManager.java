package discovery.service;

import java.net.MalformedURLException;
import java.util.List;

import javax.jws.WebService;

import discovery.citymanager.City;
import discovery.citymanager.CityNotFound;
import discovery.citymanager.Position;

@WebService
public interface CityManager {
	boolean addCity(City city);
	boolean removeCity(City city);
	List<City> getCities();
	City searchExactPosition(Position position) throws CityNotFound;
	List<City> searchNear(Position position);
	List<City> searchFor(String cityName) throws MalformedURLException, ServiceNotFound, Exception;
	List<City> searchForLocalCity(String cityName);
}
