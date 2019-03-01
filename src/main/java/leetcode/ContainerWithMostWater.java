package leetcode;

import org.junit.jupiter.api.Test;

/*
 * [11] Container With Most Water
 *
 * https://leetcode.com/problems/container-with-most-water/description/
 *
 * algorithms
 * Medium (39.27%)
 * Total Accepted:    252.3K
 * Total Submissions: 642.5K
 * Testcase Example:  '[1,8,6,2,5,4,8,3,7]'
 *
 * Given n non-negative integers a1, a2, ..., an , where each represents a
 * point at coordinate (i, ai). n vertical lines are drawn such that the two
 * endpoints of line i is at (i, ai) and (i, 0). Find two lines, which together
 * with x-axis forms a container, such that the container contains the most
 * water.
 *
 * Note: You may not slant the container and n is at least 2.

 * The above vertical lines are represented by array [1,8,6,2,5,4,8,3,7]. In
 * this case, the max area of water (blue section) the container can contain is
 * 49.
 *
 * Example:
 *
 *
 * Input: [1,8,6,2,5,4,8,3,7]
 * Output: 49
 *
 */
class ContainerWithMostWater extends TestCase {

	//找最长的长
	public int maxArea(int[] height) {
		int max = 0;

		int i = 0, j = height.length - 1;

		while (i < j) {
			max = Math.max(max, Math.min(height[i], height[j]) * (j - i));

			if (height[i] < height[j]) {//key point
				i++;
			}
			else {
				j--;
			}
		}

		return max;
	}

	@Test
	void solution() {
		assertEquals(49, maxArea(new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7}));
	}
}
