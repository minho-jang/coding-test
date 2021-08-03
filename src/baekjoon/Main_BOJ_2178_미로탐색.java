package baekjoon;

import java.util.*;
import java.io.*;

public class Main_BOJ_2178_미로탐색 {
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	static class Pos {
		int r, c;
		int distance;
		Pos(int r, int c, int distance) {
			this.r = r;
			this.c = c;
			this.distance = distance;
		}
	}
	static int R, C;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk;

		stk = new StringTokenizer(br.readLine());
		R = Integer.parseInt(stk.nextToken());
		C = Integer.parseInt(stk.nextToken());

		char[][] map = new char[R][C];
		for (int i=0; i<R; i++) {
			map[i] = br.readLine().toCharArray();
		}

		boolean[][] visited = new boolean[R][C];
		Queue<Pos> queue = new LinkedList<>();
		queue.add(new Pos(0, 0, 1));
		visited[0][0] = true;

		int answer = 0;
		while(!queue.isEmpty()) {
			Pos polled = queue.poll();
			if (polled.r == R-1 && polled.c == C-1) {
				answer = polled.distance;
				break;
			}

			for (int d=0; d<4; d++) {
				int nr = polled.r + dr[d];
				int nc = polled.c + dc[d];

				if (isOut(nr, nc) || visited[nr][nc] || map[nr][nc] == '0') {
					continue;
				}

				visited[nr][nc] = true;
				queue.add(new Pos(nr, nc, polled.distance + 1));
			}
		}

		System.out.println(answer);
	}

	private static boolean isOut(int r, int c) {
		return r < 0 || r >= R || c < 0 || c >= C;
	}
}
