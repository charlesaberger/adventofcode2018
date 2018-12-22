package thebergers.adventofcode2018.day1;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

import thebergers.adventofcode2018.day1.FrequencyData.FrequencyDataType;

public class TestFrequencyCalculator {

	private FrequencyCalculator frequencyCalculator = new FrequencyCalculator();
	
	private FrequencyData frequencyData;
	
	@Test
	public void test1() throws Exception {
		
		List<Integer> data = new LinkedList<>();
		for (int i = 1; i <= 3; i++) {
			data.add(1);
		}
		frequencyData = FrequencyData.getInstance(FrequencyDataType.LIST, data);
		assertThat(frequencyCalculator.calculateResult(frequencyData)).as("Calculate Frequency Result 1").isEqualTo(3);
	}
	
	@Test
	public void test2() throws Exception {
		int[] intArray = new int[] { +1, +1, -2 };
		frequencyData = FrequencyData.getInstance(FrequencyDataType.ARRAY, intArray);
		assertThat(frequencyCalculator.calculateResult(frequencyData)).as("Calculate Frequency Result 2").isEqualTo(0);
	}
	
	@Test
	public void test3() throws Exception {
		
		int[] intArray = new int[] { -1, -2, -3 };
		frequencyData = FrequencyData.getInstance(FrequencyDataType.ARRAY, intArray);
		assertThat(frequencyCalculator.calculateResult(frequencyData)).as("Calculate Frequency Result 3").isEqualTo(-6);
	}

	@Test
	public void testCalibration1() throws Exception {
		int[] intArray = new int[] { 1, -2, 3, 1 };
		frequencyData = FrequencyData.getInstance(FrequencyDataType.ARRAY, intArray);
		assertThat(frequencyCalculator.calculateResult(frequencyData)).as("Calculate Frequency Result").isEqualTo(3);
		assertThat(frequencyCalculator.calibrate(frequencyData)).as("Is calibrated").isEqualTo(2);
	}

	@Test
	public void testCalibration2() throws Exception {
		int[] intArray = new int[] { +1, -1 };
		frequencyData = FrequencyData.getInstance(FrequencyDataType.ARRAY, intArray);
		assertThat(frequencyCalculator.calculateResult(frequencyData)).as("Calculate Frequency Result").isEqualTo(0);
		assertThat(frequencyCalculator.calibrate(frequencyData)).as("Is calibrated").isEqualTo(0);
	}

	@Test
	public void testCalibration3() throws Exception {
		int[] intArray = new int[] { 3, 3, 4, -2, -4 };
		frequencyData = FrequencyData.getInstance(FrequencyDataType.ARRAY, intArray);
		assertThat(frequencyCalculator.calculateResult(frequencyData)).as("Calculate Frequency Result").isEqualTo(4);
		assertThat(frequencyCalculator.calibrate(frequencyData)).as("Is calibrated").isEqualTo(10);
	}

	@Test
	public void testCalibration4() throws Exception {
		int[] intArray = new int[] { -6, 3, 8, 5, -6 };
		frequencyData = FrequencyData.getInstance(FrequencyDataType.ARRAY, intArray);
		assertThat(frequencyCalculator.calculateResult(frequencyData)).as("Calculate Frequency Result").isEqualTo(4);
		assertThat(frequencyCalculator.calibrate(frequencyData)).as("Is calibrated").isEqualTo(5);
	}

	@Test
	public void testCalibration5() throws Exception {
		int[] intArray = new int[] { 7, 7, -2, -7, -4 };
		frequencyData = FrequencyData.getInstance(FrequencyDataType.ARRAY, intArray);
		assertThat(frequencyCalculator.calculateResult(frequencyData)).as("Calculate Frequency Result").isEqualTo(1);
		assertThat(frequencyCalculator.calibrate(frequencyData)).as("Is calibrated").isEqualTo(14);
	}
}
