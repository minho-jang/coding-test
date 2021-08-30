package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_BOJ_2573_빙산 {
	private static int R, C;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk;

		stk = new StringTokenizer(br.readLine());
		R = Integer.parseInt(stk.nextToken());
		C = Integer.parseInt(stk.nextToken());

		int[][] map = new int[R][C];
		for (int i = 0; i < R; i++) {
			stk = new StringTokenizer(br.readLine());
			for (int j = 0; j < C; j++) {
				map[i][j] = Integer.parseInt(stk.nextToken());
			}
		}

		int answer = 0;
		int time = 0;
		while (true) {
//			printMap(map);

			int areaCount = getAreaCount(map);
			if (areaCount == 0) {
				answer = 0;
				break;

			} else if (areaCount >= 2) {
				answer = time;
				break;

			}

			map = melt(map);
			time++;
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

	private static int getAreaCount(int[][] map) {
		int[][] copyMap = arrayCopy(map);

		int count = 0;
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (copyMap[i][j] != 0) {
					checkArea(copyMap, i, j);
					count++;
				}
			}
		}

		return count;
	}

	private static int[] dr = {-1, 1, 0, 0};
	private static int[] dc = {0, 0, -1, 1};

	private static void checkArea(int[][] map, int r, int c) {
		Queue<Pos> queue = new LinkedList<>();
		queue.offer(new Pos(r, c));

		while (!queue.isEmpty()) {
			Pos polled = queue.poll();
			for (int d = 0; d < 4; d++) {
				int nr = polled.r + dr[d];
				int nc = polled.c + dc[d];
				if (isOut(nr, nc) || map[nr][nc] == 0) {
					continue;
				}
				map[nr][nc] = 0;
				queue.add(new Pos(nr, nc));
			}
		}
	}

	private static boolean isOut(int r, int c) {
		return r < 0 || r >= R || c < 0 || c >= C;
	}

	private static int[][] arrayCopy(int[][] arr) {
		int[][] tmp = new int[R][C];
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				tmp[i][j] = arr[i][j];
			}
		}
		return tmp;
	}

	private static int[][] melt(int[][] map) {
		int[][] tmp = arrayCopy(map);

		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (map[i][j] != 0) {
					int oceanCount = checkOcean(map, i, j);
					tmp[i][j] = Math.max(map[i][j] - oceanCount, 0);
				}
			}
		}

		return tmp;
	}

	private static int checkOcean(int[][] map, int r, int c) {
		int count = 0;
		for (int d = 0; d < 4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			if (isOut(nr, nc)) {
				continue;
			}
			if (map[nr][nc] == 0) {
				count++;
			}
		}

		return count;
	}

	private static void printMap(int[][] map) {
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}
}


/*

5 7
0 0 0 0 0 0 0
0 2 4 5 3 0 0
0 3 0 2 5 2 0
0 7 6 2 4 0 0
0 0 0 0 0 0 0
return  2

3 3
1 2 0
0 1 1
0 2 1
return 1

*/
