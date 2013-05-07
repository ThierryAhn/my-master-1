package formulaire;

/**
 * Abstract class Formulaire
 * @author ahounfol
 * @date 23/10/12
 *
 */
public abstract class Formulaire {
	
	/**
	 * The form
	 */
	protected IFormulaire formulaire;
	/**
	 * Company name
	 */
	private String companyName;
	
	/**
	 * Constructor with two parameters
	 * @param formulaire, the type of the form
	 * @param companyName, the name of the company
	 */
	public Formulaire(IFormulaire formulaire, String companyName){
		this.formulaire = formulaire;
		this.companyName = companyName;
	}
	
	/**
	 * Display the company name and the form title.
	 * @param formTitle the form title.
	 * return companyName and formTitle
	 */
	public String showTitleHeader(String formTitle){
		return companyName + ", " +formTitle;
	}
	
	
	public abstract String showNameLine();
	public abstract String showFirstNameLine();
	public abstract String showEmailLine();
	public abstract String send(); 
	
	
}
