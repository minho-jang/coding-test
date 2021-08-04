package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_BOJ_16948_데스나이트 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk;

		int N = Integer.parseInt(br.readLine());
		stk = new StringTokenizer(br.readLine());
		int[] start = {Integer.parseInt(stk.nextToken()), Integer.parseInt(stk.nextToken())};
		int[] end = {Integer.parseInt(stk.nextToken()), Integer.parseInt(stk.nextToken())};

		int[] dr = {-2, -2, 0, 0, 2, 2};
		int[] dc = {-1, 1, -2, 2, -1, 1};

		boolean[][] visited = new boolean[N][N];
		Queue<int[]> queue = new LinkedList<>();
		visited[start[0]][start[1]] = true;
		queue.add(start);

		boolean hit = false;
		int move = 0;
		while (!queue.isEmpty()) {
			int size = queue.size();
			move++;

			for (int i = 0; i < size; i++) {
				int[] polled = queue.poll();
				if (polled[0] == end[0] && polled[1] == end[1]) {
					hit = true;
					move--;
					queue.clear();
					break;
				}

				for (int d = 0; d < 6; d++) {
					int nr = polled[0] + dr[d];
					int nc = polled[1] + dc[d];

					if (nr < 0 || nr >= N || nc < 0 || nc >= N) continue;
					if (visited[nr][nc]) continue;

					visited[nr][nc] = true;
					queue.add(new int[]{nr, nc});
				}
			}
		}

		if (hit)
			System.out.println(move);
		else
			System.out.println(-1);
	}
}
