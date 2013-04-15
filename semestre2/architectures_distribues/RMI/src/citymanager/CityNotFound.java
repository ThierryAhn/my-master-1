package citymanager;

import java.io.Serializable;

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
public class CityNotFound extends Exception implements Serializable{
	public CityNotFound(){}
	
	public CityNotFound(String s){
		super(s);
	}
}
