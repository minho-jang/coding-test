package baekjoon;

import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main_BOJ_2565_전깃줄 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int N = Integer.parseInt(br.readLine());
		int[][] lines = new int[N][2];
		for (int i = 0; i < N; i++) {
			StringTokenizer stk = new StringTokenizer(br.readLine());
			lines[i][0] = Integer.parseInt(stk.nextToken());
			lines[i][1] = Integer.parseInt(stk.nextToken());
		}

		Arrays.sort(lines, Comparator.comparingInt(o -> o[0]));

		int[] dp = new int[N];
		for (int i = 0; i < N; i++) {
			if (dp[i] == 0) {
				dp[i] = 1;
			}

			for (int j = 0; j < i; j++) {
				if (lines[i][0] > lines[j][0] && lines[i][1] > lines[j][1]) {
					dp[i] = Math.max(dp[i], dp[j] + 1);
				}
			}
		}

		int answer = Arrays.stream(dp).max().orElse(0);
		answer = N - answer;
		bw.write(answer + "");

		br.close();
		bw.close();
	}
}
