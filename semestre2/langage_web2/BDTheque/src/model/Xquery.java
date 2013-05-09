package model;

import org.xmldb.api.base.*;
import org.xmldb.api.modules.*;
import org.xmldb.api.*;

/**
 * Classe Xquery qui permet d'accéder aux ressources sur exist.
 * @author AHOUNOU Folabi Thierry & ABALINE Rachid.
 *
 */
public class Xquery {
	
	/**
	 * URI de la ressource.
	 */
	private static String URI = "xmldb:exist://localhost:8081/exist/xmlrpc/db/bedetheque";
	/**
	 * Ressource XML.
	 */
	private XMLResource res;
	
	/**
	 * Constructor.
	 * @throws Exception
	 */
	public Xquery() throws Exception{
		 final String driver = "org.exist.xmldb.DatabaseImpl";
	        
	     // initialize database driver
		 Class<?> cl = Class.forName(driver);
		 Database database = (Database) cl.newInstance();
		 database.setProperty("create-database", "true");
		 DatabaseManager.registerDatabase(database);
		 
	        
	     Collection col = null;
	     try { 
	    	 col = DatabaseManager.getCollection(URI);
	    	 res = (XMLResource)col.getResource("BD.xml");
	     }finally{
	           	// cleanup
	            if(col != null) {
	                try { 
	                	col.close(); 
	                }catch(XMLDBException xe){
	                	xe.printStackTrace();
	                }
	            }
	     }
	}
	
	/**
	 * Retourne l'URI de la ressource.
	 * @return l'URI de la ressource.
	 */
	public static String getURI(){
		return URI;
	}
	
	/**
	 * Retourne la ressource xml.
	 * @return la ressource xml.
	 */
    public XMLResource getXMLResource(){
    	return res;
    }
    
    public static void main(String [] args) throws Exception{
    	Xquery xquery = new Xquery();
    	System.out.println(xquery.getXMLResource().getContent());
    }
}
