package leetcode;

/*
 * [7] Reverse Integer
 *
 * https://leetcode.com/problems/reverse-integer/description/
 *
 * algorithms
 * Easy (24.37%)
 * Total Accepted:    455.8K
 * Total Submissions: 1.9M
 * Testcase Example:  '123'
 *
 * Given a 32-bit signed integer, reverse digits of an integer.
 *
 * Example 1:
 *
 *
 * Input: 123
 * Output: 321
 *
 *
 * Example 2:
 *
 *
 * Input: -123
 * Output: -321
 *
 *
 * Example 3:
 *
 *
 * Input: 120
 * Output: 21
 *
 *
 * Note:
 * Assume we are dealing with an environment which could only store integers
 * within the 32-bit signed integer range: [−231,  231 − 1]. For the purpose of
 * this problem, assume that your function returns 0 when the reversed integer
 * overflows.
 *
 */

class ReverseInteger7 {

	static int reverse(int x) {

		int pop, temp = 0;

		while (x != 0) {
			pop = x % 10;
			x /= 10;                                       //2147483647
			if (temp > Integer.MAX_VALUE / 10 || (temp == Integer.MAX_VALUE / 10 && pop > 7))
				return 0;                                  //-2147483648
			if (temp < Integer.MIN_VALUE / 10 || (temp == Integer.MIN_VALUE / 10 && pop < -8))
				return 0;
			temp = temp * 10 + pop;
		}

		return temp;
	}

	public static void main(String[] args) {
		System.out.println(reverse(-1234567));
	}
}
