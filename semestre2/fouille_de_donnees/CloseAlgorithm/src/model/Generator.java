package model;

import java.text.DecimalFormat;
import java.util.Set;
import java.util.TreeSet;

/**
 * Classe qui represente un generateur.
 * @author  ahounou & ayadi
 * @date  22/02/2013 - 11/03/2013.
 * 
 */
public class Generator{
	/**
	 * Liste de candidats du generateur.
	 */
	private Set<String> candidates;
	/**
	 * Fermeture du generateur.
	 */
	private Set<String> closure;
	/**
	 * Support du generateur.
	 */
	private double support;
	
	/**
	 * Initialise les donnees d'un generateur.
	 */
	public Generator(){
		candidates = new TreeSet<String>();
		closure = new TreeSet<String>();
		support = 0;
	}
	
	/**
	 * Construit un generateur juste avec la liste des candidats.
	 * @param generator liste de candidats du generateur.
	 */
	public Generator(Set<String> generator){
		this.candidates = generator;
		closure = new TreeSet<String>();
		support = 0;
	}

	/**
	 * Retourne la liste des candidats du generateur.
	 * @return  generator.
	 */
	public Set<String> getCandidates() {
		return candidates;
	}
	
	/**
	 * Modifie la liste des candidats du  generateur.
	 * @param candidates  nouveau generateur.
	 */
	public void setCandidates(Set<String> candidates) {
		this.candidates = candidates;
	}
	
	/**
	 * Retourne la fermeture du generateur.
	 * @return  closure;
	 */
	public Set<String> getClosure() {
		return closure;
	}
	
	/**
	 * Modifie la fermeture du generateur.
	 * @param closure  nouvelle fermeture.
	 */
	public void setClosure(Set<String> closure) {
		this.closure = closure;
	}
	
	/**
	 * Retourne le support du generateur.
	 * @return  support.
	 */
	public double getSupport() {
		return support;
	}
	
	/**
	 * Modifie le support du generateur.
	 * @param support  nouveau support du generateur.
	 */
	public void setSupport(double support) {
		DecimalFormat df = new DecimalFormat();
		df.setMaximumFractionDigits(2); //arrondi a 2 chiffres apres la virgules
		
		String str = df.format(support);
		support = Double.parseDouble(str.replace(',', '.')); 
		
		this.support =  support;
	}
	
	/**
	 * Compare deux generateurs.
	 * @return true quand les deux generateurs sont egaux, false sinon.
	 */
	public boolean equals(Object o){
		boolean result = false;
		Generator otherGenerator = (Generator)o;
		// si les deux generateurs n'ont pas la meme taille
		if(this.getCandidates().size() != otherGenerator.getCandidates().size()){
			result = false;
		}else{
			// s'ils ont la meme taille et otherGenerator est inclus dans le generateur courant
			if(this.contains(otherGenerator)){
				result = true;
			}
		}
		return result;
	}
	
	/**
	 * Verifie si le generateur en parametre est inclus dans le generateur courant.
	 * @param o instance de generateur
	 * @return true si le generateur en paramatre est inclus dans le generateur courant, false
	 * 	sinon.
	 */
	public boolean contains(Object o){
		boolean result = false;
		if (o instanceof Generator){
			Generator otherGenerator = (Generator)o;
			if(this.getCandidates().containsAll(otherGenerator.getCandidates()))
				result = true;
		}
		return result;
	}
	
	/**
	 * Ajoute un nouveau candidat dans la liste des candidats du generateur.
	 * @param candidate nouveau candidat a ajouter.
	 */
	public void addCandidate(String candidate){
		candidates.add(candidate);
	}
	
	/**
	 * Affiche les donnees d'un generateur.
	 */
	public String toString(){
		String print = "{";
		
		for(String c : candidates){
			print += c;
		}
		print += "}|{";
		
		for(String c : closure){
			print += c;
		}
		print += "}|";
		
		print += support +"\n";
		
		return print;
	}
	
}
