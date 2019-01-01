package thebergers.adventofcode2018.day4;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DayFour {
	
	public static void main(String[] args) {
		final Logger log = LoggerFactory.getLogger(DayFour.class);
		String fileName = "./src/main/resources/dayfour/input.txt";
		try {
			ObservationProcessor processor = ObservationProcessor.getInstance(fileName);
			Integer partOneResult = processor.calculateGuardMinuteCombo();
			log.info("Part One Result = {}", partOneResult);
			Integer partTwoResult = processor.getMostAsleepGuard();
			log.info("Part Two Result = {}", partTwoResult);
		} catch (Exception e) {
			log.error("An error occurred", e);
		}
	}
}
