package baekjoon;

import java.io.*;
import java.util.*;

public class Main_BOJ_2458_키순서 {
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input.txt"));

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer stk = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(stk.nextToken());
		int M = Integer.parseInt(stk.nextToken());

		int[][] adjMatrix = new int[N][N];
		int[][] adjMatrixRev = new int[N][N];
		for (int i = 0; i < M; i++) {
			stk = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(stk.nextToken()) - 1;
			int to = Integer.parseInt(stk.nextToken()) - 1;
			adjMatrix[from][to] = 1;
			adjMatrixRev[to][from] = 1;
		}

		int answer = 0;
		for (int i = 0; i < N; i++) {
			boolean[] visited = new boolean[N];
			visited[i] = true;
			checkVisited(adjMatrix, visited, i);
			checkVisited(adjMatrixRev, visited, i);
			if (isAllTrue(visited)) {
				answer++;
			}
		}

		bw.write(answer + "");

		bw.flush();
		br.close();
		bw.close();
	}

	static void checkVisited(int[][] adjMatrix, boolean[] visited, int start) {
		for (int i = 0; i < adjMatrix.length; i++) {
			if (adjMatrix[start][i] == 1 && !visited[i]) {
				visited[i] = true;
				checkVisited(adjMatrix, visited, i);
			}
		}
	}

	static boolean isAllTrue(boolean[] visited) {
		for (int i = 0; i < visited.length; i++) {
			if (!visited[i]) {
				return false;
			}
		}
		return true;
	}
}
