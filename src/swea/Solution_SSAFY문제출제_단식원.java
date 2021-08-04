package swea;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution_SSAFY문제출제_단식원 {
	static ArrayList<Pos> empty;
	static int[][] map;
	static int answer, N, M;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input.txt"));

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk;
		StringBuilder answerSb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			answer = 0;

			stk = new StringTokenizer(br.readLine());
			N = Integer.parseInt(stk.nextToken());
			M = Integer.parseInt(stk.nextToken());
			map = new int[N][M];
			empty = new ArrayList<>();
			for (int i = 0; i < N; i++) {
				stk = new StringTokenizer(br.readLine());
				for (int j = 0; j < M; j++) {
					map[i][j] = Integer.parseInt(stk.nextToken());
					if (map[i][j] == 0) {
						empty.add(new Pos(i, j));
					}
				}
			}

			comb(0, 0, new int[3]);
			answerSb.append("#").append(tc).append(" ").append(answer).append("\n");
		}

		System.out.print(answerSb.toString());
	}

	private static void comb(int cnt, int start, int[] res) {
		if (cnt == 3) {
			int result = checkZone(res);
			if (answer < result)
				answer = result;
			return;
		}
		for (int i = start; i < empty.size(); i++) {
			res[cnt] = i;
			comb(cnt + 1, i + 1, res);
		}
	}

	private static int checkZone(int[] poses) {
		int[][] tmpMap = new int[N][M];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				tmpMap[i][j] = map[i][j];
			}
		}
		for (int i = 0; i < 3; i++) {
			Pos pos = empty.get(poses[i]);
			tmpMap[pos.r][pos.c] = 1;
		}

		// BFS
		boolean[][] visited = new boolean[N][M];
		Queue<Pos> queue = new LinkedList<>();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (tmpMap[i][j] == 2) {
					visited[i][j] = true;
					queue.add(new Pos(i, j));
				}
			}
		}

		while (!queue.isEmpty()) {
			Pos p = queue.poll();
			for (int d = 0; d < 4; d++) {
				int nr = p.r + dr[d];
				int nc = p.c + dc[d];
				if (isOut(nr, nc)) continue;
				if (visited[nr][nc]) continue;
				if (tmpMap[nr][nc] == 0) {
					visited[nr][nc] = true;
					tmpMap[nr][nc] = 2;
					queue.add(new Pos(nr, nc));
				}
			}
		}
		int cnt = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (tmpMap[i][j] == 0) {
					cnt++;
				}
			}
		}
		return cnt;
	}

	private static boolean isOut(int r, int c) {
		return r < 0 || r >= N || c < 0 || c >= M;
	}

	static class Pos {
		int r, c;

		Pos(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
}
