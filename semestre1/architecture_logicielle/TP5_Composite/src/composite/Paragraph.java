package composite;

/**
 * Class which represents a paragraph
 * @author ahounfol
 * @date 06/11/12
 *
 */
public class Paragraph implements Element {
	/**
	 * The style of the paragraph.
	 * @see Paragraph#Paragraph(Style)
	 */
	private Style style;
	
	/**
	 * Constructor
	 * @param style, the style of the paragraph
	 */
	public Paragraph(Style style){
		this.style = style;
	}
	
	/**
	 * Print the paragraph
	 */
	public void print() {
		String s = "";
		switch(style){
			case GRAS :
				s ="<GRAS> Paragraphe </GRAS>";
				break;
			case ITALIQUE : 
				s ="<ITALIQUE> Paragraphe </ITALIQUE>";
				break;
			case SOULIGNE : 
				s ="<SOULIGNE> Paragraphe </SOULIGNE>";
				break;
		}
		System.out.println(s);
	}

}
