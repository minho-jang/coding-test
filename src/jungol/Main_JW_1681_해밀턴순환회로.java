package jungol;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_JW_1681_해밀턴순환회로 {
    static boolean[] visited;
    static int N, answer;
    static int[][] matrix;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk;

        N = Integer.parseInt(br.readLine().trim());
        matrix = new int[N][N];
        for (int i = 0; i < N; i++) {
            stk = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                matrix[i][j] = Integer.parseInt(stk.nextToken());
            }
        }

        answer = Integer.MAX_VALUE;
        visited = new boolean[N];
        dfs(0, 0, 1, new int[N + 1]);

        System.out.println(answer);
    }

    private static void dfs(int current, int cost, int count, int[] path) {
        if (current == 0 && count > 1 && count < N + 1) return;
        if (cost > answer) return;
        if (count == N + 1) {
//			System.out.println(Arrays.toString(path));
//			System.out.println(cost);
            answer = Math.min(answer, cost);
            return;
        }

        for (int i = 0; i < N; i++) {
            if (!visited[i] && matrix[current][i] > 0) {
                visited[i] = true;
                path[count] = i;
                dfs(i, cost + matrix[current][i], count + 1, path);
                visited[i] = false;
            }
        }
    }
}
