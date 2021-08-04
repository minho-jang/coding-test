package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_BOJ_1309_동물원 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		int[][] dp = new int[N + 1][3];
		dp[1][0] = 1;
		dp[1][1] = 1;
		dp[1][2] = 1;

		for (int i = 2; i <= N; i++) {
			dp[i][0] = (dp[i - 1][0] + dp[i - 1][1] + dp[i - 1][2]) % 9901;
			dp[i][1] = (dp[i - 1][0] + dp[i - 1][2]) % 9901;
			dp[i][2] = (dp[i - 1][0] + dp[i - 1][1]) % 9901;
		}

//		for (int i = 0; i <= N; i++) {
//			System.out.println(Arrays.toString(dp[i]));
//		}

		int answer = (dp[N][0] + dp[N][1] + dp[N][2]) % 9901;
		System.out.println(answer);
	}
}
