package patronComposite;

import java.util.ArrayList;
import java.util.List;

import patronVisiteur.IVisiteurConfig;


/*
 * Classe ChoiceField des elements choicefield du fichier xml 
 * */

public class ChoiceField extends AbstractComposantNS {

	//attributs
	
    private List<ChoiceValue> listChoiceValue;

    //constructeur
    
    public ChoiceField(String name, String settingKey) {
        super(name, settingKey);
        listChoiceValue = new ArrayList<ChoiceValue>();
    }

    //fonctions
    
    public List<ChoiceValue> getListChoicevalue() {
        return listChoiceValue;
    }
    
    //methodes
    
    public void add(ChoiceValue e) {
        listChoiceValue.add(0, e);
    }

    public void remove(ChoiceValue e) {
        listChoiceValue.remove(e);
    }
    
    public void accept(IVisiteurConfig visiteur) {
        visiteur.visite(this);
    }
}
