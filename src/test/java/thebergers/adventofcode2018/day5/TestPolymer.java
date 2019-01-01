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
	
	@Test
	public void testRemoveUnitA() {
		polymerStr = "dabAcCaCBAcCcaDA";
		polymer = new Polymer(polymerStr);
		assertThat(polymer.removeUnit("a")).as("Remove a").isEqualTo("dbcCCBcCcD");
	}
	
	@Test
	public void testRemoveUnitB() {
		polymerStr = "dabAcCaCBAcCcaDA";
		polymer = new Polymer(polymerStr);
		assertThat(polymer.removeUnit("b")).as("Remove b").isEqualTo("daAcCaCAcCcaDA");
	}
	
	@Test
	public void testRemoveUnitC() {
		polymerStr = "dabAcCaCBAcCcaDA";
		polymer = new Polymer(polymerStr);
		assertThat(polymer.removeUnit("c")).as("Remove c").isEqualTo("dabAaBAaDA");
	}
	
	@Test
	public void testRemoveUnitD() {
		polymerStr = "dabAcCaCBAcCcaDA";
		polymer = new Polymer(polymerStr);
		assertThat(polymer.removeUnit("d")).as("Remove d").isEqualTo("abAcCaCBAcCcaA");
	}
	
	@Test
	public void testRemoveUnitX() {
		polymerStr = "dabAcCaCBAcCcaDA";
		polymer = new Polymer(polymerStr);
		assertThat(polymer.removeUnit("x")).as("Remove x").isEqualTo(polymerStr);
	}
	
	@Test
	public void testRemoveProblematicUnit() {
		polymerStr = "dabAcCaCBAcCcaDA";
		polymer = new Polymer(polymerStr);
		assertThat(Polymer.removeProblematicUnit(polymer)).as("Remove problematic unit").isEqualTo(4);
	}
}
