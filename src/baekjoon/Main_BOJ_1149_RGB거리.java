package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_BOJ_1149_RGB거리 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk;
		
		int N = Integer.parseInt(br.readLine());
		int[][] rgb = new int[N][3];
		for (int i = 0; i < N; i++) {
			stk = new StringTokenizer(br.readLine());
			for (int j = 0; j < 3; j++) {
				rgb[i][j] = Integer.parseInt(stk.nextToken());
			}
		}
		
		int[][] dp = new int[N][3];
		dp[0][0] = rgb[0][0];
		dp[0][1] = rgb[0][1];
		dp[0][2] = rgb[0][2];
		
		for (int i = 1; i < N; i++) {
			for (int j = 0; j < 3; j++) {
				int min = Math.min(dp[i-1][(j+1)%3], dp[i-1][(j+2)%3]);
				dp[i][j] = rgb[i][j] + min;
			}
		}
		
		int answer = 0;
		int[] tmp = dp[N-1].clone();
		Arrays.sort(tmp);
		answer = tmp[0];
		
		System.out.println(answer);
	}
}
