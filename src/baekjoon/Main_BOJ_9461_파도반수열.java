package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_BOJ_9461_파도반수열 {
	private static final int MAX = 100;
	private static long[] memo;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder answerSb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		initMemo();
		for (int tc = 0; tc < T; tc++) {
			int N = Integer.parseInt(br.readLine());
			answerSb.append(P(N)).append("\n");
		}
		System.out.print(answerSb);
	}

	private static void initMemo() {
		memo = new long[MAX + 1];
		memo[1] = 1;
		memo[2] = 1;
		memo[3] = 1;
		memo[4] = 2;
		memo[5] = 2;
	}

	private static long P(int n) {
		if (memo[n] != 0) {
			return memo[n];
		}

		return memo[n] = P(n - 1) + P(n - 5);
	}
}
