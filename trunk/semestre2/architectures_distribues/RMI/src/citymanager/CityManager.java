package citymanager;

import java.util.LinkedList;
import java.util.List;

import javax.jws.WebService;
import javax.management.Attribute;
import javax.management.AttributeList;
import javax.management.AttributeNotFoundException;
import javax.management.DynamicMBean;
import javax.management.InvalidAttributeValueException;
import javax.management.MBeanAttributeInfo;
import javax.management.MBeanException;
import javax.management.MBeanInfo;
import javax.management.MBeanOperationInfo;
import javax.management.ReflectionException;

/**
 * This class represent a city manager, it can  
 * <ul>
 * 	<li>add a city</li>
 * 	<li>remove a city</li>
 * 	<li>return the list of cities</li>	
 * 	<li>search a city with a given name</li>
 *  <li>search a city at a position</li>
 * 	<li>return the list of cities near 10km of the given position</li>
 * </ul>
 * 
 * @author Folabi & Caron & Saval.
 * @date 20/02/2013.
 * 
 */
@WebService(endpointInterface = "tp.model.CityManagerService", serviceName = "CityManagerService")
public class CityManager implements DynamicMBean{
	
	/**
	 * List of the city.
	 */
	private List<City> cities;
	
	/**
	 * Constructor.
	 * Initializes the list of cities.
	 */
	public CityManager() {
		this.cities = new LinkedList<City>();
	}
	
	/**
	 * Return the list of cities.
	 * @return cities.
	 */
	public List<City> getCities() {
		return cities;
	}
	
	/**
	 * Set a new list of cities.
	 * @param cities list of new cities.
	 */
	public void setCities(List<City> cities) {
		this.cities = cities;
	}
	
	/**
	 * Add a city.
	 * @param city new city to be added.
	 * @return true if the city is well added.
	 */
	public boolean addCity(City city){
		return cities.add(city);
	}
	
	/**
	 * Remove a city.
	 * @param city city to removed.
	 * @return true if the city is well removed.
	 */
	public boolean removeCity(City city){
		return cities.remove(city);
	}
	
	/**
	 * Return the city at a position.
	 * @param position position of the city.
	 * @return the city at a position.
	 * @throws CityNotFound if there is not a city at the position.
	 */
	public City searchExactPosition(Position position) throws CityNotFound{
		for(City city:cities){
			if (city.getPosition().equals(position)){
				return city;
			}
		}
		throw new CityNotFound("Aucune ville n'existe a la position : " +position);
	}
	
	/**
	 * Return the list of cities near 10km of the given position.
	 * @param position position which helps to search the cities near 10km.
	 * @return the list of cities near 10km of the given position.
	 */
	public List<City> searchNear(Position position){
		List<City> listCities = new LinkedList<City>();
		for(City city : cities){
			double distance = distanceBetween(position, city.getPosition());
			if(distance <= 10.0){
				listCities.add(city);
			}
		}
		return listCities;
	}
	
	/**
	 * Return the distance in km between two postions.
	 * @param p1 the first position.
	 * @param p2 the second position.
	 * @return the distance in km between two postions.
	 */
	public double distanceBetween(Position p1, Position p2) {
	    double earthRadius = 3958.75;
	    
	    double latitude = Math.toRadians(p2.getLatitude() - p1.getLatitude()) / 2;
	    double longitude = Math.toRadians(p2.getLongitude() - p1.getLongitude()) / 2;
	    
	    double sinusLatitude = Math.sin(latitude);
	    double sindLongitude = Math.sin(longitude);
	    
	    double a = Math.pow(sinusLatitude, 2) + Math.pow(sindLongitude, 2)
	            * Math.cos(Math.toRadians(p1.getLatitude())) 
	            * Math.cos(Math.toRadians(p2.getLatitude()));
	    double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
	    double distance = earthRadius * c;
	    double kmConversion = 1.609;
	    
	    return Math.abs(distance * kmConversion);
	}
	
	/**
	 * Return a list of cities with the name in parameter.
	 * @param cityName name of the city.
	 * @return the list of cities with the name in parameter.
	 */
	public List<City> searchFor(String cityName){
		
		List<City> listCities = new LinkedList<City>();
		for(City city : cities){
			if(city.getName().trim().equals(cityName)){
				listCities.add(city);
			}
		}
		
		return listCities;
	}

	@Override
	public Object getAttribute(String arg0) throws AttributeNotFoundException,
			MBeanException, ReflectionException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AttributeList getAttributes(String[] arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MBeanInfo getMBeanInfo() {
		MBeanAttributeInfo[] attrs = new MBeanAttributeInfo[0];
		MBeanOperationInfo[] opers = new MBeanOperationInfo[1];
		
		try {
			opers[0] = new MBeanOperationInfo("addCity", CityManager.class.getMethod("addCity", City.class));
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
		
		return new MBeanInfo(getClass().getName(), "City Manager MBean", attrs, null, opers, null);
	}

	@Override
	public Object invoke(String actionName, Object[] params, String[] signature)
			throws MBeanException, ReflectionException {
		if("addCity".equals(actionName) && params.length == 1 && params[0] instanceof City){
			return this.addCity((City)params[0]);
		}
		return null;
	}

	@Override
	public void setAttribute(Attribute arg0) throws AttributeNotFoundException,
			InvalidAttributeValueException, MBeanException, ReflectionException {
	
	}

	@Override
	public AttributeList setAttributes(AttributeList arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
