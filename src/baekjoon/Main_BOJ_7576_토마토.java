package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_BOJ_7576_토마토 {
	static int[] dr = {1, -1, 0, 0};
	static int[] dc = {0, 0, 1, -1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk;

		stk = new StringTokenizer(br.readLine());
		int M = Integer.parseInt(stk.nextToken());
		int N = Integer.parseInt(stk.nextToken());

		Queue<Integer> queue = new LinkedList<>();
		boolean[][] visited = new boolean[N][M];
		int[][] map = new int[N][M];
		for (int i = 0; i < N; i++) {
			stk = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(stk.nextToken());
				if (map[i][j] == 1) {
					visited[i][j] = true;
					queue.add(i);
					queue.add(j);
				} else if (map[i][j] == -1) {
					visited[i][j] = true;
				}
			}
		}

		// BFS
		int time = 0;
		while (!queue.isEmpty()) {
			int size = queue.size() / 2;
			for (int i = 0; i < size; i++) {
				int r = queue.remove();
				int c = queue.remove();
				for (int d = 0; d < 4; d++) {
					int nr = r + dr[d];
					int nc = c + dc[d];
					if (nr < 0 || nr >= N || nc < 0 || nc >= M)
						continue;
					if (visited[nr][nc])
						continue;

					visited[nr][nc] = true;
					queue.add(nr);
					queue.add(nc);
				}
			}
			time++;
		}

		int answer = time - 1;
		// visited 확인
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (!visited[i][j])
					answer = -1;
			}
		}

		System.out.println(answer);
	}
}
