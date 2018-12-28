package thebergers.adventofcode2018.day3;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class TestSquareInch {

	private SquareInch si1;
	
	private SquareInch si2;
	
	@Test
	public void testHashCode1() {
		si1 = new SquareInch(1, 1);
		si2 = new SquareInch(1, 1);
		assertThat(si1.hashCode()).as("Compare hash codes").isEqualTo(si2.hashCode());
	}
	
	@Test
	public void testHashCode2() {
		si1 = new SquareInch(100, 79);
		si2 = new SquareInch(100, 79);
		assertThat(si1.hashCode()).as("Compare hash codes").isEqualTo(si2.hashCode());
	}
	
	@Test
	public void testHashCode3() {
		si1 = new SquareInch(1, 2);
		si2 = new SquareInch(2, 1);
		assertThat(si1.hashCode()).as("Compare hash codes").isNotEqualTo(si2.hashCode());
	}
}
