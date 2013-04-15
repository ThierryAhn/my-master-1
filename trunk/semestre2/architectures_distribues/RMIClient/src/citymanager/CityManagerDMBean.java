package citymanager;

import java.util.List;

public interface CityManagerDMBean {
	boolean addCity(City city);
	List<City> getCities();
}
