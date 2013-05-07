package formulaire;

/**
 * Class FormulaireEnglish to have a form in french
 * @author ahounfol
 * @date 23/10/12
 *
 */
public class FormulaireFrancais extends Formulaire {

	public FormulaireFrancais(IFormulaire formulaire, String companyName) {
		super(formulaire, companyName);
		
	}

	@Override
	public String showNameLine() {
		return "Nom : " + formulaire.showNameLine();
	}

	@Override
	public String showFirstNameLine() {
		// TODO Auto-generated method stub
		return "Prenom : " + formulaire.showFirstNameLine();
	}

	@Override
	public String showEmailLine() {
		// TODO Auto-generated method stub
		return "Email : " + formulaire.showEmailLine();
	}

	@Override
	public String send() {
		// TODO Auto-generated method stub
		return	super.showTitleHeader("Compagnie") + "\n" +
							showNameLine() + "\n" +
							showFirstNameLine() + "\n" +
							showEmailLine();
	}

}
