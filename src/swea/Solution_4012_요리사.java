package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_4012_요리사 {
	static int N, answer;
	static int[][] synergy;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk;
		StringBuilder answerSb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {

			answer = 987654321;
			N = Integer.parseInt(br.readLine());
			synergy = new int[N][N];
			for (int i = 0; i < N; i++) {
				stk = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					synergy[i][j] = Integer.parseInt(stk.nextToken());
				}
			}

			comb(0, 0, new int[N / 2]);

			answerSb.append("#").append(tc).append(" ").append(answer).append("\n");
		}

		System.out.println(answerSb.toString());
	}

	private static void comb(int start, int cnt, int[] result) {
		if (cnt == N / 2) {
			boolean[] res1 = new boolean[N];
			boolean[] res2 = new boolean[N];
			for (int i = 0; i < N; i++) {
				boolean hit = false;
				for (int j = 0; j < N / 2; j++) {
					if (i == result[j])
						hit = true;
				}
				res1[i] = hit;
				res2[i] = !hit;
			}

			int cuisine1 = getSynergy(res1);
			int cuisine2 = getSynergy(res2);
			int diff = Math.abs(cuisine1 - cuisine2);
			answer = Math.min(answer, diff);

			return;
		}

		for (int i = start; i < N; i++) {
			result[cnt] = i;
			comb(i + 1, cnt + 1, result);
		}

	}

	private static int getSynergy(boolean[] res) {
		int sum = 0;

		for (int i = 0; i < N; i++) {
			if (res[i]) {

				for (int j = 0; j < N; j++) {
					if (res[j])
						sum += synergy[i][j];

				}
			}
		}

		return sum;
	}
}

/*
1
4
0 5 3 8
4 0 4 1
2 5 0 3
7 2 3 0

*/