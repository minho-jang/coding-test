package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution_10966_물놀이를가자 {
	private static int[] dr = {1, 0, -1, 0};
	private static int[] dc = {0, 1, 0, -1};

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk;
		StringBuilder answerSb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {

			stk = new StringTokenizer(br.readLine());
			int R = Integer.parseInt(stk.nextToken());
			int C = Integer.parseInt(stk.nextToken());

			ArrayList<Pos> waterList = new ArrayList<>();
			char[][] map = new char[R][C];
			for (int i = 0; i < R; i++) {
				map[i] = br.readLine().toCharArray();
				for (int j = 0; j < C; j++) {
					if (map[i][j] == 'W')
						waterList.add(new Pos(i, j, 0));
				}
			}

			boolean[][] visited = new boolean[R][C];
			Queue<Pos> queue = new LinkedList<>();
			for (Pos water : waterList) {
				visited[water.r][water.c] = true;
				queue.offer(water);
			}

			int sum = 0;
			while (!queue.isEmpty()) {
				Pos polled = queue.poll();
				for (int d = 0; d < 4; d++) {
					int nr = polled.r + dr[d];
					int nc = polled.c + dc[d];
					if (nr < 0 || nr >= R || nc < 0 || nc >= C)
						continue;
					if (visited[nr][nc])
						continue;
					if (map[nr][nc] == 'W')
						continue;

					sum += polled.d + 1;
					visited[nr][nc] = true;
					queue.offer(new Pos(nr, nc, polled.d + 1));
				}
			}

			answerSb.append("#").append(tc).append(" ")
					.append(sum).append("\n");
		}

		System.out.println(answerSb.toString());
	}

	static class Pos {
		int r, c, d;

		public Pos(int r, int c, int d) {
			super();
			this.r = r;
			this.c = c;
			this.d = d;
		}

		@Override
		public String toString() {
			return "Pos [r=" + r + ", c=" + c + ", d=" + d + "]";
		}
	}
}