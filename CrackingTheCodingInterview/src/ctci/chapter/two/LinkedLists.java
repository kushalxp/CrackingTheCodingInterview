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

	public static void main(String[] args) {

		LinkedLists obj = new LinkedLists();

		ListNode head = new ListNode(1);
		ListNode seco = new ListNode(2);
		ListNode thir = new ListNode(1);
		ListNode four = new ListNode(4);
		ListNode five = new ListNode(5);
		ListNode sixx = new ListNode(1);

		head.next = seco;
		seco.next = thir;
		thir.next = four;
		four.next = five;
		five.next = sixx;
		sixx.next = null;

//		2.1
		obj.printList(head);
		ListNode ans = obj.removeDuplicates(head);
		obj.printList(ans);

	}

}
