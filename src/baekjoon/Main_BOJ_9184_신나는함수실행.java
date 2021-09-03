package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BOJ_9184_신나는함수실행 {
	private static final int MAX = 101;
	private static BufferedReader br;
	private static int a, b, c;
	private static int[][][] memo;

	public static void main(String[] args) throws IOException {
		StringBuilder answerSb = new StringBuilder();
		br = new BufferedReader(new InputStreamReader(System.in));

		initMemo();
		while (input()) {
			int result = w(a, b, c);
			answerSb.append(String.format("w(%d, %d, %d) = %d\n", a, b, c, result));
		}

		System.out.print(answerSb);
	}

	private static boolean input() throws IOException {
		StringTokenizer stk = new StringTokenizer(br.readLine());

		a = Integer.parseInt(stk.nextToken());
		b = Integer.parseInt(stk.nextToken());
		c = Integer.parseInt(stk.nextToken());

		return !(a == -1 && b == -1 && c == -1);
	}

	private static void initMemo() {
		memo = new int[MAX][MAX][MAX];
		for (int i = 0; i < MAX; i++) {
			for (int j = 0; j < MAX; j++) {
				for (int k = 0; k < MAX; k++) {
					memo[i][j][k] = Integer.MIN_VALUE;
				}
			}
		}
	}

	private static int w(int a, int b, int c) {
		int positiveA = a + 50;
		int positiveB = b + 50;
		int positiveC = c + 50;

		if (memo[positiveA][positiveB][positiveC] != Integer.MIN_VALUE) {
			return memo[positiveA][positiveB][positiveC];
		}

		if (a <= 0 || b <= 0 || c <= 0) {
			return 1;
		}

		if (a > 20 || b > 20 || c > 20) {
			return memo[positiveA][positiveB][positiveC] = w(20, 20, 20);
		}

		if (a < b && b < c) {
			return memo[positiveA][positiveB][positiveC] = w(a, b, c - 1) + w(a, b - 1, c - 1) - w(a, b - 1, c);
		}

		return memo[positiveA][positiveB][positiveC] = w(a - 1, b, c) + w(a - 1, b - 1, c) + w(a - 1, b, c - 1) - w(a - 1, b - 1, c - 1);
	}
}
