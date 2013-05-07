package proxy_virtuel;

public class ProxyWebImage implements IWebImage{
	
	private WebImage webImage = null;
	
	// url de l'image :
	private final String _URL;
	
	// Donnees factices:
	private int _data;
	
	public ProxyWebImage(String URL){
		_URL=URL;
	}
	
	
	@Override
	public void afficher(int length, int height) {
		if(webImage == null)
			webImage = new WebImage(_URL);
		// Simulation d'affichage :
		System.out.println("J'affiche "+_URL+" en "+length+"x"+height);
	}

}
