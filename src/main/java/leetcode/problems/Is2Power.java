package leetcode.problems;

import leetcode.TestCase;
import org.junit.jupiter.api.Test;

/**
 * Created by camork on 2019-02-15.
 */
class Is2Power extends TestCase {

	boolean is2power(int num) {
		if (num < 2){
			return false;
		}

		int temp = 1;
		while (num > temp) {
			temp <<= 1;// 1 -> 10  ->  100  ->  1000
		}

		return temp == num;
	}

	@Test
	void test() {
		assertEquals(false, is2power(14));
		assertEquals(true, is2power(16));
	}
}
