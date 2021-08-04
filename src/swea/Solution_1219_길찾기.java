package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_1219_길찾기 {
	private static boolean[][] adjMatrix;
	private static boolean find;

	public static void main(String[] args) throws IOException {
//		System.setIn(new FileInputStream("input.txt"));

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk;
		StringBuilder answer = new StringBuilder();

		int T = 10;
		for (int tc = 1; tc <= T; tc++) {

			stk = new StringTokenizer(br.readLine());
			stk.nextToken();
			int N = Integer.parseInt(stk.nextToken());

			stk = new StringTokenizer(br.readLine());
			adjMatrix = new boolean[100][100];
			for (int i = 0; i < N; i++) {
				int from = Integer.parseInt(stk.nextToken());
				int to = Integer.parseInt(stk.nextToken());
				adjMatrix[from][to] = true;
			}

			find = false;
			dfs(0);

			answer.append("#").append(tc).append(" ")
					.append(find ? 1 : 0).append("\n");

		}

		System.out.println(answer.toString());
	}

	private static void dfs(int start) {
		if (find) {
			return;
		}
		if (start == 99) {
			find = true;
			return;
		}

		for (int i = 0; i < 100; i++) {
			if (adjMatrix[start][i]) {
				dfs(i);
			}
		}
	}


}
