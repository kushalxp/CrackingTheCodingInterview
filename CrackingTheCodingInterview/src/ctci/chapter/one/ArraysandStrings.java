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

	/*
	 * 1.4 Palindrome Permutation: Given a string, write a function to check if it
	 * is a permutation of a palindrome. A palindrome is a word or phrase that is
	 * the same forwards and backwards. A permutation is a rea rrangement of
	 * letters. The palindrome does not need to be limited to just dictionary words.
	 * EXAMPLE
	 */
//	Input: Tact Coa 
//	Output: True (permutations: "taco cat". "atco cta".* etc.)
	public boolean palindromePermutation(String s) {

		if (s == null || s.length() == 0) {
			return false;
		}

		byte[] characters = new byte[26];
		String alphabets = s.replaceAll("[^a-zA-Z]", "").toLowerCase();

		for (int i = 0; i < alphabets.length(); i++) {
			characters[alphabets.charAt(i) - 'a']++;
		}

		boolean isEven = true;
		if (alphabets.length() % 2 != 0) {
			isEven = false;
		}
		int count = 0;
		for (int i = 0; i < characters.length; i++) {
			if (isEven) {
				if (characters[i] == 0 || characters[i] % 2 == 0) {
				} else {
					return false;
				}
			} else {
				if (characters[i] % 2 != 0) {
					count++;
				} else if (count > 1) {
					return false;
				}
			}
		}
		return true;
	}

	/*
	 * 1.5 One Away: There are three types of edits that can be performed on
	 * strings: insert a character, remove a character, or replace a character.
	 * Given two strings, write a function to check if they are one edit (or zero
	 * edits) away. EXAMPLE
	 */
//		pale, pIe -> true
//		pales. pale -> true
//		pale. bale -> true
//		pale. bake -> false

	public boolean oneAway(String one, String two) {
		int editAway = 0;
		if (one.length() == two.length()) {
			for (int i = 0; i < one.length(); i++) {
				if (one.charAt(i) != two.charAt(i)) {
					editAway++;
				}
			}
			if (editAway <= 1) {
				return true;
			}
		}

		if (Math.abs(one.length() - two.length()) == 1) {
			int i = 0;
			int j = 0;
			while (i < one.length() && j < two.length()) {
				if (one.charAt(i) != two.charAt(j)) {
					editAway++;
					i++;
				} else {
					i++;
					j++;
				}
			}

			if (editAway <= 1) {
				return true;
			}
		}
		return false;
	}

	/*
	 * 1.6 String Compression: Implement a method to perform basic string
	 * compression using the counts of repeated characters. For example, the string
	 * aabcccccaaa would become a2b1c5a3. If the "compressed" string would not
	 * become smaller than the original string, your method should return the
	 * original string. You can assume the string has only uppercase and lowercase
	 * letters (a - z).
	 */

	public String stringCompression(String str) {
		StringBuilder compressed = new StringBuilder();
		int countConsecutive = 0;
		for (int i = 0; i < str.length(); i++) {
			countConsecutive++;

			if (i + 1 >= str.length() || str.charAt(i) != str.charAt(i + 1)) {
				compressed.append(str.charAt(i));
				compressed.append(countConsecutive);
				countConsecutive = 0;
			}
		}

		return compressed.length() < str.length() ? compressed.toString() : str;
	}

	/*
	 * 1.7 Rotate Matrix: Given an image represented by an NxN matrix, where each
	 * pixel in the image is 4 bytes, write a method to rotate the image by 90
	 * degrees. (an you do this in place?
	 */

	public void rotateMatrix(int[][] matrix) {
		int n = matrix.length;
		int temp;

		System.out.println("Before rotation . . .");
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				System.out.print(matrix[i][j] + " ");
			}
			System.out.println(" ");
		}

//		Transpose of matrix, Row to column and column to row
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < i; j++) {
				temp = matrix[i][j];
				matrix[i][j] = matrix[j][i];
				matrix[j][i] = temp;
			}
		}
		
//		Swap the first columns 
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < (n / 2); j++) {
				temp = matrix[i][j];
				matrix[i][j] = matrix[i][n - 1 - j];
				matrix[i][n - 1 - j] = temp;
			}
		}
		System.out.println("After rotation . . .");
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				System.out.print(matrix[i][j] + " ");
			}
			System.out.println(" ");
		}
	}

	/*
	 * Zero Matrix: Write an algorithm such that if an element in an MxN matrix is
	 * 0, its entire row and column are set to O.
	 */
	public void setZeroes(int[][] matrix) {
		int rowLength = matrix.length;
		int colLength = matrix[0].length;

		boolean[] row = new boolean[rowLength];
		boolean[] col = new boolean[colLength];

		System.out.println("Before setting . . .");
		for (int i = 0; i < rowLength; i++) {
			for (int j = 0; j < colLength; j++) {
				System.out.print(matrix[i][j] + " ");
			}
			System.out.println(" ");
		}

		for (int i = 0; i < rowLength; i++) {
			for (int j = 0; j < colLength; j++) {
				if (matrix[i][j] == 0) {
					row[i] = true;
					col[j] = true;
				}
			}
		}

		for (int i = 0; i < rowLength; i++) {
			for (int j = 0; j < colLength; j++) {
				if (row[i] || col[j]) {
					matrix[i][j] = 0;
				}
			}
		}

		System.out.println("After setting . . .");
		for (int i = 0; i < rowLength; i++) {
			for (int j = 0; j < colLength; j++) {
				System.out.print(matrix[i][j] + " ");
			}
			System.out.println(" ");
		}
	}

	public static void main(String[] args) {

		ArraysandStrings obj = new ArraysandStrings();

//		1.1
//		String str = "kushal";
//		System.out.println(obj.isUniqueCharString(str));

//		1.2
//		  String stringOne = "aab"; 

//		  String stringTwo = "abb";
//		  System.out.println(obj.checkPermutation(stringOne, stringTwo));

//		1.3
//		String str = "Mr john smith    ";
//		System.out.println("The given string is: " + str);
//		char[] arr = str.toCharArray();
//		obj.URLify(arr, 13);
//		String string = String.valueOf(arr);
//		System.out.print("The urlified string is: " + string);

//		1.4
//		String s = "Leevl";
//		System.out.println(obj.palindromePermutation(s));

//		1.5
//		String stringOne = "pales";
//		String stringTwo = "bakes";
//		System.out.println(obj.oneAway(stringOne, stringTwo));

//		1.6
//		String longString = "abbbcccceedss";
//		System.out.println(obj.stringCompression(longString));

//		1.7
//		int[][] matrix = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };
//		obj.rotateMatrix(matrix);

//		1.8
		int[][] arr = { { 1, 1, 1 }, { 1, 0, 1 }, { 1, 1, 1 } };
		obj.setZeroes(arr);
	}

}
