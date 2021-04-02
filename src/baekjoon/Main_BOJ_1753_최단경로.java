package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main_BOJ_1753_최단경로 {
	static final int INF = Integer.MAX_VALUE;
	static class Edge {
		int v, w;

		public Edge(int v, int w) {
			super();
			this.v = v;
			this.w = w;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk;
		
		stk = new StringTokenizer(br.readLine());
		int V = Integer.parseInt(stk.nextToken());
		int E = Integer.parseInt(stk.nextToken());
		int startV = Integer.parseInt(br.readLine());
		
		// 인접행렬이 아닌 "인접리스트"로 풀 것!!
		List<Edge>[] adjList = new ArrayList[V];
		for (int i = 0; i < V; i++) {
			adjList[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < E; i++) {
			stk = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(stk.nextToken());
			int v = Integer.parseInt(stk.nextToken());
			int w = Integer.parseInt(stk.nextToken());
			
			adjList[u-1].add(new Edge(v-1, w));
		}
		
		int[] distance = new int[V];
		boolean[] visited = new boolean[V];
		Arrays.fill(distance, INF);
		distance[startV-1] = 0;
		
		int min=0, current=0;
		for (int i = 0; i < V; i++) {
			min = INF;
			current = -1;
			for (int j = 0; j < V; j++) {
				if (!visited[j] && distance[j] < min) {
					min = distance[j];
					current = j;
				}
			}
			
			if (current == -1)	break;	// 방문할 곳이 없음
			visited[current] = true;
			
			for (Edge e : adjList[current]) {
				if (!visited[e.v] && distance[e.v] > min+e.w) {
					distance[e.v] = min+e.w;
				}
			}
		}
		
		for (int i = 0; i < V; i++) {
			System.out.println((distance[i] == INF) ? "INF" : distance[i]);
		}
	}
}
