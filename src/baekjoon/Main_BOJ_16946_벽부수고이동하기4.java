package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_BOJ_16946_벽부수고이동하기4 {
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	static char[][] map;
	static int[][] mapNumbering;
	static int N, M;
	static ArrayList<Integer> areaMemo;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk;

		stk = new StringTokenizer(br.readLine());
		N = Integer.parseInt(stk.nextToken());
		M = Integer.parseInt(stk.nextToken());
		map = new char[N][M];
		mapNumbering = new int[N][M];
		for (int i = 0; i < N; i++) {
			map[i] = br.readLine().toCharArray();
		}

		areaMemo = new ArrayList<>();
		int idx = 1;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == '0') {
					int result = bfs(new Node(i, j), idx);
					areaMemo.add(result);
					idx++;
				}
			}
		}

		int[][] answer = new int[N][M];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (mapNumbering[i][j] == 0) {
					answer[i][j] = addFourDirection(i, j) % 10;
				}
			}
		}

		StringBuilder answerSb = new StringBuilder();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				answerSb.append(answer[i][j]);
			}
			answerSb.append("\n");
		}

		System.out.print(answerSb.toString());
	}

	private static int addFourDirection(int r, int c) {
		int result = 1;

		boolean[] visited = new boolean[areaMemo.size()];
		for (int d = 0; d < 4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			if (isOut(nr, nc)) continue;

			int areaNum = mapNumbering[nr][nc] - 1;
			if (areaNum < 0 || visited[areaNum]) continue;

			visited[areaNum] = true;
			result += areaMemo.get(areaNum);
		}

		return result;
	}

	private static int bfs(Node node, int idx) {
		int result = 1;

		Queue<Node> queue = new LinkedList<>();

		mapNumbering[node.r][node.c] = idx;
		map[node.r][node.c] = '1';
		queue.add(node);

		while (!queue.isEmpty()) {
			Node polled = queue.poll();
			for (int d = 0; d < 4; d++) {
				int nr = polled.r + dr[d];
				int nc = polled.c + dc[d];
				if (isOut(nr, nc)) continue;
				if (map[nr][nc] == '1') continue;

				mapNumbering[nr][nc] = idx;
				map[nr][nc] = '1';
				result++;
				queue.add(new Node(nr, nc));
			}
		}

		return result;
	}

	private static boolean isOut(int nr, int nc) {
		return nr < 0 || nr >= N || nc < 0 || nc >= M;
	}

	static class Node {
		int r, c;

		Node(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
}
