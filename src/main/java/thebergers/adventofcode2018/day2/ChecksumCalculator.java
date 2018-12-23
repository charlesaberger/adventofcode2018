package thebergers.adventofcode2018.day2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class ChecksumCalculator {

	private List<BoxCode> boxCodes;
	
	private ChecksumCalculator(List<BoxCode> boxCodes) {
		this.boxCodes = boxCodes;
	}
	
	public Integer calculate() {
		Integer appearsTwice = 0;
		Integer appearsThrice = 0;
		for (BoxCode boxCode: boxCodes) {
			if (!boxCode.appearsTwice().isEmpty()) {
				appearsTwice++;
			}
			if (!boxCode.appearsThrice().isEmpty()) {
				appearsThrice++;
			}
		}
		return appearsTwice * appearsThrice;
	}

	public static ChecksumCalculator getInstance(List<String> boxCodes) {
		List<BoxCode> codes = new LinkedList<>();
		boxCodes.forEach(code -> {
			codes.add(new BoxCode(code));
		});
		return new ChecksumCalculator(codes);
	}
	
	public static ChecksumCalculator getInstance(String fileName) throws IOException {
		File file = new File(fileName);
		List<String> codes = new LinkedList<>();
		try(BufferedReader reader = new BufferedReader(new FileReader(file))) {
			while (true) {
				String code = reader.readLine();
				if (code == null) {
					break;
				}
				codes.add(code);
			}
		}
		return getInstance(codes);
	}

	public List<BoxCode> findMatchingCodes() {
		List<BoxCode> boxes = new LinkedList<>();
		int index = 0;
		for (BoxCode box : boxCodes) {
			ListIterator<BoxCode> boxCodeIterator = boxCodes.listIterator(index + 1);
			while (boxCodeIterator.hasNext()) {
				BoxCode box2 = boxCodeIterator.next();
				Integer differences = box.compareWith(box2);
				if (differences == 1) {
					boxes.add(box);
					boxes.add(box2);
					break;
				}
			}
			index++;
		}
		return boxes;
	}
	
	public String findMatchingLetters() {
		List<BoxCode> matchingCodes = findMatchingCodes();
		char[] code1 = matchingCodes.get(0).getCode().toCharArray();
		char[] code2 = matchingCodes.get(1).getCode().toCharArray();
		StringBuffer commonLetters = new StringBuffer();
		int index = 0;
		for (char c : code1) {
			if (c == code2[index]) {
				commonLetters.append(c);
			}
			index++;
		}
		return commonLetters.toString();
	}

}
