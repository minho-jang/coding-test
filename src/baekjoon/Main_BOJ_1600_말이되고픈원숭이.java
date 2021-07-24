package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_BOJ_1600_말이되고픈원숭이 {
    // 상하좌우4, 말의이동8
    static int[] dr = {0, 1, 0, -1, 2, 2, 1, 1, -2, -2, -1, -1};
    static int[] dc = {1, 0, -1, 0, 1, -1, 2, -2, 1, -1, 2, -2};

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk;

        // input
        int K = Integer.parseInt(br.readLine());
        stk = new StringTokenizer(br.readLine());
        int W = Integer.parseInt(stk.nextToken());
        int H = Integer.parseInt(stk.nextToken());
        int[][] map = new int[H][W];
        for (int i = 0; i < H; i++) {
            stk = new StringTokenizer(br.readLine());
            for (int j = 0; j < W; j++) {
                map[i][j] = Integer.parseInt(stk.nextToken());
            }
        }

        // BFS
        boolean[][][] visited = new boolean[H][W][K + 1];
//		int[][][] dp = new boolean[H][W][K + 1];

        int min = Integer.MAX_VALUE;
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(0);
        queue.offer(0);
        queue.offer(0);
        queue.offer(0);
        while (!queue.isEmpty()) {
            int r = queue.poll();            // 행 좌표
            int c = queue.poll();            // 열 좌표
            int k = queue.poll();            // 말이동 횟수
            int distance = queue.poll();    // 움직인 거리

            if (r == H - 1 && c == W - 1) {
                if (min > distance)
                    min = distance;
                continue;
            }

            for (int d = 0; d < 12; d++) {
                int nr = r + dr[d];
                int nc = c + dc[d];

                if (nr < 0 || nr >= H || nc < 0 || nc >= W)
                    continue;

                if (map[nr][nc] == 1)
                    continue;

                if (d < 4) { // 상하좌우
                    if (!visited[nr][nc][k]) {
                        visited[nr][nc][k] = true;

                        queue.offer(nr);
                        queue.offer(nc);
                        queue.offer(k);
                        queue.offer(distance + 1);
                    }
                } else { // 말이동
                    if (k < K && !visited[nr][nc][k + 1]) {
                        visited[nr][nc][k + 1] = true;

                        queue.offer(nr);
                        queue.offer(nc);
                        queue.offer(k + 1);
                        queue.offer(distance + 1);
                    }
                }
            }
        }

        if (min == Integer.MAX_VALUE)
            System.out.println(-1);
        else
            System.out.println(min);

    }
}
