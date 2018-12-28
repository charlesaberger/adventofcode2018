package thebergers.adventofcode2018.day3;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class TestFabric {

	private List<String> claims = Arrays.asList("#1 @ 1,3: 4x4", "#2 @ 3,1: 4x4", "#3 @ 5,5: 2x2",
			"#4 @ 0,3: 4x1", "#5 @ 6,7: 1x2");

	private Fabric fabric;
	
	@Test
	public void testCountOverlaps2() {
		fabric = Fabric.getInstance(claims);
		assertThat(fabric.countOverlaps()).as("Count overlaps").isEqualTo(6);
	}

}
