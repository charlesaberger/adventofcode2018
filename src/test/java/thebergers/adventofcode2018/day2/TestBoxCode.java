package thebergers.adventofcode2018.day2;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;

import org.assertj.core.api.SoftAssertions;
import org.junit.Test;

public class TestBoxCode {

	private String boxId;
	
	private BoxCode boxCode;
	
	private List<BoxCode> boxCodes = Arrays.asList(new BoxCode("abcde"), new BoxCode("fghij"), new BoxCode("klmno"), 
				new BoxCode("pqrst"), new BoxCode("fguij"), new BoxCode("axcye"), new BoxCode("wvxyz"), new BoxCode("abcde"));
	
	@Test
	public void test1() {
		boxId = "abcdef";
		boxCode = new BoxCode(boxId);
		SoftAssertions.assertSoftly(softly -> {
			softly.assertThat(boxCode.appearsTwice()).as("Letter that appears twice").isEmpty();
			softly.assertThat(boxCode.appearsThrice()).as("Letter that appears thrice").isEmpty();
		});
	}
	
	@Test
	public void test2() {
		boxId = "bababc";
		boxCode = new BoxCode(boxId);
		SoftAssertions.assertSoftly(softly -> {
			softly.assertThat(boxCode.appearsTwice()).as("Letter that appears twice").isEqualTo("a");
			softly.assertThat(boxCode.appearsThrice()).as("Letter that appears thrice").isEqualTo("b");
		});
	}
	
	@Test
	public void test3() {
		boxId = "abbcde";
		boxCode = new BoxCode(boxId);
		SoftAssertions.assertSoftly(softly -> {
			softly.assertThat(boxCode.appearsTwice()).as("Letter that appears twice").isEqualTo("b");
			softly.assertThat(boxCode.appearsThrice()).as("Letter that appears thrice").isEmpty();
		});
	}
	
	@Test
	public void test4() {
		boxId = "abcccd";
		boxCode = new BoxCode(boxId);
		SoftAssertions.assertSoftly(softly -> {
			softly.assertThat(boxCode.appearsTwice()).as("Letter that appears twice").isEmpty();
			softly.assertThat(boxCode.appearsThrice()).as("Letter that appears thrice").isEqualTo("c");
		});
	}
	
	@Test
	public void test5() {
		boxId = "aabcdd";
		boxCode = new BoxCode(boxId);
		SoftAssertions.assertSoftly(softly -> {
			softly.assertThat(boxCode.appearsTwice()).as("Letter that appears twice").isEqualTo("a");
			softly.assertThat(boxCode.appearsThrice()).as("Letter that appears thrice").isEmpty();
		});
	}
	
	@Test
	public void test6() {
		boxId = "abcdee";
		boxCode = new BoxCode(boxId);
		SoftAssertions.assertSoftly(softly -> {
			softly.assertThat(boxCode.appearsTwice()).as("Letter that appears twice").isEqualTo("e");
			softly.assertThat(boxCode.appearsThrice()).as("Letter that appears thrice").isEmpty();
		});
	}
	
	@Test
	public void test7() {
		boxId = "ababab";
		boxCode = new BoxCode(boxId);
		SoftAssertions.assertSoftly(softly -> {
			softly.assertThat(boxCode.appearsTwice()).as("Letter that appears twice").isEmpty();
			softly.assertThat(boxCode.appearsThrice()).as("Letter that appears thrice").isEqualTo("a");
		});
	}
	
	@Test
	public void testCompare1() {
		BoxCode boxCode1 = boxCodes.get(0);
		BoxCode boxCode2 = boxCodes.get(1);
		assertThat(boxCode1.compareWith(boxCode2)).as("Compare %s and %s", boxCode1, boxCode2).isGreaterThan(1);
	}
	
	@Test
	public void testCompare2() {
		BoxCode boxCode1 = boxCodes.get(1);
		BoxCode boxCode2 = boxCodes.get(2);
		assertThat(boxCode1.compareWith(boxCode2)).as("Compare %s and %s", boxCode1, boxCode2).isGreaterThan(1);
	}
	
	@Test
	public void testCompare3() {
		BoxCode boxCode1 = boxCodes.get(0);
		BoxCode boxCode2 = boxCodes.get(5);
		assertThat(boxCode1.compareWith(boxCode2)).as("Compare %s and %s", boxCode1, boxCode2).isGreaterThan(1);
	}
	
	@Test
	public void testCompare4() {
		BoxCode boxCode1 = boxCodes.get(1);
		BoxCode boxCode2 = boxCodes.get(4);
		assertThat(boxCode1.compareWith(boxCode2)).as("Compare %s and %s", boxCode1, boxCode2).isEqualTo(1);
	}
	
	@Test
	public void testCompare5() {
		BoxCode boxCode1 = boxCodes.get(0);
		BoxCode boxCode2 = boxCodes.get(7);
		assertThat(boxCode1.compareWith(boxCode2)).as("Compare %s and %s", boxCode1, boxCode2).isEqualTo(0);
	}
}
