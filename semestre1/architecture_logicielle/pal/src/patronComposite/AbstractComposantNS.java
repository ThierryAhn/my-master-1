package patronComposite;

/*
 * classe abstraite des composants ayant les attributs name et settingKey
 * */

public abstract class AbstractComposantNS extends AbstractComposantN implements ComposantNS {

	//attributs
	
    private String settingKey;

    //constructeur
    
    public AbstractComposantNS(String name, String settingKey) {
        super(name);
        this.settingKey = settingKey;
    }
    
    // fonctions

    public String getSettingKey() {
        return settingKey;
    }
    
    //methodes
    
    public void setSettingKey(String settingKey) {
        this.settingKey = settingKey;
    }
    
}
