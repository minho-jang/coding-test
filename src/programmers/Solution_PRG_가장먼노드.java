package programmers;

import java.util.LinkedList;
import java.util.Queue;

public class Solution_PRG_가장먼노드 {
	public static void main(String[] args) {
		Solution_PRG_가장먼노드 sol = new Solution_PRG_가장먼노드();

		int n = 6;
		int[][] edge = {{3, 6}, {4, 3}, {3, 2}, {1, 3}, {1, 2}, {2, 4}, {5, 2}};
		System.out.println(sol.solution(n, edge));
	}

	public int solution(int n, int[][] edge) {
		int answer = 0;

		LinkedList<Integer>[] adjList = new LinkedList[n + 1];
		for (int i = 0; i <= n; i++) {
			adjList[i] = new LinkedList<>();
		}

		for (int i = 0; i < edge.length; i++) {
			int[] e = edge[i];
			adjList[e[0]].add(e[1]);
			adjList[e[1]].add(e[0]);
		}

		boolean[] visited = new boolean[n + 1];
		Queue<Integer> queue = new LinkedList<>();

		visited[1] = true;
		queue.add(1);

		while (!queue.isEmpty()) {
			int size = queue.size();
			answer = size;

			for (int i = 0; i < size; i++) {
				int polled = queue.poll();
				for (int nn : adjList[polled]) {
					if (visited[nn]) continue;
					visited[nn] = true;
					queue.add(nn);
				}
			}
		}

		return answer;
	}
}
