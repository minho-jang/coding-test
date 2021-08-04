package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_BOJ_2206_벽부수고이동하기 {
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	static int N, M;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk;

		stk = new StringTokenizer(br.readLine());
		N = Integer.parseInt(stk.nextToken());
		M = Integer.parseInt(stk.nextToken());
		char[][] map = new char[N][M];
		for (int i = 0; i < N; i++) {
			map[i] = br.readLine().toCharArray();
		}

		boolean[][][] visited = new boolean[N][M][2];
		Queue<Node> queue = new LinkedList<>();
		queue.add(new Node(0, 0, 1, 1));

		boolean isFinish = false;
		int answer = 0;
		while (!queue.isEmpty()) {
			Node polled = queue.poll();
			if (polled.r == N - 1 && polled.c == M - 1) {
				isFinish = true;
				answer = polled.moveCount;
				break;
			}
			for (int d = 0; d < 4; d++) {
				int nr = polled.r + dr[d];
				int nc = polled.c + dc[d];
				if (isOut(nr, nc)) continue;
				if (visited[nr][nc][polled.destroyCount]) continue;

				if (map[nr][nc] == '1') {
					if (polled.destroyCount > 0) {
						visited[nr][nc][polled.destroyCount - 1] = true;
						queue.add(new Node(nr, nc, polled.moveCount + 1, polled.destroyCount - 1));
					}

				} else {
					visited[nr][nc][polled.destroyCount] = true;
					queue.add(new Node(nr, nc, polled.moveCount + 1, polled.destroyCount));
				}
			}
		}

		if (isFinish)
			System.out.println(answer);
		else
			System.out.println(-1);
	}

	private static boolean isOut(int nr, int nc) {
		return nr < 0 || nr >= N || nc < 0 || nc >= M;
	}

	static class Node {
		int r, c;
		int moveCount;
		int destroyCount;

		Node(int r, int c, int m, int d) {
			this.r = r;
			this.c = c;
			this.moveCount = m;
			this.destroyCount = d;
		}
	}
}
