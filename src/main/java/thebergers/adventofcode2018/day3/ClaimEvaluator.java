package thebergers.adventofcode2018.day3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import thebergers.adventofcode2018.day2.CheckedClaimPair;

public class ClaimEvaluator {
	
	private static final Logger LOG = LoggerFactory.getLogger(ClaimEvaluator.class);
	
	private List<Claim> claims = new LinkedList<>();
	
	private ClaimEvaluator(List<Claim> claims) {
		this.claims = claims;
	}

	public Integer calculateOverlaps() {
		LOG.info("Starting calculateOverlaps...");
		Map<CheckedClaimPair, Integer> checked = new HashMap<>();
		Integer totalOverlaps = 0;
		int index = 0;
		for (Claim claim : claims) {
			LOG.info("Claim: {}", claim);
			ListIterator<Claim> iterator = claims.listIterator(index + 1);
			while (iterator.hasNext()) {
				Claim claim2 = iterator.next();
				if (claim2.getId().equals(claim.getId())) {
					continue;
				}
				CheckedClaimPair ccp = new CheckedClaimPair(claim.getId(), claim2.getId());
				CheckedClaimPair ccp2 = ccp.reverse();
				if (!checked.containsKey(ccp)) {
					List<SquareInch> overlaps = claim.overlaps(claim2);
					Integer numOverlaps = overlaps.size();
					totalOverlaps += numOverlaps;
					checked.put(ccp, 1);
					checked.put(ccp2,  1);
					LOG.info("Checked Claim {}: found {} overlaps, total = {}", claim2, numOverlaps, totalOverlaps);
				} else {
					LOG.info("Already checked claim pair {}", ccp);
				}
			}
		}
		return totalOverlaps;
	}

	public static ClaimEvaluator getInstance(List<String> claims) {
		List<Claim> claimList = new LinkedList<>();
		for (String claimData : claims) {
			claimList.add(Claim.getInstance(claimData));
		}
		LOG.info("Added {} Claims to ClaimEvaluator", claimList.size());
		return new ClaimEvaluator(claimList);
	}

	public static ClaimEvaluator getInstance(String fileName) throws IOException {
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
