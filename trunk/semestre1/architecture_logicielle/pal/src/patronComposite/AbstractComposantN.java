package patronComposite;

/*
 * classe abstraite des composants ayant que l'attribut name 
 * */

public abstract class AbstractComposantN implements ComposantN {
	
	//attribut
	
    private String name;

    //constructeur
    
    public AbstractComposantN(String name) {
        this.name = name;
    }
    
    //fonctions
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
