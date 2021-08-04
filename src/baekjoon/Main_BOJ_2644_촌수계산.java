package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BOJ_2644_촌수계산 {
	static int targetFrom, targetTo, answer, N;
	static boolean[][] adjMatrix;
	static boolean[] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk;

		N = Integer.parseInt(br.readLine());

		stk = new StringTokenizer(br.readLine());
		targetFrom = Integer.parseInt(stk.nextToken()) - 1;
		targetTo = Integer.parseInt(stk.nextToken()) - 1;

		adjMatrix = new boolean[N][N];
		visited = new boolean[N];
		int edgeNum = Integer.parseInt(br.readLine());
		for (int i = 0; i < edgeNum; i++) {
			stk = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(stk.nextToken()) - 1;
			int to = Integer.parseInt(stk.nextToken()) - 1;
			adjMatrix[from][to] = adjMatrix[to][from] = true;
		}

		answer = -1;
		dfs(targetFrom, 0);

		System.out.println(answer);
	}

	private static void dfs(int from, int count) {
		if (answer > 0) {
			return;
		}
		if (from == targetTo) {
			answer = count;
			return;
		}

		visited[from] = true;
		for (int i = 0; i < N; i++) {
			if (!visited[i] && adjMatrix[from][i]) {
				dfs(i, count + 1);
			}
		}
	}
}
