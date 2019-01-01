package thebergers.adventofcode2018.day5;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Polymer {

	private static final Logger LOG = LoggerFactory.getLogger(Polymer.class);
	
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
		LOG.info("processedPolymer.length()={}, processedPolymer={}", processedPolymer.length(), processedPolymer);
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

}
