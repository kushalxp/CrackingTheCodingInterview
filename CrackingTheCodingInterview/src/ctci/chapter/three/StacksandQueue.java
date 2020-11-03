package ctci.chapter.three;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class StacksandQueue {

	/*
	 * 3.2 Stack Min: How would you design a stack which, in addition to push and
	 * pop, has a function min which returns the minimum element? Push, pop and min
	 * should all operate in 0(1) time.
	 */
	List<Integer> list = new ArrayList<Integer>();
	Stack<Integer> s2 = new Stack<Integer>();
	int top = -1;

	public void push(int x) {
		if (s2.isEmpty() || s2.peek() >= x) {
			s2.push(x);
		}
		list.add(x);
		top++;
	}

	public int pop() {
		int x = list.remove(top);
		top--;
		if (x == s2.peek()) {
			s2.pop();
		}
		return x;
	}

	public int getMin() {
		int y = 0;
		if (!s2.isEmpty()) {
			y = s2.peek();
		}
		return y;

	}

	public static void main(String[] args) {

		StacksandQueue obj = new StacksandQueue();
		obj.push(4);
		System.out.println("Pushing 4");
		obj.push(5);
		System.out.println("Pushing 5");
		obj.push(6);
		System.out.println("Pushing 6");
		System.out.println("Min is " + obj.getMin());
		obj.push(3);
		System.out.println("Pushing 3");
		obj.push(10);
		System.out.println("Pushing 10");
		System.out.println(obj.pop());
		System.out.println(obj.pop());
		System.out.println("Min is " + obj.getMin());
		obj.push(1);
		System.out.println("Pushing 1");
		System.out.println("Min is " + obj.getMin());
	}

}
