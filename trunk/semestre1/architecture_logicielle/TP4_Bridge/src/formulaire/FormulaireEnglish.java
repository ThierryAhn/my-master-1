package formulaire;

/**
 * Class FormulaireEnglish to have a form in english
 * @author ahounfol
 * @date 23/10/12
 *
 */
public class FormulaireEnglish extends Formulaire{

	public FormulaireEnglish(IFormulaire formulaire, String companyName) {
		super(formulaire, companyName);
	}

	@Override
	public String showNameLine() {
		return "Name : " + formulaire.showNameLine();
	}

	@Override
	public String showFirstNameLine() {
		// TODO Auto-generated method stub
		return "First Name : " + formulaire.showFirstNameLine();
	}

	@Override
	public String showEmailLine() {
		// TODO Auto-generated method stub
		return "Email : " + formulaire.showEmailLine();
	}

	@Override
	public String send() {
		// TODO Auto-generated method stub
		return	super.showTitleHeader("Company") + "\n" +
							showNameLine() + "\n" +
							showFirstNameLine() + "\n" +
							showEmailLine();
	}
	

}
