package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_BOJ_1904_01타일 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		int answer = getTileCount(N);
		System.out.println(answer);

	}

	private static int getTileCount(int n) {
		if (n == 0) {
			return 0;
		} else if (n == 1) {
			return 1;
		} else if (n == 2) {
			return 2;
		}

		final int MOD = 15746;
		int[] memo = new int[n + 1];
		memo[0] = 0;
		memo[1] = 1;
		memo[2] = 2;

		for (int i = 3; i <= n; i++) {
			memo[i] = (memo[i - 2] + memo[i - 1]) % MOD;
		}

		return memo[n];
	}
}
