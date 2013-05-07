package patronComposite;

import java.util.ArrayList;
import java.util.List;

import patronComposite.AbstractComposantN;
import patronVisiteur.IVisiteurConfig;


/*
 * Classe Module des elements module du fichier xml
 * */

public class Module extends AbstractComposantN {

	//attributs
	
    List<ComposantNS> ListSousComposant;

    //constructeur
    
    public Module(String name) {
        super(name);
        ListSousComposant = new ArrayList<ComposantNS>();
    }

    //fonctions
    
    public List<ComposantNS> getlistSousComposant() {
        return ListSousComposant;
    }
    
    //methodes
    
    public void add(ComposantNS e) {
        ListSousComposant.add(0, e);
    }

    public void remove(ComposantNS e) {
        ListSousComposant.remove(e);
    }

    public void accept(IVisiteurConfig visiteur) {
        visiteur.visite(this);
    }
}
