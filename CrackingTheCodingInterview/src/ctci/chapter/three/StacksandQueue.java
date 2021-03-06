package ctci.chapter.three;

import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.LinkedList;
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

	/*
	 * 3.4 Queue via Stacks: Implement a MyQueue class which impl ements a queue
	 * using two stacks.
	 */

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

	/*
	 * 3.5 Sort Stack: Write a program to sort a stack such that the smallest items
	 * are on the top. You can use an additional temporary stack, but you may not
	 * copy the elements into any other data structure (such as an array). The stack
	 * supports the following operations: push, pop, peek, and isEmpty.
	 */

	public void sortStack(Stack<Integer> s1) {
		Stack<Integer> s2 = new Stack<Integer>();
		while (!s1.isEmpty()) {
			int temp = s1.pop();
			while (!s2.isEmpty() && s2.peek() > temp) {
				s1.push(s2.pop());
			}
			s2.push(temp);
		}

		while (!s2.isEmpty()) {
			s1.push(s2.pop());
		}
	}
	/*
	 * 3.6 Animal Shelter: An animal shelter, which holds only dogs and cats,
	 * operates on a strictly"first in, first
	 * out" basis. People must adopt either the "oldest" (based on arrival time) of
	 * all animals at the shelter, or they can select whether they would prefer a
	 * dog or a cat (and will receive the oldest animal of that type). They cannot
	 * select which specific animal they would like. Create the data structures to
	 * maintain this system and implement operations such as enqueue, dequeueAny,
	 * dequeueDog, and dequeueCat. You may use the built-in Linked List data
	 * structure.
	 */

	public static class AnimalShelter {
		List<String> list;

		public AnimalShelter() {
			list = new LinkedList<String>();
		}

		public void enqueue(String s) {
			list.add(s);
		}

		public String dequeueAny() {
			String animal;
			if (!list.isEmpty()) {
				animal = list.remove(0);
			} else {
				return "No animal available";
			}
			return animal;
		}

		public String dequeueDog() {
			if (!list.isEmpty()) {
				for (int i = 0; i < list.size(); i++) {
					if (list.get(i).startsWith("Dog")) {
						return list.remove(i);
					}
				}
			}
			return "No Dog available";
		}

		public String dequeueCat() {
			if (!list.isEmpty()) {
				for (int i = 0; i < list.size(); i++) {
					if (list.get(i).startsWith("Cat")) {
						return list.remove(i);
					}
				}
			}
			return "No Cat available";
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
//		StacksandQueue.QueueViaStack obj2 = new StacksandQueue.QueueViaStack();
//		obj2.add(1);
//		obj2.add(2);
//		obj2.add(3);
//		obj2.add(4);
//		obj2.add(5);
//		obj2.peek();
//		obj2.remove();
//		obj2.remove();
//		obj2.add(6);

//		3.5
//		Stack<Integer> s1 = new Stack<Integer>();
//		s1.push(3);
//		s1.push(2);
//		s1.push(4);
//		s1.push(1);
//		s1.push(0);
//		s1.push(9);
//		System.out.println("Unsorted stack " + s1);
//		obj.sortStack(s1);
//		System.out.println("Sorted stack " + s1);

//		3.6
		StacksandQueue.AnimalShelter obj2 = new StacksandQueue.AnimalShelter();
		obj2.enqueue("Dog1");
		obj2.enqueue("Dog2");
		obj2.enqueue("Dog3");
		obj2.enqueue("Cat1");
		obj2.enqueue("Cat2");
		obj2.enqueue("Dog4");
		obj2.dequeueCat();
		obj2.dequeueDog();
		obj2.dequeueAny();
	}

}
