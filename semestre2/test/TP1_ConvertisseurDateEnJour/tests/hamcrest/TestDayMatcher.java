package hamcrest;

import static org.hamcrest.MatcherAssert.assertThat;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import calendrier.InvalidGregorianDateException;
import calendrier.Zeller;


public class TestDayMatcher {
	
	private static Zeller zeller = null;
	private GregorianCalendar gc = null;
	
	@Before
	public void setUp() throws Exception {
		zeller = Zeller.getInstance();
	}

	@After
	public void tearDown() throws Exception {
		zeller = null;
	}
	
	@Test
	public void testIsDay1() throws InvalidGregorianDateException{
		assertThat(zeller.getDelay(4, 3, 2013), DayMatcher.isDay(Day.LUNDI));
	}
	
	@Test
	public void testIsDay2(){
		
		for(int i = 0; i < 1000; i++){
			int jour = (int)(Math.random() * (31-1)) + 1;
			int mois = (int)(Math.random() * (12-1)) + 1;
			int annee = (int)(Math.random() * (4000-1582)) + 1582;
			
			gc = new GregorianCalendar(annee, mois-1, jour);
			int jourSemaine = gc.get(Calendar.DAY_OF_WEEK);
			
			Day temp;
			switch (jourSemaine) {
				case 1: temp = Day.DIMANCHE; break;
				case 2: temp = Day.LUNDI; break;
				case 3: temp = Day.MARDI; break;
				case 4: temp = Day.MERCREDI; break;
				case 5: temp = Day.JEUDI; break;
				case 6: temp = Day.VENDREDI; break;
				case 7: temp = Day.SAMEDI; break;
				default: temp = null;
			}
			
			try{
				int result = zeller.getDelay(jour, mois, annee);
				assertThat("Echec du test aleatoire sur la date "+jour+"/"+mois+"/"+annee, result, 
						DayMatcher.isDay(temp));
			}catch(InvalidGregorianDateException e){
				i--;
				continue;
			}
			
		}
		
	}
	
	

}
