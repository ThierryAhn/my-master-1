//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2013.05.09 at 04:42:51 PM CEST 
//


package util;

import javax.xml.bind.annotation.XmlRegistry;



/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the fr.univ_rouen.bd package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: fr.univ_rouen.bd
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Bds }
     * 
     */
    public Bds createBds() {
        return new Bds();
    }

    /**
     * Create an instance of {@link Bds.Bd }
     * 
     */
    public Bds.Bd createBdsBd() {
        return new Bds.Bd();
    }

    /**
     * Create an instance of {@link Bds.Bd.Informations }
     * 
     */
    public Bds.Bd.Informations createBdsBdInformations() {
        return new Bds.Bd.Informations();
    }

    /**
     * Create an instance of {@link Bds.Bd.Image }
     * 
     */
    public Bds.Bd.Image createBdsBdImage() {
        return new Bds.Bd.Image();
    }

    /**
     * Create an instance of {@link Bds.Bd.Description }
     * 
     */
    public Bds.Bd.Description createBdsBdDescription() {
        return new Bds.Bd.Description();
    }

}