package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_BOJ_2579_계단오르기 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		int[] scores = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			scores[i] = Integer.parseInt(br.readLine());
		}

		int[][] memo = new int[2][N + 1];
		memo[0][1] = scores[1];
		memo[1][1] = 0;

		for (int i = 2; i <= N; i++) {
			memo[0][i] = Math.max(memo[0][i - 2], memo[1][i - 2]) + scores[i];
			memo[1][i] = memo[0][i - 1] + scores[i];
		}

		int answer = Math.max(memo[0][N], memo[1][N]);
		System.out.println(answer);
	}
}
