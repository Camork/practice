package leetcode;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * @lc app=leetcode id=15 lang=java
 *
 * [15] 3Sum
 *
 * https://leetcode.com/problems/3sum/description/
 *
 * algorithms
 * Medium (22.62%)
 * Total Accepted:    438.5K
 * Total Submissions: 1.9M
 * Testcase Example:  '[-1,0,1,2,-1,-4]'
 *
 * Given an array nums of n integers, are there elements a, b, c in nums such
 * that a + b + c = 0? Find all unique triplets in the array which gives the
 * sum of zero.
 *
 * Note:
 *
 * The solution set must not contain duplicate triplets.
 *
 * Example:
 *
 *
 * Given array nums = [-1, 0, 1, 2, -1, -4],
 *
 * A solution set is:
 * [
 * ⁠ [-1, 0, 1],
 * ⁠ [-1, -1, 2]
 * * ]
 *
 *
 */
class ThreeSum_15 extends TestCase {

	public List<List<Integer>> threeSum(int[] nums) {
		Arrays.sort(nums);

		List<List<Integer>> reval = new ArrayList<>();

		for (int i = 0; i < nums.length - 2; i++) {
			//avoid duplicate
			if (i == 0 || nums[i] != nums[i - 1]) {
				int sum = 0 - nums[i];

				int low = i + 1, hi = nums.length - 1;

				while (low < hi) {
					if (nums[low] + nums[hi] == sum) {
						reval.add(Arrays.asList(nums[i], nums[low], nums[hi]));

						while (low < hi && nums[low] == nums[low + 1]) {
							low++;
						}
						while (low < hi && nums[hi] == nums[hi - 1]) {
							hi--;
						}
						low++;
						hi--;
					}
					else if (nums[low] + nums[hi] < sum) {
						low++;
					}
					else if (nums[low] + nums[hi] > sum) {
						hi--;
					}
				}
			}
		}

		return reval;
	}

	@Test
	void solution() {
		assertEquals(Arrays.asList(Arrays.asList(-1, -1, 2), Arrays.asList(-1, 0, 1)), threeSum(new int[]{-1, 0, 1, 2, -1, -4}));
		assertEquals(Arrays.asList(Arrays.asList(0, 0, 0)), threeSum(new int[]{0, 0, 0}));
		assertEquals(Arrays.asList(Arrays.asList(0, 0, 0)), threeSum(new int[]{0, 0, 0, 0}));
	}
}
