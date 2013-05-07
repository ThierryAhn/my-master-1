package utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import exo1.Fonctions;

public class TestGlyph {
	
	public static void main(String ...args){
		ArrayList<ArrayList<byte[]>> dataset = new ArrayList<ArrayList<byte[]>>();
		try {
			dataset = Utils.loadImages ("aatp1-fichiers/classe");
		} catch (IOException e) {
			e.printStackTrace();
		}
		// classe0 premiere glyphe
		//Utils.viewGlyph(dataset.get(0).get(0));
		
		// distance entre deux glyphes de classe0
		/* System.out.println("Distance entre deux glyphes : " +
				Fonctions.distance(dataset.get(0).get(0), 
				dataset.get(0).get(1)));*/
		
		// test listGlyph
		List<LabelledData> l = Fonctions.makeSet(dataset, 2, 1);
		System.out.println(l.size());
		
		// 5 plus proches voisins
		Map sortedMap = new TreeMap<Float, LabelledData>();
		sortedMap = Fonctions.nearestNeighbor(l, dataset.get(1).get(0), 5);
		System.out.println(sortedMap);
		
	}
	
}
