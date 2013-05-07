package exo1;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import utils.LabelledData;

public class Fonctions {
	
	/**
	 * Calcule la distance euclidienne entre deux glyphes.
	 * @param glyph1 premiere glyphe.
	 * @param glyph2 deuxieme glyphe
	 * @return le resultat en double.
	 */
	public static double distance(byte[] glyph1, byte[] glyph2){
		double resultat = 0;
		
		for(int i = 0; i < 784; i++){
			int d = glyph1[i] - glyph2[i];
			resultat += d * d;
		}
		return Math.sqrt(resultat);
	}
	
	/**
	 * Retourne une liste de glyphes etiquetes.
	 * @param dataset jeu de donnees.
	 * @param nombreGlyphe nombre de glyphe a prendre.
	 * @param offset decalage pour avoir une liste de glyphes disjointes.
	 * @return une liste de glyphes etiquetes.
	 */
	public static List<LabelledData> makeSet(ArrayList<ArrayList<byte[]>> dataset,
			int nombreGlyphe, int offset){
		List<LabelledData> array = new ArrayList<LabelledData>();
		
		for(int i = 0; i < dataset.size(); i++){
			for(int j = offset; j < (nombreGlyphe + offset); j++){
				LabelledData labelledData = new LabelledData(i, 
						dataset.get(i).get(j));
				array.add(labelledData);
			}
		}
		return array;
	} 
	
	/**
	 * Retourne les k-plus-proches voisins.
	 * @param list list des glyphes.
	 * @param glyph glyphe a considerer.
	 * @param k le nombre de voisins a considerer.
	 * @return les k-plus-proches voisins.
	 */
	public static Map nearestNeighbor(List<LabelledData> list, byte[] glyph, int k){
		SortedMap<Float, LabelledData> sortedMap = new TreeMap<Float, LabelledData>();
		
		// recuperation des k premiers elements de la liste
		for(int i = 0; i < list.size(); i++){
			LabelledData labelledData = list.get(i);
			double dist = distance(labelledData.getGlyph(), glyph);
			sortedMap.put((float)dist, labelledData);
		}
		
		for(int i = k; i < sortedMap.size(); i++){
			//sortedMap.remove(key)
		}
		return sortedMap;
	}
	
}
