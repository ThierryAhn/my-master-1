package model;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * Classe qui permet de generer les regles exactes et approximatives.
 * @author ahounou & ayadi.
 * @date  25/02/2013 - 25/03/2013.
 * 
 */
public class BaseGeneration {
	/**
	 * Ensemble de k-groupes frequents des k-generateurs.
	 */
	private ArrayList<GeneratorManager> arrayFF;
	/**
	 * Ensemble des fermetures de base.
	 */
	private ContextClosure contextClosure;
	
	/**
	 * Liste des generateurs avec une regle exacte.
	 */
	private List<GeneratorBG> arrayGeneratorBG;
	
	/**
	 * Liste des generateurs avec une regle approximative.
	 */
	private List<GeneratorRI> arrayGeneratorRI;
	
	/**
	 * Constructeur.
	 * @param arrayFF liste des FFk
	 * @param contextClosure ensemble des fermetures de base.
	 */
	public BaseGeneration(ArrayList<GeneratorManager> arrayFF, ContextClosure contextClosure) {
		this.arrayFF = arrayFF;
		this.contextClosure = contextClosure;
		arrayGeneratorBG = new ArrayList<GeneratorBG>();
		arrayGeneratorRI = new ArrayList<GeneratorRI>();
	}
	
	/**
	 * Algorithme pour generer les regles d'association exactes.
	 */
	public void genBG(){
		for(GeneratorManager gm : arrayFF){
			for(Generator generator : gm.getArrayGenerator()){
				GeneratorBG generatorBG = new GeneratorBG(generator);
				// recuperation de la partie gauche de la regle du generateur.
				String ruleLeftPart = "";
				for(String c : generator.getCandidates())
					ruleLeftPart += c;
				
				// recuperation de la partie droite de la regle du generateur.
				String ruleRightPart = "";
				for(String c : generator.getClosure())
					ruleRightPart += c;
				ruleRightPart = removeString(ruleLeftPart, ruleRightPart);
				if(!ruleRightPart.isEmpty()){
					generatorBG.setRule(ruleLeftPart + " --> " +ruleRightPart);
				}
				// ajout du generateur dans la liste
				arrayGeneratorBG.add(generatorBG);
			}
		}
	}
	
	/**
	 * Algorithme pour generer les regles d'association approximatives.
	 */
	public void genRI(){
		for(GeneratorManager gm : arrayFF){
			for(Generator generator : gm.getArrayGenerator()){
				// calcul du sur ensemble ferme
				List<String> arrayClosure = new ArrayList<String>();
				arrayClosure = getSuperSetClosure(generator.getClosure());
				
				GeneratorRI generatorRI;
				
				// si la liste des sur-ensemble est vide on ne recupere que le nom du generateur
				// et sa fermeture
				if(arrayClosure.isEmpty()){
					generatorRI = new GeneratorRI(generator);
					arrayGeneratorRI.add(generatorRI);
				}
				else{
					for(String s : arrayClosure){
						generatorRI = new GeneratorRI(generator);
						
						String ruleLeftPart = "";
						for(String c : generator.getCandidates())
							ruleLeftPart += c;
						
						// sur-ensemble ferme
						generatorRI.setSuperSetClosure(s);
						
						// recuperation de la partie droite de la regle
						String ruleRightPart = removeString(ruleLeftPart, s);
						generatorRI.setRule(ruleLeftPart +" --> " +ruleRightPart);
						
						// recuperer le support de s dans contextClosure
						// treeset qui va nous permettre de calucler le support du sur-ensemble 
						// ferme
						Set<String> set = new TreeSet<String>();
						for(int i = 0; i < s.length(); i++){
							set.add(""+s.charAt(i));
						}
						
						int countClosure = contextClosure.getArrayClosure().size();
						// on construit un generateur avec les candidats du sur-ensemble ferme
					    // avec ce generateur on pourra determiner son support
						Generator genSet = new Generator(set);
						
						double supportValue = (double)getSupport(genSet)/countClosure;
						generatorRI.setSuperSetSupport(this.formatDouble(supportValue));
						
						double confidenceValue = supportValue / generator.getSupport();
						generatorRI.setConfidence(this.formatDouble(confidenceValue));
						
						// calcul de lift
						// creation de generateur avec la deuxieme partie de la regle
						set = new TreeSet<String>();
						for(int i = 0; i < ruleRightPart.length(); i++){
							set.add(""+ruleRightPart.charAt(i));
						}
						genSet = new Generator(set);
						double supportGenSet = (double)getSupport(genSet)/countClosure;
						double liftValue = supportValue / (generator.getSupport() * supportGenSet);
						generatorRI.setLift(this.formatDouble(liftValue));
						arrayGeneratorRI.add(generatorRI);
					}
				}
			}
		}
	}
	
	/**
	 * Arrondit un double avec deux chiffres apres la virgule.
	 * @param valueToFormat valeur a arrondire.
	 * @return un double arrondi avec deux chiffres apres la virgule.
	 */
	public double formatDouble(double valueToFormat){
		DecimalFormat df = new DecimalFormat();
		df.setMaximumFractionDigits(2); //arrondi a 2 chiffres apres la virgules
		
		String str = df.format(valueToFormat);
		double valueFormat = Double.parseDouble(str.replace(',', '.'));
		
		return valueFormat;
	}
	
	/**
	 * Retourne l'ensemble des sur-ensemble ferme d'une fermeture passee en parametre.
	 * @param closure fermeture dont il faut retourner l'ensemble des sur-ensemble ferme.
	 * @return une liste contenant les sur-ensemble ferme d'une fermeture.
	 */
	public List<String> getSuperSetClosure(Set<String> closure){
		
		Generator temp = new Generator(closure);
		int size = temp.getCandidates().size();
		// recuperation de toutes les fermetures de tous les ff
		List<String> arrayClosure = new ArrayList<String>();
		for(GeneratorManager generatorManager : arrayFF){
			for(Generator generator : generatorManager.getArrayGenerator()){
				Generator temp2 = new Generator(generator.getClosure());
				if(temp2.contains(temp) && (temp2.getCandidates().size() > size)){
					String tempClosure = "";
					for(String s : temp2.getCandidates()){
						tempClosure += s;
					}
					// enregistrement des sur-ensemble ferme du generateur courant
					if(!arrayClosure.contains(tempClosure)){
						arrayClosure.add(tempClosure);
					}
				}
			}
		}
		return arrayClosure;
	}
	
	/**
	 * Retourne la partie droite d'une regle.
	 * @param s1 partie gauche de la regle.
	 * @param s2 sur-ensemble ferme.
	 * @return la partie droite d'une regle.
	 */
	public String removeString(String s1, String s2){
		String s = s2;
		for(int i = 0; i < s1.length(); i++){
			String temp = ""+s1.charAt(i);
			s = s.replaceAll(temp, "");
		}
		return s;
	}
	
	/**
	 * Retourne le support d'un generateur en terme absolu. 
	 * @param generator generateur dont il faut retourner le support.
	 * @return le support d'un generateur en terme absolu. 
	 */
	public int getSupport(Generator generator){
		int support = 0;
		for(Generator gen : contextClosure.getArrayClosure()){
			if(gen.contains(generator))
				support += 1;
		}
		return support;
	}
	
	/**
	 * Affiche la trace des regles exactes.
	 * @return
	 */
	public String traceBG(){
		int generatorMaxSize = arrayFF.get(arrayFF.size()-1).generatorMaxSize;
		int closureMaxSize = arrayFF.get(arrayFF.size()-1).closureMaxSize;
		int ruleMaxSize = arrayFF.get(arrayFF.size()-1).generatorMaxSize;
		
		// maj de ruleMaxSize si sa taille est inferieur a : 11 (taille de |Exact Rule)
		if(ruleMaxSize < 11){
			ruleMaxSize = 11;
		}
		
		String barre = "----------";
		for(int i = 1; i <= generatorMaxSize+3; i++){
			barre +="-";
		}
		for(int i = 1; i <= closureMaxSize+3; i++){
			barre +="-";
		}
		// exact rule
		for(int i = 1; i <= ruleMaxSize+5; i++){
			barre +="-";
		}
		
		String print = "Exact Rules \n" +barre +"\n";
		print += "|Generator";
		for(int i = 10; i <= (generatorMaxSize + 2); i++)
			print +=" ";
		
		print += "|Closure";
		for(int i = 8; i <= (closureMaxSize + 2); i++)
			print +=" ";
		
		print += "|Exact Rule";
		for(int i = 11; i <= (ruleMaxSize + 5); i++)
			print +=" ";
		
		print += "|Support|\n";
		print += barre +"\n";
		
		for(GeneratorBG generator : arrayGeneratorBG){
			String gen = "";
			String closure = "";
			String rule = "";
			
			gen += "|{";
			// recuperation du generateur
			for(String c : generator.getCandidates()){
				gen += c;
			}
			gen += "}";
			
			closure += "|{";
			// recuperation de la fermeture du generateur
			for(String c : generator.getClosure()){
				closure += c;
			}
			closure += "}";
			
			// boucle pour avoir un beau affichage, +2 parce qu'on rajoute {} au generateur
			for(int i = gen.length(); i <=(generatorMaxSize+2); i++ ){
				gen += " ";
			}
			print += gen;
			
			// boucle pour avoir un beau affichage, +2 parce qu'on rajoute {} a la fermeture
			for(int i = closure.length(); i <=(closureMaxSize+2); i++ ){
				closure += " ";
			}
			print += closure;
			
			// recuperation de la regle
			if(generator.getRule().equals("")){
				rule = "|";
			}else{
				rule = "|" +generator.getRule();
			}
			// boucle pour avoir un beau affichage, +5 parce qu'on rajoute _-->_ a la regle
			for(int i = rule.length(); i <= (ruleMaxSize + 5); i++)
				rule+= " ";
			
			print += rule;
			
			// recuperation du support
			String support = "";
			if(rule.contains("-->"))
				support = "" +generator.getSupport();
			else
				support = "";
			
			for(int i = support.length(); i < 7; i++)
				support += " ";
			print += "|" + support +"|" +"\n"+barre +"\n";
			
		}
		print += "\n______\n\n";
		return print;
	}
	
	/**
	 * Affiche la trace des regles approximatives.
	 * @return
	 */
	public String traceRI(){
		int generatorMaxSize = arrayFF.get(arrayFF.size()-1).generatorMaxSize;
		int closureMaxSize = arrayFF.get(arrayFF.size()-1).closureMaxSize;
		int closureMaxSize2 = arrayFF.get(arrayFF.size()-1).closureMaxSize;
		int ruleMaxSize = arrayFF.get(arrayFF.size()-1).generatorMaxSize;
		
		// maj de ruleMaxSize si sa taille est inferieur a : 20 (taille de |Approximatives Rules)
		if(ruleMaxSize < 20){
			ruleMaxSize = 20;
		}
		
		// maj de closureMaxSize2 si sa taille est inferieur a : 17 
		// (taille de |Superset Closure)
		if(closureMaxSize2 < 17){
			closureMaxSize2 = 17;
		}
		
		String barre = "--------------------------";
		for(int i = 1; i <= generatorMaxSize+3; i++){
			barre +="-";
		}
		for(int i = 1; i <= closureMaxSize+3; i++){
			barre +="-";
		}
		// sur ensemble ferme
		for(int i = 1; i <= closureMaxSize2+3; i++){
			barre +="-";
		}
		// approximative rule
		for(int i = 1; i <= ruleMaxSize+5; i++){
			barre +="-";
		}
		
		String print = "Approximatives Rules \n" +barre +"\n";
		print += "|Generator";
		for(int i = 10; i <= (generatorMaxSize + 2); i++)
			print +=" ";
		
		print += "|Closure";
		for(int i = 8; i <= (closureMaxSize + 2); i++)
			print +=" ";
		
		print += "|Superset Closure";
		for(int i = 17; i <= (closureMaxSize2 + 2); i++)
			print +=" ";
		
		print += "|Approximatives Rule";
		for(int i = 20; i <= (ruleMaxSize + 5); i++)
			print +=" ";
		
		print += "|Support|Confidence|Lift|\n";
		print += barre +"\n";
		
		for(GeneratorRI generator : arrayGeneratorRI){
			String gen = "";
			String closure = "";
			String closure2 = "";
			String rule = "";
			String support ="";
			String confidence = "";
			String lift = "";
			
			String ruleLeftPart = "";
			gen += "|{";
			// recuperation du generateur
			for(String c : generator.getCandidates()){
				gen += c;
				if(!generator.getSuperSetClosure().isEmpty()){
					ruleLeftPart += c;
				}
			}
			gen += "}";
			
			closure += "|{";
			// recuperation de la fermeture
			for(String c : generator.getClosure()){
				closure += c;
			}
			closure += "}";
			
			// boucle pour avoir un beau affichage, +2 parce qu'on rajoute {} au generateur
			for(int i = gen.length(); i <=(generatorMaxSize+2); i++ )
				gen += " ";
			print += gen;
				
			// boucle pour avoir un beau affichage, +2 parce qu'on rajoute {} a la fermeture
			for(int i = closure.length(); i <=(closureMaxSize+2); i++ )
				closure += " ";
			print += closure;
			
			if(generator.getSuperSetClosure().isEmpty()){
				closure2 = "|"; rule ="|"; support = ""; confidence =""; lift = "";
				
				for(int i = closure2.length(); i <=(closureMaxSize2+2); i++ )
					closure2 += " ";
				print += closure2;
				
				// boucle pour avoir un beau affichage, +5 parce qu'on rajoute _-->_ a la regle
				for(int i = rule.length(); i <= (ruleMaxSize + 5); i++)
					rule+= " ";
				print += rule;
					
				for(int i = support.length(); i < 7; i++)
					support += " ";
				
				for(int i = confidence.length(); i < 10; i++)
					confidence += " ";
				
				for(int i = lift.length(); i < 4; i++)
					lift += " ";
				
			}else{
				
				// sur-ensemble ferme
				closure2 += "|{" + generator.getSuperSetClosure() +"}";
				for(int i = closure2.length(); i <=(closureMaxSize2+2); i++ )
					closure2 += " ";
				print += closure2;
				
				// recuperation de la regle
				rule += "|{" + generator.getRule() +"}";
				for(int i = rule.length(); i <= (ruleMaxSize + 5); i++)
					rule+= " ";
				print += rule;
				
				support = "" +generator.getSuperSetSupport();
				for(int i = support.length(); i < 7; i++)
					support += " ";
				
				confidence = ""+generator.getConfidence();
				for(int i = confidence.length(); i < 10; i++)
					confidence += " ";
				
				lift = ""+generator.getLift();
				for(int i = lift.length(); i < 4; i++)
					lift += " ";
			}
			print += "|" + support +"|" +confidence +"|" +lift +"|"+"\n"+barre +"\n";
		}
		
		return print;
	}
}
