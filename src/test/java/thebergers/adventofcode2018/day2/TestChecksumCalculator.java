package thebergers.adventofcode2018.day2;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;

import org.assertj.core.api.SoftAssertions;
import org.junit.Test;

public class TestChecksumCalculator {

	private ChecksumCalculator checksumCalculator;
	
	@Test
	public void test1() {
		List<String> boxCodes = Arrays.asList("abcdef", "bababc", "abbcde",
				"abcccd", "aabcdd", "abcdee", "ababab");
		checksumCalculator = ChecksumCalculator.getInstance(boxCodes);
		
		assertThat(checksumCalculator.calculate()).as("Calculate checksum").isEqualTo(12);
	}
	
	@Test
	public void testFindCodePair() {
		List<String> boxCodes = Arrays.asList("abcde", "fghij", "klmno", "pqrst", "fguij", "axcye", "wvxyz");
		checksumCalculator = ChecksumCalculator.getInstance(boxCodes);
		List<BoxCode> boxes = checksumCalculator.findMatchingCodes();
		SoftAssertions.assertSoftly(softly -> {
			softly.assertThat(boxes.get(0).getCode()).as("Code 1").isEqualTo("fghij");
			softly.assertThat(boxes.get(1).getCode()).as("Code 2").isEqualTo("fguij");
			softly.assertThat(checksumCalculator.findMatchingLetters()).as("Matchinmg Letters").isEqualTo("fgij");
		});
	}
}
