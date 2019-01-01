package thebergers.adventofcode2018.day6;

import java.util.LinkedList;
import java.util.List;

public class Point {

	private final Integer x;
	
	private final Integer y;
	
	private List<Point> closestPoints = new LinkedList<>();
	
	public Point(Integer x, Integer y) {
		this.x = x;
		this.y = y;
	}

	public Integer getX() {
		return x;
	}

	public Integer getY() {
		return y;
	}
	
	public Integer distanceTo(Point point) {
		Integer pointX = point.getX();
		Integer pointY = point.getY();
		Integer diffX = Math.abs(x - pointX);
		Integer diffY = Math.abs(y - pointY);
		return diffX + diffY;
	}
	
	public void addClosestPoint(Point point) {
		if (!closestPoints.contains(point)) {
			closestPoints.add(point);
		}
	}
	
	public Integer getClosestPointsSize() {
		return closestPoints.size();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((x == null) ? 0 : x.hashCode());
		result = prime * result + ((y == null) ? 0 : y.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Point other = (Point) obj;
		if (x == null) {
			if (other.x != null)
				return false;
		} else if (!x.equals(other.x))
			return false;
		if (y == null) {
			if (other.y != null)
				return false;
		} else if (!y.equals(other.y))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Point [x=" + x + ", y=" + y + "]";
	}
	
	
}
