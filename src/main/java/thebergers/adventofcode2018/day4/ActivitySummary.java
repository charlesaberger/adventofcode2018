package thebergers.adventofcode2018.day4;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ActivitySummary {

	private Integer guardId;
	
	private Map<LocalDate, DaySummary> days;

	private ActivitySummary(Integer guardId) {
		this.guardId = guardId;
		days = new HashMap<>();
	}
	
	public Integer getGuardId() {
		return guardId;
	}
	
	public Integer getTotalMinutesAsleep() {
		Integer totalSleep = 0;
		Set<LocalDate> keySet = days.keySet();
		for (LocalDate date : keySet) {
			totalSleep += days.get(date).getMinutesAsleep();
		}
		return totalSleep;
	}

	public Integer getMostSleptMinute() {
		Map<Integer, Integer> minutes = new HashMap<>();
		for (Integer minute : getMinutesAsleep()) {
			Integer sleepCount = null;
			if (minutes.containsKey(minute)) {
				sleepCount = minutes.get(minute);
			} else {
				sleepCount = 0;
			}
			sleepCount++;
			minutes.put(minute, sleepCount);
		}
		Set<Integer> keySet = minutes.keySet();
		Integer mostSleptMinute = -1;
		Integer mostSleeps = -1;
		for (Integer minute : keySet) {
			Integer numberSleeps = minutes.get(minute);
			if (numberSleeps > mostSleeps) {
				mostSleeps = numberSleeps;
				mostSleptMinute = minute;
			}
		}
		return mostSleptMinute;
	}

	private List<Integer> getMinutesAsleep() {
		List<Integer> minutes = new LinkedList<>();
		Set<LocalDate> keySet = days.keySet();
		for (LocalDate date : keySet) {
			minutes.addAll(days.get(date).getSleepingMinutes());
		}
		return minutes;
	}

	public void addActivity(LocalDateTime activityDate, String asleepStr, String awakeStr) {
		LocalDate date = activityDate.toLocalDate();
		DaySummary daySummary = null;
		if (days.containsKey(date)) {
			daySummary = days.get(date);
		} else {
			daySummary = DaySummary.getInstance(date);
		}
		daySummary.addActivity(asleepStr, awakeStr);
		days.put(date,  daySummary);
	}

	public static ActivitySummary getInstance(Integer currentGuardId) {
		return new ActivitySummary(currentGuardId);
	}

	public GuardMostAsleepMinute getMostAsleepMinute() {
		Set<LocalDate> dates = days.keySet();
		Map<Integer, Integer> minuteSleepCount = new HashMap<>();
		for (LocalDate date : dates) {
			DaySummary daySummary = days.get(date);
			List<Integer> minutes = daySummary.getSleepingMinutes();
			for (Integer minute : minutes) {
				Integer count = 0;
				if (minuteSleepCount.containsKey(minute)) {
					count = minuteSleepCount.get(minute);
				}
				count++;
				minuteSleepCount.put(minute, count);
			}
		}
		Integer mostSleptMinute = 0;
		Integer highestSleepCount = 0;
		Set<Integer> minutes = minuteSleepCount.keySet();
		for (Integer minute : minutes) {
			Integer sleepCount = minuteSleepCount.get(minute);
			if (sleepCount > highestSleepCount) {
				highestSleepCount = sleepCount;
				mostSleptMinute = minute;
			}
		}
		return new GuardMostAsleepMinute(mostSleptMinute, highestSleepCount);
	}
}
