package patronComposite;

import patronVisiteur.IVisiteurConfig;

/*
 * Classe CheckField des elements checkfield du fichier xml
 * */

public class CheckField extends AbstractComposantNS {

	//constructeur
	
    public CheckField(String name, String settingKey) {
        super(name, settingKey);
    }
 
    //methodes
    
    public void accept(IVisiteurConfig visiteur) {
         visiteur.visite(this);
    }

}
