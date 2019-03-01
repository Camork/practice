package leetcode;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

/*
 * @lc app=leetcode id=16 lang=java
 *
 * [16] 3Sum Closest
 *
 * https://leetcode.com/problems/3sum-closest/description/
 *
 * algorithms
 * Medium (33.43%)
 * Total Accepted:    223.4K
 * Total Submissions: 668.3K
 * Testcase Example:  '[-1,2,1,-4]\n1'
 *
 * Given an array nums of n integers and an integer target, find three integers
 * in nums such that the sum is closest to target. Return the sum of the three
 * integers. You may assume that each input would have exactly one solution.
 *
 * Example:
 *
 *
 * Given array nums = [-1, 2, 1, -4], and target = 1.
 *
 * The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).
 *
 */
class ThreeSumClosest extends TestCase {
	public int threeSumClosest(int[] nums, int target) {
		Arrays.sort(nums);

		int result = nums[0] + nums[1] + nums[2];
		for (int i = 0; i < nums.length - 2; i++) {
			int lo = i + 1, hi = nums.length - 1;

			while (lo < hi) {
				//i+lo+hi ~= closet
				int closet = nums[i] + nums[lo] + nums[hi];

				if (closet < target) {
					if (Math.abs(closet - target) < Math.abs(result - target)) {
						result = closet;
					}

					lo++;
				}
				else if (closet > target) {
					if (Math.abs(closet - target) < Math.abs(result - target)) {
						result = closet;
					}

					hi--;
				}
				else {
					return closet;
				}
			}
		}

		return result;
	}

	@Test
	void solution() {
		assertEquals(0, threeSumClosest(new int[]{0, 2, 1, -3}, 1));
		assertEquals(2, threeSumClosest(new int[]{-1, 2, 1, -4}, 1));
		assertEquals(-2, threeSumClosest(new int[]{-5, -4, -3, 0, 1, 2}, -2));
	}
}
