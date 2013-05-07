package filtrage_texte;

public class Numberize extends Filter{
	private int i =0;
	
	public Numberize(Filter successor) {
		super(successor);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String filter(String is) {
		// TODO Auto-generated method stub
		
		
		String s = "";
		
		i = i+1;
		s+= i+"\t" +is;
			
		if(successor == null)
			return is;
		else
			return this.delegate(s);
	}

}
