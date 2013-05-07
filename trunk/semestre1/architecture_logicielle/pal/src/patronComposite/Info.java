package patronComposite;

import java.util.ArrayList;
import java.util.List;

import patronComposite.AbstractComposantN;
import patronVisiteur.IVisiteurConfig;

/*
 * Classe Info des elements info du fichier xml
 * */

public class Info extends AbstractComposantN {
	
	//attributs
	
    private List<InfoLine> listeInfoLine;

    //constructions
    
    public Info(String name) {
        super(name);
        listeInfoLine = new ArrayList<InfoLine>();
    }

    // fonctions
    
    public List<InfoLine> getListeInfoLine() {
        return listeInfoLine;
    }
    
    // methodes
    
    public void add(InfoLine e) {
        listeInfoLine.add(0, e);
    }

    public void remove(InfoLine e) {
        listeInfoLine.remove(e);
    }
   
    public void accept(IVisiteurConfig visiteur) {
        visiteur.visite(this);
    }
}
