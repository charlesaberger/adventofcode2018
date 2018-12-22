package thebergers.adventofcode2018.day1;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import java.util.List;

import org.assertj.core.api.SoftAssertions;
import org.junit.Test;

import thebergers.adventofcode2018.day1.FrequencyData.FrequencyDataType;

public class TestFrequencyDataUrl {

	private FrequencyData frequencyData;
	
	@Test
	public void testInvalidDataSource() throws Exception {
		int[] data = { 1, 2, 3 };
		assertThatExceptionOfType(DataParseException.class)
		.isThrownBy(() -> { FrequencyData.getInstance(FrequencyDataType.URL, data); })
		.withMessageContaining("must be a String");
	}
	
	@Test
	public void testBadUrl() throws Exception {
		String url = "http://httpsta.us/404";
		assertThatExceptionOfType(DataParseException.class)
		.isThrownBy(() -> { FrequencyData.getInstance(FrequencyDataType.URL, url); })
		.withMessageStartingWith("IOException for url");
	}
	
	@Test
	public void testInvalidContent() throws Exception {
		String url = "https://www.google.co.uk";
		assertThatExceptionOfType(DataParseException.class)
		.isThrownBy(() -> { FrequencyData.getInstance(FrequencyDataType.URL, url); })
		.withMessageStartingWith("Content Type");
	}
	
	@Test
	public void testGoodData() throws Exception {
		String url = "https://adventofcode.com/2018/day/1/input";
		frequencyData = FrequencyData.getInstance(FrequencyDataType.URL, url);
		SoftAssertions.assertSoftly(softly -> {
			List<Integer> data = frequencyData.getData();
			softly.assertThat(data).as("List is not null").isNotNull();
			int dataSize = data.size();
			softly.assertThat(dataSize).as("List has elements").isGreaterThan(0);
			softly.assertThat(data.get(0)).as("First element").isEqualTo(3);
			softly.assertThat(data.get(1)).as("Second element").isEqualTo(15);
			softly.assertThat(data.get(2)).as("Third element").isEqualTo(-1);
			softly.assertThat(data.get(3)).as("Fourth element").isEqualTo(-18);
			softly.assertThat(data.get(4)).as("Fifth element").isEqualTo(3);
			// check last 5 elements of list
			softly.assertThat(data.get(dataSize - 4)).as("Last but 4 element").isEqualTo(9);
			softly.assertThat(data.get(dataSize - 3)).as("Last but 3 element").isEqualTo(8);
			softly.assertThat(data.get(dataSize - 2)).as("Last but 2 element").isEqualTo(-13);
			softly.assertThat(data.get(dataSize - 1)).as("Last but 1 element").isEqualTo(4);
			softly.assertThat(data.get(dataSize)).as("Last element").isEqualTo(80915);
		});
	}
}
