package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_BOJ_1003_피보나치함수 {
	private static final int MAX = 40;
	private static int[][] memo;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder answerSb = new StringBuilder();

		memo = new int[MAX + 1][2];
		int T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			int n = Integer.parseInt(br.readLine());
			int[] result = fibonacci(n);
			answerSb.append(result[0]).append(" ").append(result[1]).append("\n");
		}

		System.out.print(answerSb);
	}

	private static int[] fibonacci(int n) {
		if (memo[n][0] != 0 && memo[n][1] != 0) {
			return memo[n];
		}

		if (n == 0) {
			return new int[]{1, 0};
		} else if (n == 1) {
			return new int[]{0, 1};
		} else {
			int[] result1 = fibonacci(n - 1);
			int[] result2 = fibonacci(n - 2);

			return memo[n] = new int[]{result1[0] + result2[0], result1[1] + result2[1]};
		}
	}
}
