//Junwei Chen

package finalexam;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import week3inclass.ArrayStack;

/*
 *  CountPalindromes class
 */
public class CountPalindromes {

	// Method to check if text is a palindrome using Stack
	public static boolean isPalindrome(String text) {
		ArrayStack<String> stack = new ArrayStack<String>(text.length());
		// Populate stack with characters in text
		for (int i = 0; i < text.length(); i++) {
			char c = text.toLowerCase().charAt(i);
			if (c < 'a' || c > 'z')
				continue;
			String s = "" + c;
			stack.push(s);
		}
		// Check characters against popping values from the Stack
		for (int i = 0; i < text.length(); i++) {
			char c = text.toLowerCase().charAt(i);
			if (c < 'a' || c > 'z')
				continue;
			String s = "" + c;
			// If a match is not found: not a palindrome
			if (!s.equals(stack.pop()))
				return false;
		}
		// Otherwise, you have a palindrome
		return true;
	}

	// Main method for program execution
	public static void main(String[] args) {
		// Query input file name
		Scanner s = new Scanner(System.in);
		System.out.print("Enter text file name: ");
		String filename = s.nextLine();
		s.close();
		// Count number of palindromes
		int numPalindromes = 0;
		try {
			// Read file one line at a time
			s = new Scanner(new File(filename));
			while (s.hasNextLine()) {
				String text = s.nextLine();
				// Use isPalindrome method to check if text is palindrome
				if (isPalindrome(text)) {
					System.out.println("\"" + text + "\" is a palindrome.");
					numPalindromes++;
				} else {
					System.out.println("\"" + text + "\" is NOT a palindrome.");
				}
			}
			s.close();
			// Print total number of palindromes
			System.out.println("Total # of palindromes: " + numPalindromes);
		} catch (FileNotFoundException e) {
			System.out.println("Error: Cannot find file " + filename);
		}

	}

}