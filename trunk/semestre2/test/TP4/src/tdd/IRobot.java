package tdd;

public interface IRobot {
	public void avance() throws CannotGoThereException;
	public void tourne();
	public int getHeight();
	public int getWidth();
	public Position getPosition();
	public Orientation getOrientation();
}
