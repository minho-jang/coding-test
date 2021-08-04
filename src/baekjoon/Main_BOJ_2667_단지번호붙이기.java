package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main_BOJ_2667_단지번호붙이기 {
	static int N;
	static char[][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		map = new char[N][N];
		for (int i = 0; i < N; i++) {
			map[i] = br.readLine().toCharArray();
		}

		ArrayList<Integer> answerList = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] == '1') {
					answerList.add(dfs(i, j));
				}
			}
		}

		Collections.sort(answerList);
		System.out.println(answerList.size());
		for (int i = 0; i < answerList.size(); i++) {
			System.out.println(answerList.get(i));
		}
	}

	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};

	private static int dfs(int r, int c) {
		int sum = 1;
		map[r][c] = '0';
		for (int d = 0; d < 4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];

			if (isOut(nr, nc) || map[nr][nc] == '0') {
				continue;
			}

			sum += dfs(nr, nc);
		}
		return sum;
	}

	private static boolean isOut(int r, int c) {
		return r < 0 || r >= N || c < 0 || c >= N;
	}
}
