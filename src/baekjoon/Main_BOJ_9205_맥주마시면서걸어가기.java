package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_BOJ_9205_맥주마시면서걸어가기 {
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk;
		StringBuilder answer = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for (int tc = 0; tc < T; tc++) {
			// input
			int N = Integer.parseInt(br.readLine());
			int[][] nodes = new int[N+2][2];
			for (int j = 0; j < N+2; j++) {
				stk = new StringTokenizer(br.readLine());
				nodes[j][0] = Integer.parseInt(stk.nextToken());
				nodes[j][1] = Integer.parseInt(stk.nextToken());
			}
			
			// logic
			// 플루이드-워샬
			int[][] adjMatrix = new int[N+2][N+2];
			// 각 노드에 대해서 거리가 1,000m이하인 간선 표시
			for (int j = 0; j < N+2; j++) {
				int[] node1 = nodes[j];
				for (int j2 = j+1; j2 < N+2; j2++) {
					int[] node2 = nodes[j2];
					int distance = Math.abs(node2[0]-node1[0]) + Math.abs(node2[1]-node1[1]);
					if (distance > 1000)
						adjMatrix[j][j2] = adjMatrix[j2][j] = 987654321;
					else
						adjMatrix[j][j2] = adjMatrix[j2][j] = distance;
				}
			}
			
			for (int k = 0; k < N+2; k++) {
				for (int i = 0; i < N+2; i++) {
					if (i == k)	continue;
					for (int j = 0; j < N+2; j++) {
						if (j == i || j == k)	continue;
						
						adjMatrix[i][j] = Math.min(adjMatrix[i][k]+adjMatrix[k][j], adjMatrix[i][j]);
					}
				}
			}
			
//			System.out.println(adjMatrix[0][N+1]);
			int minDistance = adjMatrix[0][N+1];
			if (minDistance == 987654321)
				answer.append("sad\n");
			else
				answer.append("happy\n");
		}
		
		System.out.println(answer.toString());
	}
}

/*
플로이드-워샬로 접근 안하고 처음에 DFS로 접근한 Solution.

public class Main_BOJ_9205_맥주마시면서걸어가기2 {
	static int[][] adjMatrix, nodes;
	static int N;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk;
		StringBuilder answer = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for (int i = 0; i < T; i++) {
			// input
			N = Integer.parseInt(br.readLine());
			nodes = new int[N+2][2];
			for (int j = 0; j < N+2; j++) {
				stk = new StringTokenizer(br.readLine());
				nodes[j][0] = Integer.parseInt(stk.nextToken());
				nodes[j][1] = Integer.parseInt(stk.nextToken());
			}
			
			// logic
			adjMatrix = new int[N+2][N+2];
			// 각 노드에 대해서 거리가 1,000m이하인 간선 표시
			for (int j = 0; j < N+2; j++) {
				int[] node1 = nodes[j];
				for (int j2 = j+1; j2 < N+2; j2++) {
					int[] node2 = nodes[j2];
					int distance = Math.abs(node2[0]-node1[0]) + Math.abs(node2[1]-node1[1]);
					if (distance <= 1000)
						adjMatrix[j][j2] = adjMatrix[j2][j] = distance;
				}
			}
			
//			for (int j = 0; j < N+2; j++) {
//				System.out.println(Arrays.toString(adjMatrix[j]));
//			}
//			System.out.println();
			
			// 인접행렬 DFS
			if (dfs(0, new boolean[N+2], nodes[N+1])) {
				answer.append("happy\n");
			} else {
				answer.append("sad\n");
			}
			
		}
		
		System.out.println(answer.toString());
	}

	private static boolean dfs(int cur, boolean[] visited, int[] finish) {
//		System.out.println("cur: " + cur);
//		System.out.println(Arrays.toString(nodes[cur]));
		
		visited[cur] = true;
		
		if (nodes[cur][0] == finish[0] && nodes[cur][1] == finish[1]) {
//			System.out.println("FINISH");
			return true;
		}
		
		for (int i = 0; i < N+2; i++) {
			if (!visited[i] && adjMatrix[cur][i] != 0) {
				if (dfs(i, visited, finish)) {
					return true;
				};
			}
		}
		
		return false;
	}
}
*/
