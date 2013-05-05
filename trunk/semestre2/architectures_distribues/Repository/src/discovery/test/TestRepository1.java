package discovery.test;

import discovery.Repository;

public class TestRepository1 {
	
	public static void main(String[] args) throws Exception {
		Repository repository = Repository.createRepository(8084);
		repository.addCityManagerService("CityManagerService1");
		
		
		repository.searchFor("CityManagerService1");
		
		repository.addRepository("localhost:8085");
	}
}
