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
		if(n != null && n.next != null) {
			ListNode next = n.next;
			n.val = next.val;
			n.next = next.next;
		}
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
		obj.printList(head);
		obj.deleteMiddleNode(thir);
		obj.printList(head);

	}

}
