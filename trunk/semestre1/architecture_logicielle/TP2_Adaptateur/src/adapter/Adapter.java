package adapter;
import java.util.Enumeration;
import java.util.Iterator;

/**
 * Adapter for StringTokenizer.
 * @author ahounfol
 * @date 9/10/2012
 *
 */
public class Adapter implements Iterator<String>{
	
	/**
	 * Attribute enumeration
	 * @see Adaptator(Enumeration<Object> enumeration)
	 * @see hasNext()
	 * @see next()
	 */
	Enumeration<Object> enumeration; 
	
	public Adapter(Enumeration<Object> enumeration){
		this.enumeration = enumeration;
	}
	
	
	@Override
	public boolean hasNext() {
		return enumeration.hasMoreElements();
	}

	@Override
	public String next() {
		return enumeration.nextElement().toString();
	}

	@Override
	public void remove() {
		// this method is undefined in Enumeration
		throw new UnsupportedOperationException("Méthode non définie dans Enumeration");
	}
}
