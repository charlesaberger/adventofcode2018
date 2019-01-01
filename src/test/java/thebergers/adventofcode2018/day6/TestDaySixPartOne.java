package thebergers.adventofcode2018.day6;

import static org.assertj.core.api.Assertions.assertThat;

import org.assertj.core.api.SoftAssertions;
import org.junit.Before;
import org.junit.Test;

public class TestDaySixPartOne {

	private Grid grid;
	
	private Point point;
	
	@Before
	public void setup() {
		grid = new Grid();
		grid.addPoint(1, 1);
		grid.addPoint(1, 6);
		grid.addPoint(8, 3);
		grid.addPoint(3, 4);
		grid.addPoint(5, 5);
		grid.addPoint(8, 9);
	}
	
	@Test
	public void testCheckInfinitePoints() {
		SoftAssertions.assertSoftly(softly -> {
			softly.assertThat(grid.hasInfiniteArea(new Point(1, 1))).as("Is 1, 1 infinite").isTrue();
			softly.assertThat(grid.hasInfiniteArea(new Point(1, 6))).as("Is 1, 6 infinite").isTrue();
			softly.assertThat(grid.hasInfiniteArea(new Point(8, 3))).as("Is 8, 3 infinite").isTrue();
			softly.assertThat(grid.hasInfiniteArea(new Point(3, 4))).as("Is 3, 4 infinite").isFalse();
			softly.assertThat(grid.hasInfiniteArea(new Point(5, 5))).as("Is 5, 5 infinite").isFalse();
			softly.assertThat(grid.hasInfiniteArea(new Point(8, 9))).as("Is 8, 9 infinite").isTrue();
		});
	}
	
	@Test
	public void testCalculateDistanceFor1_1() {
		point = new Point(1, 1);
		SoftAssertions.assertSoftly(softly -> {
			softly.assertThat(point.distanceTo(new Point(1, 1))).as("Distance to self").isEqualTo(0);
			softly.assertThat(point.distanceTo(new Point(1, 6))).as("Distance to 1, 6").isEqualTo(5);
			softly.assertThat(point.distanceTo(new Point(3, 4))).as("Distance to 3, 4").isEqualTo(5);
			softly.assertThat(point.distanceTo(new Point(8, 9))).as("Distance to 8, 9").isEqualTo(15);
		});
	}
	
	@Test
	public void testCalculateDistanceFor8_9() {
		point = new Point(8, 9);
		SoftAssertions.assertSoftly(softly -> {
			softly.assertThat(point.distanceTo(new Point(8, 9))).as("Distance to self").isEqualTo(0);
			softly.assertThat(point.distanceTo(new Point(1, 6))).as("Distance to 1, 6").isEqualTo(10);
			softly.assertThat(point.distanceTo(new Point(3, 4))).as("Distance to 3, 4").isEqualTo(10);
			softly.assertThat(point.distanceTo(new Point(1, 1))).as("Distance to 1, 1").isEqualTo(15);
			softly.assertThat(point.distanceTo(new Point(8, 3))).as("Distance to 8, 3").isEqualTo(6);
		});
	}
	
	@Test
	public void testFindClosestPoint2_4() {
		Integer x = 2;
		Integer y = 4;
		point = new Point(x, y);
		assertThat(grid.findClosestPoint(point)).as("Closest point to %d, %s", x, y).isEqualTo(new Point(3, 4));
	}
	
	@Test
	public void testFindClosestPoint3_6() {
		Integer x = 3;
		Integer y = 6;
		point = new Point(x, y);
		assertThat(grid.findClosestPoint(point)).as("Closest point to %d, %s", x, y).isNull();
	}
	
	@Test
	public void testFindClosestPoint5_2() {
		Integer x = 5;
		Integer y = 2;
		point = new Point(x, y);
		assertThat(grid.findClosestPoint(point)).as("Closest point to %d, %s", x, y).isEqualTo(new Point(5, 5));
	}
	
	@Test
	public void testFindLargestEnclosedArea() {
		assertThat(grid.findLargestEnclosedArea()).as("Find largest enclosed area").isEqualTo(17);
	}
	
	@Test
	public void testCalculateDistanceToAllPoints() {
		point = new Point(4, 3);
		assertThat(grid.calculateDistanceToAllPoints(point)).as("Calc distance to all points").isEqualTo(30);
	}
	
	@Test
	public void testCalculateSafeRegionSize() {
		Integer distanceThreshold = 32;
		assertThat(grid.calculateSafeRegionSize(distanceThreshold)).as("Calculate safe region size").isEqualTo(16);
	}
}
