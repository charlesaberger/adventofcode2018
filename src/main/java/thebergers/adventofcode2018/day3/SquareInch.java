package thebergers.adventofcode2018.day3;

import java.util.LinkedList;
import java.util.List;

public class SquareInch {

	private Integer fromLeft;
	
	private Integer fromTop;
	
	private List<Claim> claims = new LinkedList<>();
	
	public SquareInch(Integer fromLeft, Integer fromTop) {
		this.fromLeft = fromLeft;
		this.fromTop = fromTop;
	}

	public Integer getFromLeft() {
		return fromLeft;
	}

	public Integer getFromTop() {
		return fromTop;
	}
	
	public void addClaim(Claim claim) {
		claims.add(claim);
	}
	
	public List<Claim> getClaims() {
		return claims;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fromLeft == null) ? 0 : fromLeft.hashCode());
		result = prime * result + ((fromTop == null) ? 0 : fromTop.hashCode());
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
		SquareInch other = (SquareInch) obj;
		if (fromLeft == null) {
			if (other.fromLeft != null)
				return false;
		} else if (!fromLeft.equals(other.fromLeft))
			return false;
		if (fromTop == null) {
			if (other.fromTop != null)
				return false;
		} else if (!fromTop.equals(other.fromTop))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "SquareInch [fromLeft=" + fromLeft + ", fromTop=" + fromTop + "]";
	}
	
	
}
