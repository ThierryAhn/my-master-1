package calendrier;

public class SimpleTest {

	/**
	 * @param args
	 * @throws InvalidGregorianDateException 
	 */
	public static void main(String[] args) throws InvalidGregorianDateException {
		// TODO Auto-generated method stub
		Zeller z = Zeller.getInstance();
		int jour = z.getDelay(1, 1, 2400);
		String []tableauJour = {"Dim", "Lun", "Mar", "Mer", "Jeu", "Ven", "Sam"};
		System.out.print(jour + " --> ");
		System.out.println(tableauJour[jour-1]);
		
	}

}
