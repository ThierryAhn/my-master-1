package composite;

import java.util.ArrayList;
import java.util.regex.Pattern;

/**
 * Class which represents a section
 * @author ahounfol
 * @date 06/11/12
 *
 */
public class Section implements Element{
	
	private Integer number;
	
	/**
	 * The title of the section.
	 * @see Section#Section(String)
	 */
	private String title;
	/**
	 * collection of child elements
	 * @see Section#add(Element)
	 * @see Section#remove()
	 */
	private ArrayList<Element> arr = new ArrayList<Element>();
	
	/**
	 * Constructor
	 * @param title, the title of the section
	 */
	public Section(String title){
		this.title = title;
	}
	
	/**
	 * Add an element at the end of the composite
	 * @param e the new element to add
	 */
	public void add(Element e){
		arr.add(e);
	}
	
	/**
	 * Remove the last element of the collection
	 * @return the last element of the collection
	 */
	public Element remove(){
		return arr.remove(arr.size() - 1);
	}
	
	/**
	 * Print the title of the section and all his children;
	 */
	public void print() {
		System.out.println("Section n° " + " : " +title);
		for(Element e : arr){
			e.print();
		}
	}

}
