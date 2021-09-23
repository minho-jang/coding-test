package baekjoon;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_BOJ_11053_가장긴증가하는부분수열 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		StringTokenizer stk = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(stk.nextToken());
		}

		int[] dp = new int[N];
		for (int i = 0; i < N; i++) {
			if (dp[i] == 0) {
				dp[i] = 1;
			}

			for (int j = 0; j < i; j++) {
				if (arr[i] > arr[j]) {
					dp[i] = Math.max(dp[i], dp[j] + 1);
				}
			}
		}

		bw.write(Arrays.stream(dp).max().getAsInt() + "");
		bw.flush();

		br.close();
		bw.close();
	}
}

/*

9
10 20 50 25 20 50 30 70 85
// return 6

16
277 730 790 994 242 185 633 545 830 557 194 994 44 28 755 661
// return 5

 */
