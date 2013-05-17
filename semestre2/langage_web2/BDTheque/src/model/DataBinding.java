package model;

import java.io.File;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.XMLResource;

import util.Bds;
import util.Bds.Bd;
import util.ObjectFactory;

public class DataBinding {

	/**
	 * Retourne une instance de bds deserialisée depuis la ressource xml.
	 * @param res
	 * @return
	 * @throws XMLDBException
	 */
	public static Bds deserialise(XMLResource res) throws XMLDBException{
		JAXBContext context = null;
		Unmarshaller um;

		try {
			context = JAXBContext.newInstance("util");
			um = context.createUnmarshaller();

			ObjectFactory factory = new ObjectFactory();
			Bds bds = factory.createBds();

			bds = (Bds)um.unmarshal(res.getContentAsDOM());
			return bds;
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Permet serialisé une instance de Bds sous format xml
	 * @param bds
	 */
	public static void serialise(Bds bds){
		try {
			JAXBContext context = JAXBContext.newInstance(Bds.class);
			Marshaller m = context.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			m.marshal(bds, new File("src/data/BDcreated.xml"));
		} catch (JAXBException ex) {
			ex.printStackTrace();
		}
	}
	
	/**
	 * Serialise une liste de bd au format string.
	 * @param bds liste de bd a serialise.
	 * @return une liste de bd serialise au format string.
	 */
	public static String serialisetoString(Bds bds) {
		StringWriter sw = new StringWriter();	
		try {
			JAXBContext context = JAXBContext.newInstance(Bds.class);
			Marshaller m = context.createMarshaller();
			
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			
			m.marshal(bds, sw);

		} catch (JAXBException ex) {
			ex.printStackTrace();
		}
		return sw.toString();
	}
	
	/**
	 * Serialise un bd au format string.
	 * @param bd bd a serialise.
	 * @return un bd serialise au format string.
	 */
	public static String serialisetoString(Bd bd) {
		StringWriter sw = new StringWriter();	
		try {
			JAXBContext context = JAXBContext.newInstance(Bd.class);
			Marshaller m = context.createMarshaller();
			
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, false);
			m.setProperty(Marshaller.JAXB_FRAGMENT, true);
			
			//String schemaLocation ="xsi:schemaLocation=http://www.univ-rouen.fr/bd ../xsd/BD.xsd";
			//m.setProperty(Marshaller.JAXB_SCHEMA_LOCATION,schemaLocation);
			
			m.marshal(bd, sw);

		} catch (JAXBException ex) {
			ex.printStackTrace();
		}
		return sw.toString();
	}
}
