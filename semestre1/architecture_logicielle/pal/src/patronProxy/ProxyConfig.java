package patronProxy;

import org.xml.sax.InputSource;

import patronBuilder.BuilderConfig;
import patronBuilder.IBuilderConfig;
import patronVisiteur.Visitable;

/*
 * Le proxy de la classe Config (proxy)
 * */

public class ProxyConfig implements IConfig {
	
	//attributs
	
    private IConfig config;

    //fonctions
    
    public Visitable getComposant() {
    	
        if (config == null) {
            config = creeConfig();
        } 
        
        return config.getComposant();
    }

	private IConfig creeConfig() {
    	
        IBuilderConfig bc = new BuilderConfig(new InputSource("src/config.xml"));
        Config cf = null;
        try {
           cf = (Config) bc.build();
        } catch (Exception e) {
        	e.getMessage();
        } 
            return cf;
    }
	
}
