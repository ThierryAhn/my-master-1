package patronComposite;

import java.util.ArrayList;
import java.util.List;

import patronComposite.AbstractComposantN;
import patronComposite.ComposantN;
import patronVisiteur.IVisiteurConfig;


/*
 * Classe Category des elements category du fichier xml
 * */

public class Category extends AbstractComposantN {

	//attributs
	
    protected List<ComposantN> listSousComposant;

    //constructeur
    
    public Category(String name) {
        super(name);
        listSousComposant = new ArrayList<ComposantN>();
    }

    //fonctions
    
    public List<ComposantN> getlistSousComposant() {
        return listSousComposant;
    }
    
    //methodes
    
    public void add(ComposantN e) {
        listSousComposant.add(0, e);
    }

    public void remove(ComposantN e) {
        listSousComposant.remove(e);
    }
   
    public void accept(IVisiteurConfig visiteur) {
        try {
			visiteur.visite(this);
		} catch (Exception e) {
			e.getMessage();
		}
    }
}
