package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_3307_최장증가부분수열 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk;

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			int N = Integer.parseInt(br.readLine());
			stk = new StringTokenizer(br.readLine());
			int[] arr = new int[N];
			for (int i = 0; i < N; i++) {
				arr[i] = Integer.parseInt(stk.nextToken());
			}
			int[] dp = new int[N];

			int max = 1;
			for (int i = 0; i < N; i++) {
				dp[i] = 1;
				for (int j = 0; j < i; j++) {
					if (arr[i] > arr[j] && dp[i] < dp[j] + 1) {
						dp[i] = dp[j] + 1;
					}
				}
				if (max < dp[i]) max = dp[i];
			}

			System.out.println("#" + tc + " " + max);
		}
	}
}
