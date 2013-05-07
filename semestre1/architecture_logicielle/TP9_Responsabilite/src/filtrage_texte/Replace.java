package filtrage_texte;

public class Replace extends Filter{

	public Replace(Filter successor) {
		super(successor);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String filter(String is) {
		// TODO Auto-generated method stub
		String s = is.replaceAll("toto", "momo");
		if(successor == null)
			return is;
		else
			return this.delegate(s);
	}

}
