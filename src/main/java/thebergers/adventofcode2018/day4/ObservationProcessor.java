package thebergers.adventofcode2018.day4;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import thebergers.adventofcode2018.utils.Utils;

public class ObservationProcessor {
	
	private static final Logger LOG = LoggerFactory.getLogger(ObservationProcessor.class);
	
	private List<String> sortedInput;
	
	private Map<Integer, ActivitySummary> guardActivitySummaries = new HashMap<>();
	
	private ObservationProcessor(List<String> sortedInput) {
		this.sortedInput = sortedInput;
	}
	
	public static ObservationProcessor getInstance(List<String> rawTestData) {
		rawTestData.sort(String.CASE_INSENSITIVE_ORDER);
		ObservationProcessor observationProcessor = new ObservationProcessor(rawTestData);
		observationProcessor.parseData();
		return observationProcessor;
	}

	public static ObservationProcessor getInstance(String fileName) throws IOException {
		List<String> data = Utils.getDataFromFile(fileName);
		return getInstance(data);
	}

	public List<String> getSortedData() {
		return sortedInput;
	}

	public Integer calculateGuardMinuteCombo() {
		Integer guardId = findSleepiestGuard();
		Integer minuteNumber = findMinuteMostAsleep(guardId);
		return guardId * minuteNumber;
	}

	private void parseData() {
		Integer index = 0;
		Integer currentGuardId = -1;
		while (index < sortedInput.size()) {
			String record = sortedInput.get(index);
			String dateStr = record.substring(record.indexOf('[') + 1, record.indexOf(']'));
			LocalDateTime activityDate = LocalDateTime.parse(dateStr, DayFourUtils.getDateFormatter());
			String activityStr = record.substring(record.indexOf(']') + 2);
			ActivitySummary activitySummary = null;
			if (activityStr.startsWith("Guard")) {
				currentGuardId = parseGuardId(activityStr);
				index++;
			}
			if (guardActivitySummaries.containsKey(currentGuardId)) {
				activitySummary = guardActivitySummaries.get(currentGuardId);
			} else {
				activitySummary = ActivitySummary.getInstance(currentGuardId);
			}
			String asleepStr = sortedInput.get(index++);
			String awakeStr = sortedInput.get(index++);
			activitySummary.addActivity(activityDate, asleepStr, awakeStr);
			guardActivitySummaries.put(currentGuardId, activitySummary);
		}
	}
	
	private Integer parseGuardId(String activityStr) {
		String[] parts = activityStr.split(" ");
		String guardIdStr = parts[1].substring(1);
		return Integer.parseInt(guardIdStr);
	}

	private Integer findMinuteMostAsleep(Integer guardId) {
		ActivitySummary activitySummary = guardActivitySummaries.get(guardId);
		return activitySummary.getMostSleptMinute();
	}

	private Integer findSleepiestGuard() {
		Integer sleepiestGuardId = 0;
		Integer highestSleepTime = 0;
		Set<Integer> guardIds = guardActivitySummaries.keySet();
		for (Integer guardId : guardIds) {
			ActivitySummary activitySummary = guardActivitySummaries.get(guardId);
			Integer sleepTime = activitySummary.getTotalMinutesAsleep();
			if (sleepTime > highestSleepTime) {
				highestSleepTime = sleepTime;
				sleepiestGuardId = guardId;
			}
		}
		return sleepiestGuardId;
	}

	public Integer getMostAsleepGuard() {
		Set<Integer> guardIds = guardActivitySummaries.keySet();
		Map<Integer, GuardMostAsleepMinute> guardMinutes = new HashMap<>();
		for (Integer guardId : guardIds) {
			GuardMostAsleepMinute gmam = guardActivitySummaries.get(guardId).getMostAsleepMinute();
			guardMinutes.put(guardId, gmam);
		}
		guardIds = guardMinutes.keySet();
		Integer sleepiestGuardId = 0;
		Integer mostSleptMinute = 0;
		Integer highestSleepCount= 0;
		for (Integer guardId : guardIds) {
			GuardMostAsleepMinute gmam = guardMinutes.get(guardId);
			LOG.info("guardId: {}, {}, result={}", guardId, gmam, guardId * gmam.getMinute());
			Integer minute = gmam.getMinute();
			Integer sleepCount = gmam.getAsleepCount();
			if (sleepCount > highestSleepCount) {
				highestSleepCount = sleepCount;
				mostSleptMinute = minute;
				sleepiestGuardId = guardId;
			}
		}
		LOG.info("sleepiestGuardId: {}, mostSleptMinute: {}", sleepiestGuardId, mostSleptMinute);
		return sleepiestGuardId * mostSleptMinute;
	}
}
