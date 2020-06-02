package ctci.chapter.one;

import java.util.HashSet;

public class ArraysandStrings {

	// 1.1
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

	// 1.2
	public boolean checkPermutation(String str1, String str2) {
		HashSet<Character> hashSet = new HashSet<Character>();

		if (str1.length() == str2.length()) {
			// Dumping str1 to Hash set
			for (int i = 0; i < str1.length(); i++) {
				hashSet.add(str1.charAt(i));
			}

			// Checking if str2 is same as str1
			for (int i = 0; i < str2.length(); i++) {
				if (!hashSet.contains(str2.charAt(i))) {
					return false;
				}
			}
		} else {
			return false;
		}

		return true;

	}

	public static void main(String[] args) {

		ArraysandStrings obj = new ArraysandStrings();
		String stringOne = "abc";
		String stringTwo = "cab";
		System.out.println(obj.checkPermutation(stringOne, stringTwo));
	}

}
