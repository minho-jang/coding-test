package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_5643_키순서 {
	static final int INF = 987654321;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk;

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {

			int N = Integer.parseInt(br.readLine());
			int M = Integer.parseInt(br.readLine());

			int[][] adjMatrix = new int[N][N];
			for (int i = 0; i < M; i++) {
				stk = new StringTokenizer(br.readLine());
				int input1 = Integer.parseInt(stk.nextToken());
				int input2 = Integer.parseInt(stk.nextToken());
				adjMatrix[input1 - 1][input2 - 1] = 1;
			}

			// 플로이드-워샬
			int[][] distance = new int[N][N];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (i != j && adjMatrix[i][j] == 0)
						distance[i][j] = INF;
					else
						distance[i][j] = adjMatrix[i][j];
				}
			}

			for (int k = 0; k < N; k++) {
				for (int i = 0; i < N; i++) {
					for (int j = 0; j < N; j++) {
						distance[i][j] = Math.min(distance[i][j], distance[i][k] + distance[k][j]);
					}
				}
			}

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (distance[i][j] == INF) {
						distance[i][j] = distance[j][i];
					}
				}
			}

			int answer = 0;
			for (int i = 0; i < N; i++) {
				boolean noINF = true;
				for (int j = 0; j < N; j++) {
					if (distance[i][j] == INF) {
						noINF = false;
						break;
					}
				}
				if (noINF)
					answer++;
			}

			System.out.println("#" + tc + " " + answer);

		}


	}
}