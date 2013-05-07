package calendrier;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Zeller implements ConvertisseurDateEnJour{

	/**
	 * Instance du singleton Zeller.
	 */
	private static Zeller instance = null;

	/**
	 * Constructeur priv� du singleton Zeller.
	 */
	private Zeller(){
		super();
	}

	/**
	 * Renvoie une instance de la classe Zeller.
	 * @return Retourne l'instance du singleton Zeller. 
	 */
	public static Zeller getInstance(){
		if(instance == null){
			// synchronized empeche toute instanciation multiple meme par differents threads.
			synchronized(Zeller.class){
				if(instance == null){
					instance = new Zeller();
				}
			}
		}
		return instance;
	}

	@Override
	public int getDelay(int jour, int mois, int annee) throws InvalidGregorianDateException{

		String dateToValidate = jour + "/" + mois + "/" +annee;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setLenient(false);

		try{
			Date date = sdf.parse(dateToValidate);
		}catch(ParseException e){
			throw new InvalidGregorianDateException("Date invalide");
		}

		if((jour <= 15) && (mois <= 10) && (annee <= 1582)){
			throw new InvalidGregorianDateException("Date avant 15 octobre 1582");
		}

		if (mois == 1 || mois == 2) {
			mois += 12;
			annee--;
		}

		int d = annee - (int) Math.floor(annee / 100) * 100;
		int s = (int) Math.floor(annee / 100);
		int k = jour + (int) Math.floor(2.6 * (mois + 1)) + (int) Math.floor(s / 4) - 2 * s + d + (int) Math.floor(d / 4);

		int result = (mod(k, 7) == 0) ? 7 : mod(k, 7);

		return result;
	}
	
	
	private int mod(int i, int j) {
		int result = i % j;
		if (result < 0)
			result += j;
		return result;
	}
	
	

}
