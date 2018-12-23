package thebergers.adventofcode2018.day2;

import java.util.HashMap;
import java.util.Map;

public class BoxCode {

	private static final String EMPTY_STRING = "";
	
	private String code;
	
	private Map<String, Integer> letters = new HashMap<>();
	
	public BoxCode(String code) {
		this.code = code;
		parseBoxCode();
	}
	
	public String getCode() {
		return code;
	}
	
	public String appearsTwice() {
		for (Map.Entry<String, Integer> entry: letters.entrySet()) {
			if (entry.getValue() == 2) {
				return entry.getKey();
			}
		}
		return EMPTY_STRING;
	}
	
	public String appearsThrice() {
		for (Map.Entry<String, Integer> entry: letters.entrySet()) {
			if (entry.getValue() == 3) {
				return entry.getKey();
			}
		}
		return EMPTY_STRING;
	}
	
	private Map<String, Integer> parseBoxCode() {
		for (char c : code.toCharArray()) {
			String letter = new String(new char[] {c});
			Integer count = 0;
			if (letters.containsKey(letter)) {
				count = letters.get(letter);
			}
			count++;
			letters.put(letter, count);
		}
		return letters;
	}

	public Integer compareWith(BoxCode boxCode) {
		char[] charArrayIn = boxCode.getCode().toCharArray();
		char[] charArrayThis = this.code.toCharArray();
		int i = 0;
		Integer differences = 0;
		for (char c : charArrayThis) {
			if (charArrayIn[i] != c) {
				differences++;
			}
			i++;
		}
		return differences;
	}

	@Override
	public String toString() {
		return "BoxCode [code=" + code + "]";
	}
}
