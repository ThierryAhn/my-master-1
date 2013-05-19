package model;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.xml.sax.SAXException;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.XMLResource;
import util.Bds;
import util.Bds.Bd;
import util.ObjectFactory;
import javax.xml.parsers.ParserConfigurationException;

/**
 * Classe DataBinding pour serialiser et deserialiser les bd.
 * @author AHOUNOU & ABALINE
 *
 */
public class DataBinding {

	/**
	 * Retourne une instance de bds deserialisée depuis la ressource xml.
	 * @param res ressource xml.
	 * @return une instance de bds.
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
	 * Retourne une instance de bds depuis un fichier xml.
	 * @param xml fichier xml.
	 * @return une instance de bds.
	 * @throws XMLDBException
	 * @throws IOException 
	 * @throws SAXException 
	 * @throws ParserConfigurationException 
	 */
	public static Bds deserialise(InputStream xml) throws XMLDBException, SAXException, IOException, ParserConfigurationException{
		JAXBContext context = null;
		Unmarshaller um;
		try {
			context = JAXBContext.newInstance("util");
			um = context.createUnmarshaller();

			ObjectFactory factory = new ObjectFactory();
			Bds bds = factory.createBds();

			bds = (Bds)um.unmarshal(xml);
			return bds;
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return null;
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
			
			m.marshal(bd, sw);

		} catch (JAXBException ex) {
			ex.printStackTrace();
		}
		return sw.toString();
	}
}
