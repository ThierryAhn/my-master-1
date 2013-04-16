package tp.model;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * This class represents the position with its
 * <ul>
 * 	<li>latitude</li>
 * 	<li>longitude</li>
 * </ul>
 * 
 * @author Caron & Saval.
 * @date 20/02/2013.
 * 
 */
@XmlRootElement
public class Position {

	private double latitude,longitude;
	
	/**
	 * Creates a position with latitude and longitude.
	 * @param latitude the latitude used to create the position.
	 * @param longitude the longitude used to create the position.
	 */
	public Position(double latitude, double longitude) {
		this.latitude = latitude;
		this.longitude = longitude;
	}
	
	/**
	 * Default Constructor.
	 */
	public Position() {	}
	
	/**
	 * Compare two positions.
	 */
	public boolean equals(Object o){
		boolean result = false;
		if (o instanceof Position){
			Position otherPosition = (Position)o; 
			result = otherPosition.latitude == this.latitude && 
					 otherPosition.longitude == this.longitude;
		}
		return result;
	}
	
	/**
	 * Return the latitude.
	 * @return latitude
	 */
	public double getLatitude() {
		return latitude;
	}
	
	/**
	 * Set a new latitude.
	 * @param latitude new latitude.
	 */
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	
	/**
	 * Return the longitude.
	 * @return longitude.
	 */
	public double getLongitude() {
		return longitude;
	}
	
	/**
	 * Set a new longitude.
	 * @param longitude new longitude.
	 */
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	
	/**
	 * Print position data.
	 */
	public String toString(){
		final StringBuffer buffer = new StringBuffer();
		buffer.append("(").append(latitude).append(", ").append(longitude).append(")");
		return buffer.toString();
	}
	
}
