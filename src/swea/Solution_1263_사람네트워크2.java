package swea;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_1263_사람네트워크2 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk;

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {

			stk = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(stk.nextToken());
			int[][] adjMatrix = new int[N][N];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					adjMatrix[i][j] = Integer.parseInt(stk.nextToken());
					if (i != j && adjMatrix[i][j] == 0)
						adjMatrix[i][j] = 987654321;
				}
			}

			for (int k = 0; k < N; k++) {
				for (int i = 0; i < N; i++) {
					if (i == k) continue;
					for (int j = 0; j < N; j++) {
						if (j == i || j == k) continue;

						adjMatrix[i][j] = Math.min(adjMatrix[i][k] + adjMatrix[k][j], adjMatrix[i][j]);
					}
				}
			}
//			for (int i = 0; i < N; i++) {
//				System.out.println(Arrays.toString(adjMatrix[i]));
//			}
//			System.out.println();

			int min = 987654321;
			for (int i = 0; i < N; i++) {
				int sum = 0;
				for (int j = 0; j < N; j++) {
					sum += adjMatrix[i][j];
				}
				if (sum < min) {
					min = sum;
				}
			}

			System.out.println("#" + tc + " " + (min));
		}
	}
}
