package evolution;

public class JeuMain {
	
	public static void main(String ... args){
		Clavier clavier = new Clavier();
		Jeu jeu = new Jeu(new AdapterClavier(clavier));
		jeu.mainLoop();
	}
}	
