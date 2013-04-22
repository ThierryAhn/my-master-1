package evolution;

public class Jeu {
	
	private Manette manette;
	
	public Jeu(Manette manette){
		this.manette = manette;
	}
	
	public void mainLoop(){
		if(manette.isLeft()){
			System.out.println("Left");
		}else{
			if(manette.isRight()){
				System.out.println("Right");
			}else{
				if(manette.isPush()){
					System.out.println("Appui");
				}
			}
		}
	}
}
