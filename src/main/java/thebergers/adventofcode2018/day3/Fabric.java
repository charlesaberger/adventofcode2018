package thebergers.adventofcode2018.day3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Fabric {

	private static final Logger LOG = LoggerFactory.getLogger(Fabric.class);
	
	private Integer maxFromLeft;
	
	private Integer maxFromTop;
	
	private Map<SquareInch, List<Claim>> squareInches = new HashMap<>();
	
	private List<Claim> claims = new LinkedList<>();
	
	private Fabric(Integer maxFromLeft, Integer maxFromTop, List<Claim> claimList) {
		this.maxFromLeft = maxFromLeft;
		this.maxFromTop = maxFromTop;
		this.claims = claimList;
		LOG.info("Processing claims...");
		for (Claim claim : claimList) {
			List<SquareInch> claimedInches = claim.getCoverage();
			for (SquareInch inch : claimedInches) {
				List<Claim> claimsForinch =  null;
				if (squareInches.containsKey(inch)) {
					claimsForinch = squareInches.get(inch);
				} else {
					claimsForinch = new LinkedList<>();
				}
				claimsForinch.add(claim);
				squareInches.put(inch, claimsForinch);
				inch.addClaim(claim);
			}
		}
	}
	
	public Integer getArea() {
		return maxFromLeft * maxFromTop;
	}
	
	public Map<SquareInch, List<Claim>> getInches() {
		return squareInches;
	}
	
	public Integer countOverlaps() {
		Integer overlaps = 0;
		Set<SquareInch> keys = squareInches.keySet();
		for (SquareInch si : keys) {
			if (squareInches.get(si).size() >= 2) {
				overlaps ++;
			}
		}
		return overlaps;
	}
	
	public Integer countOverlaps2() {
		Map<SquareInch, Integer> inchClaimsCount = new HashMap<>();
		Integer overlaps = 0;
		for (Claim claim : claims) {
			List<SquareInch> claimedInches = claim.getCoverage();
			for (SquareInch si : claimedInches) {
				Integer cnt = null;
				if (inchClaimsCount.containsKey(si)) {
					cnt = inchClaimsCount.get(si);
				} else {
					cnt = 0;
				}
				cnt++;
				inchClaimsCount.put(si, cnt);
			}
			if (overlaps <= 5) {
				LOG.info("Claim {} has fewer than 5 overlaps", claim.getId());
			}
		}
		Set<SquareInch> keys = inchClaimsCount.keySet();
		for (SquareInch si : keys) {
			Integer count = inchClaimsCount.get(si);
			if (count >= 2) {
				overlaps++;
			}
		}
		return overlaps;
	}

	public Integer getClaimWithNoOverlaps() {
		Map<Integer, Boolean> claimOverlaps = new HashMap<>();
		for (Claim claim : claims) {
			Boolean hasOverlaps = false;
			for (Claim claim2 : claims) {
				if (!claim2.getId().equals(claim.getId())) {
					List<SquareInch> overlaps = claim.overlaps(claim2);
					hasOverlaps = !overlaps.isEmpty();
					if (hasOverlaps) {
						break;
					}
				}
			}
			claimOverlaps.put(claim.getId(), hasOverlaps);
			LOG.info("Claim {}, hasOverlaps {}", claim.getId(), hasOverlaps);
			if (!hasOverlaps) {
				return claim.getId();
			}
		}
		return null;
	}

	public static Fabric getInstance(List<String> claims) {
		List<Claim> claimList = new LinkedList<>();
		Integer maxFromLeft = 0;
		Integer maxFromTop = 0;
		for (String claimData : claims) {
			Claim claim = Claim.getInstance(claimData);
			claimList.add(claim);
			if (claim.getMaxFroMLeft() > maxFromLeft) {
				maxFromLeft = claim.getMaxFroMLeft();
			}
			if (claim.getMaxFromTop() > maxFromTop) {
				maxFromTop = claim.getMaxFromTop();
			}
		}
		LOG.info("Added {} Claims to ClaimEvaluator, maxFromLeft={}, maxFromTop={}", 
				claimList.size(), maxFromLeft, maxFromTop);
		return new Fabric(maxFromLeft, maxFromTop, claimList);
	}

	public static Fabric getInstance(String fileName) throws IOException {
		File file = new File(fileName);
		List<String> claims = new LinkedList<>();
		try(BufferedReader reader = new BufferedReader(new FileReader(file))) {
			while (true) {
				String claim = reader.readLine();
				if (claim == null) {
					break;
				}
				claims.add(claim);
			}
		}
		return getInstance(claims);
	}
}
