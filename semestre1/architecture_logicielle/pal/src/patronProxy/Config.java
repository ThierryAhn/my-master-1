package patronProxy;

import patronVisiteur.Visitable;


/*
 * Classe Configuration (Realsubject)
 * */

public class Config implements IConfig {

	//attributs
    
	private Visitable cn;

    //constructeur
	
    public Config(Visitable cn) {
    	if (cn == null){
    	}
        this.cn = cn;
    }

    //fonctions
    
    public Visitable getComposant() {
        return cn;
    }
    
}
