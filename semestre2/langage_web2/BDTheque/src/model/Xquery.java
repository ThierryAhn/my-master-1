package model;

import javax.xml.transform.OutputKeys;

import org.exist.storage.serializers.EXistOutputKeys;
import org.xmldb.api.base.*;
import org.xmldb.api.modules.*;
import org.xmldb.api.*;

import util.Bds.Bd;


/**
 * Classe Xquery qui permet d'accéder aux ressources sur exist.
 * @author AHOUNOU Folabi Thierry & ABALINE Rachid.
 *
 */
public class Xquery {

	/**
	 * URI de la ressource.
	 */
	private static String URI = "xmldb:exist://localhost:8080/exist/xmlrpc/db/bedetheque";
	/**
	 * Ressource sur exists.
	 */
	private XMLResource resource;
	/**
	 * Service XPathQuery
	 */
	private XPathQueryService xpqs;
	/**
	 * Espace de nommage des bd.
	 */
	private static String namespace = "declare namespace bd=\"http://www.univ-rouen.fr/bd\";";

	/**
	 * Collection des ressources sur exists.
	 */
	private Collection collection;
	
	/**
	 * Constructor.
	 * @throws Exception
	 */
	public Xquery() throws Exception{
		Class<?> cl = Class.forName("org.exist.xmldb.DatabaseImpl");
		Database database = (Database) cl.newInstance();
		DatabaseManager.registerDatabase(database);
		collection  = DatabaseManager.getCollection(URI);
		
		xpqs = (XPathQueryService)collection.getService("XPathQueryService", "1.0");
		xpqs.setProperty("indent", "yes");
		
		collection.setProperty(OutputKeys.INDENT, "yes");
		collection.setProperty(EXistOutputKeys.EXPAND_XINCLUDES, "no");
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
	public XMLResource getResource(String resourceName){
		try {
			resource = (XMLResource)collection.getResource(resourceName);
		} catch (XMLDBException e) {
			e.printStackTrace();
		}
		
		return resource;
	}
	
	/**
	 * Insere une bd.
	 * @param bd nouvelle bd a inserer.
	 * @throws XMLDBException 
	 */
	public void insert(Bd bd) throws XMLDBException{
		String str = DataBinding.serialisetoString(bd);
		String query= namespace + "update insert "+str+" into //bd:bds";
		xpqs.query(query);
	}
	
	/**
	 * Supprime une bd.
	 * @param identifiant identifiant de la bd a supprimer.
	 * @throws XMLDBException 
	 */
	public void delete(int identifiant) throws XMLDBException{
		String query = namespace + "for $c in collection(\"bedetheque\")/bd:bds/bd:bd"+
				" where $c/bd:Informations/bd:Identifiant = "+ identifiant +" return update delete $c";
		
		xpqs.query(query);
	}
	
	
}
