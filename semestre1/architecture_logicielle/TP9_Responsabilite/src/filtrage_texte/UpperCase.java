package filtrage_texte;

public class UpperCase extends Filter{

	public UpperCase(Filter successor) {
		super(successor);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String filter(String is) {
		// TODO Auto-generated method stub
		if(successor == null)
			return is;
		else
			return this.delegate(is.toUpperCase());
	}

}
