package ctci.chapter.two;

import java.util.HashSet;

public class LinkedLists {

	static class ListNode {
		int val;
		ListNode next;

		ListNode(int d) {
			val = d;
			next = null;
		}
	}

	ListNode head;

	public void printList(ListNode head) {
		ListNode n = head;
		while (n != null) {
			String arrow = (n.next == null) ? "" : " -> ";
			System.out.print(n.val + arrow);
			n = n.next;
		}
		System.out.println("");
	}

	/*
	 * 2.1 Remove Dups: Write code to remove duplicates from an unsorted linked
	 */
	public ListNode removeDuplicates(ListNode n) {
		HashSet<Integer> set = new HashSet<>();
		ListNode current = n;
		ListNode prev = null;
		while (current != null) {
			if (!set.contains(current.val)) {
				set.add(current.val);
				prev = current;
			} else {
				prev.next = current.next;
			}
			current = current.next;
		}
		return n;
	}

	/*
	 * 2.2 Return Kth to Last: Implement an algorithm to find the kth to last
	 * element of a singly linked list.
	 */
	public ListNode returnKthToLastElement(ListNode n, int k) {
		ListNode fast = n;
		ListNode slow = n;

		int index = 0;

		while (index != k) {
			index++;
			fast = fast.next;
		}

		while (fast != null) {
			slow = slow.next;
			fast = fast.next;
		}

		return slow;
	}

	/*
	 * 2.3 Delete Middle Node: Implement an algorithm to delete a node in the middle
	 * (i.e., any node but the first and last node, not necessarily the exact
	 * middle) of a singly linked list, given only access to that node. EXAMPLE
	 * Input: the node c from the linked list a - >b- >c - >d - >e- >f Result:
	 * nothing is returned, but the new linked list looks like a - >b- >d - >e- >f
	 */

	public void deleteMiddleNode(ListNode n) {
		if (n != null && n.next != null) {
			ListNode next = n.next;
			n.val = next.val;
			n.next = next.next;
		}
	}

	/*
	 * Partition: Write code to partition a linked list around a value x, such that
	 * all nodes less than x come before all nodes greater than or equal to x. lf x
	 * is contained within the list, the values of x only need to be after the
	 * elements less than x (see below). The partition element x can appear anywhere
	 * in the "right partition"; it does not need to appear between the left and
	 * right partitions. EXAMPLE Input: 3 -> 5 -> 8 -> 5 -> 10 -> 2 -> 1 [partition
	 * = 5) Output: 3 -> 1 -> 2 -> 10 -> 5 -> 5 -> 8
	 */

	public ListNode partition(ListNode head, int x) {
		ListNode small = new ListNode(-1);
		small.next = head;
		ListNode big = new ListNode(-1);
		big.next = head;
		ListNode current = head;
		ListNode ans = small;
		ListNode ans2 = big;

		while (current != null) {
			if (current.val < x) {
				small.next = current;
				small = small.next;
			} else {
				big.next = current;
				big = big.next;
			}
			current = current.next;
		}

		big.next = null;
		small.next = ans2.next;
		return ans.next;
	}

	/*
	 * 2.5 Sum Lists: You have two numbers represented by a linked list, where each
	 * node contains a single digit. The digits are stored in reverse order, such
	 * that the 1 's digit is at the head of the list. Write a function that adds
	 * the two numbers and returns the sum as a linked list. EXAMPLE Input: (7-> 1
	 * -> 6) + (5 -> 9 -> 2) .That is,617 + 295. Output: 2 -> 1 -> 9. That is, 912.
	 * FOLLOW UP Suppose the digits are stored in forward order. Repeat the above
	 * problem. EXAMPLE Input: (6 -> 1 -> 7) + (2 -> 9 -> 5).That is,617 + 295.
	 * Output: 9 -> 1 -> 2. That is, 912.
	 */

	public ListNode sumLists(ListNode l1, ListNode l2) {
		ListNode dummy = new ListNode(-1);
		ListNode p = l1, q = l2, current = dummy;
		int carry = 0;
		while (p != null || q != null) {
			int x = (p != null) ? p.val : 0;
			int y = (q != null) ? q.val : 0;
			int sum = x + y + carry;
			carry = sum / 10;
			current.next = new ListNode(sum % 10);
			current = current.next;
			if (p != null)
				p = p.next;
			if (q != null)
				q = q.next;
		}

		if (carry > 0) {
			current.next = new ListNode(carry);
		}
		return dummy.next;
	}

	/*
	 * Recursive solution
	 */
	public ListNode sumLists(ListNode l1, ListNode l2, int carry) {

		if (l1 == null && l2 == null && carry == 0) {
			return null;
		}

		int value = carry;

		if (l1 != null) {
			value += l1.val;
		}

		if (l2 != null) {
			value += l2.val;
		}

		ListNode resultHead = new ListNode(value % 10);

		if (l1 != null || l2 != null) {
			ListNode nextNode = sumLists((l1 == null) ? null : l1.next, (l2 == null) ? null : l2.next, value / 10);
			resultHead.next = nextNode;
		}

		return resultHead;

	}

	public static void main(String[] args) {

		LinkedLists obj = new LinkedLists();

		ListNode head = new ListNode(9);
		ListNode seco = new ListNode(7);
		ListNode thir = new ListNode(8);
		ListNode four = new ListNode(9);
		ListNode five = new ListNode(7);
		ListNode sixx = new ListNode(8);

		head.next = seco;
		seco.next = thir;
		thir.next = four;
		four.next = five;
		five.next = sixx;
		sixx.next = null;

//		2.1
//		obj.printList(head);
//		ListNode ans = obj.removeDuplicates(head);
//		obj.printList(ans);

//		2.2
//		obj.printList(head);
//		ListNode ans = obj.returnKthToLastElement(head, 4);
//		obj.printList(ans);

//		2.3
//		obj.printList(head);
//		obj.deleteMiddleNode(thir);
//		obj.printList(head);

//		2.4
//		obj.printList(head);
//		ListNode ans = obj.partition(head, 3);
//		obj.printList(ans);

//		2.5
		obj.printList(four);
		ListNode newList1 = new ListNode(6);
		ListNode newList2 = new ListNode(8);
		ListNode newList3 = new ListNode(5);
		newList1.next = newList2;
		newList2.next = newList3;
		newList3.next = null;
		obj.printList(newList1);
		ListNode ans = obj.sumLists(four, newList1);
		obj.printList(ans);

	}

}
