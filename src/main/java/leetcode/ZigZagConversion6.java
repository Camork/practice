package leetcode;

import org.junit.jupiter.api.Test;

/*
 * [6] ZigZag Conversion
 *
 * https://leetcode.com/problems/zigzag-conversion/description/
 *
 * algorithms
 * Medium (28.55%)
 * Total Accepted:    237.7K
 * Total Submissions: 832.6K
 * Testcase Example:  '"PAYPALISHIRING"\n3'
 *                      01234567890123
 * The string "PAYPALISHIRING" is written in a zigzag pattern on a given number
 * of rows like this: (you may want to display this pattern in a fixed font for
 * better legibility)
 * 
 * 
 * P   A   H   N
 * A P L S I I G
 * Y   I   R
 * 
 * 
 * And then read line by line: "PAHNAPLSIIGYIR"
 * 
 * Write the code that will take a string and make this conversion given a
 * number of rows:
 * 
 * 
 * string convert(string s, int numRows);
 * 
 * Example 1:
 * 
 * 
 * Input: s = "PAYPALISHIRING", numRows = 3
 * Output: "PAHNAPLSIIGYIR"
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: s = "PAYPAL ISHIRIN G", numRows = 4
 * Output: "PINALSIGYAHRPI"
 * Explanation:
 *
 * P     I     N                   K*circle+line
 * A   L S   I G           K*circle+line  ||   (k+1)circle-line
 * Y A   H R
 * P     I                        K*circle+line
 *
 */
public class ZigZagConversion6 extends TestCase {

	public String convert(String s, int numRows) {
		if (numRows<=1) return s;

		char[] chars = s.toCharArray();
		int circle=(2*numRows-2);
		StringBuilder newString = new StringBuilder();

		for (int i = 0; i < numRows; i++) {

			for (int k = 0; k + i < chars.length; k = k + circle) {

				if (i != 0 && i != numRows - 1 && k + circle - i < chars.length) {
					newString.append(chars[k + i]);
					newString.append(chars[k + circle - i]);
				} else {
					newString.append(chars[k + i]);
				}

			}
		}

		return newString.toString();
	}

    @Test
    void solution(){
	    assertEquals("PINALSIGYAHRPI",convert("PAYPALISHIRING",4));
	    assertEquals("PAHNAPLSIIGYIR",convert("PAYPALISHIRING",3));
	}
}
