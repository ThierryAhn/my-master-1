package model;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import util.Bds;

public class DataBinding {

	/**
	 * Retourne une instance de bds deserialisée depuis le fichier xml 
	 * @param file
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static Bds deserialise(File file){
		JAXBContext context = null;
		Unmarshaller um;
		
		try {
			context = JAXBContext.newInstance("util");
			um = context.createUnmarshaller();
			
			Bds bds = (Bds)um.unmarshal(file);
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
}
