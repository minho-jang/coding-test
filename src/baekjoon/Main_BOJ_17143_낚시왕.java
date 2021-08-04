package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BOJ_17143_낚시왕 {
	static int R, C;
	static int[][] map;
	static Shark[] sharks;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, 1, -1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk;

		stk = new StringTokenizer(br.readLine());
		R = Integer.parseInt(stk.nextToken());
		C = Integer.parseInt(stk.nextToken());
		int M = Integer.parseInt(stk.nextToken());

		map = new int[R][C];
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				map[i][j] = -1;
			}
		}

		sharks = new Shark[M];
		for (int i = 0; i < M; i++) {
			stk = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(stk.nextToken()) - 1;
			int c = Integer.parseInt(stk.nextToken()) - 1;
			int v = Integer.parseInt(stk.nextToken());
			int d = Integer.parseInt(stk.nextToken()) - 1;
			int size = Integer.parseInt(stk.nextToken());
			sharks[i] = new Shark(i, r, c, v, d, size);
			map[r][c] = i;
		}

		int answer = 0;
		int curPos = 0;
		while (curPos < C) {

			// 가까운 상어 잡기
			for (int i = 0; i < R; i++) {
				if (map[i][curPos] >= 0) {
					int sharkIdx = map[i][curPos];
					answer += sharks[sharkIdx].size;
					sharks[sharkIdx].isDead = true;
					map[i][curPos] = -1;
					break;
				}
			}

			// 상어 이동
			for (int i = 0; i < M; i++) {
				if (!sharks[i].isDead) {
					move(sharks[i]);
				}
			}

			// map 초기화
			for (int i = 0; i < R; i++) {
				for (int j = 0; j < C; j++) {
					map[i][j] = -1;
				}
			}
			// 상어 재배치
			for (int i = 0; i < M; i++) {
				if (!sharks[i].isDead) {
					Shark curShark = sharks[i];
					if (map[curShark.r][curShark.c] >= 0) {    // 다른 상어랑 충돌
						Shark existing = sharks[map[curShark.r][curShark.c]];
						if (existing.size > curShark.size) {
							curShark.isDead = true;

						} else {    // 크기가 같은 경우는 존재하지 않음
							existing.isDead = true;
							map[curShark.r][curShark.c] = i;
						}

					} else {
						map[curShark.r][curShark.c] = i;
					}
				}
			}

			// 낚시왕 이동
			curPos++;

		}

		System.out.println(answer);

	}

	private static void move(Shark shark) {

		int moveCount = shark.v;    // 초당 속력만큼 움직인다.
		if (shark.d == 0 || shark.d == 1)        // 상하 방향이라면,
			moveCount = moveCount % ((R - 1) * 2);    // 행개수*2 만큼 반복됨
		else                                    // 좌우 방향이라면
			moveCount = moveCount % ((C - 1) * 2);    // 열개수*2 만큼 반복됨

		int r = shark.r, c = shark.c, d = shark.d;
		while (moveCount != 0) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			if (nr < 0 || nr >= R || nc < 0 || nc >= C) {
				// 방향바꾸고
				d = cross(d);
				// 위치 다시
				nr = r + dr[d];
				nc = c + dc[d];
			}

			r = nr;
			c = nc;
			moveCount--;
		}

		shark.r = r;
		shark.c = c;
		shark.d = d;
	}

	private static int cross(int d) {
		if (d == 0)
			return 1;
		if (d == 1)
			return 0;
		if (d == 2)
			return 3;
		if (d == 3)
			return 2;

		return -1;
	}

	static class Shark {
		int idx;
		int r, c;
		int v;
		int d;
		int size;
		boolean isDead;

		public Shark(int idx, int r, int c, int v, int d, int size) {
			this.idx = idx;
			this.r = r;
			this.c = c;
			this.v = v;
			this.d = d;
			this.size = size;
			this.isDead = false;
		}
	}
}
