package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_BOJ_16933_벽부수고이동하기3 {
	static final int DAY = 0;
	static final int NIGHT = 1;
	static final char WALL = '1';
	static final int[] dr = {-1, 1, 0, 0, 0};
	static final int[] dc = {0, 0, -1, 1, 0};
	static BufferedReader br;
	static StringTokenizer stk;
	static int N, M, K;
	static char[][] map;
	static int answer = -1;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input.txt"));
		br = new BufferedReader(new InputStreamReader(System.in));

		input();
		bfs();
		output();
	}

	private static void bfs() {
		boolean[][][][] visited = new boolean[N][M][2][K + 1];
		Queue<Node> queue = new LinkedList<>();

		visited[0][0][DAY][K] = true;
		queue.add(new Node(0, 0, DAY, K, 1, new ArrayList<>()));

		while (!queue.isEmpty()) {
			Node polled = queue.poll();
			if (polled.r == N - 1 && polled.c == M - 1) {
				answer = polled.distance;
				break;
			}

			for (int d = 0; d < 5; d++) {
				int nr = polled.r + dr[d];
				int nc = polled.c + dc[d];
				int nDayOrNight = (polled.dayOrNight + 1) % 2;

				if (isOut(nr, nc)) continue;

				if (map[nr][nc] == WALL && !polled.containsCrushedWall(nr, nc)) {
					if (polled.dayOrNight == DAY && polled.crush > 0) {
						if (!visited[nr][nc][nDayOrNight][polled.crush - 1]) {
							visited[nr][nc][nDayOrNight][polled.crush - 1] = true;
							ArrayList<int[]> nCrushedWall = new ArrayList<>(polled.crushedWall);
							nCrushedWall.add(new int[]{nr, nc});
							queue.add(new Node(nr, nc, nDayOrNight, polled.crush - 1, polled.distance + 1, nCrushedWall));
						}
					}

				} else {
					if (!visited[nr][nc][nDayOrNight][polled.crush]) {
						visited[nr][nc][nDayOrNight][polled.crush] = true;
						queue.add(new Node(nr, nc, nDayOrNight, polled.crush, polled.distance + 1, polled.crushedWall));
					}
				}

			}
		}
	}

	private static boolean isOut(int nr, int nc) {
		return nr < 0 || nr >= N || nc < 0 || nc >= M;
	}

	private static void output() {
		System.out.println(answer);
	}

	private static void input() throws IOException {
		stk = new StringTokenizer(br.readLine());
		N = Integer.parseInt(stk.nextToken());
		M = Integer.parseInt(stk.nextToken());
		K = Integer.parseInt(stk.nextToken());

		map = new char[N][M];
		for (int i = 0; i < N; i++) {
			map[i] = br.readLine().toCharArray();
		}
	}

	static class Node {
		int r, c, dayOrNight, crush, distance;
		ArrayList<int[]> crushedWall;

		Node(int r, int c, int dayOrNight, int crush, int distance, ArrayList<int[]> crushedWall) {
			this.r = r;
			this.c = c;
			this.dayOrNight = dayOrNight;
			this.crush = crush;
			this.distance = distance;
			this.crushedWall = crushedWall;
		}

		boolean containsCrushedWall(int r, int c) {
			for (int[] wall : crushedWall) {
				if (wall[0] == r && wall[1] == c)
					return true;
			}
			return false;
		}
	}
}
