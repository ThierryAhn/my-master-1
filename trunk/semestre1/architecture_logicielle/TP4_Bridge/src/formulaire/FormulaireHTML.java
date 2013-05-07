package formulaire;

/**
 * Class FormulaireHTML which implements IFormulaire
 * @author ahounfol
 * @date 23/10/12.
 *
 */
public class FormulaireHTML implements IFormulaire{

	@Override
	public String showNameLine() {
		// TODO Auto-generated method stub
		return "<input type=\"text\"></input>";
	}

	@Override
	public String showFirstNameLine() {
		// TODO Auto-generated method stub
		return "<input type=\"text\"></input>";
	}

	@Override
	public String showEmailLine() {
		// TODO Auto-generated method stub
		return "<input type=\"text\"></input>";
	}
}
