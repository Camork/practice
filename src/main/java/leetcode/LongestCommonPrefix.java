package leetcode;

import org.junit.jupiter.api.Test;

/*
 * [14] Longest Common Prefix
 *
 * https://leetcode.com/problems/longest-common-prefix/description/
 *
 * algorithms
 * Easy (32.00%)
 * Total Accepted:    336.1K
 * Total Submissions: 1.1M
 * Testcase Example:  '["flower","flow","flight"]'
 *
 * Write a function to find the longest common prefix string amongst an array
 * of strings.
 *
 * If there is no common prefix, return an empty string "".
 *
 * Example 1:
 *
 *
 * Input: ["flower","flow","flight"]
 * Output: "fl"
 *
 *
 * Example 2:
 *
 *
 * Input: ["dog","racecar","car"]
 * Output: ""
 * Explanation: There is no common prefix among the input strings.
 *
 *
 * Note:
 *
 * All given inputs are in lowercase letters a-z.
 *
 */
class LongestCommonPrefix extends TestCase {

	public String longestCommonPrefix(String[] strs) {
		if (strs.length == 0) return "";

		String prefix = strs[0];

		for (int i = 1; i < strs.length; i++) {

			while (strs[i].indexOf(prefix) != 0) {
				prefix = prefix.substring(0, prefix.length() - 1);

				if (prefix.isEmpty()) {
					return "";
				}
			}
		}

		return prefix;
	}

	@Test
	void solution() {
		assertEquals("fl", longestCommonPrefix(new String[]{"flower", "flow", "flight"}));
	}
}
