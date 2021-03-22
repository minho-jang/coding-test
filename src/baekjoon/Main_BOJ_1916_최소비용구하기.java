package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_BOJ_1916_최소비용구하기 {
	static final int INF = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk;
		
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		int[][] adjMatrix = new int[N+1][N+1];
		for (int i = 0; i <= N; i++) {
			Arrays.fill(adjMatrix[i], -1);
		}
		
		for (int i = 0; i < M; i++) {
			stk = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(stk.nextToken());
			int v = Integer.parseInt(stk.nextToken());
			int w = Integer.parseInt(stk.nextToken());
			adjMatrix[u][v] = (adjMatrix[u][v] >= 0) ? Math.min(adjMatrix[u][v], w) : w;
		}
		
		stk = new StringTokenizer(br.readLine());
		int start = Integer.parseInt(stk.nextToken());
		int end = Integer.parseInt(stk.nextToken());
		
		int[] distance = new int[N+1];
		Arrays.fill(distance, INF);
		distance[start] = 0;
		boolean[] visited = new boolean[N+1];
		
		int min=0, cur=0;
		for (int i = 1; i <= N; i++) {
			min = INF;
			cur = -1;
			for (int j = 1; j <= N; j++) {
				if (!visited[j] && min > distance[j]) {
					min = distance[j];
					cur = j;
				}
			}
			if (cur < 0)	break;
			
			visited[cur] = true;
			if (cur == end) 	break;
			
			for (int j = 1; j <= N; j++) {
				if (!visited[j] && adjMatrix[cur][j] >= 0 && distance[j] > min+adjMatrix[cur][j]) {
					distance[j] = min+adjMatrix[cur][j];
				}
			}
			
			System.out.println(Arrays.toString(distance));
		}
		
		System.out.println(distance[end]);
	}
}
