package chapert1;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class AvoidCreateObject {

	public static void main(String args[]){
		
	}
}

class Person{
	
	private final Date birthDate;
	
	public Person(Date birthDate){
		this.birthDate = birthDate;
	}
	
	//DON'T DO THIS!!
	public boolean isBabyBoomer(){
		// Unnecessary allocation of expensive object
		Calendar gmtCal = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
		gmtCal.set(1946, Calendar.JANUARY, 1, 0, 0, 0);
		Date boomStart = gmtCal.getTime();
		gmtCal.set(1965, Calendar.JANUARY, 1, 0, 0, 0);
		Date boomEnd = gmtCal.getTime();
		return birthDate.compareTo(boomStart) >= 0 && birthDate.compareTo(boomEnd) < 0;
	}
}

class PersonCorrect{
	
	private final Date birthDate;
	private static final Date BOOM_START;
	private static final Date BOOM_END;
	
	
	public PersonCorrect(Date birthDate) {
		this.birthDate = birthDate;
	}
	
	static{
		
		Calendar gmtCal = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
		gmtCal.set(1946, Calendar.JANUARY, 1, 0, 0, 0);
		BOOM_START = gmtCal.getTime();
		gmtCal.set(1965, Calendar.JANUARY, 1, 0, 0, 0);
		BOOM_END = gmtCal.getTime();
		
	}
	
	public boolean isBabyBoomer(){
		return birthDate.compareTo(BOOM_START) >= 0 && birthDate.compareTo(BOOM_END) < 0;
	}
}




















