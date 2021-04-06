package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_BOJ_14442_벽부수고이동하기2 {
	static private int[] dr = {1, 0, -1, 0};
	static private int[] dc = {0, 1, 0, -1};
	static class Node {
		int r, c, k;
		public Node(int r, int c, int k) {
			super();
			this.r = r;
			this.c = c;
			this.k = k;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk;
		
		stk = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(stk.nextToken());
		int M = Integer.parseInt(stk.nextToken());
		int K = Integer.parseInt(stk.nextToken());
		
		char[][] map = new char[N][M];
		for (int i = 0; i < N; i++) {
			String inputLine = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = inputLine.charAt(j);
			}
		}
		
		if (N == 1 && M == 1) {
			System.out.println(1);
			return;
		}
		
		int answer = -1;
		boolean[][][] visited = new boolean[N][M][K+1];
		Queue<Node> queue = new LinkedList<>();
		
		int distance = 1;
		visited[0][0][K] = true;
		queue.offer(new Node(0, 0, K));
		while(!queue.isEmpty()) {
			
			int size = queue.size();
			for (int i = 0; i < size; i++) {
				Node n = queue.poll();
				
				for (int d = 0; d < 4; d++) {
					int nr = n.r + dr[d];
					int nc = n.c + dc[d];

					if (nr == N-1 && nc == M-1) {
						answer = distance+1;
						break;
					}
					
					if (nr >= N || nr < 0 || nc >= M || nc < 0)
						continue;
					if (visited[nr][nc][n.k])
						continue;
					
					if (map[nr][nc] == '1' && n.k > 0 && !visited[nr][nc][n.k-1])  {
						visited[nr][nc][n.k-1] = true;
						queue.offer(new Node(nr, nc, n.k-1));
						
					} else if (map[nr][nc] == '0') {
						visited[nr][nc][n.k] = true;
						queue.offer(new Node(nr, nc, n.k));
						
					}
				}
				
				if (answer > 0)
					break;
			}
			
			if (answer > 0)
				break;
			
			distance ++;
			
		}
		
		System.out.println(answer);
	}
}
