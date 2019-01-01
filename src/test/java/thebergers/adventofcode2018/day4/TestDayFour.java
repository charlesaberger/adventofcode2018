package thebergers.adventofcode2018.day4;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.util.List;

import org.assertj.core.api.SoftAssertions;
import org.junit.Test;

public class TestDayFour {
	
	private TestFixtureFactory testFixtureFactory = new TestFixtureFactory();
	
	private ObservationProcessor observationProcessor;
	
	@Test
	public void testSortInput() {
		observationProcessor = ObservationProcessor.getInstance(testFixtureFactory.getRawTestData());
		List<String> sortedData = observationProcessor.getSortedData();
		SoftAssertions.assertSoftly(softly -> {
			softly.assertThat(sortedData).as("Is not null").isNotNull();
			softly.assertThat(sortedData.size()).as("Contains 17 entries").isEqualTo(17);
			softly.assertThat(sortedData.get(0)).as("First entry").isEqualTo("[1518-11-01 00:00] Guard #10 begins shift");
			softly.assertThat(sortedData.get(1)).as("Second Entry").isEqualTo("[1518-11-01 00:05] falls asleep");
			softly.assertThat(sortedData.get(8)).as("Ninth entry").isEqualTo("[1518-11-03 00:05] Guard #10 begins shift");
			softly.assertThat(sortedData.get(9)).as("Tenth entry").isEqualTo("[1518-11-03 00:24] falls asleep");
			softly.assertThat(sortedData.get(15)).as("Sixteenth entry").isEqualTo("[1518-11-05 00:45] falls asleep");
			softly.assertThat(sortedData.get(16)).as("Last entry").isEqualTo("[1518-11-05 00:55] wakes up");
		});
	}
	
	@Test
	public void testStrategy1() {
		observationProcessor = ObservationProcessor.getInstance(testFixtureFactory.getRawTestData());
		assertThat(observationProcessor.calculateGuardMinuteCombo()).as("Calculate Guard Minute combo").isEqualTo(240);
	}
	
	@Test
	public void testPartTwo() {
		observationProcessor = ObservationProcessor.getInstance(testFixtureFactory.getRawTestData());
		assertThat(observationProcessor.getMostAsleepGuard()).as("Calculate Guard Minute combo").isEqualTo(4455);
	}
	
	@Test
	public void testWithActualData() throws IOException {
		String fileName = "./src/main/resources/dayfour/input.txt";
		observationProcessor = ObservationProcessor.getInstance(fileName);
		Integer guardMinuteCombo = observationProcessor.calculateGuardMinuteCombo();
		assertThat(guardMinuteCombo).as("guardMinuteCombo is not null").isNotNull();
		assertThat(guardMinuteCombo).as("guardMinuteCombo is greater than zero").isGreaterThan(0);
	}
}
