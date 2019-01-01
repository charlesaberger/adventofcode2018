package thebergers.adventofcode2018.day4;

import org.assertj.core.api.SoftAssertions;
import org.junit.Test;

public class TestActivity {

	@Test
	public void testCreateActivity() {
		String asleepStr = "[1518-11-01 00:05] falls asleep";
		String awakeStr = "[1518-11-01 00:25] wakes up";
		Activity activity = Activity.getInstance(asleepStr, awakeStr);
		SoftAssertions.assertSoftly(softly -> {
			softly.assertThat(activity.getStartMinute()).as("Check start minute").isEqualTo(5);
			softly.assertThat(activity.getEndMinute()).as("Check end minute").isEqualTo(24);
			softly.assertThat(activity.getMinutesAsleep().size()).as("Check minutes asleep").isEqualTo(20);
		});
	}
}
