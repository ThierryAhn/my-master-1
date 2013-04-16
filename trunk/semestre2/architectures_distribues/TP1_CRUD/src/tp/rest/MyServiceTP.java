package tp.rest;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.util.JAXBSource;
import javax.xml.transform.Source;
import javax.xml.ws.Endpoint;
import javax.xml.ws.Provider;
import javax.xml.ws.Service;
import javax.xml.ws.ServiceMode;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceProvider;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.http.HTTPBinding;

import tp.model.City;
import tp.model.CityManager;
import tp.model.CityNotFound;
import tp.model.Message;
import tp.model.Position;
import tp.model.SerializableBoolean;

@WebServiceProvider
                   
@ServiceMode(value=Service.Mode.MESSAGE)
public class MyServiceTP implements Provider<Source> {
	
	/**
	 * Manage the cities.
	 */
	private CityManager cityManager = new CityManager();
	
	private JAXBContext jc;
	
	@javax.annotation.Resource(type=Object.class)
	protected WebServiceContext wsContext;
	
	/**
	 * Create a new instance of JAXBContext.
	 */
	public MyServiceTP(){
		try {
            jc = JAXBContext.newInstance(CityManager.class,City.class,Position.class,SerializableBoolean.class,
            		Message.class);
            
        } catch(JAXBException je) {
            System.out.println("Exception " + je);
            throw new WebServiceException("Cannot create JAXBContext", je);
        }
        //cityManager.addCity(new City("Rouen",49.437994,1.132965,"FR"));
        //cityManager.addCity(new City("Neuor",12,42,"RF"));
	}
	
	/**
	 * Manage differents requests.
	 */
    public Source invoke(Source source) {
    	
        try{
            MessageContext mc = wsContext.getMessageContext();
            String path = (String)mc.get(MessageContext.PATH_INFO);
            String method = (String)mc.get(MessageContext.HTTP_REQUEST_METHOD);
            System.out.println("Got HTTP "+method+" request for "+path);
		    if (method.equals("GET")) 
	                return get(mc);
			if (method.equals("POST")) 
				    return post(source, mc);
           	if (method.equals("PUT")) 
					return put(source, mc); 
           	if (method.equals("DELETE")) 
					return delete(source, mc); 
			throw new WebServiceException("Unsupported method:" +method);  
        } catch(JAXBException je) {
            throw new WebServiceException(je);
        }
    }
    
    /**
     * Http request put to add a new city.
     * @param source
     * @param mc
     * @return
     * @throws JAXBException
     */
	private Source put(Source source, MessageContext mc) throws JAXBException {
		Unmarshaller u = jc.createUnmarshaller();
		City city = (City) u.unmarshal(source);
		Object message = null;
		
		try{ // si une ville existe deja a cette position, on ne l'ajoute pas.
			message = cityManager.searchExactPosition(city.getPosition());
			message = new Message("Une ville existe deja a cette position !");
		}catch (CityNotFound e1) { // si aucune ville n'existe a cette position, on la rajoute.
			message = new SerializableBoolean(cityManager.addCity(city));
		}
		return new JAXBSource(jc, message);
	}
	
	/**
	 * Http request delete to delete a city or all the cities.
	 * @param source
	 * @param mc
	 * @return
	 * @throws JAXBException
	 */
	private Source delete(Source source, MessageContext mc) throws JAXBException {
		String path = (String)mc.get(MessageContext.QUERY_STRING);
		Object message = null; 
		
		if(path != null){
			if(path.equals("deleteAll")){ // suppression de toutes les villes
				message = new SerializableBoolean(cityManager.removeAllCities());
			}else{
				String []tabTemp = path.split("&");
				Position position = new Position(Double.parseDouble(tabTemp[0]), Double.parseDouble(tabTemp[1]));
				
				try{ // si une ville existe a cette position, on la supprime.
					City city = cityManager.searchExactPosition(position);
					message = new SerializableBoolean(cityManager.removeCity(city));
				}catch (CityNotFound e1) { // si aucune ville n'existe a cette position
					message = new Message("Aucune ville n'existe a cette position !");
				}
			}
		}
		return new JAXBSource(jc, message);
	}
	
	/**
	 * Http request post to print a city at given position or a city near 10km from the given position.
	 * @param source
	 * @param mc
	 * @return
	 * @throws JAXBException
	 */
	private Source post(Source source, MessageContext mc) throws JAXBException {
		Unmarshaller u = jc.createUnmarshaller();
		Position position=(Position)u.unmarshal(source);
		Object message = null;
		
		String path = (String)mc.get(MessageContext.QUERY_STRING);
		
		if(path != null){
			if(path.equals("exact")){ // ville a la position exacte
				try {
					message = cityManager.searchExactPosition(position);
				} catch (CityNotFound cnf) {
					message = new Message("Aucune ville n'existe a cette position !");
				}
			}else{
				if(path.equals("near")){ // ville a 10 km
					CityManager temp = new CityManager();
					temp.setCities(cityManager.searchNear(position));
					return new JAXBSource(jc, temp);
				}
			}
		}
		return new JAXBSource(jc, message);
		
	}
	
	/**
	 * Http request to print in the cities or city which name is given.
	 * @param mc
	 * @return Retourne la liste des villes.
	 * @throws JAXBException
	 */
	private Source get(MessageContext mc) throws JAXBException {
		String path = (String)mc.get(MessageContext.PATH_INFO);
		CityManager temp = new CityManager();
		
		
		if(path != null){ 
			String []tabPath = path.split("/");
			if(tabPath[0].equals("city")){
				if(tabPath[1].equals("all")){
					temp = cityManager;
				}else{
					temp.setCities(cityManager.searchFor(tabPath[1]));
				}
			}else{
				if(tabPath[0].equals("country") && tabPath[1] != null){
					temp.setCities(cityManager.getCitiesByCountry(tabPath[1]));
				}
			}
				
		}
		
		/* if(path != null){ 
			if(path.equals("all")){
				temp = cityManager;
			}else{
				temp.setCities(cityManager.searchFor(path));
			}
		} */
		
		
		
		return new JAXBSource(jc, temp);
	}

	public static void main(String args[]) {
	      Endpoint e = Endpoint.create( HTTPBinding.HTTP_BINDING, new MyServiceTP());
	      e.publish("http://127.0.0.1:8084/");
	      // pour arreter : e.stop();
	 }
}
