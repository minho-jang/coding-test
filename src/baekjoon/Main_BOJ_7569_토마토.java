package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_BOJ_7569_토마토 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk;

		stk = new StringTokenizer(br.readLine());
		C = Integer.parseInt(stk.nextToken());
		R = Integer.parseInt(stk.nextToken());
		H = Integer.parseInt(stk.nextToken());

		int notArrivalCount = 0;
		Queue<Pos> queue = new LinkedList<>();
		int[][][] map = new int[H][R][C];
		for (int h = 0; h < H; h++) {
			for (int i = 0; i < R; i++) {
				stk = new StringTokenizer(br.readLine());
				for (int j = 0; j < C; j++) {
					map[h][i][j] = Integer.parseInt(stk.nextToken());
					if (map[h][i][j] == 1) {
						queue.add(new Pos(i, j, h, 0));
					} else if (map[h][i][j] == 0) {
						notArrivalCount++;
					}
				}
			}
		}

		if (notArrivalCount == 0) {
			System.out.println(0);
			return;
		}

		int answer = 0;
		while (!queue.isEmpty()) {
			Pos polled = queue.poll();
			answer = Math.max(answer, polled.day);

			for (int d = 0; d < 6; d++) {
				int nr = polled.r + dr[d];
				int nc = polled.c + dc[d];
				int nh = polled.h + dh[d];
				int nDay = polled.day + 1;

				if (!isOut(nr, nc, nh) && map[nh][nr][nc] == 0) {
					map[nh][nr][nc] = 1;
					notArrivalCount--;
					queue.add(new Pos(nr, nc, nh, nDay));
				}
			}
		}

		if (notArrivalCount > 0) {
			System.out.println(-1);
			return;
		}

		System.out.println(answer);
	}

	static int R, C, H;

	static boolean isOut(int r, int c, int h) {
		return r < 0 || r >= R || c < 0 || c >= C || h < 0 || h >= H;
	}

	static int[] dr = {-1, 1, 0, 0, 0, 0};
	static int[] dc = {0, 0, -1, 1, 0, 0};
	static int[] dh = {0, 0, 0, 0, -1, 1};

	static class Pos {
		int r, c, h;
		int day;

		Pos(int r, int c, int h, int day) {
			this.r = r;
			this.c = c;
			this.h = h;
			this.day = day;
		}
	}
}
