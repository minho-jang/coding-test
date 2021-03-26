package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main_BOJ_1194_달이차오른다가자 {
	static int[] dr = {1, 0, -1, 0};
	static int[] dc = {0, 1, 0, -1};
	
	static class Pos {
		int r, c, keyState, moveCount;

		public Pos(int r, int c, int k, int m) {
			super();
			this.r = r;
			this.c = c;
			this.keyState = k;
			this.moveCount = m;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk;
		
		stk = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(stk.nextToken());
		int M = Integer.parseInt(stk.nextToken());
		
		Pos start = null;
		char[][] map = new char[N][M];
		for (int i = 0; i < N; i++) {
			String line = new String(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = line.charAt(j);
				if (map[i][j] == '0') {
					start = new Pos(i,j,0,0);
				}
			}
		}
		
		int answer = -1;
		// 키상태에 따른 visit배열 => boolean[행][열][키상태]
		// 0  0  0  0  0  0
		// a  b  c  d  e  f		1이면 키를 가지고 있음.
		boolean[][][] visited = new boolean[N][M][64];
		// BFS
		Deque<Pos> queue = new ArrayDeque<>();
		visited[start.r][start.c][0] = true;
		queue.offer(start);
		while(!queue.isEmpty()) {
			Pos p = queue.poll();
			// 출구를 만나면
			if (map[p.r][p.c] == '1') {
				answer = p.moveCount;
				break;
			}
			
			for (int d = 0; d < 4; d++) {
				int nr = p.r + dr[d];
				int nc = p.c + dc[d];
				
				// 범위를 벗어나면, 벽이면,
				if (nr < 0 || nr >= N || nc < 0 || nc >= M || map[nr][nc] == '#')
					continue;
				// 방문한 적 있으면
				if (visited[nr][nc][p.keyState])
					continue;
				
				// 문을 발견하면
				if (map[nr][nc] >= 'A' && map[nr][nc] <= 'F')  {
					if ((p.keyState>>(5 - (map[nr][nc] - 'a'))&1) != 1)
						continue;
				}
				
				// 키를 발견하면
				if (map[nr][nc] >= 'a' && map[nr][nc] <= 'f') {
					int tmp = p.keyState | 1 << (5 - (map[nr][nc] - 'a'));
					visited[nr][nc][tmp] = true;
					queue.offer(new Pos(nr, nc, tmp, p.moveCount+1));
				}
				else {
					visited[nr][nc][p.keyState] = true;
					queue.offer(new Pos(nr, nc, p.keyState, p.moveCount+1));
				}
			}
			
		}
		
		System.out.println(answer);
	}
}
