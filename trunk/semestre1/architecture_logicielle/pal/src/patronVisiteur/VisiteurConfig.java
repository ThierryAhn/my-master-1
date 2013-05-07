package patronVisiteur;

import device.IDevice;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import patronComposite.*;


/*
 * VisiteurConfig : Le visiteur
 * */

public class VisiteurConfig implements IVisiteurConfig {
	
	//attributs
	
	private IDevice device;
    private PrintWriter sortie;
    private String url;

    //constructeur
    
    public VisiteurConfig(IDevice device) {
    	 if (device == null) {
             throw new IllegalArgumentException();
         }
        this.device = device;
    }

    //focntions
    
    public String getUrl() {
        return url;
    }
    
    //methodes
    
    public void setSortie(PrintWriter sortie) {
    	 if (sortie == null) {
             throw new IllegalArgumentException();
         }
        this.sortie = sortie;
    }

    public void setUrl(String url) {
    	 if (url == null) {
             throw new IllegalArgumentException();
         }
        url = url.replace(".", "/");
        if (url.startsWith("/")) {
            url = url.substring(1);
        }
        if (url.endsWith("/")) {
            url = url.substring(0, url.length() - 1);
        }
        this.url = url;
    }
    
    // traitement d'affichage de Categorie
    public void visite(Category cat) throws Exception{
    	 if (url == null) {
             throw new IllegalArgumentException();
         }
         if (url.equals(cat.getName())) {
        	 sortie.print("<div>\n" +
             		"<div class='title'>\n" + cat.getName() + "</div>\n" +
             		"<ul>\n");
             for (ComposantN c : cat.getlistSousComposant()) {
            	 sortie.print("<li>\n");
                 try {
                	 sortie.print("<a href='" + java.net.URLEncoder.encode(c.getName(), "UTF-8") + "/' >\n");
 				} catch (UnsupportedEncodingException e) {
 					e.getMessage();
 				}
                 sortie.print(c.getName() + 
                 		"</a>\n" +
                 		"</li>\n");
             }
             sortie.print("</ul>\n" +
             		"</div>\n");
         } else if (url.startsWith(cat.getName())) {
            url = url.substring(url.indexOf('/') + 1);
            for (ComposantN c : cat.getlistSousComposant()) {
                if (url.startsWith(c.getName())) {
                    c.accept(this);
                }
            }
        } 
    }
    
    // traitement d'affichage de Module
    public void visite(Module m) {
    	 if (m == null) {
             throw new IllegalArgumentException();
         }
        sortie.print("<div class='title'>\n" + m.getName() + "</div>\n");
        for (ComposantNS cns : m.getlistSousComposant()) {
            cns.accept(this);
        }
    }
    
    // traitement d'affichage de Info
    public void visite(Info inf) {
    	 if (inf == null) {
             throw new IllegalArgumentException();
         }
        sortie.print("<div class='title'>\n" + inf.getName() + "</div>\n" +
        		"<table class='info'>\n");
        for (InfoLine i : inf.getListeInfoLine()) {
            i.accept(this);
        }
        sortie.print("</table>\n");
    }

    // traitement d'affichage de InfoLine
    public void visite(InfoLine il) {
    	 if (il == null) {
             throw new IllegalArgumentException();
         }
        sortie.println("<tr>\n<td>\n" + il.getTitle() + "</td>\n" +
        		"<td>\n" + il.getDesc() + "</td>\n</tr>\n");
    }
    
    // traitement d'affichage de TextField
    public void visite(TextField tf) {
    	 if (tf == null) {
             throw new IllegalArgumentException();
         }
    	 sortie.print("<form method='GET' action='/setvalue'>\n" +
         		"<label>" + 
         		tf.getName() +
         		"</label>\n" +
         		"<input type='text' name='" +
                 tf.getSettingKey() +
                 "' value='" +
                 device.getSettingValue(tf.getSettingKey())+
                 "' />\n" +
                 "<input type='submit' value='apply'/>\n" +
                 "</form>\n");
    }

    // traitement d'affichage de CheckField
    public void visite(CheckField cf) {
    	 if (cf == null) {
             throw new IllegalArgumentException();
         }
    	sortie.print("<form method='GET'  action='/setvalue'>\n<label>\n" +
        		cf.getName() +
        		"</label>\n" +
        		"<select type='text' name='" +
                cf.getSettingKey() +
                "'>\n");
 
        if (Boolean.TRUE.toString().equals(device.getSettingValue(cf.getSettingKey()))) {
        	sortie.print("<option value='true' selected='selected'>true</option>\n" +
            		"<option value='false'>\nfalse</option>\n");
        } else {
        	sortie.print("<option value='true'>\ntrue</option>\n" +
            		"<option value='false' selected='selected'>false</option>\n");
        }

        sortie.print("</select>\n" +
        		"<input type='submit' value='apply'/>\n" +
        		"</form>\n");
    }
    
    // traitement d'affichage de ChoiceField
    public void visite(ChoiceField cf) {
    	 if (cf == null) {
             throw new IllegalArgumentException();
         }
    	 sortie.print("<form method='GET' action='/setvalue'>\n" +
         		"<label>" +
         		cf.getName() +
         		"</label>\n" +
         		"<select type='text' name="
                 + cf.getSettingKey() + ">\n");
        
         for (ChoiceValue c : cf.getListChoicevalue()) {
             if (Integer.toString(c.getValue()).equals(device.getSettingValue(cf.getSettingKey()))) {
                 c.setSelected(Boolean.TRUE);
             } else {
                 c.setSelected(Boolean.FALSE);
             }
             c.accept(this);
         }
         sortie.println("</select>\n" +
         		"<input type='submit' value='apply'/>\n" +
         		"</form>\n");
    }

    // traitement d'affichage de ChoiceValue
    public void visite(ChoiceValue cv) {
    	 if (cv == null) {
             throw new IllegalArgumentException();
         }
        if (cv.isSelected()) {
            sortie.print("<option value='" + cv.getValue()
                    + "' selected='selected'>" + cv.getName() + "</option>\n");
        } else {
            sortie.print("<option value='" + cv.getValue()
                    + "'>" + cv.getName() + "</option>\n");
        }
    }
}
