package filtrage_texte;

/**
 * Abstract class Filter 
 * @author ahounfol
 * @date 27/11/12
 *
 */
public abstract class Filter implements IFilter {
	protected Filter successor;
	
	public Filter(Filter successor){
		this.successor = successor;
	}
	
	public void setSuccessor(Filter filter){
		this.successor = filter;
	}
	
	public String delegate(String is){
		if(successor == null)
			return is;
		else
			return successor.filter(is);
	}
	
}
