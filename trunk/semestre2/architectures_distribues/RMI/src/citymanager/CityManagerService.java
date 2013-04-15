package citymanager;

import java.util.List;

import javax.jws.WebService;

@WebService
public interface CityManagerService{
	boolean addCity(City city);
	boolean removeCity(City city);
	List<City> getCities();
	City searchExactPosition(Position position) throws CityNotFound;
	List<City> searchNear(Position position);
	List<City> searchFor(String cityName);
}
