package model;



/**
 * Classe qui represente un generateur avec une regle exacte.
 * @author  ahounou & ayadi
 * @date 20/03/2013 - 25/03/2013.
 *
 */
public class GeneratorBG extends Generator {
	/**
	 * Regle du generateur.
	 */
	private String rule;
	
	/**
	 * Constructeur.
	 * @param generator
	 */
	public GeneratorBG(Generator generator) {
		this.setCandidates(generator.getCandidates());
		this.setClosure(generator.getClosure());
		this.setSupport(generator.getSupport());
		rule = "";
	}
	
	/**
	 * Retourne la regle du generateur.
	 * @return rule
	 */
	public String getRule(){
		return rule;
	}
	
	/**
	 * Modifie la regle du generateur.
	 * @param rule nouvelle regle.
	 */
	public void setRule(String rule){
		this.rule = rule;
	}
	
	
}
