package thebergers.adventofcode2018.day2;

import java.util.List;

public class DayTwo {

	public static void main(String[] args) {
		String fileName = "./src/main/resources/daytwo/input.txt";
		try {
			ChecksumCalculator cc = ChecksumCalculator.getInstance(fileName);
			Integer result = cc.calculate();
			System.out.println(String.format("Checksum = %d", result));
			List<BoxCode> boxes = cc.findMatchingCodes();
			System.out.println(String.format("Box 1: %s, Box 2: %s, matchingLetters: %s", boxes.get(0).getCode(), 
					boxes.get(1).getCode(), cc.findMatchingLetters()));
		} catch (Exception e) {
			System.out.println(e);
		}

	}

}
