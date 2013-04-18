package rmi;

import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.jws.WebService;
import javax.management.Attribute;
import javax.management.AttributeChangeNotification;
import javax.management.AttributeList;
import javax.management.AttributeNotFoundException;
import javax.management.DynamicMBean;
import javax.management.InvalidAttributeValueException;
import javax.management.MBeanAttributeInfo;
import javax.management.MBeanException;
import javax.management.MBeanInfo;
import javax.management.MBeanOperationInfo;
import javax.management.Notification;
import javax.management.NotificationBroadcasterSupport;
import javax.management.ReflectionException;
import javax.management.RuntimeOperationsException;

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
public class CityManager extends NotificationBroadcasterSupport implements DynamicMBean, CityManagerDMBean, Serializable{
	
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
	public synchronized List<City> getCities() {
		sendNotification(new Notification("getCities", cities, System.currentTimeMillis(), "Print all cities"));
		return cities;
	}
	
	/**
	 * Set a new list of cities.
	 * @param cities list of new cities.
	 */
	public synchronized void setCities(List<City> cities) {
		sendNotification(new Notification("setCities", cities, System.currentTimeMillis(), "Setting a list of cities"));
		this.cities = cities;
	}
	
	/**
	 * Add a city.
	 * @param city new city to be added.
	 * @return true if the city is well added.
	 */
	public synchronized boolean addCity(City city){
		sendNotification(new Notification("addCity", city, System.currentTimeMillis(), "Adding of a new city"));
		if(!cities.contains(city))
			return cities.add(city);
		
		return false;
	}
	
	/**
	 * Remove a city.
	 * @param city city to removed.
	 * @return true if the city is well removed.
	 */
	public synchronized boolean removeCity(City city){
		sendNotification(new Notification("removeCity", city, System.currentTimeMillis(), "Removing a city"));
		return cities.remove(city);
	}
	
	/**
	 * Remove all cities.
	 * @return true if all the cities are well removed.
	 */
	public synchronized boolean removeAllCities(){
		sendNotification(new Notification("removeAllCities", cities, System.currentTimeMillis(), "Delete all cities"));
		return cities.removeAll(cities);
	}
	
	/**
	 * Return the city at a position.
	 * @param position position of the city.
	 * @return the city at a position.
	 * @throws CityNotFound if there is not a city at the position.
	 */
	public synchronized City searchExactPosition(Position position) throws CityNotFound{
		for(City city:cities){
			if (city.getPosition().equals(position)){
				sendNotification(new Notification("searchExactPosition", position, System.currentTimeMillis(), 
						"Searching a city at an exact position : "+city));
				
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
	public synchronized List<City> searchNear(Position position){
		List<City> listCities = new LinkedList<City>();
		for(City city : cities){
			double distance = distanceBetween(position, city.getPosition());
			if(distance <= 10.0){
				listCities.add(city);
			}
		}
		sendNotification(new Notification("searchNear", position, System.currentTimeMillis(), 
				"Searching cities near the position : "+listCities));
		
		return listCities;
	}
	
	/**
	 * Return the distance in km between two postions.
	 * @param p1 the first position.
	 * @param p2 the second position.
	 * @return the distance in km between two postions.
	 */
	private double distanceBetween(Position p1, Position p2) {
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
	public synchronized List<City> searchFor(String cityName){
		
		List<City> listCities = new LinkedList<City>();
		for(City city : cities){
			if(city.getName().trim().equals(cityName)){
				listCities.add(city);
			}
		}
		sendNotification(new Notification("searchFor", cityName, System.currentTimeMillis(), 
				"Searching for cities with specific name : "+listCities));
		
		return listCities;
	}

	@Override
	public Object getAttribute(String attributeName) throws AttributeNotFoundException,
			MBeanException, ReflectionException {
		
		// check attribute name to avoid NullPointerException
		if(attributeName == null){
			throw new RuntimeOperationsException(new IllegalArgumentException("Attribute name cannot be null"), 
		            "Cannot invoke a getter of " + this.getClass().getName() +
		                " with null attribute name");
		}
		
		// attribute cities recognized
		if(attributeName.equals("Cities")){
			return getCities();
		}
		
		// if attribute name not recognized
		throw(new AttributeNotFoundException("Cannot find " + attributeName + " attribute in " + this.getClass().getName()));
	}

	@Override
	public AttributeList getAttributes(String[] attributeNames) {
		// Check attribute names to avoid NullPointerException
	    if (attributeNames == null) {
	        throw new RuntimeOperationsException(new IllegalArgumentException("attributeNames[] cannot be null"),
	            "Cannot invoke a getter of " + this.getClass().getName());
	    }
	    
	    AttributeList attributeList = new AttributeList();
	    
	    // if attributeNames is empty, return an empty result list
	    if (attributeNames.length == 0)
	            return attributeList;
	       
	    for(int i = 0 ; i < attributeNames.length ; i++){
	        try {        
	            Object value = getAttribute((String) attributeNames[i]);     
	            attributeList.add(new Attribute(attributeNames[i], value));
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	    
	    return attributeList;
	}

	@Override
	public MBeanInfo getMBeanInfo() {
		MBeanAttributeInfo[] attrs = new MBeanAttributeInfo[0];
		MBeanOperationInfo[] opers = new MBeanOperationInfo[6];
		
		try {
			opers[0] = new MBeanOperationInfo("addCity", CityManager.class.getMethod("addCity", City.class));
			opers[1] = new MBeanOperationInfo("removeAllCities", CityManager.class.getMethod("removeAllCities"));
			opers[2] = new MBeanOperationInfo("removeCity", CityManager.class.getMethod("removeCity", City.class));
			opers[3] = new MBeanOperationInfo("searchExactPosition", CityManager.class.getMethod("searchExactPosition", Position.class));
			opers[4] = new MBeanOperationInfo("searchNear", CityManager.class.getMethod("searchNear", Position.class));
			opers[5] = new MBeanOperationInfo("searchFor", CityManager.class.getMethod("searchFor", String.class));
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
		
		// addCity
		if("addCity".equals(actionName) && params.length == 1 && params[0] instanceof City){
			return this.addCity((City)params[0]);
		}
		
		// removeAllCities
		if("removeAllCities".equals(actionName) && params.length == 0){
			return this.removeAllCities();
		}
		
		// removeCity
		if("removeCity".equals(actionName) && params.length == 1 && params[0] instanceof City){
			return this.removeCity((City)params[0]);
		}
		
		// searchExactPosition
		if("searchExactPosition".equals(actionName) && params.length == 1 && params[0] instanceof Position){
			try {
				return this.searchExactPosition((Position)params[0]);
			} catch (CityNotFound e) {
				System.out.println("Exception : " +e.getMessage());
			}
		}
		
		// searchNear
		if("searchNear".equals(actionName) && params.length == 1 && params[0] instanceof Position){
			return this.searchNear((Position)params[0]);
		}
		
		// searchFor
		if("searchFor".equals(actionName) && params.length == 1 && params[0] instanceof String){
			return this.searchFor((String)params[0]);
		}
		
		return null;
	}

	@Override
	public void setAttribute(Attribute 	attributeName) throws AttributeNotFoundException,
			InvalidAttributeValueException, MBeanException, ReflectionException {
		
		// check attribute name to avoid NullPointerException
		if(attributeName == null){
			throw new RuntimeOperationsException(new IllegalArgumentException("Attribute name cannot be null"), 
					"Cannot invoke a setter of " + this.getClass().getName() +
				                " with null attribute name");
		}
		
		// attribute cities recognized
		if(attributeName.getName().equals("Cities")){
			// if attribute value is null
			if(attributeName.getValue() == null){
				try{
					setCities(null);
				}catch(Exception e){
					throw (new InvalidAttributeValueException("Cannot set attribute "+ attributeName.getName() +" to null"));
				}
			// if not null value, we ensure that the value is assignable to the attribute
			}else{
				try{
					// if assignable
	                if ((Class.forName("java.util.List")).isAssignableFrom(attributeName.getValue().getClass())) {
	                	System.out.println("jsui la");
	                	setCities((List<City>) attributeName.getValue());
	                }
	                // if not assignable
	                else {
	                    throw(new InvalidAttributeValueException("Cannot set attribute "+ attributeName.getName() +
	                            " to a " + attributeName.getValue().getClass().getName() +
	                            " object, List<City> expected"));
	                }
	            } catch (ClassNotFoundException e) {
	                e.printStackTrace();
	            }
			}
		// unrecoginized attribute name
		}else{
			throw(new AttributeNotFoundException("Attribute " + attributeName.getName() + " not found in " + this.getClass().getName()));
		}
	}

	@Override
	public AttributeList setAttributes(AttributeList attributes) {
		// Check attributesto avoid NullPointerException later on
	    if (attributes == null) {
	        throw new RuntimeOperationsException(new IllegalArgumentException("AttributeList attributes cannot be null"),
	            "Cannot invoke a setter of " + this.getClass().getName());
	    }
	    AttributeList attributeList = new AttributeList();

	    // if attributeNames is empty
	    if (attributes.isEmpty())
	        return attributeList;

	    // try to set each attribute and add to result list if successful
	    for (Iterator it = attributes.iterator(); it.hasNext();) {
	        Attribute attribute = (Attribute) it.next();
	        
	        try {
	            setAttribute(attribute);
	            String name = attribute.getName();
	            Object value = getAttribute(name); 
	            attributeList.add(new Attribute(name, value));
	        } catch(Exception e) {
	            e.printStackTrace();
	        }
	    }
	    return attributeList;
	}

	@Override
	public void handleNotification(Notification notification, Object arg1) {
		// TODO Auto-generated method stub
		System.out.println("\nReceived notification:");
		System.out.println("\tClassName: " + notification.getClass().getName());
        System.out.println("\tSource: " + notification.getSource());
        System.out.println("\tType: " + notification.getType());
        System.out.println("\tMessage: " + notification.getMessage());
        if (notification instanceof AttributeChangeNotification) {
            AttributeChangeNotification acn =
                (AttributeChangeNotification) notification;
            System.out.println("\tAttributeName: " + acn.getAttributeName());
            System.out.println("\tAttributeType: " + acn.getAttributeType());
            System.out.println("\tNewValue: " + acn.getNewValue());
            System.out.println("\tOldValue: " + acn.getOldValue());
        }
	}
	
	
}
