package model;

import javax.xml.transform.OutputKeys;

import org.exist.storage.serializers.EXistOutputKeys;
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
		Class<?> cl = Class.forName("org.exist.xmldb.DatabaseImpl");
		Database database = (Database) cl.newInstance();
		DatabaseManager.registerDatabase(database);
		Collection col  = DatabaseManager.getCollection(URI);
		col.setProperty(OutputKeys.INDENT, "yes");
		col.setProperty(EXistOutputKeys.EXPAND_XINCLUDES, "no");
		res = (XMLResource)col.getResource("BD.xml");
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
