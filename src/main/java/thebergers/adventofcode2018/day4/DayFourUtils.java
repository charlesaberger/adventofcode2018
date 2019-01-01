package thebergers.adventofcode2018.day4;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

public class DayFourUtils {

	private DayFourUtils() {
		
	}
	
	public static DateTimeFormatter getDateFormatter() {
		DateTimeFormatterBuilder builder = new DateTimeFormatterBuilder();
		builder.appendPattern("yyyy-MM-dd HH:mm");
		return builder.toFormatter();
	}
}
