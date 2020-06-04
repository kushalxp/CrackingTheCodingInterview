package ctci.chapter.one;

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

	/*
	 * 1.3 URLify: Write a method to replace all spaces in a string with '%20: You
	 * may assume that the string has sufficient space at the end to hold the
	 * additional characters, and that you are given the "true" length of the
	 * string. (Note: If implementing in Java, please use a character array so that
	 * you can perform this operation in place.) EXAMPLE Input: "Mr John Smith", 13
	 * Output: "Mr%20John%20Smith"
	 */
	public void URLify(char[] str, int length) {

		int countspace = 0;
		int index;

		for (int i = 0; i <= length - 1; i++) {
			if (str[i] == ' ') {
				countspace++;
			}
		}
		index = length + countspace * 2;

		for (int i = length - 1; i >= 0; i--) {
			if (str[i] == ' ') {
				str[index - 1] = '0';
				str[index - 2] = '2';
				str[index - 3] = '%';

				index = index - 3;
			} else {
				str[index - 1] = str[i];
				index--;
			}
		}
	}

	public static void main(String[] args) {

		ArraysandStrings obj = new ArraysandStrings();
		/*
		 * 1.2 String stringOne = "aab"; String stringTwo = "abb";
		 * System.out.println(obj.checkPermutation(stringOne, stringTwo));
		 */
		
		String str = "Mr john smith    ";
		System.out.println("The given string is: " + str);
		char[] arr = str.toCharArray();
		obj.URLify(arr, 13);
		String string = String.valueOf(arr);
		System.out.print("The urlified string is: "+ string);
	}

}
