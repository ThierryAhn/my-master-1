package test_unitaire;

import composite.Element;
import composite.Paragraph;
import composite.Style;

/**
 * Test of the class Paragraph
 * @author ahounfol
 * @date 06/11/12
 *
 */
public class TestParagraph {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// gras
		Element paragraph = new Paragraph(Style.GRAS);
		paragraph.print();
		
		// italique
		paragraph = new Paragraph(Style.ITALIQUE);
		paragraph.print();
		
		// souligne
		paragraph = new Paragraph(Style.SOULIGNE);
		paragraph.print();
	}

}
