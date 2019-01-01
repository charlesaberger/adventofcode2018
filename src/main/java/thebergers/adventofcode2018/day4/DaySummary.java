package thebergers.adventofcode2018.day4;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

public class DaySummary {
	
	private LocalDate date;
	
	private Integer minutesAsleep;
	
	private Integer minutesAwake;
	
	private List<Activity> activities;
	
	private DaySummary(LocalDate date) {
		this.date = date;
		this.activities = new LinkedList<>();
		this.minutesAsleep = 0;
		this.minutesAwake = 0;
	}

	public LocalDate getDate() {
		return date;
	}

	public Integer getMinutesAsleep() {
		return minutesAsleep;
	}

	public Integer getMinutesAwake() {
		return minutesAwake;
	}

	public List<Activity> getActivities() {
		return activities;
	}
	
	public List<Integer> getSleepingMinutes() {
		List<Integer> minutes = new LinkedList<>();
		for (Activity activity : activities) {
			minutes.addAll(activity.getMinutesAsleep());
		}
		return minutes;
	}

	public void addActivity(String asleepStr, String awakeStr) {
		Activity activity = Activity.getInstance(asleepStr, awakeStr);
		activities.add(activity);
		minutesAsleep += activity.getMinutesAsleep().size();
	}
	
	public static DaySummary getInstance(LocalDate date) {
		return new DaySummary(date);
	}
}
