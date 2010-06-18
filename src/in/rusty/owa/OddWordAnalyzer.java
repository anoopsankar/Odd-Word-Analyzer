package in.rusty.owa;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;

public class OddWordAnalyzer {

	public static int letterDistances[][] = new int[26][26];

	/**
	 * @param args
	 * @throws FileNotFoundException
	 */
	public static void main(String[] args) throws IOException {

		buildLetterDistances();
		// printLetterDistances();
		findOddestWord();
	}

	private static void findOddestWord() throws IOException {
		BufferedReader wordReader = new BufferedReader(new FileReader(
				"/home/anoop/code/hobby/odd-word-analyzer/src/words.lst"));

		String word = "";
		String oddestWord = "";
		int lowestWeight = Integer.MAX_VALUE;

		while ((word = wordReader.readLine()) != null) {
			word = word.toUpperCase();
			int currentWeight = 0;
			if (word.length() > 20 && !word.contains(".") && !word.contains("-") && !word.contains(" ")) {
				for (int i = 0; i < word.length() - 1; i++) {
					char thisLetter = word.charAt(i);
					char nextLetter = word.charAt(i + 1);
					if (thisLetter > 64 && nextLetter > 64 && thisLetter < 92
							&& nextLetter < 92) {
						currentWeight += letterDistances[thisLetter - 65][nextLetter - 65];
					}
				}
				// System.out.println(currentWeight);

				currentWeight = currentWeight / word.length();

				if (currentWeight < lowestWeight) {
					lowestWeight = currentWeight;
					oddestWord = word;
				}
			}
		}
		wordReader.close();

		System.out
				.println("The word with the oddest spelling in the English language is "
						+ oddestWord);
	}

	private static void buildLetterDistances() throws IOException {
		BufferedReader wordReader = new BufferedReader(new FileReader(
				"/home/anoop/code/hobby/odd-word-analyzer/src/words.lst"));

		String word = "";
		while ((word = wordReader.readLine()) != null) {
			updateFrequencyFromWord(word.toUpperCase());
		}
		wordReader.close();
	}

	private static void updateFrequencyFromWord(String word) {
		for (int i = 0; i < word.length() - 1; i++) {

			char thisLetter = word.charAt(i);
			char nextLetter = word.charAt(i + 1);
			if (thisLetter > 64 && nextLetter > 64 && thisLetter < 92
					&& nextLetter < 92) {
				letterDistances[thisLetter - 65][nextLetter - 65]++;
			}
		}
	}

	// Debug
	private static void printLetterDistances() {
		for (int i = 65; i <= 65 + 26; i++) {
			System.out.print("\t" + (char) i);
		}
		System.out.println("");
		for (int i = 0; i < 26; i++) {
			System.out.print("" + (char) (i + 65) + "\t");
			for (int j = 0; j < 26; j++) {
				System.out.print(letterDistances[i][j] + "\t");
			}
			System.out.println("");
		}

	}

}
