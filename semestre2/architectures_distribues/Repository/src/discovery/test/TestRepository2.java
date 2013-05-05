package discovery.test;

import discovery.Repository;

public class TestRepository2 {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		Repository repository = Repository.createRepository(8085);
		repository.addCityManagerService("CityManagerService2");
		repository.addCityManagerService("CityManagerService3");
		
		repository.searchFor("CityManagerService2");
		repository.searchFor("CityManagerService3");
		
		repository.addRepository("127.0.0.1:8084");
		
		
	}

}
