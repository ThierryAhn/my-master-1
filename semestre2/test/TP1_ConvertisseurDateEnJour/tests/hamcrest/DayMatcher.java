package hamcrest;

import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

public class DayMatcher extends TypeSafeMatcher<Integer>{
	
	private Day day;
	
	public DayMatcher(Day day){
		this.day = day;
	}
	
	@Override
	public void describeTo(Description description) {
		description.appendText("un entier egal a ");
		description.appendValue(day.getIndex());
		description.appendText(" representant le jour de la semaine du " + day);
	}

	@Override
	protected boolean matchesSafely(Integer d) {
		return day.getIndex() == d;
	}
	
	@Factory
	public static Matcher<Integer> isDay(Day d){
		return new DayMatcher(d);
	}

}
