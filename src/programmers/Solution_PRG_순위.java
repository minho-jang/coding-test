package programmers;

import java.util.LinkedList;
import java.util.Queue;

public class Solution_PRG_순위 {
	boolean[][] visited;

	public static void main(String[] args) {
		Solution_PRG_순위 sol = new Solution_PRG_순위();
		int n = 5;
		int[][] results = {
				{4, 3},
				{4, 2},
				{3, 2},
				{1, 2},
				{2, 5}
		};
		System.out.println(sol.solution(n, results));
	}

	public int solution(int n, int[][] results) {
		int answer = 0;

		int[][] adjMatrix = new int[n][n];

		for (int[] result : results) {
			adjMatrix[result[0] - 1][result[1] - 1] = 1;
		}

		visited = new boolean[n][n];
		bfs(adjMatrix, n);

		for (int i = 0; i < n; i++) {
			boolean hit = true;
			for (int j = 0; j < n; j++) {
				if (!visited[i][j] && !visited[j][i])
					hit = false;
			}
			if (hit) answer++;
		}

		return answer;
	}

	private void bfs(int[][] adjMatrix, int n) {
		for (int i = 0; i < n; i++) {

			boolean[] visitedTmp = new boolean[n];
			Queue<Integer> queue = new LinkedList<>();

			visitedTmp[i] = true;
			queue.add(i);

			while (!queue.isEmpty()) {
				int polled = queue.poll();
				for (int j = 0; j < n; j++) {
					if (!visitedTmp[j] && adjMatrix[polled][j] > 0) {
						visitedTmp[j] = true;
						queue.add(j);
					}
				}
			}

			for (int j = 0; j < n; j++)
				if (visitedTmp[j])
					visited[i][j] = true;

		}
	}
}
