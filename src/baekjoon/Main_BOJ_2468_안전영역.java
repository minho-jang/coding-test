package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_BOJ_2468_안전영역 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk;

		int N = Integer.parseInt(br.readLine());

		int maxHeight = 0;
		int[][] map = new int[N][N];
		for (int i = 0; i < N; i++) {
			stk = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(stk.nextToken());
				if (map[i][j] > maxHeight) {
					maxHeight = map[i][j];
				}
			}
		}

		int answer = 1;
		for (int rain = 1; rain <= maxHeight; rain++) {
			int count = getSafeAreaCount(map, rain);
			if (answer < count) {
				answer = count;
			}
		}

		System.out.println(answer);
	}

	static class Pos {
		int r, c;

		Pos(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

	private static int getSafeAreaCount(int[][] map, int rain) {
		int N = map.length;

		int count = 0;

		int[][] tmpMap = copyMap(map);

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (tmpMap[i][j] > rain) {
					checkArea(tmpMap, i, j, rain);
					count++;
				}
			}
		}

		return count;
	}

	private static int[][] copyMap(int[][] map) {
		int N = map.length;
		int[][] tmp = new int[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				tmp[i][j] = map[i][j];
			}
		}
		return tmp;
	}

	private static int[] dr = {-1, 1, 0, 0};
	private static int[] dc = {0, 0, -1, 1};

	private static void checkArea(int[][] map, int row, int col, int rain) {
		int N = map.length;

		Queue<Pos> queue = new LinkedList<>();
		queue.add(new Pos(row, col));

		while (!queue.isEmpty()) {
			Pos polled = queue.poll();
			for (int d = 0; d < 4; d++) {
				int nr = polled.r + dr[d];
				int nc = polled.c + dc[d];
				if (nr < 0 || nr >= N || nc < 0 || nc >= N || map[nr][nc] <= rain) {
					continue;
				}
				map[nr][nc] = 0;
				queue.add(new Pos(nr, nc));
			}
		}
	}
}
