package thebergers.adventofcode2018.day2;

public final class CheckedClaimPair {

	private final Integer id1;
	
	private final Integer id2;
	
	public CheckedClaimPair(Integer id1, Integer id2) {
		this.id1 = id1;
		this.id2 = id2;
	}
	
	public CheckedClaimPair reverse() {
		return new CheckedClaimPair(id2, id1);
	}

	@Override
	public String toString() {
		return "CheckedClaimPair [id1=" + id1 + ", id2=" + id2 + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id1 == null) ? 0 : id1.hashCode());
		result = prime * result + ((id2 == null) ? 0 : id2.hashCode());
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
		CheckedClaimPair other = (CheckedClaimPair) obj;
		if (id1 == null) {
			if (other.id1 != null)
				return false;
		} else if (!id1.equals(other.id1))
			return false;
		if (id2 == null) {
			if (other.id2 != null)
				return false;
		} else if (!id2.equals(other.id2))
			return false;
		return true;
	}
}
