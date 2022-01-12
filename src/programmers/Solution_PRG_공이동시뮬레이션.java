package programmers;

public class Solution_PRG_공이동시뮬레이션 {
	public long solution(int n, int m, int x, int y, int[][] queries) {
		// L(I) R(E) T(I) B(E)
		int[] range = new int[]{y, y + 1, x, x + 1};
		int[] dir = {-1, 1, -1, 1};
		int[] limit = {m, m, n, n};
		int[] wall = {0, m, 0, n};

		for (int i = queries.length - 1; i >= 0; i--) {
			int[] query = queries[i];
			int command = query[0];
			int dx = query[1];

			// 반대방향으로 범위 증가
			int opposite = command ^ 1;
			range[opposite] += dir[opposite] * dx;
			range[opposite] = Math.max(Math.min(range[opposite], limit[opposite]), 0);

			// 주어진 방향을 봤는데 벽이 아니면, 반대방향으로 같이 이동 (범위 이동)
			if (range[command] != wall[command]) {
				range[command] += dir[opposite] * dx;
				range[command] = Math.max(Math.min(range[command], limit[command]), 0);
			}

			if (range[0] == m || range[1] == 0 || range[2] == n || range[3] == 0)
				return 0L;
		}

		return ((long) range[1] - range[0]) * ((long) range[3] - range[2]);
	}

	public static void main(String[] args) {
		Solution_PRG_공이동시뮬레이션 sol = new Solution_PRG_공이동시뮬레이션();
		int n = 2;
		int m = 2;
		int x = 0;
		int y = 0;
		int[][] queries = {
				{2, 1},
				{0, 1},
				{1, 1},
				{0, 1},
				{2, 1}
		};
		// answer >> 4

//		int n = 2;
//		int m = 5;
//		int x = 0;
//		int y = 1;
//		int[][] queries = {
//				{3, 1},
//				{2, 2},
//				{1, 1},
//				{2, 3},
//				{0, 1},
//				{2, 1}
//		};
		// answer >> 2

//		int n = 1000;
//		int m = 1000;
//		int x = 1;
//		int y = 1;
//		int[][] queries = {
//				{0, 100001},
//				{2, 100001}
//		};
		// answer >> 0

//		int n = 3;
//		int m = 3;
//		int x = 0;
//		int y = 1;
//		int[][] queries = {
//				{1, 300},
//				{0, 300},
//		};
		System.out.println(sol.solution(n, m, x, y, queries));
	}
}
