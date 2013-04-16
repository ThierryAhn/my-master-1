package tp.model;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * This class represents an exception class.
 * It helps to handle an exception when a city is not found.
 * 
 * @author Caron & Saval.
 * @date 20/02/2013.
 *
 */
@SuppressWarnings("serial")
@XmlRootElement
public class CityNotFound extends Exception {
	
	public CityNotFound(){
	}
}
