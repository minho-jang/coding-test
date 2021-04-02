package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main_BOJ_18290_NM과K1 {
	private static int[] dr = {1, 0, -1, 0};
	private static int[] dc = {0, -1, 0, 1};
	private static int N, M, K, max = -500000;
	private static int[][] arr;
	private static boolean[][] visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk;
		
		stk = new StringTokenizer(br.readLine());
		N = Integer.parseInt(stk.nextToken());
		M = Integer.parseInt(stk.nextToken());
		K = Integer.parseInt(stk.nextToken());
		
		arr = new int[N][M];
		visited = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			stk = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(stk.nextToken());
			}
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (!visited[i][j]) {
					visited[i][j] = true;
					comb(1, i, j, arr[i][j]);
					visited[i][j] = false;
				}
			}
		}
		
		System.out.println(max);
	}

	private static void comb(int cnt, int r, int c, int res) {
		if (cnt == K) {
			if (res > max)
				max = res;
			return;
		}
		
		ArrayList<int[]> vistiedList = new ArrayList<>();
		for (int d = 0; d < 4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			
			if (nr < 0 || nr >= N || nc < 0 || nc >= M || visited[nr][nc])
				continue;
			
			visited[nr][nc] = true;
			vistiedList.add(new int[] {nr,nc});
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (!visited[i][j]) {
					visited[i][j] = true;
					comb(cnt+1, i, j, res+arr[i][j]);
					visited[i][j] = false;
				}
			}
		}
		
		for (int[] v : vistiedList) {
			visited[v[0]][v[1]] = false;
		}
	}
}
