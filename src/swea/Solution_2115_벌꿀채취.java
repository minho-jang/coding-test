package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_2115_벌꿀채취 {
	static int[][] map;
	static int N, M, C;
	static int answer;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk;

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			answer = 0;

			stk = new StringTokenizer(br.readLine());
			N = Integer.parseInt(stk.nextToken());    // 크기
			M = Integer.parseInt(stk.nextToken());    // 벌통개수
			C = Integer.parseInt(stk.nextToken());    // 채취제한

			map = new int[N][N];
			for (int i = 0; i < N; i++) {
				stk = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(stk.nextToken());
				}
			}

			comb(0, 0, new int[2]);

			System.out.println("#" + tc + " " + answer);
		}
	}

	private static void comb(int cnt, int start, int[] res) {
		if (cnt == 2) {
			// (r1, c1) , (r2, c2) 좌표 조합
			int r1 = res[0] / N;
			int c1 = res[0] % N;
			int r2 = res[1] / N;
			int c2 = res[1] % N;

			if (c1 + M > N || c2 + M > N)    // 범위를 넘어가는 벌통 선택
				return;
			if (r1 == r2 && c2 - c1 < M)    // 벌통이 겹친다
				return;

			int sum1 = getMaxProfit(r1, c1);
			int sum2 = getMaxProfit(r2, c2);
			answer = Math.max(answer, sum1 + sum2);

			return;
		}

		for (int i = start; i < N * N; i++) {
			res[cnt] = i;
			comb(cnt + 1, i + 1, res);
		}
	}

	private static int getMaxProfit(int r, int c) {
		int[] honey = new int[M];
		for (int i = 0; i < M; i++) {
			honey[i] = map[r][c + i];
		}

		int profit = subset(0, new boolean[M], honey);

		return profit;
	}

	private static int subset(int cnt, boolean[] visited, int[] honey) {
		if (cnt == M) {
			int sum = 0;
			int profit = 0;
			for (int i = 0; i < M; i++) {
				if (visited[i]) {
					sum += honey[i];
					profit += (honey[i] * honey[i]);
				}
			}

			if (sum > C)
				profit = 0;

			return profit;
		}

		visited[cnt] = true;
		int profit1 = subset(cnt + 1, visited, honey);
		visited[cnt] = false;
		int profit2 = subset(cnt + 1, visited, honey);

		return Math.max(profit1, profit2);
	}
}
