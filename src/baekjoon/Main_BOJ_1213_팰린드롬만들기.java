package baekjoon;

import java.io.*;

public class Main_BOJ_1213_팰린드롬만들기 {
	static int[] alphabetCounter;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		String s = br.readLine();
		alphabetCounter = new int[26];
		for (char c : s.toCharArray()) {
			alphabetCounter[c - 'A']++;
		}

		int oddIdx = hasOddBetterThanTwo();
		if (oddIdx == -2) {
			bw.write("I'm Sorry Hansoo");
		} else {
			String palindrome = makePalindrome(oddIdx);
			bw.write(palindrome);
		}

		bw.flush();
		br.close();
		bw.close();
	}

	private static String makePalindrome(int oddIdx) {
		StringBuilder sb = new StringBuilder();
		// front
		for (int i = 0; i < alphabetCounter.length; i++) {
			if (alphabetCounter[i] > 0) {
				char c = (char) (i + 'A');
				for (int j = 0; j < alphabetCounter[i] / 2; j++) {
					sb.append(c);
				}
			}
		}
		// mid
		if (oddIdx >= 0) {
			sb.append((char) (oddIdx + 'A'));
		}
		// back
		for (int i = alphabetCounter.length - 1; i >= 0; i--) {
			if (alphabetCounter[i] > 0) {
				char c = (char) (i + 'A');
				for (int j = 0; j < alphabetCounter[i] / 2; j++) {
					sb.append(c);
				}
			}
		}
		return sb.toString();
	}

	// -2 : has odd better than two
	// -1 : no odd
	// 0~ : odd index
	private static int hasOddBetterThanTwo() {
		int oddIdx = -1;
		int oddCount = 0;
		for (int i = 0; i < alphabetCounter.length; i++) {
			int count = alphabetCounter[i];
			if (count % 2 == 1) {
				oddCount++;
				oddIdx = i;
			}
		}
		return oddCount > 1 ? -2 : oddIdx;
	}
}
