package baekjoon;

import java.util.Scanner;

// N과 M (1)
public class Main_BOJ_15649_N과M1 {
	private static int N, M;
	private static boolean[] visited;
	private static StringBuilder answer;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		M = sc.nextInt();
		visited = new boolean[N];
		answer = new StringBuilder();
		perm(0, new int[M]);

		System.out.println(answer.toString());
		sc.close();
	}

	private static void perm(int cnt, int[] res) {
		if (cnt == M) {
			for (int i = 0; i < M; i++) {
				answer.append(res[i]).append(" ");
			}
			answer.append("\n");
			return;
		}

		for (int i = 0; i < N; i++) {
			if (!visited[i]) {
				visited[i] = true;
				res[cnt] = i + 1;
				perm(cnt + 1, res);
				visited[i] = false;
			}
		}
	}
}
