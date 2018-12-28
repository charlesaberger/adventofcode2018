package thebergers.adventofcode2018.day3;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DayThree {
	
	public static void main(String[] args) {
		final Logger log = LoggerFactory.getLogger(DayThree.class);
		String fileName = "./src/main/resources/daythree/input.txt";
		try {
			Fabric fabric = Fabric.getInstance(fileName);
			Integer overlaps = fabric.countOverlaps2();
			log.info("Fabric Area = {}, Overlaps = {}", fabric.getArea(), overlaps);
			log.info("Only claim {} has no overlaps", fabric.getClaimWithNoOverlaps());
		} catch (Exception e) {
			log.error("An error occurred", e);
		}
	}
}
