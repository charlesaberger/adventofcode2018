package thebergers.adventofcode2018.day4;

import java.util.LinkedList;
import java.util.List;

public class TestFixtureFactory {

	public List<String> getRawTestData() {

		List<String> rawData = new LinkedList<>();
		rawData.add("[1518-11-02 00:40] falls asleep");
		rawData.add("[1518-11-01 00:25] wakes up");
		rawData.add("[1518-11-01 00:00] Guard #10 begins shift");
		rawData.add("[1518-11-01 00:30] falls asleep");
		rawData.add("[1518-11-04 00:36] falls asleep");
		rawData.add("[1518-11-01 23:58] Guard #99 begins shift");
		rawData.add("[1518-11-01 00:05] falls asleep");
		rawData.add("[1518-11-02 00:50] wakes up");
		rawData.add("[1518-11-04 00:02] Guard #99 begins shift");
		rawData.add("[1518-11-01 00:55] wakes up");
		rawData.add("[1518-11-03 00:05] Guard #10 begins shift");
		rawData.add("[1518-11-05 00:03] Guard #99 begins shift");
		rawData.add("[1518-11-03 00:24] falls asleep");
		rawData.add("[1518-11-04 00:46] wakes up");
		rawData.add("[1518-11-03 00:29] wakes up");
		rawData.add("[1518-11-05 00:45] falls asleep");
		rawData.add("[1518-11-05 00:55] wakes up");
		return rawData;
	}
}
