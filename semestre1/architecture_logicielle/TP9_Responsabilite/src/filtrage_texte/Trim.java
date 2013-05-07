package filtrage_texte;

/**
 * Concrete class Trim 
 * @author ahounfol
 * @date 27/11/12
 *
 */
public class Trim extends Filter{

	public Trim(Filter successor) {
		super(successor);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String filter(String is) {
		// TODO Auto-generated method stub
		if(successor == null)
			return is;
		else
			return this.delegate(is.trim());
		
	}

}
