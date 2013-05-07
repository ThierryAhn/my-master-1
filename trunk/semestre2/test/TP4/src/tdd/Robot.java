package tdd;

public class Robot implements IRobot {
	/**
	 * Position du robot.
	 * @see Robot#Robot(int, int)
	 */
	private Position position;
	/**
	 * Orientation du robot.
	 * @see Robot#Robot(int, int)
	 */
	private Orientation orientation;
	
	/**
	 * Longueur de la salle.
	 * @see Robot#Robot(int, int)
	 */
	private int height;
	/**
	 * Largeur de la salle.
	 * @see Robot#Robot(int, int)
	 */
	private int width;
	
	/**
	 * Constructeur qui initialise la taille de la salle, la position du robot et son orientation.
	 * @param height longeur de la salle.
	 * @param width largeur de la salle.
	 */
	public Robot(int height , int width) {
		this.height = height;
		this.width = width;
		position = new Position();
		
		orientation = Orientation.EST;
	}
	
	/**
	 * Permet d'avancer le robot en fonction de son orientation.
	 */
	@Override
	public void avance() throws CannotGoThereException {
		if(this.getOrientation() == Orientation.NORD){
			if(this.getPosition().getY() < height){
				position.setY(position.getY()+1);
			}else{
				throw new CannotGoThereException();
			}
		}else{
			if(this.getOrientation() == Orientation.EST){
				if(this.getPosition().getX() < width){
					position.setX(position.getX() + 1);
				}else{
					throw new CannotGoThereException();
				}
			}else{
				if(this.getOrientation() == Orientation.SUD){
					if(this.getPosition().getY() > 0 ){
						position.setY(position.getY()-1);
					}else{
						throw new CannotGoThereException();
					}
				}else{// OUEST
					if(this.getPosition().getX() > 0 ){
						position.setX(position.getX() - 1);
					}else{
						throw new CannotGoThereException();
					}
				}
			}
		}
	}
	
	/**
	 * Tourne le robot dans le sens des aiguilles d'une montre.
	 */
	@Override
	public void tourne() {
		if(this.getOrientation() == Orientation.NORD){
			orientation = Orientation.EST;
		}else{
			if(this.getOrientation() == Orientation.EST){
				orientation = Orientation.SUD;
			}else{
				if(this.getOrientation() == Orientation.SUD){
					orientation = Orientation.OUEST;
				}else{
					orientation = Orientation.NORD;
				}
			}
		}
	}
	
	/**
	 * Retourne la postion du robot.
	 * @return position.
	 */
	public Position getPosition(){
		return position;
	}
	
	/**
	 * Retourne l'orientation du robot.
	 * @return orientation.
	 */
	public Orientation getOrientation(){
		return orientation;
	}
	
	/**
	 * Retourne le nombre de lignes de la salle.
	 * @return height;
	 */
	public int getHeight(){
		return height;
	}
	
	/**
	 * Retourne le nombre de colonnes de la salle.
	 * @return width.
	 */
	public int getWidth(){
		return width;
	}
}
