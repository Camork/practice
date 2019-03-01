package leetcode;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/*
 * [4] Median of Two Sorted Arrays
 *
 * https://leetcode.com/problems/median-of-two-sorted-arrays/description/
 *
 * algorithms
 * Hard (23.51%)
 * Total Accepted:    284.4K
 * Total Submissions: 1.2M
 * Testcase Example:  '[1,3]\n[2]'
 *
 * There are two sorted arrays nums1 and nums2 of size m and n respectively.
 *
 * Find the median of the two sorted arrays. The overall run time complexity
 * should be O(log (m+n)).
 *
 * You may assume nums1 and nums2 cannot be both empty.
 *
 *
 *
 * Example 1:
 *
 *
 * nums1 = [1, 3]
 * nums2 = [2]
 *
 * The median is 2.0
 *
 *
 * Example 2:
 *
 *
 * nums1 = [1, 2]
 * nums2 = [3, 4]
 *
 * The median is (2 + 3)/2 = 2.5
 *
 *
 */
class MedianOfTwoSortedArrays {
	static double findMedianSortedArrays(int[] nums1, int[] nums2) {
		List<Integer> nums = new LinkedList<>();

		nums.addAll(Arrays.stream(nums1).boxed().collect(Collectors.toList()));
		nums.addAll(Arrays.stream(nums2).boxed().collect(Collectors.toList()));

		nums.sort(Integer::compareTo);

		System.out.println(nums.toString());
		int mid = nums.size() / 2;
		if (nums.size() % 2 == 1) {
			return nums.get(mid);
		} else {
			return (nums.get(mid).intValue() + nums.get(mid - 1).intValue()) / 2.0;
		}

	}

	public static void main(String[] args) {
		System.out.println(findMedianSortedArrays(new int[]{1, 2, 3}, new int[]{1, 3, 4}));
	}
}
