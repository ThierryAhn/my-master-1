package tp.model;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This class helps to serialize different data.
 * 
 * @author Folabi & Caron & Saval.
 * @date 20/02/2013.
 *
 */
@XmlRegistry
public class ObjectFactory {

	private final static QName Position_QNAME = new QName("m2GIL:rest:tp", "Position");
	private final static QName City_QNAME = new QName("m2GIL:rest:tp", "City");
    private final static QName CityManager_QNAME = new QName("m2GIL:rest:tp", "CityManager");
    private final static QName SerializableBoolean_QNAME = new QName("m2GIL:rest:tp", "SerializableBoolean");
    private final static QName Message_QNAME = new QName("m2GIL:rest:tp", "Message");
    
    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for this package
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link City }
     * 
     */
    public City createCity() {
        return new City();
    }
    
    /**
     * Create an instance of {@link Position }
     * 
     */
    public Position createPosition() {
        return new Position();
    }
    
    /**
     * Create an instance of {@link CityManager }
     * 
     */
    public CityManager createCityManager() {
        return new CityManager();
    }
    
    /**
     * Create an instance of {@link SerializableBoolean }
     * 
     */
    public SerializableBoolean createSerializableBoolean(){
    	return new SerializableBoolean();
    }
    
    /**
     * Create an instance of {@link Message }
     * 
     */
    public Message createMessage(){
    	return new Message();
    }
    
    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link City }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "m2GIL:rest:tp", name = "City")
    public JAXBElement<City> createCity(City value) {
        return new JAXBElement<City>(City_QNAME, City.class, null, value);
    }
    
    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Position }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "m2GIL:rest:tp", name = "Position")
    public JAXBElement<Position> createPosition(Position value) {
        return new JAXBElement<Position>(Position_QNAME, Position.class, null, value);
    }
    
    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CityManager }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "m2GIL:rest:tp", name = "CityManager")
    public JAXBElement<CityManager> createCityManager(CityManager value) {
        return new JAXBElement<CityManager>(CityManager_QNAME, CityManager.class, null, value);
    }
    
    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SerializableBoolean }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "m2GIL:rest:tp", name = "SerializableBoolean")
    public JAXBElement<SerializableBoolean> createSerializableBoolean(SerializableBoolean value) {
        return new JAXBElement<SerializableBoolean>(SerializableBoolean_QNAME, SerializableBoolean.class, null, value);
    }
    
    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Message }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "m2GIL:rest:tp", name = "Message")
    public JAXBElement<Message> createMessage(Message value) {
        return new JAXBElement<Message>(SerializableBoolean_QNAME, Message.class, null, value);
    }
    
}
