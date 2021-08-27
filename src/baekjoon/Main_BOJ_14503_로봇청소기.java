package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BOJ_14503_로봇청소기 {
	static class Pos {
		int r, c, d;

		Pos(int r, int c, int d) {
			this.r = r;
			this.c = c;
			this.d = d;
		}
	}

	static int answer;
	static int[][] map;
	static Pos cleanerPos;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk;

		stk = new StringTokenizer(br.readLine());
		int R = Integer.parseInt(stk.nextToken());
		int C = Integer.parseInt(stk.nextToken());

		stk = new StringTokenizer(br.readLine());
		int cleanerR = Integer.parseInt(stk.nextToken());
		int cleanerC = Integer.parseInt(stk.nextToken());
		int cleanerD = Integer.parseInt(stk.nextToken());
		cleanerPos = new Pos(cleanerR, cleanerC, cleanerD);

		map = new int[R][C];
		for (int i = 0; i < R; i++) {
			stk = new StringTokenizer(br.readLine());
			for (int j = 0; j < C; j++) {
				int value = Integer.parseInt(stk.nextToken());
				map[i][j] = value;
			}
		}

		boolean movingBack = false;
		while (true) {
//			printMap();

			if (!movingBack) {
				cleanCurrentPos();
			}

			movingBack = false;
			boolean possibleMoveNext = checkAreaAndMove();

			if (possibleMoveNext) {
				continue;
			} else {
				if (moveBack()) {
					movingBack = true;
					continue;
				} else {
					System.out.println(answer);
					return;
				}
			}
		}
	}

	private static void cleanCurrentPos() {
		map[cleanerPos.r][cleanerPos.c] = 2;
		answer++;
	}

	private static boolean checkAreaAndMove() {
		for (int i = 0; i < 4; i++) {
			Pos nextPos = leftPosOfCleaner();
			if (map[nextPos.r][nextPos.c] == 0) {
				cleanerPos = nextPos;
				return true;
			}

			cleanerPos.d = nextPos.d;
		}
		return false;
	}

	private static Pos leftPosOfCleaner() {
		if (cleanerPos.d == 0) {
			return new Pos(cleanerPos.r, cleanerPos.c - 1, 3);
		} else if (cleanerPos.d == 1) {
			return new Pos(cleanerPos.r - 1, cleanerPos.c, 0);
		} else if (cleanerPos.d == 2) {
			return new Pos(cleanerPos.r, cleanerPos.c + 1, 1);
		} else if (cleanerPos.d == 3) {
			return new Pos(cleanerPos.r + 1, cleanerPos.c, 2);
		} else {
			return null;
		}
	}

	private static Pos backPosOfCleaner() {
		if (cleanerPos.d == 0) {
			return new Pos(cleanerPos.r + 1, cleanerPos.c, cleanerPos.d);
		} else if (cleanerPos.d == 1) {
			return new Pos(cleanerPos.r, cleanerPos.c - 1, cleanerPos.d);
		} else if (cleanerPos.d == 2) {
			return new Pos(cleanerPos.r - 1, cleanerPos.c, cleanerPos.d);
		} else if (cleanerPos.d == 3) {
			return new Pos(cleanerPos.r, cleanerPos.c + 1, cleanerPos.d);
		} else {
			return null;
		}
	}

	private static boolean moveBack() {
		Pos backPos = backPosOfCleaner();
		if (map[backPos.r][backPos.c] == 1) {
			return false;
		}

		cleanerPos = backPos;
		return true;
	}

	private static void printMap() {
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				if (cleanerPos.r == i && cleanerPos.c == j) {
					System.out.print("*" + " ");
				} else {
					System.out.print(map[i][j] + " ");
				}
			}
			System.out.println();
		}
		System.out.println();
	}
}
