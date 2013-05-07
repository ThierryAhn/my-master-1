package patronComposite;

import patronVisiteur.IVisiteurConfig;
import patronVisiteur.Visitable;

/*
 * Classe ChoiceValue des elements choicefield du fichier xml
 * */

public class ChoiceValue implements Visitable {

	//attributs
	
    private String name;
    private int value;
    private boolean selected;

    //constructeur
  
    public ChoiceValue(String name, int value) {
    	
        this.name = name;
        this.value = value;
        selected = false;
    }

    //fonctions
    
    public String getName() {
        return name;
    }
        
    public boolean isSelected() {
        return selected;
    }
    
    public int getValue() {
        return value;
    }
    
    //methodes
    
    public void setName(String name) {
        this.name = name;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
    
    public void accept(IVisiteurConfig visiteur) {
        visiteur.visite(this);
    }
    
}
