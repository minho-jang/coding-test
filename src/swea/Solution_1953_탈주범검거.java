package swea;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution_1953_탈주범검거 {
	static class Pos {
		int r, c, type;
		public Pos(int r, int c, int type) {
			super();
			this.r = r;
			this.c = c;
			this.type = type;
		}
	}
	private static int[] dr = {1, 0, -1, 0};
	private static int[] dc = {0, -1, 0, 1};
	//						       하    좌      상    우   
	private static int[][] pipeType = {
			{0, 1, 2, 3},	//0.상하좌우
			{0, 2},			//1.상하
			{1, 3},			//2.좌우
			{2, 3},			//3.상우
			{0, 3},			//4.하우
			{0, 1},			//5.하좌
			{1, 2}			//6.상좌
	};
	private static int[][] possiblePipeType = {	// 방향에 따라 연결 가능한 파이프타입
			{0, 1, 3, 6},	//방향이 '하'이면 '상'에 연결된 파이프
			{0, 2, 3, 4}, 	//좌
			{0, 1, 4, 5},	//상
			{0, 2, 5, 6},	//우
	};
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("input2.txt"));
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk;
		StringBuilder answerSb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			
			stk = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(stk.nextToken());
			int M = Integer.parseInt(stk.nextToken());
			int R = Integer.parseInt(stk.nextToken());
			int C = Integer.parseInt(stk.nextToken());
			int L = Integer.parseInt(stk.nextToken());
			
			int[][] map = new int[N][M];
			for (int i = 0; i < N; i++) {
				stk = new StringTokenizer(br.readLine());
				for (int j = 0; j < M; j++) {
					map[i][j] = Integer.parseInt(stk.nextToken());
				}
			}
			
			// BFS
			boolean[][] visited = new boolean[N][M];
			Queue<Pos> queue = new LinkedList<>();
			
			Pos start = new Pos(R, C, map[R][C]-1);
			visited[start.r][start.c] = true;
			queue.add(start);
			
			int answer = 0;
			int time = 1;
			while(!queue.isEmpty()) {
				int size = queue.size();
				answer += size;
				if (time == L)
					break;
				
				for (int i = 0; i < size; i++) {
					Pos polled = queue.poll();
					int[] directions = pipeType[polled.type];
					for (int d : directions) {
						int nr = polled.r + dr[d];
						int nc = polled.c + dc[d];
						
						if (nr < 0 || nr >= N || nc < 0 || nc >= M)
							continue;
						if (visited[nr][nc])
							continue;
						if (map[nr][nc] == 0)
							continue;
						
						boolean impossibleConnect = true;
						for (int dd : possiblePipeType[d]) {
							if (map[nr][nc]-1 == dd)
								impossibleConnect = false;
						}
						if (impossibleConnect)
							continue;
						
						visited[nr][nc] = true;
						queue.offer(new Pos(nr, nc, map[nr][nc]-1));
					}
				}
				
				time++;
			}
			
			answerSb.append("#").append(tc).append(" ").append(answer).append("\n");
		}
		
		System.out.print(answerSb.toString());
	}
}
