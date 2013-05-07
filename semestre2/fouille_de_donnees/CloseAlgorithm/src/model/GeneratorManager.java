package model;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Classe qui gere les generateurs.
 * @author  ahounou & ayadi.
 * @date  19/02/2013 - 22/03/2013.
 */
public class GeneratorManager{
	
	/**
	 * Liste des generateurs.
	 */
	private List<Generator> arrayGenerator;
	/**
	 * Variables pour avoir un beau affichage.
	 */
	public int generatorMaxSize;
	/**
	 * Variables pour avoir un beau affichage.
	 */
	public int closureMaxSize;
	
	/**
	 * Constructeur par defaut.
	 */
	public GeneratorManager(){
		arrayGenerator = new ArrayList<Generator>();
		generatorMaxSize = 0;
		closureMaxSize = 0;
	}
	
	/**
	 * Construit une liste de generateurs a partir d'un fichier.
	 * @param filename fichier dans lequel se trouve les donnees des generateurs.
	 */
	public GeneratorManager(String filename){
		// initialisations
		generatorMaxSize = 0;
		closureMaxSize = 0;
		arrayGenerator = new ArrayList<Generator>();
		
		// lecture du fichier
		try{
			InputStream ips=new FileInputStream(filename); 
			InputStreamReader ipsr=new InputStreamReader(ips);
			BufferedReader br=new BufferedReader(ipsr);
			
			String line;
			while((line = br.readLine())!= null){ // recuperation de chaque ligne
				StringTokenizer token = new StringTokenizer(line, "\\|");
				int countGrep = token.countTokens() - 1; // variable pour avoir un beau affichage
				token.nextToken(); // on ne tient pas le numero de la ligne
				
				// recuperation des candidats
				while(token.hasMoreElements()){
					String candidate = token.nextToken();
					// creation d'un generateur avec le candidat recupere
					Generator generator = new Generator();
					generator.addCandidate(candidate);
					
					// ajout du generateur dans la liste des generateurs
					this.addGenerator(generator);
					
					// si on trouve un generateur avec une taille plus longue, on met a jour la 
					// taille du plus long generateur : c'est juste pour avoir un beau affichage
					if(candidate.length() > generatorMaxSize){
						generatorMaxSize = candidate.length();
					}
				}
				
				// si on trouve une fermteture avec une taille plus longue, on met a jour la 
				// taille du plus long fermeture : c'est juste pour avoir un beau affichage
				if(countGrep > closureMaxSize){
					closureMaxSize = countGrep;
				}
			}
			br.close();
		
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}

	/**
	 * Retourne la liste des generateurs.
	 * @return  generatorManager.
	 */
	public List<Generator> getArrayGenerator() {
		return arrayGenerator;
	}
	
	/**
	 * Modifie la liste des generateurs.
	 * @param arrayGenerator  nouvelle liste de  generateurs.
	 */
	public void setArrayGenerator(List<Generator> arrayGenerator) {
		this.arrayGenerator = arrayGenerator;
	}
	
	/**
	 * Ajoute un nouveau generateur.
	 * @param generator nouveau generateur a ajouter.
	 */
	public void addGenerator(Generator generator){
		// si le generateur n'est pas deja ajoute
		if(containsGenerator(generator) == false){
			arrayGenerator.add(generator);
		}
	}
	
	/**
	 * Verifie l'existence d'un generateur dans la liste des generateurs.
	 * @param generator generateur qu'il faut chercher.
	 * @return true si le generateur a ete trouve, false sinon.
	 */
	public boolean containsGenerator(Generator generator){
		for(Generator temp : this.arrayGenerator){
			if(temp.equals(generator)){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Affiche les generateurs.
	 */
	public String toString(){
		// maj de generaMaxSize si sa taille est inferieur a : 10 (taille de |Generator)
		if(generatorMaxSize < 10){
			generatorMaxSize = 10;
		}
		// maj de closureMaxSize si sa taille est inferieur a : 8 (taille de |Closure)
		if(closureMaxSize < 8){
			closureMaxSize = 8;
		}
		
		String barre = "---------";
		for(int i = 1; i <= generatorMaxSize+3; i++){
			barre +="-";
		}
		for(int i = 1; i <= closureMaxSize+3; i++){
			barre +="-";
		}
		
		String print = barre + "\n";
		print += "|Generator";
		for(int i = 10; i <= (generatorMaxSize + 2); i++)
			print +=" ";
		
		print += "|Closure";
		for(int i = 8; i <= (closureMaxSize + 2); i++)
			print +=" ";
		
		print += "|Support|\n";
		print += barre +"\n";
		
		for(Generator g : arrayGenerator){
			String generator = "";
			String closure = "";
				
			generator += "|{";
			// recuperation du generateur
			for(String c : g.getCandidates()){
				generator += c;
			}
			generator += "}";
			
			closure += "|{";
			// recuperation de la fermeture du generateur
			for(String c : g.getClosure()){
				closure += c;
			}
			closure += "}";
			
			// boucle pour avoir un beau affichage, +2 parce qu'on rajoute {} au generateur
			for(int i = generator.length(); i <=(generatorMaxSize+2); i++ ){
				generator += " ";
			}
			print += generator;
			
			// boucle pour avoir un beau affichage, +2 parce qu'on rajoute {} a la fermeture
			for(int i = closure.length(); i <=(closureMaxSize+2); i++ ){
				closure += " ";
			}
			print += closure;
			
			
			//taille Support = 7
			String support = "" +g.getSupport();
			for(int i = support.length(); i < 7; i++)
				support += " ";
			print += "|" + support +"|" +"\n"+barre +"\n";
			
			
		}
		print += "\n______\n\n";
		return print;
	}
}
