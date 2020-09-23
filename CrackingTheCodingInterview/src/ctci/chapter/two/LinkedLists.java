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

	/*
	 * 2.6 Palindrome: Implement a function to check if a linked list is a
	 * palindrome.
	 */

	public Boolean palindrome(ListNode head) {
		ListNode prev = null;
		ListNode curr = head;
		ListNode temp;
		ListNode secondHeadHunter = head;
		ListNode secondHead = null;
		int length = 0;

		while (head != null) {
			length++;
			head = head.next;
		}

		int index = length / 2;
		int count = 0;
		if (length % 2 != 0) {
			index += 1;
		}

		while (secondHeadHunter != null) {
			if (count == index) {
				secondHead = secondHeadHunter;
				break;
			}
			count++;
			secondHeadHunter = secondHeadHunter.next;
		}
		index = length / 2;
		while (index > 0 && curr != null) {
			temp = curr.next;
			curr.next = prev;
			prev = curr;
			curr = temp;
			index--;
		}
		head = prev;

		while (head != null && secondHead != null) {
			if (head.val != secondHead.val) {
				return false;
			}
			head = head.next;
			secondHead = secondHead.next;
		}
		return true;
	}

	/*
	 * 2.7 Intersection: Given two (singly) linked lists, determine if the two lists
	 * intersect. Return the intersecting node. Note that the intersection is
	 * defined based on reference, not value. That is, if the kth node of the first
	 * linked list is the exact same node (by reference) as the jth node of the
	 * second linked list, then they are intersecting.
	 */
	public ListNode intersection(ListNode l1, ListNode l2) {
		ListNode A = l1;
		ListNode B = l2;

		while (A != B) {
			if (A != null) {
				A = A.next;
			} else {
				A = l2;
			}

			if (B != null) {
				B = B.next;
			} else {
				B = l1;
			}
		}

		return A;

	}

	/*
	 * 2.8 Loop Detection: Given a circular linked list, implement an algorithm that
	 * returns the node at the beginning of the loop. DEFINITION Circular linked
	 * list: A (corrupt) linked list in which a node's next pointer points to an
	 * earlier node, so as to make a loop in the linked list. EXAMPLE Input: A -) B
	 * -) C -) 0 -) E - ) C[thesameCasearlierl Output: C
	 */

	public ListNode loopDetection(ListNode l1) {
		ListNode original = l1;
		ListNode slow = l1;
		ListNode fast = l1;

		while (slow != null && fast != null && fast.next != null) {
			slow = slow.next;
			fast = fast.next.next;
			if (slow == fast) {
				while (original != slow) {
					System.out.println("what");
					original = original.next;
					slow = slow.next;
				}
				return slow;
			}
		}
		return null;

	}

	public static void main(String[] args) {

		LinkedLists obj = new LinkedLists();

		ListNode head = new ListNode(1);
		ListNode seco = new ListNode(2);
		ListNode thir = new ListNode(3);
		ListNode four = new ListNode(4);
		ListNode five = new ListNode(5);
		ListNode sixx = new ListNode(6);

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
//		obj.printList(four);
//		ListNode newList1 = new ListNode(6);
//		ListNode newList2 = new ListNode(8);
//		ListNode newList3 = new ListNode(5);
//		newList1.next = newList2;
//		newList2.next = newList3;
//		newList3.next = null;
//		obj.printList(newList1);
//		ListNode ans = obj.sumLists(four, newList1);
//		obj.printList(ans);

//		2.6
//		obj.printList(head);
//		System.out.println(obj.palindrome(head));

//		2.7
//		obj.printList(head);
//		ListNode newList1 = new ListNode(6);
//		ListNode newList2 = new ListNode(8);
//		newList1.next = newList2;
//		newList2.next = four;
//		obj.printList(newList1);
//		ListNode ans = obj.intersection(head, newList1);
//		obj.printList(ans);

//		2.8
		obj.printList(head);
		sixx.next = thir;
		ListNode ans = obj.loopDetection(head);
		System.out.println("Loop started at " + ans.val);
	}

}
