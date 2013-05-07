package tdd;

public class TeleCommande implements ITeleCommande {
	
	/**
	 * Robot a guider.
	 */
	private Robot robot;
	
	/**
	 * Constructeur.
	 * @param r robot.
	 */
	public TeleCommande(Robot r){
		this.robot = r;
	}
	
	/**
	 * Oriente le robot vers le nord.
	 */
	public void orienteToiAuNord() {
		while(robot.getOrientation() != Orientation.NORD){
			robot.tourne();
		}
	}
	
	/**
	 * Oriente le robot vers le sud.
	 */
	public void orienteToiAuSud() {
		while(robot.getOrientation() != Orientation.SUD){
			robot.tourne();
		}
	}

	/**
	 * Oriente le robot vers l'est.
	 */
	public void orienteToiALEst() {
		while(robot.getOrientation() != Orientation.EST){
			robot.tourne();
		}
	}

	/**
	 * Oriente le robot vers l'ouest.
	 */
	public void orienteToiALOuest() {
		while(robot.getOrientation() != Orientation.OUEST){
			robot.tourne();
		}
	}

	/**
	 * Avance le robot du nombre de cases indique en parametre ou proteste s'il ne peut avancer en
	 * restant dans la salle.
	 */
	public void avanceDe(int cases) throws CannotGoThereException {
		for(int i = 0; i < cases; i++){
			robot.avance();
		}
	}

	/**
	 * Avance le robot dans la direction actuellement, et poursuit dans la direction opposee si on
	 * atteint le mur.
	 */
	public void avanceEtRebonditDe(int cases) {
		for(int i = 0; i < cases; i++){
			try {
				robot.avance();
			} catch (CannotGoThereException e) {
				robot.tourne(); robot.tourne();
				i--;
			}
		}
	}
	
	/**
	 * Retourne le robot guide par la telecommande.
	 * @return
	 */
	public Robot getRobot(){
		return robot;
	}

}
