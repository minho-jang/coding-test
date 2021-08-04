package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main_BOJ_2026_소풍 {
	private static boolean[] visited;
	private static ArrayList<Integer> answer;
	private static int K;
	private static int N;
	private static boolean finish;
	private static boolean[][] adjMatrix;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk;

		stk = new StringTokenizer(br.readLine());
		K = Integer.parseInt(stk.nextToken());
		N = Integer.parseInt(stk.nextToken());
		int F = Integer.parseInt(stk.nextToken());
		int[] numEdge = new int[N];
		adjMatrix = new boolean[N][N];
		for (int i = 0; i < F; i++) {
			stk = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(stk.nextToken()) - 1;
			int to = Integer.parseInt(stk.nextToken()) - 1;
			adjMatrix[from][to] = adjMatrix[to][from] = true;

			// 해당 정점의 간선 수 세기
			numEdge[from]++;
			numEdge[to]++;
		}
		answer = new ArrayList<>();

		for (int i = 0; i < N; i++) {
			if (numEdge[i] < K - 1)
				continue;

			visited = new boolean[N];

			// 완전그래프 확인
			visited[i] = true;
			dfs(i, 1);

			if (finish)
				break;
		}

		if (finish) {
			for (int ans : answer)
				System.out.println(ans);

		} else
			System.out.println(-1);
	}

	private static void dfs(int cur, int depth) {
		if (finish)
			return;
		if (depth == K) {    // K만큼 완전그래프 만들어짐
			finish = true;
			for (int i = 0; i < N; i++) {
				if (visited[i])
					answer.add(i + 1);
			}
			return;
		}

		// 완전그래프를 확인하는 방법은
		// 임의의 정점에서 정점을 하나씩 방문하면서,
		// 이전에 visited 처리된 정점들에 대해서 모든 연결되어 있는지 확인.
		for (int i = cur + 1; i < N; i++) {
			if (!visited[i] && isFriend(i)) {
				visited[i] = true;
				dfs(i, depth + 1);
				visited[i] = false;
			}

			if (finish)
				break;
		}
	}

	private static boolean isFriend(int i) {
		for (int j = 0; j < N; j++) {
			if (visited[j] && !adjMatrix[i][j])
				return false;
		}

		return true;
	}
}
