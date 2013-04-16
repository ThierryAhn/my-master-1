package tp.rest;

import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import tp.model.City;
import tp.model.CityManagerService;
import tp.model.CityNotFound;
import tp.model.Position;



public class MyClient {
	private static final QName SERVICE_NAME = new QName("http://model.tp/", "CityManagerService");
	private static final QName PORT_NAME = new QName("http://model.tp/", "CityManagerPort");
	private static CityManagerService cityManager;
	
	/**
	 * Return all the cities.
	 */
	public void getCities(){
		System.out.println("\nListe des villes");
		for(City city : cityManager.getCities()){
			System.out.println("+-+ " +city);
		}
		System.out.println("------------------------");
	}
	
	/**
	 * Search for city with a given position.
	 * @param position
	 */
	public void searchForCity(Position position){
		try {
			System.out.println("\nVille a la position : " +position);
			System.out.println("+-+ " +cityManager.searchExactPosition(position));
		} catch (CityNotFound e) {
			System.out.println(e.getMessage());
		}
		System.out.println("------------------------");
	}
	
	/**
	 * Search near cities with a given position.
	 * @param position
	 */
	public void searchNearCity(Position position){
		System.out.println("\nVilles a 10 km de la position : "+position);
		int i = 0;
		for(City city : cityManager.searchNear(position)){
			System.out.println("+-+ " +city);
			i++;
		}
		if(i == 0) System.out.println("+-+ Aucune ville a 10km !");
		System.out.println("------------------------");
	}
	
	/**
	 * Add a city.
	 * @param city
	 */
	public void addCity(City city){
		System.out.println("\nAjout de la ville : "+city);
		try{
			City temp = cityManager.searchExactPosition(city.getPosition());
			if(temp != null)
				System.out.println("+-+ La ville "+temp +" est presente a la position : "+city.getPosition());
		}catch(CityNotFound e){
			cityManager.addCity(city);
			System.out.println("+-+ Ajout effecute");
		}
		System.out.println("------------------------");
	}
	
	/**
	 * Remove a city.
	 * @param city
	 */
	public void removeCity(City city){
		System.out.println("\nSuppression de la ville : "+city);
		cityManager.removeCity(city);
		System.out.println("------------------------");
	}
	
	/**
	 * Search for cities with a given name.
	 * @param cityName
	 */
	public void searchFor(String cityName){
		System.out.println("\nListe des villes portant le nom : "+cityName);
		int i = 0;
		for(City city : cityManager.searchFor(cityName)){
			System.out.println("+-+ " +city);
			i++;
		}
		if(i == 0) System.out.println("+-+ Aucune ville ne porte ce nom !");
		System.out.println("------------------------");
	}
	
	/**
	 * Test
	 * @param args
	 * @throws MalformedURLException
	 */
	public static void main(String args[]) throws MalformedURLException{
		URL wsdlURL = new URL("http://127.0.0.1:8084/citymanager?wsdl");
		Service service = Service.create(wsdlURL, SERVICE_NAME);
		cityManager = service.getPort(PORT_NAME, CityManagerService.class);
		
		MyClient myClient = new MyClient();
		myClient.getCities();
		myClient.searchForCity(new Position(120, 42));
		myClient.searchNearCity(new Position(120, 42));
		myClient.addCity(new City("Zanarkand", 16, 64, "Hyrule"));
		myClient.getCities();
		
		myClient.addCity(new City("Zanarkand", 16, 64, "Hyrule"));
		myClient.getCities();
		
		myClient.removeCity(new City("Zanarkand", 16, 64, "Hyrule"));
		myClient.getCities();
		
		myClient.addCity(new City("Rouen", 49.437994, 1.1329653, "FR"));
		myClient.getCities();
		
		myClient.searchFor("Rouen");
		myClient.searchFor("toto");
		
	}
}
