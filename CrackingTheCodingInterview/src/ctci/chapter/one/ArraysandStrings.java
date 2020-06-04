package ctci.chapter.one;

import java.util.HashSet;

public class ArraysandStrings {

	/*
	 * 1.1 Is Unique: Implement an algorithm to determine if a string has all unique
	 * characters. What if you cannot use additional data structures?
	 */
	public boolean isUniqueCharString(String s) {
		boolean[] chars = new boolean[26];
		for (int i = 0; i < s.length(); i++) {
			char cur = s.charAt(i);
			int index = cur - 'a';
			if (chars[index]) {
				return false;
			} else {
				chars[index] = true;
			}
		}
		return true;
	}

	/*
	 * 1.2 Check Permutation: Given two strings, write a method to decide if one is
	 * a permutation of the other.
	 */
	public boolean checkPermutation(String s, String t) {

		if (s.length() != t.length()) {
			return false;
		}

		byte[] characterCount = new byte[26];

		for (int i = 0; i < s.length(); i++) {
			characterCount[s.charAt(i) - 'a']++;
			characterCount[t.charAt(i) - 'a']--;
		}

		for (int i = 0; i < characterCount.length; i++) {
			if (characterCount[i] != 0) {
				return false;
			}
		}
		return true;

	}

	public static void main(String[] args) {

		ArraysandStrings obj = new ArraysandStrings();
		String stringOne = "aab";
		String stringTwo = "abb";
		System.out.println(obj.checkPermutation(stringOne, stringTwo));
	}

}
