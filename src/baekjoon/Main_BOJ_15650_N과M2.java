package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BOJ_15650_N과M2 {

	private static StringBuilder answer;
	private static int M, N;
	private static boolean[] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(br.readLine());

		N = Integer.parseInt(stk.nextToken());
		M = Integer.parseInt(stk.nextToken());
		answer = new StringBuilder();
		visited = new boolean[N];

		perm(0, 0, new int[M]);

		System.out.println(answer.toString());
	}

	private static void perm(int cnt, int start, int[] res) {
		if (cnt == M) {
			for (int i = 0; i < M; i++) {
				answer.append(res[i]).append(" ");
			}
			answer.append("\n");

			return;
		}

		for (int i = start; i < N; i++) {
			if (!visited[i]) {
				visited[i] = true;
				res[cnt] = i + 1;
				perm(cnt + 1, i + 1, res);
				visited[i] = false;
			}
		}
	}
}
