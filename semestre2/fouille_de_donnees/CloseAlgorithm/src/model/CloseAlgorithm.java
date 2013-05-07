package model;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;


/**
 * Classe CloseAlgorithm pour executer l'algorithme.
 * @author  ahounou & ayadi
 * @date  19/02/2013 - 25/03/2013.
 * 
 */
public class CloseAlgorithm {
	
	/**
	 * Support minimal.
	 */
	private double supportMin;
	/**
	 * Nombre fermetures.
	 */
	private int countClosure;
	/**
	 * Liste des ffc qu'on va generer.
	 */
	private ArrayList<GeneratorManager> arrayFFC;
	/**
	 * Liste des ff qu'on va generer.
	 */
	private ArrayList<GeneratorManager> arrayFF;
	/**
	 * Fermeture de depart, fermeture du fichier.
	 */
	private ContextClosure contextClosure;
	
	/**
	 * Fermeture du ff courant.
	 */
	private List<Generator> currentClosure;
	
	/**
	 * Variables pour avoir un beau affichage.
	 */
	public int generatorMaxSize;
	/**
	 * Variables pour avoir un beau affichage.
	 */
	public int closureMaxSize;
	
	
	/**
	 * Constructeur.
	 * @param supportMin support minimal pour l'execution de l'algorithme.
	 * @param generatorManager l'ensemble des generateurs.
	 * @param contextClosure l'ensemble des fermetures.
	 */
	public CloseAlgorithm(double supportMin, GeneratorManager generatorManager, 
			ContextClosure contextClosure){
		
		DecimalFormat df = new DecimalFormat();
		df.setMaximumFractionDigits(2); //arrondi a 2 chiffres apres la virgules
		String str = df.format(supportMin);
		supportMin = Double.parseDouble(str.replace(',', '.')); 
		this.supportMin =  supportMin;
		
		arrayFFC = new ArrayList<GeneratorManager>();
		arrayFF = new ArrayList<GeneratorManager>();
		
		arrayFFC.add(generatorManager);
		this.contextClosure = contextClosure; 
		countClosure = this.contextClosure.getArrayClosure().size();
		
		currentClosure = contextClosure.getArrayClosure();
		
		generatorMaxSize = generatorManager.generatorMaxSize;
		closureMaxSize = generatorManager.closureMaxSize;
	}
	
	/**
	 * Retourne le support minimal.
	 * @return  supportMin.
	 */
	public double getSupportMin(){
		return supportMin;
	}
	
	/**
	 * Modifie le support minimal.
	 * @param supportMin  nouveau supportMinimal.
	 */
	public void setSupportMin(double supportMin){
		this.supportMin = supportMin;
	}
	
	/**
	 * Calcule la fermeture de chaque generateur.
	 * @param generatorManager liste des generateurs.
	 */
	public void closure(GeneratorManager generatorManager){
		for(Generator generator : generatorManager.getArrayGenerator()){
			for(Generator closure : contextClosure.getArrayClosure()){
				if(closure.contains(generator)){
					generator.setClosure(intersect(generator.getClosure(), 
							closure.getCandidates()));
				}
			}
		}
	}
	
	/**
	 * Calcule le support de chaque generateur.
	 * @param generatorManager liste des generateurs.
	 */
	public void support(GeneratorManager generatorManager){
		for(Generator generator : generatorManager.getArrayGenerator()){
			int count = 0;
			for(Generator closure : contextClosure.getArrayClosure()){
				if(closure.contains(generator)){
					count += 1;
				}
			}
			double support = (double)count / countClosure;
			generator.setSupport(support);
			
		}
	}
	
	/**
	 * Retourne l'intersection entre deux listes(un peu particuliere comme intersection, 
	 * en effet quand la premiere liste est vide, on ajoute dans cette liste tous les elements 
	 * de la deuxieme liste).
	 * @param list premiere liste.
	 * @param list2 deuxieme liste.
	 * @return l'intersection entre les deux listes.
	 */
	public Set<String> intersect(Set<String> list, Set<String> list2) {
		Set<String> rtnList = new TreeSet<String>();
		if(list.size() == 0)
			rtnList.addAll(list2);
	    else{
			for(String dto : list) {
		        if(list2.contains(dto)) {
		            rtnList.add(dto);
		        }
		    }
		}
		return rtnList;
	}
	
	/**
	 * Lance l'algo
	 */
	public void run(){
		GeneratorManager ffc = arrayFFC.get(0);
		GeneratorManager ff = new GeneratorManager();
		
		// ces deux instructions permettent de calculer le support et la fermeture de chaque 
		// generateur --> ffc[1]
		this.closure(ffc);
		this.support(ffc);
		// ces 2 affectations sont la pour avoir un beau affichage
		ffc.generatorMaxSize = generatorMaxSize;
		ffc.closureMaxSize = closureMaxSize;
		arrayFFC.clear(); arrayFFC.add(ffc);
		
		// calcul de ff1
		ff = this.genFF(ffc);
		arrayFF.add(ff);
		
		// si ff1 n'est pas vide on calcule les ffc et ff suivants
		if(ff.getArrayGenerator().size() > 0){
			while(checkSupport(ffc)){
				
				// recuperation de l'ensemble de la fermeture courante
				currentClosure = genClosure(ff);
				
				// calcul du nouveau ffc
				ffc = this.genFFC(ff);
				
				this.support(ffc);
				arrayFFC.add(ffc);
	
				// calcul de ff
				ff = this.genFF(ffc);
				arrayFF.add(ff);
				// on arrete le programme s'il n'y a pas de nouveau ff
				if(ff.getArrayGenerator().size() == 0)
					break;
			}
		}
	}
	
	/**
	 * Permet de calculer le ffc suivant a partir du dernier ff.
	 * @param ff dernier ff.
	 * @return le ffc suivant.
	 */
	public GeneratorManager genFFC(GeneratorManager ff){
		GeneratorManager ffc = new GeneratorManager();
		for(int i = 0; i < ff.getArrayGenerator().size(); i++){
			Generator generator = ff.getArrayGenerator().get(i);
			String s = "";
			// cette boucle servira pour verifier que le nouveau generateur qu'on va creer ne
			// contienne pas deux fois ou plus la meme lettre
			Iterator<String> it = generator.getCandidates().iterator();
			while(it.hasNext()){
				s += it.next();
			}
			
			for(int j = i + 1; j < ff.getArrayGenerator().size(); j++){
				it = ff.getArrayGenerator().get(j).getCandidates().iterator();
				while(it.hasNext()){
					String s2 = it.next();
					// si le candidat n'existe pas encore dans le generateur
					if(!s.contains(s2)){
						// creation du nouveau generateur de ffc
						Generator temp = new Generator();
						Iterator<String> it2 = generator.getCandidates().iterator();
						while(it2.hasNext()){
							temp.addCandidate(it2.next());
						}
						//Generator temp = new Generator(generator.getGenerator());
						temp.addCandidate(s2);
						// si le nouveau generateur n'est pas inclus dans la fermeture courante
						if(!this.isPartOfArrayClosure(temp)){
							// calcul de la fermeture
							Set<String> treeClosure = new TreeSet<String>();
							for(Generator closure : contextClosure.getArrayClosure()){
								if(closure.contains(temp)){
									treeClosure = intersect(temp.getClosure(), 
											closure.getCandidates());
								}
							}
							if(treeClosure.size() > 0){
								temp.setClosure(treeClosure);
								ffc.addGenerator(temp);
							}
						}
					}
				}
			}
		}
		
		// ces 2 affectations sont la pour avoir un beau affichage
		ffc.generatorMaxSize = generatorMaxSize;
		ffc.closureMaxSize = closureMaxSize;
		
		return ffc;
	}
	
	/**
	 * Permet de calculer le nouveau ff a partir du dernier ffc.
	 * @param ffc dernier ffc.
	 * @return le ff suivant.
	 */
	public GeneratorManager genFF(GeneratorManager ffc){
		GeneratorManager ff = new GeneratorManager();
		for(Generator generator : ffc.getArrayGenerator()){
			if(generator.getSupport() >= supportMin){
				ff.addGenerator(generator);
			}
		}
		
		// ces 2 affectations sont la pour avoir un beau affichage
		ff.generatorMaxSize = generatorMaxSize;
		ff.closureMaxSize = closureMaxSize;
		
		return ff;
	}
	
	/**
	 * Calcule l'ensemble de la fermeture courante.
	 * @return
	 */
	public List<Generator> genClosure(GeneratorManager ff){
		List<Generator> currentClosure = new ArrayList<Generator>();
		for(int i = 0; i < ff.getArrayGenerator().size(); i++){
			Set<String> closure = ff.getArrayGenerator().get(i).getClosure();
			Generator generator = new Generator(closure);
			currentClosure.add(generator);
		}
		return currentClosure;
	}
	
	/**
	 * Verifie si l'un des generateurs a un support inferieur au support minimal.
	 * @param generatorManager l'ensemble des generateurs.
	 * @return true si l'un des generateurs a un support inferieur au support minimal.
	 */
	public boolean checkSupport(GeneratorManager generatorManager){
		boolean result = false;
		for(Generator temp : generatorManager.getArrayGenerator()){
			if(temp.getSupport() < supportMin){
				result = true;
				break;
			}
		}
		return result;
	}
	
	/**
	 * Retourne l'ensemble des k-groupes frequents des k-generateuts.
	 * @return  arrayFF.
	 */
	public ArrayList<GeneratorManager> getArrayFF(){
		return arrayFF;
	}
	
	/**
	 * Verifie si un generateur fait partie de la liste des fermetures de l'ensemble des 
	 * 	generateurs.
	 * @param generator
	 * @return
	 */
	public boolean isPartOfArrayClosure(Generator generator){
		boolean result = false;
		for(Generator temp : currentClosure){
			if(temp.contains(generator)){
				result = true;
			}
		}
		return result;
	}
	
	/**
	 * Retourne la trace de l'algorithme.
	 * @return trace.
	 */
	public String toString(){
		String print = "";
		for(int i = 0; i < arrayFFC.size(); i++){
			print += "FFC" +(i+1) +"\n";
			print += arrayFFC.get(i);
			print += "FF" +(i+1) +"\n";
			print += arrayFF.get(i);
		}
		return print;
	}
}
