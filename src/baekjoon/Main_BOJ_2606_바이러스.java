package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BOJ_2606_바이러스 {
	private static int answer = 0;
	private static int N;
	private static int[][] adjMatrix;
	private static boolean[] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk;

		N = Integer.parseInt(br.readLine());
		int edgeNum = Integer.parseInt(br.readLine());

		adjMatrix = new int[N][N];
		for (int i = 0; i < edgeNum; i++) {
			stk = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(stk.nextToken()) - 1;
			int to = Integer.parseInt(stk.nextToken()) - 1;
			adjMatrix[from][to] = adjMatrix[to][from] = 1;
		}

		visited = new boolean[N];
		dfs(0);

		System.out.println(answer);
	}

	private static void dfs(int start) {
		visited[start] = true;
		for (int i = 0; i < N; i++) {
			if (adjMatrix[start][i] == 1 && !visited[i]) {
				answer++;
				dfs(i);
			}
		}
	}
}
