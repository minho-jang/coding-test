package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BOJ_10159_저울 {
    static int[][] adjMatrix, adjMatrixRev;
    static boolean[] visited;
    static int N, M;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk;
        StringBuilder answerSb = new StringBuilder();

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        adjMatrix = new int[N][N];
        adjMatrixRev = new int[N][N];
        for (int i = 0; i < M; i++) {
            stk = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(stk.nextToken()) - 1;
            int b = Integer.parseInt(stk.nextToken()) - 1;
            adjMatrix[a][b] = 1;
            adjMatrixRev[b][a] = 1;
        }

        int[] answer = new int[N];
        for (int i = 0; i < N; i++) {
            visited = new boolean[N];
            dfs(i);
            dfs2(i);
            for (int j = 0; j < N; j++)
                if (!visited[j]) answer[i]++;
        }

        for (int i = 0; i < N; i++)
            answerSb.append(answer[i]).append("\n");
        System.out.print(answerSb.toString());

        br.close();
    }

    private static void dfs2(int here) {
        visited[here] = true;

        for (int i = 0; i < N; i++)
            if (!visited[i] && adjMatrixRev[here][i] == 1)
                dfs2(i);
    }

    private static void dfs(int here) {
        visited[here] = true;

        for (int i = 0; i < N; i++)
            if (!visited[i] && adjMatrix[here][i] == 1)
                dfs(i);

    }
}
