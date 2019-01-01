package thebergers.adventofcode2018.day6;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import thebergers.adventofcode2018.utils.Utils;

public class DaySix {

	private static final Logger LOG = LoggerFactory.getLogger(DaySix.class);
	
	public static void main(String[] args) {
	
		String fileName = "./src/main/resources/daysix/input.txt";
		try {
			List<String> points = Utils.getDataFromFile(fileName);
			Grid grid = Grid.getInstance(points);
			LOG.info("Largest enclosed area = {}", grid.findLargestEnclosedArea());
			Integer distanceThreshold = 10000;
			LOG.info("Safe region size = {}", grid.calculateSafeRegionSize(distanceThreshold));
		} catch (IOException e) {
			LOG.error("An error occurred", e);
		}
	}
}
