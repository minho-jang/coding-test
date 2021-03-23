package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_BOJ_17472_다리만들기2 {
	static int[] dr = {0, 1, -1, 0};
	static int[] dc = {1, 0, 0, -1};
	//				      우, 하, 상, 좌
	static int N, M;
	static int[][] map, newMap;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk;
		
		stk = new StringTokenizer(br.readLine());
		N = Integer.parseInt(stk.nextToken());
		M = Integer.parseInt(stk.nextToken());
		map = new int[N][M];
		newMap = new int[N][M];
		for (int i = 0; i < N; i++) {
			stk = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(stk.nextToken());
			}
		}
		
		// 맵 탐색, 섬 개수 및 섬 번호 지정 
		int islandNum = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (newMap[i][j] == 0 && map[i][j] == 1) {
					dfs(i, j, ++islandNum);
				}
			}
		}
//		for (int i = 0; i < N; i++) {
//			System.out.println(Arrays.toString(newMap[i]));
//		}
//		System.out.println();
		
		// 맵을 통해서 섬간 최소거리 찾기 -> 인접행렬 만들기
		int[][] adjMatrix = new int[islandNum][islandNum];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (newMap[i][j] != 0) {
					// 다리 연결 확인
					int num = newMap[i][j];
					int r=i, c=j;
//					System.out.println(num + " " + r + " " + c);
					for (int d = 0; d < 4; d++) {
						int length = 0;
						int nr=r+dr[d], nc=c+dc[d];
						while(true) {
							if (nr < 0 || nr >= N || nc < 0 || nc >= M || newMap[nr][nc] == num) 
								break;
							
							if (newMap[nr][nc] != 0) {
								if (length > 1) {
									int from=num-1, to=newMap[nr][nc]-1;
									if (adjMatrix[from][to] != 0) // 이미 어떤 다리가 존재하면 둘 중 짧은 거로 삽입
										adjMatrix[from][to] = adjMatrix[to][from] = Math.min(adjMatrix[from][to], length);
									else 
										adjMatrix[from][to] = adjMatrix[to][from] = length;
									
//									System.out.println("==> " + from + " " + to + " " + adjMatrix[from][to]);
								}
								
								break;
							}
							
							nr += dr[d];
							nc += dc[d];
							length++;
						}
					}
				}
			}
		}
		
//		for (int i = 0; i < islandNum; i++) {
//			System.out.println(Arrays.toString(adjMatrix[i]));
//		}
		
		// 인접행렬을 통해 MST 찾기 (프림 알고리즘)
		final int INF = Integer.MAX_VALUE;
		boolean[] visited = new boolean[islandNum];
		int[] distance = new int[islandNum];
		Arrays.fill(distance, INF);
		distance[0] = 0;	// 0 에서 출발
		
		int min=0, cur=0;
		for (int i = 0; i < islandNum; i++) {
			min = INF;
			cur = -1;
			for (int k = 0; k < islandNum; k++) {
				if (!visited[k] && min > distance[k]) {
					min = distance[k];
					cur = k;
				}
			}
			
			if (cur < 0)	break;
			visited[cur] = true;
			
			for (int j = 0; j < islandNum; j++) {
				if (!visited[j] && adjMatrix[cur][j] > 0 && adjMatrix[cur][j] < distance[j]) {
					distance[j] = adjMatrix[cur][j];
				}
			}
		}
//		System.out.println(Arrays.toString(distance));
		
		int sum = 0;
		for (int i = 0; i < islandNum; i++) {
			if (distance[i] == INF) {
				sum = -1;
				break;
			}
			sum += distance[i];
		}
		
		System.out.println(sum);
	}
	
	private static void dfs(int r, int c, int islandNum) {
		newMap[r][c] = islandNum;
		
		for (int d = 0; d < 4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			
			if (nr < 0 || nr >= N || nc < 0 || nc >= M) 
				continue;
			
			if (newMap[nr][nc] == 0 && map[nr][nc] == 1) {
				dfs(nr, nc, islandNum);
			}
		}
	}
}
