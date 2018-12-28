package thebergers.adventofcode2018.day3;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class TestDayThree {

	private List<String> claims = Arrays.asList("#1 @ 1,3: 4x4", "#2 @ 3,1: 4x4", "#3 @ 5,5: 2x2",
			"#4 @ 0,3: 4x1", "#5 @ 4,6: 1x2", "#6 @ 2,6: 3x2"/*, "#7 @ 0,0; 1x3"*/);

	private ClaimEvaluator claimEvaluator;
	
	private Fabric fabric;
	
	//@Test
	public void testCountOverlaps() {
		claimEvaluator = ClaimEvaluator.getInstance(claims);
		assertThat(claimEvaluator.calculateOverlaps()).as("Count overlaps").isEqualTo(7);
	}

	@Test
	public void testCountOverlaps2() {
		fabric = Fabric.getInstance(claims);
		assertThat(fabric.countOverlaps()).as("Count overlaps").isEqualTo(10);
	}

	@Test
	public void testCountOverlaps3() {
		fabric = Fabric.getInstance(claims);
		assertThat(fabric.countOverlaps2()).as("Count overlaps").isEqualTo(10);
	}
	
	@Test
	public void testFindNoOverlaps() {
		fabric = Fabric.getInstance(claims);
		assertThat(fabric.getClaimWithNoOverlaps()).as("Find claim with no overlaps").isEqualTo(3);
	}
	
	@Test
	public void testDayThreeData() throws Exception {
		String fileName = "./src/main/resources/daythree/input.txt";
			Fabric fabric = Fabric.getInstance(fileName);
			Integer claimId = fabric.getClaimWithNoOverlaps();
			assertThat(claimId).as("Only one claim should have no overlaps").isNotNull();
	}
}
