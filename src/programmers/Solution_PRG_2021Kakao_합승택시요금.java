package programmers;

class Solution_PRG_2021Kakao_합승택시요금 {
	static final int INF = 2000000;

	public int solution(int n, int s, int a, int b, int[][] fares) {
		int answer = INF;

		int[][] distance = new int[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (i == j) distance[i][j] = 0;
				else distance[i][j] = INF;
			}
		}
		for (int i = 0; i < fares.length; i++)
			distance[fares[i][0] - 1][fares[i][1] - 1] = distance[fares[i][1] - 1][fares[i][0] - 1] = fares[i][2];

		for (int k = 0; k < n; k++) {
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					distance[i][j] = Math.min(distance[i][j], distance[i][k] + distance[k][j]);
				}
			}
		}

		int destA = a - 1;
		int destB = b - 1;
		int start = s - 1;
		for (int k = 0; k < n; k++) {
			int fareSum = distance[start][k] + distance[k][destA] + distance[k][destB];
			answer = Math.min(answer, fareSum);
		}

		return answer;
	}
}