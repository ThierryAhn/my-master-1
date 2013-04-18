package rmi;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * This class represent a city with its
 * <ul>
 * 	<li>name</li>
 * 	<li>latitude</li>
 * 	<li>longitude</li>
 * 	<li>country</li>
 * </ul>
 * @author Caron & Saval.
 * @date 20/02/2013.
 * 
 */
@XmlRootElement
public class City implements Serializable{

	private String name;
	private Position location;
	private String country;
	
	/**
	 * Creates a city with its name, its latitude, its longitude and its country
	 * @param name the name of the city
	 * @param latitude the latitude of the city in WGS84
	 * @param longitude the longitude of the city in WGS84
	 * @param country the country of the city
	 */
	public City(String name, double latitude, double longitude, String country) {
		this.name = name;
		this.location = new Position(latitude,longitude);
		this.country = country;
	}
	
	/**
	 * Default constructor.
	 */
	public City() {
	}
	
	/**
	 * Return the position of the city.
	 * @return location.
	 */
	public Position getPosition() {
		return location;
	}
	
	/**
	 * Set a position to the city.
	 * @param position new position.
	 */
	public void setPosition(Position position) {
		this.location = position;
	}
	
	/**
	 * Return the name of the city.
	 * @return name.
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Set a name to the city.
	 * @param name new aname.
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Return the country of the city.
	 * @return country.
	 */
	public String getCountry() {
		return country;
	}
	
	/**
	 * Set a country to the city.
	 * @param country new county.
	 */
	public void setCountry(String country) {
		this.country = country;
	}
	
	/**
	 * Compare two cities.
	 */
	public boolean equals(Object o){
		boolean result = false;
		
		if(o instanceof City){
			City otherCity = (City)o;
			result = this.getName().equals(otherCity.getName()) && 
			this.getCountry().equals(otherCity.getCountry()) &&
			this.getPosition().equals(otherCity.getPosition());
		}
		
		return result;
	}
	
	
	/**
	 * Print city data.
	 */
	public String toString(){
		final StringBuffer buffer = new StringBuffer();
		buffer.append(name).append(" in ").append(country).append(" at ").append(location);
		return buffer.toString();
	}
	
}
