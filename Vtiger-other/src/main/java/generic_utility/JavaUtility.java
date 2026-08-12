package generic_utility;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class JavaUtility {
	public static int generateRandomNumber() {
		
		double randomDouble = Math.random();
		double ran = randomDouble*1000; 
		int random = (int) ran;
		return random;
		
	}
	
//	need to ask students did they perform or not !!!
	public String getCurrentDateTime() {
//		output :- 135120_20072026
		return null;
	}
	
	  public static String dateTime() {
	        // HHmmss = time without colons (24-hr)
	        // _ = underscore separator
	        // ddMMuuuu = day, month, year
	        return LocalDateTime.now()
	                .format(DateTimeFormatter.ofPattern("HHmmss_ddMMuuuu")); //+ "@gmail.com";
	    }

}
