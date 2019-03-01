package leetcode;
/*
 * [2] Add Two Numbers
 *
 * https://leetcode.com/problems/add-two-numbers/description/
 *
 * algorithms
 * Medium (28.97%)
 * Total Accepted:    544.7K
 * Total Submissions: 1.9M
 * Testcase Example:  '[2,4,3]\n[5,6,4]'
 *
 * You are given two non-empty linked lists representing two non-negative
 * integers. The digits are stored in reverse order and each of their nodes
 * contain a single digit. Add the two numbers and return it as a linked list.
 *
 * You may assume the two numbers do not contain any leading zero, except the
 * number 0 itself.
 *
 * Example:
 *
 *
 * Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
 * Output: 7 -> 0 -> 8
 * Explanation: 342 + 465 = 807.
 *
 *
 */
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class AddTwoNumbers {

	ListNode addTwoNumbers(ListNode l1, ListNode l2) {
		ListNode head = new ListNode(0);

		ListNode current = head;

		int remainer = 0;

		while (l1 != null && l2 != null) {

			current.next = new ListNode((l1.val + l2.val + remainer) % 10);

			remainer = (l1.val + l2.val + remainer) / 10;
			current = current.next;

			if(l1.next==null && l2.next!=null){
				l1.next=new ListNode(0);
			}else if(l1.next!=null && l2.next==null){
				l2.next=new ListNode(0);
			}

			l1 = l1.next;
			l2 = l2.next;
		}

		if (remainer > 0) {
			current.next = new ListNode(remainer);
		}

		return head.next;
	}


	class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
		}
	}

}


//Input: (2 -> 4 -> 3) +
//       (5 -> 6 -> 4)
//Output: 7 -> 0 -> 8