package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

public class Main_BOJ_16236_아기상어 {
	static int[] dr = {1, 0, 0, -1};
	static int[] dc = {0, 1, -1, 0};

	//				       하    우    좌      상
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk;

		int N = Integer.parseInt(br.readLine());    // 공간 크기

		int sharkR = 0;
		int sharkC = 0;
		int[][] map = new int[N][N];
		for (int i = 0; i < N; i++) {
			stk = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(stk.nextToken());
				if (map[i][j] == 9) {
					sharkR = i;
					sharkC = j;
				}
			}
		}

		int result = 0;
		int eatCount = 0;
		int sharkSize = 2;

		while (true) {
			// 각 칸마다 이동 시간 표시 (BFS)
			int[][] check = new int[N][N];
			for (int i = 0; i < N; i++) {
				Arrays.fill(check[i], -1);
			}
			check[sharkR][sharkC] = 0;

			List<Pos> eatList = new ArrayList<>();
			Queue<int[]> queue = new LinkedList<>();
			queue.offer(new int[]{sharkR, sharkC});
			while (!queue.isEmpty()) {
				int[] pos = queue.poll();
				for (int d = 0; d < 4; d++) {
					int nr = pos[0] + dr[d];
					int nc = pos[1] + dc[d];

					if (nr < 0 || nr >= N || nc < 0 || nc >= N || check[nr][nc] >= 0)
						continue;

					if (map[nr][nc] > sharkSize)
						continue;

					check[nr][nc] = check[pos[0]][pos[1]] + 1;
					if (map[nr][nc] != 0 && map[nr][nc] < sharkSize)
						eatList.add(new Pos(nr, nc, check[nr][nc]));
					queue.offer(new int[]{nr, nc});
				}
			}
			if (eatList.isEmpty())    // 먹을게 없으면 나간다.
				break;
//			for (int i = 0; i < N; i++) {
//				System.out.println(Arrays.toString(check[i]));
//			}

			// 그 중 먹잇감에 대해서 가까운, 위쪽, 왼쪽의 기준으로 선택
			Collections.sort(eatList);

			Pos target = eatList.get(0);
//			System.out.println(target.r + " " + target.c + " " + target.d);
//			System.out.println();

			// 타겟 냠냠
			map[sharkR][sharkC] = 0;
			map[target.r][target.c] = 9;
			sharkR = target.r;
			sharkC = target.c;
			result += target.d;
			eatCount++;
			if (eatCount >= sharkSize) {
				sharkSize++;
				eatCount = 0;
			}
		}

		System.out.println(result);
	}

	static class Pos implements Comparable<Pos> {
		int r, c, d;

		public Pos(int r, int c, int d) {
			super();
			this.r = r;
			this.c = c;
			this.d = d;
		}

		@Override
		public int compareTo(Pos o) {
			if (this.d == o.d) {
				if (this.r == o.r) {
					return this.c - o.c;
				} else
					return this.r - o.r;
			} else
				return this.d - o.d;
		}
	}
}
