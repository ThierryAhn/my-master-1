package model;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Classe qui permet de recuperer l'ensemble des fermeture de base.
 * @author ahounou & ayadi.
 * @date  20/03/2013 - 25/03/2013.
 *
 */
public class ContextClosure {
	/**
	 * Ensemble de fermeture de base, fermeture extraite du fichier.
	 */
	private List<Generator> arrayClosure;
	
	/**
	 * Constructeur qui recupere l'ensemble des fermetures.
	 * @param filename
	 */
	public ContextClosure(String filename){
		
		arrayClosure = new ArrayList<Generator>();
		
		// lecture du fichier
		try{
			InputStream ips=new FileInputStream(filename); 
			InputStreamReader ipsr=new InputStreamReader(ips);
			BufferedReader br=new BufferedReader(ipsr);
			
			String line;
			while((line = br.readLine())!= null){ // recuperation de chaque ligne
				StringTokenizer token = new StringTokenizer(line, "\\|");
				
				token.nextToken(); // on ne tient pas le numero de la ligne
				Generator closureCandidate = new Generator();
				// recuperation des candidats de la fermeture
				while(token.hasMoreElements()){
					String candidate = token.nextToken();
					closureCandidate.addCandidate(candidate);
				}
				// ajout de la fermeture
				arrayClosure.add(closureCandidate);
			}
			br.close();
		
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * Retourne la liste des fermetures.
	 * @return  arrayClosure.
	 */
	public List<Generator> getArrayClosure() {
		return arrayClosure;
	}
	
	
}
