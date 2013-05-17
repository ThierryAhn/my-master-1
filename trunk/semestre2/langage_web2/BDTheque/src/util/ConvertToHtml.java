package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

/**
 * Classe ConvertToHtml qui transforme un fichier bd.xml en html.
 * @author AHOUNOU Folabi Thierry & ABALINE Rachid
 *
 */
public class ConvertToHtml {
	
	/**
	 * Constructeur
	 * @param xsl nom fichier xsl
	 * @param xml nom fichier xml
	 * @param html nom fichier html 
	 * @throws Exception 
	 */
	public ConvertToHtml(String xsl, String xml, String html) throws Exception{
		
		FileInputStream fis = new FileInputStream(xml);
		InputStreamReader in = new InputStreamReader(fis, "ISO-8859-1");
		Source xmlSource = new StreamSource(in);
		
		Source xslSource = new StreamSource(new File(xsl));
		
		File htmlFile = new File(html);
		Result htmlResult = new StreamResult(htmlFile);
		TransformerFactory transFact = TransformerFactory.newInstance();
		Transformer trans = transFact.newTransformer(xslSource);
		trans.transform(xmlSource, htmlResult);
	}
	
}
