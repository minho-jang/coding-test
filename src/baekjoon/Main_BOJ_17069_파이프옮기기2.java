package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BOJ_17069_파이프옮기기2 {
	
	static int N, answer = 0;
	static int[][] map;
	static long[][][] visited;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk;
		
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		visited = new long[N][N][3];
		for (int i = 0; i < N; i++) {
			stk = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(stk.nextToken());
			}
		}
		visited[0][1][0] = 1;
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] == 1)
					continue;
				
				if (j-1 >= 0) 
					visited[i][j][0] += visited[i][j-1][0] + visited[i][j-1][2];
				
				if (i-1 >= 0) 
					visited[i][j][1] += visited[i-1][j][1] + visited[i-1][j][2];
				
				if (i-1 >= 0 && j-1 >= 0 && map[i-1][j] != 1 && map[i][j-1] != 1) 
					visited[i][j][2] += visited[i-1][j-1][0] + visited[i-1][j-1][1] + visited[i-1][j-1][2];
				
			}
		}
		
		System.out.println(visited[N-1][N-1][0] + visited[N-1][N-1][1] + visited[N-1][N-1][2]);
	}
}
