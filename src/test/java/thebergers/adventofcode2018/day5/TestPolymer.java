package thebergers.adventofcode2018.day5;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class TestPolymer {
	
	private Polymer polymer;
	
	private String polymerStr;
	
	private String processedPolymer;

	@Test
	public void testReaction1() {
		polymerStr = "aA";
		polymer = new Polymer(polymerStr);
		processedPolymer = polymer.processReactions();
		assertThat(processedPolymer).as("Test reaction").isEqualTo("");
		assertThat(polymer.getRemainingUnits()).as("Remaining Units").isEqualTo(0);
	}

	@Test
	public void testReaction2() {
		polymerStr = "Bb";
		polymer = new Polymer(polymerStr);
		assertThat(polymer.processReactions()).as("Test reaction").isEqualTo("");
		assertThat(polymer.getRemainingUnits()).as("Remaining Units").isEqualTo(0);
	}

	@Test
	public void testReaction3() {
		polymerStr = "abAB";
		polymer = new Polymer(polymerStr);
		processedPolymer = polymer.processReactions();
		assertThat(processedPolymer).as("Test reaction 1").isEqualTo(polymerStr);
		assertThat(polymer.getRemainingUnits()).as("Remaining Units").isEqualTo(4);
	}

	@Test
	public void testReaction4() {
		polymerStr = "dabAcCaCBAcCcaDA";
		polymer = new Polymer(polymerStr);
		processedPolymer = polymer.processReactions();
		assertThat(processedPolymer).as("Test reaction 1").isEqualTo("dabCBAcaDA");
		assertThat(polymer.getRemainingUnits()).as("Remaining Units").isEqualTo(10);
	}

}
