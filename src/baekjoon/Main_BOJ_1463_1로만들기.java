package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_BOJ_1463_1로만들기 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] dp = new int[N + 1];
		dp[N] = 0;

		for (int i = N - 1; i >= 1; i--) {
			int min = Integer.MAX_VALUE;
			if (i * 2 <= N)
				min = Math.min(min, dp[i * 2] + 1);

			if (i * 3 <= N)
				min = Math.min(min, dp[i * 3] + 1);

			if (i + 1 <= N)
				min = Math.min(min, dp[i + 1] + 1);

			dp[i] = min;
		}

//		System.out.println(Arrays.toString(dp));
		System.out.println(dp[1]);
	}
}
