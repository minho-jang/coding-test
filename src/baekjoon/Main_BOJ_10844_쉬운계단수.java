package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main_BOJ_10844_쉬운계단수 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		int[][] memo = new int[100][10];
		Arrays.fill(memo[0], 1);

		final int MOD = 1000000000;

		for (int i = 1; i < 100; i++) {
			for (int j = 0; j < 10; j++) {
				if (j == 0) {
					memo[i][0] = memo[i - 1][1] % MOD;
				} else if (j == 9) {
					memo[i][9] = memo[i - 1][8] % MOD;
				} else {
					memo[i][j] = (memo[i - 1][j - 1] + memo[i - 1][j + 1]) % MOD;
				}
			}
		}

		long sum = 0;
		for (int i = 1; i < 10; i++) {
			sum += memo[N - 1][i];
			sum %= MOD;
		}

		System.out.println(sum);
	}
}
