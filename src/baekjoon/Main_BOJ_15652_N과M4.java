package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BOJ_15652_N과M4 {
	private static int N, M;
	private static StringBuilder answer;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(br.readLine());

		N = Integer.parseInt(stk.nextToken());
		M = Integer.parseInt(stk.nextToken());
		answer = new StringBuilder();

		perm(1, new int[M + 1]);

		System.out.println(answer.toString());
	}

	private static void perm(int cnt, int[] res) {
		if (cnt == M + 1) {
			for (int i = 1; i <= M; i++) {
				answer.append(res[i]).append(" ");
			}
			answer.append("\n");
			return;
		}

		for (int i = 0; i < N; i++) {
			if (i + 1 >= res[cnt - 1]) {
				res[cnt] = i + 1;
				perm(cnt + 1, res);
			}
		}
	}
}
