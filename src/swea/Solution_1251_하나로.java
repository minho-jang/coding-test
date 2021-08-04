package swea;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution_1251_하나로 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk;

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {

			int N = Integer.parseInt(br.readLine());
			int[][] islandPos = new int[N][2];
			for (int i = 0; i < 2; i++) {
				stk = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					islandPos[j][i] = Integer.parseInt(stk.nextToken());
				}
			}
			double E = Double.parseDouble(br.readLine());

			int answer = 0;
			double[][] adjMatrix = new double[N][N];
			for (int i = 0; i < N; i++) {
				for (int j = i + 1; j < N; j++) {
					double distance = Math.sqrt(Math.pow(islandPos[i][0] - islandPos[j][0], 2) + Math.pow(islandPos[i][1] - islandPos[j][1], 2));
					adjMatrix[i][j] = adjMatrix[j][i] = distance;
				}
			}
//			for (int i = 0; i < N; i++) {
//				System.out.println(Arrays.toString(adjMatrix[i]));
//			}

			final int INF = Integer.MAX_VALUE;
			// MST (프림)
			double[] distance = new double[N];
			Arrays.fill(distance, INF);
			distance[0] = 0;

			boolean[] visited = new boolean[N];
			double min = 0, result = 0;
			int cur = 0;
			for (int i = 0; i < N; i++) {
				min = INF;
				cur = -1;
				for (int j = 0; j < N; j++) {
					if (!visited[j] && min > distance[j]) {
						min = distance[j];
						cur = j;
					}
				}

				if (cur < 0) break;
				result += Math.pow(min, 2) * E;
				visited[cur] = true;

				for (int j = 0; j < N; j++) {
					if (!visited[j] && adjMatrix[cur][j] > 0 && distance[j] > adjMatrix[cur][j]) {
						distance[j] = adjMatrix[cur][j];
					}
				}
			}
//			System.out.println(Arrays.toString(distance));
//			System.out.println(result);

			System.out.println("#" + tc + " " + Math.round(result));
		}
	}
}
