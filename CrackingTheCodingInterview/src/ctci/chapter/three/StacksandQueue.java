package ctci.chapter.three;

import java.util.ArrayList;
import java.util.EmptyStackException;
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

	/*
	 * Stack of Plates: Imagine a (literal) stack of plates. If the stack gets too
	 * high, it might topple. Therefore, in real life, we would likely start a new
	 * stack when the previous stack exceeds some threshold. Implement a data
	 * structure SetOfStacks that mimics this. SetOfStacks should be composed of
	 * several stacks and should create a new stack once the previous one exceeds
	 * capacity. SetOfStacks. push () and SetOfStacks. pop () should behave
	 * identically to a single stack (that is, pop ( ) should return the same values
	 * as it would if there were just a single stack). FOLLOW UP Implement a
	 * function popAt (int index) which performs a pop operation on a specific
	 * sub-stack.
	 */

	public static class StackofPlates<T> {
		List<Stack<T>> listOfStacks;
		int stackCapacity = 2;

		public StackofPlates() {
			listOfStacks = new ArrayList<Stack<T>>();
		}

		public void push(T x) {
			if (listOfStacks.size() == 0) {
				createNewStackAndPush(x);
			} else {
				Stack<T> lastStack = getLastStack();
				if (lastStack != null) {
					if (lastStack.size() >= stackCapacity) {
						createNewStackAndPush(x);
					} else {
						lastStack.push(x);
					}
				}
			}
		}

		public T pop() {
			Stack<T> lastStack = getLastStack();

			if (lastStack == null) {
				throw new EmptyStackException();
			}
			T itemToPop = lastStack.pop();
			if (lastStack.size() == 0) {
				listOfStacks.remove(listOfStacks.size() - 1);
			}

			return itemToPop;
		}

		public T popAtIndex(int index) {
			if (index >= listOfStacks.size()) {
				return null;
			}

			return listOfStacks.get(index).pop();
		}

		private Stack<T> getLastStack() {
			if (!listOfStacks.isEmpty()) {
				return listOfStacks.get(listOfStacks.size() - 1);
			}
			return null;
		}

		private void createNewStackAndPush(T x) {
			Stack<T> stack = new Stack<>();
			stack.push(x);
			listOfStacks.add(stack);
		}
	}

	public static class QueueViaStack {
		Stack<Integer> s1;
		Stack<Integer> s2;

		public QueueViaStack() {
			s1 = new Stack<Integer>();
			s2 = new Stack<Integer>();
		}

		public void add(int x) {
			s1.push(x);
		}

		public int remove() {
			shiftStacks();
			return s2.pop();
		}

		public int size() {
			return s1.size() + s2.size();
		}

		public int peek() {
			shiftStacks();
			return s2.peek();
		}

		public void shiftStacks() {
			if (s2.isEmpty()) {
				while (!s1.isEmpty()) {
					s2.push(s1.pop());
				}
			}
		}
	}

	public static void main(String[] args) {

		StacksandQueue obj = new StacksandQueue();
//		3.2 Min Stack
//		obj.push(4);
//		System.out.println("Pushing 4");
//		obj.push(5);
//		System.out.println("Pushing 5");
//		obj.push(6);
//		System.out.println("Pushing 6");
//		System.out.println("Min is " + obj.getMin());
//		obj.push(3);
//		System.out.println("Pushing 3");
//		obj.push(10);
//		System.out.println("Pushing 10");
//		System.out.println(obj.pop());
//		System.out.println(obj.pop());
//		System.out.println("Min is " + obj.getMin());
//		obj.push(1);
//		System.out.println("Pushing 1");
//		System.out.println("Min is " + obj.getMin());

//		3.3 Stack of Plates
//		StacksandQueue.StackofPlates obj2 = new StacksandQueue.StackofPlates();
//		obj2.push(1);
//		obj2.push(2);
//		obj2.push(3);
//		obj2.push(4);
//		obj2.push(5);
//		obj2.push(6);
//		obj2.pop();
//		obj2.popAtIndex(1);
//		obj2.push(3);

//		3.4 Queue via Stacks
		StacksandQueue.QueueViaStack obj2 = new StacksandQueue.QueueViaStack();
		obj2.add(1);
		obj2.add(2);
		obj2.add(3);
		obj2.add(4);
		obj2.add(5);
		obj2.peek();
		obj2.remove();
		obj2.remove();
		obj2.add(6);
	}

}
