package model;

/**
 * Classe qui represente un generateur avec une regle approximative, un sur-ensemble ferme, 
 * une confiance, un lift et le support du sur-ensemble ferme .
 * @author ahounou & ayadi.
 * @date  20/03/2013 - 25/03/2013.
 *
 */
public class GeneratorRI extends Generator {
	/**
	 * Sur-ensemble ferme du generateur.
	 */
	private String superSetClosure;
	/**
	 * Regle du generateur.
	 */
	private String rule;
	/**
	 * Support du sur-ensemble ferme de generateur.
	 */
	private double superSetSupport;
	/**
	 * Confiance du generateur.
	 */
	private double confidence;
	/**
	 * Lift du generateur.
	 */
	private double lift;
	
	/**
	 * Constructeur.
	 * @param generator
	 */
	public GeneratorRI(Generator generator) {
		this.setCandidates(generator.getCandidates());
		this.setClosure(generator.getClosure());
		this.setSupport(generator.getSupport());
		superSetClosure ="";
		rule = "";
		superSetSupport = 0;
		confidence = 0;
		lift = 0;
	}
	
	/**
	 * Retourne le sur-ensemble ferme du generateur.
	 * @return superSetClosure.
	 */
	public String getSuperSetClosure() {
		return superSetClosure;
	}
	
	/**
	 * Modifie le sur-ensemble ferme du generateur.
	 * @param superSetClosure nouveau sur-ensemble ferme.
	 */
	public void setSuperSetClosure(String superSetClosure) {
		this.superSetClosure = superSetClosure;
	}
	
	/**
	 * Retourne la regle du generateur.
	 * @return rule.
	 */
	public String getRule() {
		return rule;
	}

	/**
	 * Modifie la regle du generateur.
	 * @param rule nouvelle regle.
	 */
	public void setRule(String rule) {
		this.rule = rule;
	}
	
	/**
	 * Retourne le support du sur-ensemble ferme du generateur.
	 * @return superSetSupport.
	 */
	public double getSuperSetSupport() {
		return superSetSupport;
	}
	
	/**
	 * Modifie le support du sur-ensemble ferme du generateur.
	 * @param superSetSupport nouveau support.
	 */
	public void setSuperSetSupport(double superSetSupport) {
		this.superSetSupport = superSetSupport;
	}
	
	/**
	 * Retourne la confiance du sur-ensemble ferme du generateur.
	 * @return confidence.
	 */
	public double getConfidence() {
		return confidence;
	}
	
	/**
	 * Modifie la confiance du sur-ensemble ferme du generateur.
	 * @param confidence nouvelle confiance.
	 */
	public void setConfidence(double confidence) {
		this.confidence = confidence;
	}
	
	/**
	 * Retourne le liftdu sur-ensemble ferme du generateur.
	 * @return lift.
	 */
	public double getLift() {
		return lift;
	}

	/**
	 * Modifie le lift du sur-ensemble ferme du generateur.
	 * @param lift nouveau lift.
	 */
	public void setLift(double lift) {
		this.lift = lift;
	}
}
