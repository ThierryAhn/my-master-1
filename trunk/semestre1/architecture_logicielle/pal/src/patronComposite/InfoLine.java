package patronComposite;

import patronVisiteur.IVisiteurConfig;
import patronVisiteur.Visitable;

/*
 * Classe Infoline des elements infoline du fichier xml
 * */

public class InfoLine implements Visitable {

	//attributs
	
    private String title;
    private String desc;

    //constructeur
    
    public InfoLine(String title, String desc) {
        this.title = title;
        this.desc = desc;
    }

    //fonction
    
    public String getTitle() {
        return title;
    }
    
    public String getDesc() {
        return desc;
    }
    
    //methodes
    
    public void setTitle(String title) {
        this.title = title;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void accept(IVisiteurConfig visiteur) {
        visiteur.visite(this);
    }
    
}
