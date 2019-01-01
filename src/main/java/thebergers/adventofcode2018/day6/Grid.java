package thebergers.adventofcode2018.day6;

import java.util.LinkedList;
import java.util.List;

public class Grid {

	private List<Point> points = new LinkedList<>();
	
	private Integer highestX = 0;
	
	private Integer lowestX = Integer.MAX_VALUE;
	
	private Integer highestY = 0;
	
	private Integer lowestY = Integer.MAX_VALUE;
	
	public void addPoint(Integer x, Integer y) {
		points.add(new Point(x, y));
		if (x > highestX) {
			highestX = x;
		}
		if (x < lowestX) {
			lowestX = x;
		}
		if (y > highestY) {
			highestY = y;
		}
		if (y < lowestY) {
			lowestY = y;
		}
	}

	public boolean hasInfiniteArea(Point point) {
		return point.getX() == lowestX || point.getX() == highestX || point.getY() == lowestY || point.getY() == highestY;
	}

	public Point findClosestPoint(Point searchPoint) {
		List<Point> closestPoints = new LinkedList<>();
		Integer shortestDistance = Integer.MAX_VALUE;
		for (Point point : points) {
			Integer distance = point.distanceTo(searchPoint);
			if (distance < shortestDistance) {
				closestPoints.clear();
				closestPoints.add(point);
				shortestDistance = distance;
			} else if (distance == shortestDistance) {
				closestPoints.add(point);
			}
		}
		if (closestPoints.size() == 1) {
			return closestPoints.get(0);
		}
		return null;
	}

	public Integer findLargestEnclosedArea() {
		for (Integer x = lowestX; x <= highestX; x++) {
			for (Integer y = lowestY; y <= highestY; y++) {
				Point searchPoint = new Point(x, y);
				Point closestPoint = this.findClosestPoint(searchPoint);
				if (closestPoint != null) {
					closestPoint.addClosestPoint(searchPoint);
				}
			}
		}
		Integer largestArea = 0;
		for (Point point : points) {
			if (!this.hasInfiniteArea(point)) {
				Integer area = point.getClosestPointsSize();
				if (area > largestArea) {
					largestArea = area;
				}
			}
		}
		return largestArea;
	}
	
	public static Grid getInstance(List<String> points) {
		Grid grid = new Grid();
		for (String record : points) {
			String[] parts = record.split(", ");
			Integer x = Integer.parseInt(parts[0]);
			Integer y = Integer.parseInt(parts[1]);
			grid.addPoint(x, y);
		}
		return grid;
	}

	public Integer calculateDistanceToAllPoints(Point searchPoint) {
		Integer totalDistance = 0;
		for (Point point : points) {
			Integer distance = searchPoint.distanceTo(point);
			totalDistance += distance;
		}
		return totalDistance;
	}

	public Integer calculateSafeRegionSize(Integer distanceThreshold) {
		List<Point> safeRegion = new LinkedList<>();
		for (Integer x = lowestX; x <= highestX; x++) {
			for (Integer y = lowestY; y <= highestY; y++) {
				Point searchPoint = new Point(x, y);
				Integer distToAllPoints = calculateDistanceToAllPoints(searchPoint);
				if (distToAllPoints < distanceThreshold) {
					safeRegion.add(searchPoint);
				}
			}
		}
		return safeRegion.size();
	}
	
}
