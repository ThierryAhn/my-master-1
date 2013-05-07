package proxy_virtuel;

public class WebImage implements IWebImage {
	// url de l'image :
	private final String _URL;
	
	// Donnees factices:
	private int _data;

	public WebImage(String URL) {
		_URL=URL;
		
		// Telechargement de l'image (simulation) :
		try {
		Thread.sleep(1000);
		} catch (Exception e) {
			System.out.println("Download error !");
		}
		_data=12345;
	}

	public void afficher(int length, int height) {
		// Simulation d'affichage :
		System.out.println("J'affiche "+_URL+" en "+length+"x"+height);
	}
}

