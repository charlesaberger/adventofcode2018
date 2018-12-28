package thebergers.adventofcode2018.day3;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;

import org.assertj.core.api.SoftAssertions;
import org.junit.Test;

public class TestClaim {

	private String claimData;
	
	private Claim claim;
	
	private List<String> claims = Arrays.asList("#1 @ 1,3: 4x4", "#2 @ 3,1: 4x4", "#3 @ 5,5: 2x2",
			"#4 @ 0,3: 4x1", "#5 @ 6,7: 1x2");
	
	@Test
	public void testCreateClaim1() {
		claimData = claims.get(0);
		claim = Claim.getInstance(claimData);
		SoftAssertions.assertSoftly(softly -> {
			softly.assertThat(claim.getId()).as("Check Claim Id").isEqualTo(1);
			softly.assertThat(claim.getInchesFromLeft()).as("Inches from left").isEqualTo(1);
			softly.assertThat(claim.getInchesFromTop()).as("Inches from top").isEqualTo(3);
			softly.assertThat(claim.getWidth()).as("Width").isEqualTo(4);
			softly.assertThat(claim.getHeight()).as("Height").isEqualTo(4);
			softly.assertThat(claim.getMaxFroMLeft()).as("Max from left").isEqualTo(5);
			softly.assertThat(claim.getMaxFromTop()).as("Max from top").isEqualTo(7);
			softly.assertThat(claim.getArea()).as("Check Area").isEqualTo(16);
			List<SquareInch> coverage = claim.getCoverage();
			softly.assertThat(coverage.get(0)).as("top left").isEqualTo(new SquareInch(2, 4));
			softly.assertThat(coverage.get(3)).as("bottom left").isEqualTo(new SquareInch(2, 7));
			softly.assertThat(coverage.get(12)).as("top right").isEqualTo(new SquareInch(5, 4));
			softly.assertThat(coverage.get(coverage.size() - 1)).as("bottom right").isEqualTo(new SquareInch(5, 7));
			softly.assertThat(coverage.size()).as("Num Square inches").isEqualTo(16);
		});
	}

	@Test
	public void testCreateClaim2() {
		claimData = claims.get(1);
		claim = Claim.getInstance(claimData);
		SoftAssertions.assertSoftly(softly -> {
			softly.assertThat(claim.getId()).as("Check Claim Id").isEqualTo(2);
			softly.assertThat(claim.getInchesFromLeft()).as("Inches from left").isEqualTo(3);
			softly.assertThat(claim.getInchesFromTop()).as("Inches from top").isEqualTo(1);
			softly.assertThat(claim.getWidth()).as("Width").isEqualTo(4);
			softly.assertThat(claim.getHeight()).as("Height").isEqualTo(4);
			softly.assertThat(claim.getMaxFroMLeft()).as("Max from left").isEqualTo(7);
			softly.assertThat(claim.getMaxFromTop()).as("Max from top").isEqualTo(5);
			softly.assertThat(claim.getArea()).as("Check Area").isEqualTo(16);
			List<SquareInch> coverage = claim.getCoverage();
			softly.assertThat(coverage.get(0)).as("top left").isEqualTo(new SquareInch(4, 2));
			softly.assertThat(coverage.get(3)).as("bottom left").isEqualTo(new SquareInch(4, 5));
			softly.assertThat(coverage.get(12)).as("top right").isEqualTo(new SquareInch(7, 2));
			softly.assertThat(coverage.get(coverage.size() - 1)).as("bottom right").isEqualTo(new SquareInch(7, 5));
			softly.assertThat(coverage.size()).as("Num Square inches").isEqualTo(16);
		});
	}

	@Test
	public void testCreateClaim3() {
		claimData = claims.get(2);
		claim = Claim.getInstance(claimData);
		SoftAssertions.assertSoftly(softly -> {
			softly.assertThat(claim.getId()).as("Check Claim Id").isEqualTo(3);
			softly.assertThat(claim.getInchesFromLeft()).as("Inches from left").isEqualTo(5);
			softly.assertThat(claim.getInchesFromTop()).as("Inches from top").isEqualTo(5);
			softly.assertThat(claim.getWidth()).as("Width").isEqualTo(2);
			softly.assertThat(claim.getHeight()).as("Height").isEqualTo(2);
			softly.assertThat(claim.getMaxFroMLeft()).as("Max from left").isEqualTo(7);
			softly.assertThat(claim.getMaxFromTop()).as("Max from top").isEqualTo(7);
			softly.assertThat(claim.getArea()).as("Check Area").isEqualTo(4);
			List<SquareInch> coverage = claim.getCoverage();
			softly.assertThat(coverage.get(0)).as("top left").isEqualTo(new SquareInch(6, 6));
			softly.assertThat(coverage.get(1)).as("bottom left").isEqualTo(new SquareInch(6, 7));
			softly.assertThat(coverage.get(2)).as("top right").isEqualTo(new SquareInch(7, 6));
			softly.assertThat(coverage.get(coverage.size() - 1)).as("bottom right").isEqualTo(new SquareInch(7, 7));
			softly.assertThat(coverage.size()).as("Num Square inches").isEqualTo(4);
		});
	}
	
	@Test
	public void testOverlap1() {
		Claim claim1 = Claim.getInstance(claims.get(0));
		Claim claim2 = Claim.getInstance(claims.get(1));
		assertThat(claim1.overlaps(claim2).size()).as("Claim1 overlaps Claim2").isEqualTo(4);
	}
	
	@Test
	public void testOverlap2() {
		Claim claim1 = Claim.getInstance(claims.get(0));
		Claim claim3 = Claim.getInstance(claims.get(2));
		assertThat(claim1.overlaps(claim3).size()).as("Claim1 overlaps Claim3").isEqualTo(0);
	}
	
	@Test
	public void testOverlap3() {
		Claim claim2 = Claim.getInstance(claims.get(1));
		Claim claim3 = Claim.getInstance(claims.get(2));
		assertThat(claim2.overlaps(claim3).size()).as("Claim2 overlaps Claim3").isEqualTo(0);
	}
	
	@Test
	public void testOverlaps4() {
		Claim claim5 = Claim.getInstance(claims.get(4));
		Claim claim4 = Claim.getInstance(claims.get(3));
		assertThat(claim5.overlaps(claim4).size()).as("Claim5 overlaps Claim4").isEqualTo(0);
	}
	
	@Test
	public void testOverlaps5() {
		Claim claim3 = Claim.getInstance(claims.get(2));
		Claim claim5 = Claim.getInstance(claims.get(4));
		assertThat(claim3.overlaps(claim5).size()).as("Claim3 overlaps Claim5").isEqualTo(0);
	}
	
	@Test
	public void testOverlaps6() {
		Claim claim4 = Claim.getInstance(claims.get(3));
		Claim claim1 = Claim.getInstance(claims.get(0));
		assertThat(claim4.overlaps(claim1).size()).as("Claim1 overlaps Claim4").isEqualTo(3);
	}
}
