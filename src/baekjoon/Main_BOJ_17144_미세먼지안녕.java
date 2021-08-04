package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BOJ_17144_미세먼지안녕 {
	static int[] dr = {1, -1, 0, 0};
	static int[] dc = {0, 0, 1, -1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk;

		stk = new StringTokenizer(br.readLine());
		int R = Integer.parseInt(stk.nextToken());
		int C = Integer.parseInt(stk.nextToken());
		int T = Integer.parseInt(stk.nextToken());

		int[] airconPos = new int[2];
		int idx = 0;
		int[][] map = new int[R][C];
		for (int i = 0; i < R; i++) {
			stk = new StringTokenizer(br.readLine());
			for (int j = 0; j < C; j++) {
				map[i][j] = Integer.parseInt(stk.nextToken());
				if (map[i][j] == -1) {
					airconPos[idx++] = i;
				}
			}
		}

		for (int t = 0; t < T; t++) {
			// 미세먼지 확산
			int[][] tmp = new int[R][C];
			for (int i = 0; i < R; i++) {
				for (int j = 0; j < C; j++) {
					if (map[i][j] > 0) {    // 미세먼지가 있는 곳에서 확산

						int count = 0, amount = map[i][j] / 5;
						for (int d = 0; d < 4; d++) {    // 사방
							int nr = i + dr[d];
							int nc = j + dc[d];
							if (nr < 0 || nr >= R || nc < 0 || nc >= C)
								continue;
							if (map[nr][nc] < 0)
								continue;

							tmp[nr][nc] += amount;
							count++;
						}
						tmp[i][j] += (map[i][j] - amount * count);

					}
				}
			}
			for (int i = 0; i < R; i++) {
				for (int j = 0; j < C; j++) {
					if (map[i][j] < 0)
						continue;
					map[i][j] = tmp[i][j];
				}
			}

//			for (int i = 0; i < R; i++) {
//				System.out.println(Arrays.toString(map[i]));
//			}
//			System.out.println();

			// 공기청정기 순환
			// 위쪽, 반시계방향
			int[] airconUpPos = {Math.min(airconPos[0], airconPos[1]), 0};
			int curR = airconUpPos[0];
			int curC = airconUpPos[1];
			int nextSave = 0;
			while (true) {
				int nr = 0, nc = 0;
				if (curR == airconUpPos[0]) {    // 아래쪽
					if (curC == C - 1) {
						nr = curR - 1;
						nc = curC;
					} else {
						nr = curR;
						nc = curC + 1;
					}
				} else if (curC == C - 1) {            // 오른쪽
					if (curR == 0) {
						nr = curR;
						nc = curC - 1;
					} else {
						nr = curR - 1;
						nc = curC;
					}

				} else if (curR == 0) {            // 위쪽
					if (curC == airconUpPos[1]) {
						nr = curR + 1;
						nc = curC;
					} else {
						nr = curR;
						nc = curC - 1;
					}

				} else if (curC == airconUpPos[1]) {    // 왼쪽
					if (curR == airconUpPos[0] - 1) {
						break;
					} else {
						nr = curR + 1;
						nc = curC;
					}

				}
				// swap
				int save = map[nr][nc];
				map[nr][nc] = nextSave;
				nextSave = save;
				// 전진
				curR = nr;
				curC = nc;
			}

			// 아래쪽, 시계방향
			int[] airconDownPos = {Math.max(airconPos[0], airconPos[1]), 0};
			curR = airconDownPos[0];
			curC = airconDownPos[1];
			nextSave = 0;
			while (true) {
				int nr = 0, nc = 0;
				if (curR == airconDownPos[0]) {            // 위쪽
					if (curC == C - 1) {
						nr = curR + 1;
						nc = curC;
					} else {
						nr = curR;
						nc = curC + 1;
					}

				} else if (curC == C - 1) {            // 오른쪽
					if (curR == R - 1) {
						nr = curR;
						nc = curC - 1;
					} else {
						nr = curR + 1;
						nc = curC;
					}

				} else if (curR == R - 1) {    // 아래쪽
					if (curC == airconDownPos[1]) {
						nr = curR - 1;
						nc = curC;
					} else {
						nr = curR;
						nc = curC - 1;
					}

				} else if (curC == airconDownPos[1]) {    // 왼쪽
					if (curR == airconDownPos[0] + 1) {
						break;
					} else {
						nr = curR - 1;
						nc = curC;
					}

				}
				// swap
				int save = map[nr][nc];
				map[nr][nc] = nextSave;
				nextSave = save;
				// 전진
				curR = nr;
				curC = nc;
			}

//			for (int i = 0; i < R; i++) {
//				System.out.println(Arrays.toString(map[i]));
//			}
//			System.out.println("========================================");
//			System.out.println();
		}

		int sum = 0;
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (map[i][j] > 0)
					sum += map[i][j];
			}
		}

		System.out.println(sum);
	}
}
