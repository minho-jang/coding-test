package swea;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Solution_1249_보급로 {
    static int[] dr = {1, -1, 0, 0};
    static int[] dc = {0, 0, 1, -1};

    public static void main(String[] args) throws NumberFormatException, IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answerSb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {

            int N = Integer.parseInt(br.readLine());
            int[][] map = new int[N][N];
            for (int i = 0; i < N; i++) {
                char[] input = br.readLine().toCharArray();
                for (int j = 0; j < N; j++) {
                    map[i][j] = input[j] - '0';
                }
            }

            int[][] dp = new int[N][N];
            for (int i = 0; i < N; i++) {
                Arrays.fill(dp[i], -1);
            }
            dp[0][0] = 0;

            // BFS
            Queue<Integer> queue = new LinkedList<>();
            queue.offer(0);
            queue.offer(0);

            int result = Integer.MAX_VALUE;
            while (!queue.isEmpty()) {

                int r = queue.poll();
                int c = queue.poll();
                if (r == N - 1 && c == N - 1) {
                    if (result > dp[r][c])
                        result = dp[r][c];
                    continue;
                }

                for (int d = 0; d < 4; d++) {
                    int nr = r + dr[d];
                    int nc = c + dc[d];

                    if (nr < 0 || nr >= N | nc < 0 || nc >= N)
                        continue;
                    if (dp[nr][nc] >= 0 && dp[nr][nc] <= dp[r][c] + map[nr][nc])
                        continue;

                    dp[nr][nc] = dp[r][c] + map[nr][nc];
                    queue.offer(nr);
                    queue.offer(nc);
                }

            }

            answerSb.append("#").append(tc).append(" ").append(result).append("\n");
        }

        System.out.println(answerSb.toString());
    }
}	
