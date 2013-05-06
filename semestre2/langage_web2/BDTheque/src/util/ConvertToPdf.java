package util;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.xml.transform.Result;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamSource;

import org.apache.fop.apps.FOPException;
import org.apache.fop.apps.FOUserAgent;
import org.apache.fop.apps.Fop;
import org.apache.fop.apps.FopFactory;

public class ConvertToPdf {

	/**
	 * Constructeur
	 * @param xslt fichier xslt
	 * @param xml fichier xml
	 * @param pdf nom du fichier pdf a generer
	 * @throws IOException
	 * @throws TransformerException
	 * @throws FOPException
	 */
	public ConvertToPdf(String xslt, String xml, String pdf) throws IOException, 
				TransformerException, FOPException{

		// fichier xsl-fo
		File xsltfile = new File(xslt);
		// recuperation de la source xml
		StreamSource source = new StreamSource(new File(xml));
		// creation du transform source
		StreamSource transformSource = new StreamSource(xsltfile);

		// creation instance de fop factory
		FopFactory fopFactory = FopFactory.newInstance();

		FOUserAgent foUserAgent = fopFactory.newFOUserAgent();
		// sert a enregistrer le pdf
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();

		Transformer xslfoTransformer = getTransformer(transformSource);
		// construction fop pour pdf
		Fop fop = fopFactory.newFop("application/pdf", foUserAgent, outStream);
		
		Result result = new SAXResult(fop.getDefaultHandler());
		// xslt transformation
		xslfoTransformer.transform(source, result);
		
		// sauvegarder fichier pdf
		File pdffile = new File(pdf);
		OutputStream out;
		try {
			out = new FileOutputStream(pdffile);
			out = new BufferedOutputStream(out);
			FileOutputStream str = new FileOutputStream(pdffile);
			str.write(outStream.toByteArray());
			str.close();
			out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	private Transformer getTransformer(StreamSource streamSource){
		// mise en place xslt transformer
		TransformerFactory impl = TransformerFactory.newInstance();

		try {
			return impl.newTransformer(streamSource);
		}catch (TransformerConfigurationException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void main(String [] args) throws FOPException, IOException, 
					TransformerException{
		new ConvertToPdf("src/model/BD-FO.xsl", "src/data/BD1.xml", "BD1.pdf");
	}

}
