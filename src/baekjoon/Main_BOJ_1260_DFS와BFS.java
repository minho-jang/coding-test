package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_BOJ_1260_DFS와BFS {

    static int[][] mat;
    static boolean[] visited;
    static int N;

    public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk;

        stk = new StringTokenizer(br.readLine());
        N = Integer.parseInt(stk.nextToken());    // 정점 개수
        int M = Integer.parseInt(stk.nextToken());    // 간선 개수
        int V = Integer.parseInt(stk.nextToken());    // 시작 정점

        mat = new int[N + 1][N + 1];
        for (int i = 0; i < M; i++) {
            stk = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(stk.nextToken());
            int to = Integer.parseInt(stk.nextToken());

            mat[from][to] = mat[to][from] = 1;
        }

//		for (int i = 0; i <= N; i++) {
//			System.out.println(Arrays.toString(mat[i]));
//		}

        visited = new boolean[N + 1];
        dfs(V);
        System.out.println();
        visited = new boolean[N + 1];
        bfs(V);
    }

    private static void dfs(int v) {
        visited[v] = true;
        System.out.print(v + " ");

        for (int i = 1; i <= N; i++) {
            if (!visited[i] && mat[v][i] == 1) {
                dfs(i);
            }
        }
    }

    private static void bfs(int v) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(v);
        visited[v] = true;

        while (!queue.isEmpty()) {
            int polled = queue.poll();
            System.out.print(polled + " ");

            for (int i = 1; i <= N; i++) {
                if (!visited[i] && mat[polled][i] == 1) {
                    queue.offer(i);
                    visited[i] = true;
                }
            }
        }
    }
}
