package patronComposite;

import patronVisiteur.Visitable;

/*
 * Interface des Composants ayant juste le name comme attribut
 * */

public interface ComposantN extends Visitable {

    public String getName();

    public void setName(String name);
    
}
