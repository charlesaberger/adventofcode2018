package thebergers.adventofcode2018.day5;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Polymer {
	
	private String originalPolymer;
	
	private String processedPolymer;
	
	
	public Polymer(String polymerStr) {
		this.originalPolymer = polymerStr;
		this.processedPolymer = originalPolymer;
	}

	public String processReactions() {
		boolean stop = false;
		while (!stop) {
			int reactiveIndex = hasReactiveUnits();
			stop = reactiveIndex < 0;
			if (!stop) {
				performReaction(reactiveIndex);
			}
		}
		return processedPolymer;
	}

	private int hasReactiveUnits() {
		char[] charArray = processedPolymer.toCharArray();
		for (int index = 0; index < charArray.length - 1; index++) {
			Character ch = Character.valueOf(charArray[index]);
			int chAscii = (int)ch;
			Character nextCh = Character.valueOf(charArray[index + 1]);
			int nextChAscii = (int)nextCh;
			if (chAscii + 32 == nextChAscii || chAscii - 32 == nextChAscii) {
				return index;
			}
		}
		return -1;
	}

	private void performReaction(int reactiveIndex) {
		if (processedPolymer.length() <= 2) {
			processedPolymer = "";
		} else {
			processedPolymer = processedPolymer.substring(0, reactiveIndex) + processedPolymer.substring(reactiveIndex + 2);
		}
	}

	public Integer getRemainingUnits() {
		return processedPolymer.length();
	}

	public String removeUnit(String unitToRemove) {
		return originalPolymer.replace(unitToRemove.toLowerCase(), "").replace(unitToRemove.toUpperCase(), "");
	}

	public static Integer removeProblematicUnit(Polymer polymer) {
		Map<String, Integer> polymerLengths = new HashMap<>();
		for (int i = 65; i <= 90; i++) {
			Character unitToRemoveCh = (char)i;
			String unitToRemoveStr = String.valueOf(unitToRemoveCh);
			String newPolymerStr = polymer.removeUnit(unitToRemoveStr);
			Polymer newPolymer = new Polymer(newPolymerStr);
			newPolymer.processReactions();
			polymerLengths.put(unitToRemoveStr, newPolymer.getRemainingUnits());
		}
		Integer shortestLength = Integer.MAX_VALUE;
		Set<String> units = polymerLengths.keySet();
		for (String unit : units) {
			Integer unitsRemaining = polymerLengths.get(unit);
			if (unitsRemaining < shortestLength) {
				shortestLength = unitsRemaining;
			}
		}
		return shortestLength;
	}

}
