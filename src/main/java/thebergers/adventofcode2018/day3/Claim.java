package thebergers.adventofcode2018.day3;

import java.util.LinkedList;
import java.util.List;

public class Claim {

	private Integer id;
	
	private Integer inchesFromLeft;
	
	private Integer inchesFromTop;
	
	private Integer width;
	
	private Integer height;
	
	private List<SquareInch> coverage = new LinkedList<>();

	private Claim(Integer id, Integer fromLeft, Integer fromTop, Integer width, Integer height, List<SquareInch> coverage) {
		this.id = id;
		this.inchesFromLeft = fromLeft;
		this.inchesFromTop = fromTop;
		this.width = width;
		this.height = height;
		this.coverage = coverage;
	}

	public Integer getId() {
		return id;
	}

	public Integer getInchesFromLeft() {
		return inchesFromLeft;
	}

	public Integer getInchesFromTop() {
		return inchesFromTop;
	}

	public Integer getWidth() {
		return width;
	}

	public Integer getHeight() {
		return height;
	}
	
	public Integer getMaxFroMLeft() {
		return getInchesFromLeft() + getWidth();
	}
	
	public Integer getMaxFromTop() {
		return getInchesFromTop() + getHeight();
	}
	
	public List<SquareInch> getCoverage() {
		return coverage;
	}
	
	public Integer getArea() {
		return coverage.size();
	}

	public List<SquareInch> overlaps(Claim claim) {
		List<SquareInch> overlaps = new LinkedList<>();
		for (SquareInch si1 : this.getCoverage()) {
			for (SquareInch si2 : claim.getCoverage()) {
				if (si1.equals(si2)) {
					overlaps.add(si2);
				}
			}
		}
		return overlaps;
	}

	@Override
	public String toString() {
		return "Claim [id=" + id + ", inchesFromLeft=" + inchesFromLeft + ", inchesFromTop=" + inchesFromTop
				+ ", width=" + width + ", height=" + height + ", area=" + coverage.size() + "]";
	}
	
	public static Claim getInstance(String claimData) {
		String[] parts = claimData.split(" ");
		String idStr = parts[0].substring(1);
		Integer id = Integer.parseInt(idStr);
		String[] coordinates = parts[2].split(",");
		Integer fromLeft = Integer.parseInt(coordinates[0]);
		Integer fromTop = Integer.parseInt(coordinates[1].substring(0, coordinates[1].length() - 1));
		String[] dimensions = parts[3].split("x");
		Integer width = Integer.parseInt(dimensions[0]);
		Integer height = Integer.parseInt(dimensions[1]);
		List<SquareInch> coverage = getCoverage(fromLeft, fromTop, width, height);
		return new Claim(id, fromLeft, fromTop, width, height, coverage);
	}
	
	private static List<SquareInch> getCoverage(Integer fromLeft, Integer fromTop, Integer width, Integer height) {
		List<SquareInch> coverage = new LinkedList<>();
		for (int i = 1; i <= width; i++) {
			Integer x = fromLeft + i;
			for (int j = 1; j <= height; j++) {
				Integer y = fromTop + j;
				coverage.add(new SquareInch(x, y));
			}
		}
		return coverage;
	}
}
