package filtrage_texte;

public class SkipFilter extends Filter{
	private int p, n;
	private int compteur = 0;
	
	public SkipFilter(Filter successor, int p, int n) {
		super(successor);
		// TODO Auto-generated constructor stub
		this.p = p;
		this.n = n;
	}
	
	
	@Override
	public String filter(String is) {
		// TODO Auto-generated method stub
		String s ="";
		if(successor == null)
			return is;
		else{
			s+=is;
			
			return this.delegate(s);
			
		}
			
	}

}
