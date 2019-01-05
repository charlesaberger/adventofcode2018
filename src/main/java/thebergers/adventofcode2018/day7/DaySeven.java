package thebergers.adventofcode2018.day7;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import thebergers.adventofcode2018.utils.Utils;

public class DaySeven {
	
	private static final Logger LOG = LoggerFactory.getLogger(DaySeven.class);
	
	private static final Integer STEP_MIN_DURATION = 60;
	
	private static final Integer NUMBER_OF_WORKERS = 5;

	public static void main(String[] args) {
		String fileName = "./src/main/resources/dayseven/input.txt";
		try {
			List<String> data = Utils.getDataFromFile(fileName);
			Instructions instructions = Instructions.getInstance(data, STEP_MIN_DURATION);
			String stepOrder = instructions.orderSteps();
			LOG.info("Order of steps is: {}", stepOrder);
			Integer timeToComplete = instructions.calculateDuration(NUMBER_OF_WORKERS);
			LOG.info("Time to complete tasks is {} seconds", timeToComplete);
		} catch (IOException e) {
			LOG.error("An error occurred", e);
		}
	}

}
