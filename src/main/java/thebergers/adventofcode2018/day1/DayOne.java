package thebergers.adventofcode2018.day1;

import thebergers.adventofcode2018.day1.FrequencyData.FrequencyDataType;

public class DayOne {

	public static void main(String[] args) {

		String filename = "./src/main/resources/dayone/input.txt";
		try {
			FrequencyData frequencyData = FrequencyData.getInstance(FrequencyDataType.FILE, filename);
			FrequencyCalculator calculator = new FrequencyCalculator();
			Integer result = calculator.calculateResult(frequencyData);
			System.out.println(String.format("Result = %d", result));
			Integer calibrationFrequency = calculator.calibrate(frequencyData);
			System.out.println(String.format("calibrationFrequency = %d", calibrationFrequency));
		} catch (DataParseException e) {
			System.out.println(e);
		}

	}

}
