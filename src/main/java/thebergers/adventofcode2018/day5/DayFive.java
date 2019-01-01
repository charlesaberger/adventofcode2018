package thebergers.adventofcode2018.day5;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import thebergers.adventofcode2018.utils.Utils;

public class DayFive {

	private static final Logger LOG = LoggerFactory.getLogger(DayFive.class);
	public static void main(String[] args) {
		try {
			String fileName = "./src/main/resources/dayfive/input.txt";
			List<String> polymerStr = Utils.getDataFromFile(fileName);
			Polymer polymer = new Polymer(polymerStr.get(0));
			polymer.processReactions();
			LOG.info("Remaining units = {}", polymer.getRemainingUnits());
			LOG.info("Shortest polymer length = {}", Polymer.removeProblematicUnit(polymer));
		} catch (IOException e) {
			LOG.error("Error loading data", e);
		}
	}
}
