
import java.io.File;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

public class Transformation {

	public static void main(String[] args) throws TransformerException {
		try {			
			File xmlFile = new File("CV.xml");
			File xslFile = new File("CV.xsl");
			File htmlFile = new File("CV.html");
			Source xmlSource = new StreamSource(xmlFile);
			Source xslSource = new StreamSource(xslFile);
			Result htmlResult = new StreamResult(htmlFile);
			TransformerFactory transFact = TransformerFactory.newInstance();
			Transformer trans = transFact.newTransformer(xslSource);
			trans.transform(xmlSource, htmlResult);
			System.out.println("done");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
