package thebergers.adventofcode2018.day4;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

public class Activity {

	private Action action;
	
	private Integer startMinute;
	
	private Integer endMinute;
	
	private Activity(Action action, Integer startMinute, Integer endMinute) {
		this.action = action;
		this.startMinute = startMinute;
		this.endMinute = endMinute;
	}

	public Action getAction() {
		return action;
	}

	public Integer getStartMinute() {
		return startMinute;
	}

	public Integer getEndMinute() {
		return endMinute;
	}
	
	public List<Integer> getMinutesAsleep() {
		List<Integer> minutes = new LinkedList<>();
		for (Integer i = startMinute; i <= endMinute; i++) {
			minutes.add(i);
		}
		return minutes;
	}

	@Override
	public String toString() {
		return "Activity [action=" + action + ", startMinute=" + startMinute + ", endMinute=" + endMinute + "]";
	}

	public static Activity getInstance(String asleepStr, String awakeStr) {
		String asleepDateStr = asleepStr.substring(asleepStr.indexOf('[') + 1, asleepStr.indexOf(']'));
		LocalDateTime asleepDate = LocalDateTime.parse(asleepDateStr, DayFourUtils.getDateFormatter());
		String awakeDateStr = awakeStr.substring(awakeStr.indexOf('[') + 1, awakeStr.indexOf(']'));
		LocalDateTime awakeDate = LocalDateTime.parse(awakeDateStr, DayFourUtils.getDateFormatter());
		Integer startMinute = asleepDate.getMinute();
		Integer endMinute = awakeDate.getMinute() - 1;
		return new Activity(Action.FALL_ASLEEP, startMinute, endMinute);
	}
	
	
}
