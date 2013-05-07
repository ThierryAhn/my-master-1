package test_unitaire;

import composite.Paragraph;
import composite.Section;
import composite.Style;

public class TestComposite {
	public static void main(String ... args){
		Section section = new Section("Introduction");
		section.add(new Paragraph(Style.GRAS));
		
		section.add(new Section("Intro"));
		
		section.print();
	}
}
