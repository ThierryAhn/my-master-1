package patronBuilder;

import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

import patronComposite.*;
import patronProxy.Config;
import patronProxy.IConfig;
import patronVisiteur.Visitable;

/*
 * Classe BuilderConfig
 * */

public class BuilderConfig implements IBuilderConfig {

	//attributs
	
    private ComposantN cn;
    private InputSource is;

    //constructeur
    
    public BuilderConfig(InputSource is) {
        this.is = is;
    }
   
    //fonctions
    
    public IConfig build() throws ParserConfigurationException, SAXException {
    	
        SAXParserFactory spf = SAXParserFactory.newInstance();
        SAXParser sp = spf.newSAXParser();
        XMLReader xr = sp.getXMLReader();
        System.out.println("Parser : " + xr.getClass().getName());
        HandlerImpl handler = new HandlerImpl();
        xr.setContentHandler(handler);
        xr.setErrorHandler(handler);
        try {
            xr.parse(is);
        } catch (Exception e) {
            e.getMessage();
        }
        return new Config(cn);
        
    }

    private class HandlerImpl extends DefaultHandler {
    	
    	//attributs
    	
        private List<Visitable> composant, composantCplx;
        
        //methode
        
        public void startDocument() {
            composant = new ArrayList<Visitable>();
            composantCplx = new ArrayList<Visitable>();
        }

        public void startElement(String uri, String localName, String qName, Attributes atts) {

            Visitable cv = null;

            if ("category".equals(qName)) {
                cv = new Category(atts.getValue("name"));
                composantCplx.add(cv);
            } 
            
            else if ("module".equals(qName)) {
                cv = new Module(atts.getValue("name"));
                composantCplx.add(cv);
            } 
            
            else if ("info".equals(qName)) {
                cv = new Info(atts.getValue("name"));
                composantCplx.add(cv);
            } 
            
            else if ("infoline".equals(qName)) {
                cv = new InfoLine(atts.getValue("title"), atts.getValue("desc"));
            } 
            
            else if ("textfield".equals(qName)) {
                cv = new TextField(atts.getValue("name"), atts.getValue("settingkey"));
            } 
            
            else if ("checkfield".equals(qName)) {
                cv = new CheckField(atts.getValue("name"), atts.getValue("settingkey"));
            } 
            
            else if ("choicefield".equals(qName)) {
                cv = new ChoiceField(atts.getValue("name"), atts.getValue("settingkey"));
                composantCplx.add(cv);
            } 
            
            else if ("choicevalue".equals(qName)) {
                cv = new ChoiceValue(atts.getValue("name"), Integer.valueOf(atts.getValue("value")));
            }
            
            if (cv != null) {
                composant.add(cv);
            }
        }

        public void endElement(String uri, String localName, String qName) {
        	
            if (!composantCplx.isEmpty()) {
            	
                Visitable cv = null;
                
                if ("category".equals(qName)) {
                	
                    Category cat = (Category) composantCplx.remove(composantCplx.size() - 1);
                    cv = composant.get(composant.size() - 1);
                    
                    while (!cat.equals(cv)) {
	                    cat.add((ComposantN) cv);
	                    composant.remove(composant.size() - 1);
	                    cv = composant.get(composant.size() - 1);
                    } 
                } 
                
                else if ("module".equals(qName)) {
                    Module module = (Module) composantCplx.remove(composantCplx.size() - 1);
                    cv = composant.get(composant.size() - 1);
                    
                    while (!module.equals(cv)) {
                        module.add((ComposantNS) cv);
                        composant.remove(composant.size() - 1);
                        cv = composant.get(composant.size() - 1);
                    }
                    
                } 
                
                else if ("info".equals(qName)) {
                	
                    Info info = (Info) composantCplx.remove(composantCplx.size() - 1);
                    cv = composant.get(composant.size() - 1);
                    
                    while (!info.equals(cv)) {
                        info.add((InfoLine) cv);
                        composant.remove(composant.size() - 1);
                        cv = composant.get(composant.size() - 1);
                    }  
                } 
                
                else if ("choicefield".equals(qName)) {
                    ChoiceField choiceField = (ChoiceField) composantCplx.remove(composantCplx.size() - 1);
                    cv = composant.get(composant.size() - 1);
                    
                    while (!choiceField.equals(cv)) {
                        choiceField.add((ChoiceValue) cv);
                        composant.remove(composant.size() - 1);
                        cv = composant.get(composant.size() - 1);
                    }
                    
                }

                if (composantCplx.isEmpty()) {
                    cn = (ComposantN) cv;
                }
            }
        }
        
        public void endDocument() throws SAXException {
            composant.clear();
            composantCplx.clear();
        }
        
    }

}
