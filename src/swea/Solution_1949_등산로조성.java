package swea;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution_1949_등산로조성 {
	static int[] dr = {0, 1, 0, -1};
	static int[] dc = {1, 0, -1, 0};
	//				      우     하    좌     상
	static int N, K;
	static int[][] map;
	static boolean[][] visited;
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input2.txt"));
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk;
		StringBuilder answer = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			
			stk = new StringTokenizer(br.readLine());
			N = Integer.parseInt(stk.nextToken());	// 맵 크기
			K = Integer.parseInt(stk.nextToken());	// 가능한 공사 깊이
			
			int maxHeight = 0;
			ArrayList<int[]> maxPos = new ArrayList<>();
			map = new int[N][N];
			visited = new boolean[N][N];
			for (int i = 0; i < N; i++) {
				stk = new StringTokenizer(br.readLine());
				for (int j = 0; j < map.length; j++) {
					map[i][j] = Integer.parseInt(stk.nextToken());
					if (map[i][j] > maxHeight) 	{
						maxPos.clear();
						maxPos.add(new int[] {i, j});
						maxHeight = map[i][j];
					} else if (map[i][j] == maxHeight) {
						maxPos.add(new int[] {i, j});
					}
				}
			}
			
			int maxLength = 0;
			for (int[] p : maxPos) {
//				System.out.println(Arrays.toString(p));
				
				visited[p[0]][p[1]] = true;
				int len = dfs(p[0], p[1], 0, 0) + 1;
				visited[p[0]][p[1]] = false;
				
				if (len > maxLength)	maxLength = len;
				
			}
			
			answer.append("#")
				.append(tc)
				.append(" ")
				.append(maxLength)
				.append("\n");
		}
		
		System.out.println(answer.toString());
	}

	private static int dfs(int r, int c, int cutCount, int depth) {
		int maxLength = 0;
		
		for (int d = 0; d < 4; d++) {						
			int nextR = r + dr[d];
			int nextC = c + dc[d];
			
			if (nextR < 0 || nextR >= N || nextC < 0 || nextC >= N || 
					visited[nextR][nextC])	continue;
			
			int len = 0;
			if (map[r][c] > map[nextR][nextC]) {	// 등산길 개척 가능
				visited[nextR][nextC] = true;
				len = dfs(nextR, nextC, cutCount, depth+1) + 1;
				visited[nextR][nextC] = false;
				
			} else {	// 공사필요
				if (cutCount == 0 && map[nextR][nextC]-(map[r][c]-1) <= K) {
					visited[nextR][nextC] = true;
					int tmp = map[nextR][nextC];
					map[nextR][nextC] = map[r][c] - 1;	// 공사
					len = dfs(nextR, nextC, cutCount+1, depth+1) + 1;
					map[nextR][nextC] = tmp;			// 공사 되돌리기
					visited[nextR][nextC] = false;
				}
			}
			
			if (maxLength < len)	maxLength = len;
		}
		
//		if (depth == 8) {
//			for (int i = 0; i < N; i++) {
//				System.out.println(Arrays.toString(visited[i]));
//			}
//			System.out.println();
//			for (int i = 0; i < N; i++) {
//				System.out.println(Arrays.toString(map[i]));
//			}
//			System.out.println();
//		}
		return maxLength;
	}
}
