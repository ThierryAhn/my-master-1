package tp.rest;

import java.util.Map;
import java.util.Scanner;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.util.JAXBSource;
import javax.xml.namespace.QName;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.ws.Dispatch;
import javax.xml.ws.Service;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.http.HTTPBinding;

import tp.model.City;
import tp.model.CityManager;
import tp.model.Message;
import tp.model.Position;
import tp.model.SerializableBoolean;

public class MyClient {
	private Service service;
	private JAXBContext jc;

	private static final QName qname = new QName("", "");
	private static final String url = "http://127.0.0.1:8084";

	public MyClient() {
		try {
			jc = JAXBContext.newInstance(CityManager.class, City.class,
					Position.class, SerializableBoolean.class, Message.class);
		} catch (JAXBException je) {
			System.out.println("Cannot create JAXBContext " + je);
		}
	}
	
	/**
	 * Envoi de requete.
	 * @param position
	 * @throws JAXBException
	 */
	public void searchForCity(Position position) throws JAXBException {
		System.out.println("\nRecherche de la ville a la position : "+position);
		
		service = Service.create(qname);
		service.addPort(qname, HTTPBinding.HTTP_BINDING, url);
		Dispatch<Source> dispatcher = service.createDispatch(qname,
				Source.class, Service.Mode.MESSAGE);
		Map<String, Object> requestContext = dispatcher.getRequestContext();
		requestContext.put(MessageContext.QUERY_STRING, "exact");
		requestContext.put(MessageContext.HTTP_REQUEST_METHOD, "POST");
		Source result = dispatcher.invoke(new JAXBSource(jc, position));
		printSource(result);
	}
	
	public void searchNearCity(Position position) throws JAXBException {
		System.out.println("\nRecherche de la ville proche de la position : "+position);
		
		service = Service.create(qname);
		service.addPort(qname, HTTPBinding.HTTP_BINDING, url);
		Dispatch<Source> dispatcher = service.createDispatch(qname,
				Source.class, Service.Mode.MESSAGE);
		Map<String, Object> requestContext = dispatcher.getRequestContext();
		requestContext.put(MessageContext.QUERY_STRING, "near");
		requestContext.put(MessageContext.HTTP_REQUEST_METHOD, "POST");
		Source result = dispatcher.invoke(new JAXBSource(jc, position));
		printSource(result);
	}
	
	public void addCity(City city) throws JAXBException{
		System.out.println("\nAjout de la ville : "+city);
		
		service = Service.create(qname);
		service.addPort(qname, HTTPBinding.HTTP_BINDING, url);
		Dispatch<Source> dispatcher = service.createDispatch(qname,
				Source.class, Service.Mode.MESSAGE);
		Map<String, Object> requestContext = dispatcher.getRequestContext();
		requestContext.put(MessageContext.HTTP_REQUEST_METHOD, "PUT");
		Source result = dispatcher.invoke(new JAXBSource(jc, city));
		printSource(result);
	}
	
	public void getCity(String path) throws JAXBException{
		//System.out.println("\nAjout de la ville : "+city);
		
		service = Service.create(qname);
		service.addPort(qname, HTTPBinding.HTTP_BINDING, url);
		Dispatch<Source> dispatcher = service.createDispatch(qname,
				Source.class, Service.Mode.MESSAGE);
		Map<String, Object> requestContext = dispatcher.getRequestContext();
		requestContext.put(MessageContext.HTTP_REQUEST_METHOD, "GET");
		requestContext.put(MessageContext.PATH_INFO, path);
		Source result = dispatcher.invoke(new JAXBSource(jc, new Message(path)));
		printSource(result);
	}
	
	
	public void removeCity(City city) throws JAXBException{
		System.out.println("\nSuppression de la ville : "+city);
		
		service = Service.create(qname);
		service.addPort(qname, HTTPBinding.HTTP_BINDING, url);
		Dispatch<Source> dispatcher = service.createDispatch(qname,
				Source.class, Service.Mode.MESSAGE);
		Map<String, Object> requestContext = dispatcher.getRequestContext();
		
		requestContext.put(MessageContext.QUERY_STRING, city.getPosition().getLatitude() 
				+ "&" + city.getPosition().getLongitude());
		
		requestContext.put(MessageContext.HTTP_REQUEST_METHOD, "DELETE");
		Source result = dispatcher.invoke(null);
		printSource(result);
	}
	
	public void removeAllCities() throws JAXBException{
		System.out.println("\nSuppression de toutes les villes");
		
		service = Service.create(qname);
		service.addPort(qname, HTTPBinding.HTTP_BINDING, url);
		Dispatch<Source> dispatcher = service.createDispatch(qname,
				Source.class, Service.Mode.MESSAGE);
		Map<String, Object> requestContext = dispatcher.getRequestContext();
		requestContext.put(MessageContext.QUERY_STRING, "deleteAll");
		requestContext.put(MessageContext.HTTP_REQUEST_METHOD, "DELETE");
		Source result = dispatcher.invoke(null);
		printSource(result);
	}
	
	public void printSource(Source s) {
		try {
			System.out.println("============================= Response Received =========================================");
			TransformerFactory factory = TransformerFactory.newInstance();
			Transformer transformer = factory.newTransformer();
			transformer.transform(s, new StreamResult(System.out));
			System.out.println("\n======================================================================");
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public static void main(String args[]) throws Exception {
		System.out.println("+-+-+-+-+CITY MANAGER+-+-+-+-+");
		
		MyClient client = new MyClient();
		// affichage de toutes les villes dans le navigateur : http://127.0.0.1:8084/all
		client.removeAllCities();
		// affichage de toutes les villes dans le navigateur : http://127.0.0.1:8084/all
		client.addCity(new City("Rouen", 49.443889, 1.103333, "France"));
		client.addCity(new City("Mogadiscio", 2.333333, 48.85, "Somalie"));
		client.addCity(new City("Rouen", 49.443889, 1.103333, "France"));
		client.addCity(new City("Bihorel", 49.455278, 1.116944, "France"));
		client.addCity(new City("Londres", 51.504872, -0.07857, "Angleterre"));
		client.addCity(new City("Paris", 48.856578, 2.351828, "France"));
		client.addCity(new City("Paris", 43.2, -80.38333, "Canada"));
		// affichage de toutes les villes dans le navigateur : http://127.0.0.1:8084/all
		client.addCity(new City("Villers-Bocage", 49.083333, -0.65, "France"));
		client.addCity(new City("Villers-Bocage", 50.021858, 2.326126, "France"));
		// affichage de toutes les villes dans le navigateur : http://127.0.0.1:8084/all
		client.removeCity(new City("Villers-Bocage", 49.083333, -0.65, "France"));
		// affichage de toutes les villes dans le navigateur
		client.removeCity(new City("Londres", 51.504872, -0.07857, "Angleterre"));
		client.removeCity(new City("Londres", 51.504872, -0.07857, "Angleterre")); // probleme exception
		
		client.searchForCity(new Position(49.443889, 1.103333));
		client.searchForCity(new Position(49.083333, -0.65));
		client.searchForCity(new Position(43.2, -80.38));
		
		client.searchNearCity(new Position(48.85, 2.34));
		client.searchNearCity(new Position(42, 64));
		client.searchNearCity(new Position(49.45, 1.11));
		
		// affichage de(des) ville(s) nommee(s) <<Mogadiscio>> dans le navigateur : http://127.0.0.1:8084/Mogadiscio
		// affichage de(des) ville(s) nommee(s) <<Paris>> dans le navigateur : http://127.0.0.1:8084/Paris
		// affichage de(des) ville(s) nommee(s) <<Hyrule>> dans le navigateur : http://127.0.0.1:8084/Hyrule
		
		//client.removeAllCities();
		// affichage de toutes les villes dans le navigateur : http://127.0.0.1:8084/all
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		/*client.addCity(new City("Rouen", 49.443889, 1.103333, "France"));
		client.addCity(new City("Rouen", 49.443889, 1.103333, "Belgique"));
		
		client.addCity(new City("Rouen", 43.2, -80.38333, "Canada"));
		
		//client.removeAllCities();
		//client.removeCity(new City("Rouen", 43.2, -80.38333, "Canada"));
		
		client.searchNearCity(new Position(2.333333, 48.85));*/
		
		//client.searchForCity(new Position(49.443889, 1.103333));
		
		/*System.out.println("++ Afficher l'ensemble des villes(dans le navigateur) : ");
		System.out.println("++++ tapez \"all\" pour afficher toutes les villes ");
		System.out.println("++++ tapez \"Nom de la ville\" pour afficher les villes portant ce nom ");
		
		int choix = -1;
		Scanner sc = new Scanner(System.in);
		
		
		do{
			
			
			
			System.out.println("++ Tapez 0 pour quitter");
			System.out.println("++ Tapez 1 pour executer le test demande");
			
			System.out.print("Tapez votre choix : ");
			choix = sc.nextInt();
			
			switch(choix){
				case 1 :
					System.out.println("Execution du test demande");
					client.removeAllCities();
					client.addCity(new City("Rouen", 49.443889, 1.103333, "France"));
					client.addCity(new City("Mogadiscio", 2.333333, 48.85, "Somalie"));
					break;
			}
		}while(choix != 0);
		System.out.println("+-+-+-+-+FIN DU PROGRAMME+-+-+-+-+");
		System.exit(0);*/
		
		//client.addCity(new City("Neuor",12,42,"RF"));
		
		//client.removeAllCities();
		
		//client.removeCity(new City("Rouen",49.437994,1.132965,"FR"));
		
		/*City city = new City("Rouen", 49.443889, 1.103333, "France");
		client.addCity(city);
		city = new City("Mogadiscio", 2.333333, 48.85, "Somalie");
		client.addCity(city);
		
		client.removeAllCites();
		
		client.addCity(city);*/
		
	}
}
