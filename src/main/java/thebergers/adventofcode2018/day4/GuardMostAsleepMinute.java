package thebergers.adventofcode2018.day4;

public class GuardMostAsleepMinute {

	private final Integer minute;
	
	private final Integer asleepCount;
	
	public GuardMostAsleepMinute(Integer minute, Integer asleepCount) {
		this.minute = minute;
		this.asleepCount = asleepCount;
	}

	public Integer getMinute() {
		return minute;
	}

	public Integer getAsleepCount() {
		return asleepCount;
	}

	@Override
	public String toString() {
		return "GuardMostAsleepMinute [minute=" + minute + ", asleepCount=" + asleepCount + "]";
	}
}
