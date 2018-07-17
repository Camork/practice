package leetcode;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/*
 * [5] Longest Palindromic Substring
 *
 * https://leetcode.com/problems/longest-palindromic-substring/description/
 *
 * algorithms
 * Medium (25.62%)
 * Total Accepted:    353.2K
 * Total Submissions: 1.4M
 * Testcase Example:  '"babad"'
 *
 * Given a string s, find the longest palindromic substring in s. You may
 * assume that the maximum length of s is 1000.
 *
 * Example 1:
 *
 *
 * Input: "babad"
 * Output: "bab"
 * Note: "aba" is also a valid answer.
 *
 *
 * Example 2:
 *
 *
 * Input: "cbbd"
 * Output: "bb"
 *
 *
 */
class LongestPalindromicSubstring5 extends TestCase {

	int low, hi, max;

	public String longestPalindrome(String s) {
		if (s == null || s.length() < 1) return "";

		for (int i = 0; i < s.length(); i++) {
			expandAroundCenter(s, i, i);
			expandAroundCenter(s, i, i + 1);
		}

		return s.substring(low, hi);

	}

	private void expandAroundCenter(String s, int left, int right) {
		int L = left, R = right;
		/*
			Q&A:
			why >=0 and L+1?
			   因为substring不包含右位,需额外扩大下标,左边则减去这个多余的
		 */
		while (L >= 0 && R < s.length() && s.charAt(L) == s.charAt(R)) {
			L--;
			R++;
		}

		int len = R - L;

		if (len > max) {
			max = len;
			low = L + 1;
			hi = R;
		}
	}

	@Test
	void test() {
		Assertions.assertEquals(longestPalindrome("ab"), "a");
		Assertions.assertEquals(longestPalindrome("abcdsdcbaqq"), "abcdsdcba");
		Assertions.assertEquals(longestPalindrome("abcdsdcba"), "abcdsdcba");
		System.out.println();
	}
}
