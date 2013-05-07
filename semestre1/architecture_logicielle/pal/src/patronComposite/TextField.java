package patronComposite;

import patronVisiteur.IVisiteurConfig;

/*
 * Classe TextField des elements textfield du fichier xml
 * */

public class TextField extends AbstractComposantNS {

	//constructeur
	
    public TextField(String name, String settingKey) {
        super(name, settingKey);
    }

    //methodes
    
    public void accept(IVisiteurConfig visiteur) {
        visiteur.visite(this);
    }
    
}
