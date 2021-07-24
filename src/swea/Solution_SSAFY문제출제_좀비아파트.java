package swea;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution_SSAFY문제출제_좀비아파트 {
    static int[][] delta = {
            {1, 0, 0},
            {0, 1, 0},
            {0, 0, 1},
            {0, -1, 0},
            {0, 0, -1},
            {-1, 0, 0},
    };

    static class Pos {
        int h, r, c;

        Pos(int h, int r, int c) {
            this.h = h;
            this.r = r;
            this.c = c;
        }
    }

    static int H, N, M;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("input.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk;
        StringBuilder answerSb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            String answer = "";

            stk = new StringTokenizer(br.readLine());
            M = Integer.parseInt(stk.nextToken());
            N = Integer.parseInt(stk.nextToken());
            H = Integer.parseInt(stk.nextToken());

            // BFS
            boolean[][][] visited = new boolean[H][N][M];
            Queue<Pos> queue = new LinkedList<>();

            int[][][] apt = new int[H][N][M];
            int zombieCount = 0;
            for (int h = 0; h < H; h++) {
                for (int r = 0; r < N; r++) {
                    stk = new StringTokenizer(br.readLine());
                    for (int c = 0; c < M; c++) {
                        apt[h][r][c] = Integer.parseInt(stk.nextToken());
                        if (apt[h][r][c] == 1) {
                            visited[h][r][c] = true;
                            queue.add(new Pos(h, r, c));

                        } else if (apt[h][r][c] == -1) {
                            zombieCount++;
                        }
                    }
                }
            }

            if (zombieCount == 0) {
                answer = "ALL HUMANS";

            } else {
                int day = 0;
                while (!queue.isEmpty()) {
                    int size = queue.size();
                    for (int i = 0; i < size; i++) {
                        Pos p = queue.poll();
                        for (int d = 0; d < 6; d++) {
                            int nh = p.h + delta[d][0];
                            int nr = p.r + delta[d][1];
                            int nc = p.c + delta[d][2];

                            if (isOut(nh, nr, nc)) continue;
                            if (visited[nh][nr][nc]) continue;

                            if (apt[nh][nr][nc] == -1) {
                                apt[nh][nr][nc] = 1;
                                visited[nh][nr][nc] = true;
                                zombieCount--;
                                queue.add(new Pos(nh, nr, nc));
                            }
                        }
                    }
                    day++;
                }
                if (zombieCount > 0) {
                    answer = "STILL ZOMBIES";
                } else {
                    answer = Integer.toString(day - 1);
                }
            }

            answerSb.append("#").append(tc).append(" ").append(answer).append("\n");
        }

        System.out.print(answerSb.toString());
    }

    private static boolean isOut(int nh, int nr, int nc) {
        return nh < 0 || nh >= H || nr < 0 || nr >= N || nc < 0 || nc >= M;
    }
}
