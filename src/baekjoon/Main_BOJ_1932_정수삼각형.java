package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_BOJ_1932_정수삼각형 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk;

		int N = Integer.parseInt(br.readLine());
		int[] memo = new int[N];
		for (int i = 0; i < N; i++) {
			int[] tmp = new int[N];
			stk = new StringTokenizer(br.readLine());

			for (int j = 0; j <= i; j++) {
				int num = Integer.parseInt(stk.nextToken());
				if (j == 0) {
					tmp[j] = memo[j] + num;
				} else {
					tmp[j] = Math.max(memo[j - 1], memo[j]) + num;
				}
			}

			for (int j = 0; j < N; j++) {
				memo[j] = tmp[j];
			}
		}

		int max = Arrays.stream(memo).max().orElse(0);
		System.out.println(max);
	}
}
