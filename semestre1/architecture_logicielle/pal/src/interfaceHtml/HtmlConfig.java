package interfaceHtml;

import java.io.OutputStream;
import java.io.PrintWriter;
import patronProxy.IConfig;
import patronVisiteur.IVisiteurConfig;


public class HtmlConfig {

	//attributs
	
    private IVisiteurConfig visiteurConfig;
    private IConfig config;

    //constructeur
    public HtmlConfig(IVisiteurConfig v, IConfig c) {
        this.visiteurConfig = v;
        this.config = c;
    }

    //methode
    
    public void AffichageInitial(OutputStream os, String url) {
    	
        PrintWriter sortie = new PrintWriter(os);
        visiteurConfig.setSortie(sortie);
        visiteurConfig.setUrl(url);
        
        sortie.print("<html>\n" +
						"<head>\n" +
			    			"<link rel='stylesheet' type='text/css' href='/ressources/config.css' />\n"+
			                "<meta http-equiv='content-type' content='text/html; charset=utf-8' />\n" +
			    			"<title>\n" +
			    				"Interface de configuration de routeur" +
			    			"</title>\n" +
			    		"</head>\n" +
		    			"<body>\n"+
			    			"<div class='appname'>\n" +
			    				"<img width='65px' src='/ressources/tux.png'>\n" +
			    					"Interface de configuration de routeur" +
			    			"</div>\n<br>\n<br>\n"+
			    			"<div class='leftmenu'>\n"+
			    				"<a href='/home/'>\n" +
			    					"Home"+
			    				"</a>\n" +
			    				"<br>\n"+
			    			"</div>\n" + 
			            	"<div class='maincontent'>\n");
        config.getComposant().accept(visiteurConfig);
        
        sortie.print(		"</div>\n" +
	        				"<div class='footer'>\n" +
	        				"</div>\n" +
	        			"</body>\n" +
	        		"</html>\n");
        
        sortie.flush();
    }

    public void AffichageModification(OutputStream os) {
    	
        PrintWriter sortie = new PrintWriter(os);

        sortie.print("<html>\n" +
						"<head>\n" +
			    			"<link rel='stylesheet' type='text/css' href='/ressources/config.css' />\n"+
			                "<meta http-equiv='content-type' content='text/html; charset=utf-8' />\n" +
							"<title>\n" +
								"Interface de configuration de routeur" +
							"</title>\n" +
						"</head>\n" +
		    			"<body>\n"+
			    			"<div class='appname'>\n" +
				    			"<img width='65px' src='/ressources/tux.png'>\n" +
				    				"Interface de configuration de routeur" +
			    			"</div>\n<br>\n<br>\n"+
			    			"<div class='leftmenu'>\n"+
				    			"<a href='/home/'>\n" +
				    				"Home"+
				            	"</a>\n" +
				            	"<br>\n"+
			    			"</div>\n" +
			            	"<div class='maincontent'>\n" +
				    			"<span>\n" +
					    			"<strong>\n" +
					    				"Modification" +
					    			"</strong>\n" +
				    			"</span>\n" +
				            	"<p>\n" +
				    				"La valeur a été modifiée avec succès !" +
				            	"</p>\n" +
			    			"</div>\n" + 
			            	"<div class='footer'>\n" +
			        		"</div>\n" +
		        		"</body>\n" +
	        		"</html>\n");
        sortie.flush();
    }

}