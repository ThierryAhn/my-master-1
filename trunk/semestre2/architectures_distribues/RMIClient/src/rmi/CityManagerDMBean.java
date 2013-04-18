package rmi;

import java.util.List;

public interface CityManagerDMBean {
	boolean addCity(City city);
	boolean removeCity(City city);
	boolean removeAllCities();
	void setCities(List<City> cities);
	List<City> getCities();
	City searchExactPosition(Position position) throws CityNotFound;
	List<City> searchNear(Position position);
	List<City> searchFor(String cityName);
}
