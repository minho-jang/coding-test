package programmers;

public class Solution_PRG_WeeklyChallenge_전력망을둘로나누기 {
	int N;

	public int solution(int n, int[][] wires) {
		int answer = 101;

		this.N = n;

		boolean[][] connected = new boolean[N + 1][N + 1];
		for (int[] wire : wires) {
			connected[wire[0]][wire[1]] =
					connected[wire[1]][wire[0]] = true;
		}

		for (int[] wire : wires) {
			connected[wire[0]][wire[1]] =
					connected[wire[1]][wire[0]] = false;

			int count1 = dfs(connected, wire[0], new boolean[N + 1]);
			int count2 = dfs(connected, wire[1], new boolean[N + 1]);

			int result = Math.abs(count1 - count2);
			answer = Math.min(answer, result);

			connected[wire[0]][wire[1]] =
					connected[wire[1]][wire[0]] = true;
		}

		return answer;
	}

	int dfs(boolean[][] connected, int start, boolean[] visited) {
		visited[start] = true;

		int sum = 1;
		for (int i = 0; i <= N; i++) {
			if (!visited[i] && connected[start][i]) {
				sum += dfs(connected, i, visited);
			}
		}

		return sum;
	}
}
