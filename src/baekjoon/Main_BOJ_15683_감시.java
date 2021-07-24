package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main_BOJ_15683_감시 {
    static class Cctv {
        int r, c, type;

        public Cctv(int r, int c, int type) {
            this.r = r;
            this.c = c;
            this.type = type;
        }
    }

    static ArrayList<Cctv> cctvList;
    static int min;
    static int[][] cctvDir = {
            {},                    // 0
            {0, 1, 2, 3},        // 1
            {4, 5},                // 2
            {6, 7, 8, 9},        // 3
            {10, 11, 12, 13},    // 4
            {14},                // 5
    };
    static int[][] dirType = {
            {0},
            {1},
            {2},
            {3},
            {0, 1},
            {2, 3},
            {0, 2},
            {0, 3},
            {1, 2},
            {1, 3},
            {0, 2, 3},
            {0, 1, 2},
            {1, 2, 3},
            {0, 1, 3},
            {0, 1, 2, 3}
    };
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static int[][] map;
    static int N, M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk;

        stk = new StringTokenizer(br.readLine());
        N = Integer.parseInt(stk.nextToken());
        M = Integer.parseInt(stk.nextToken());

        cctvList = new ArrayList<>();
        min = 987654321;

        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            stk = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(stk.nextToken());
                if (map[i][j] > 0 && map[i][j] < 6) {
                    cctvList.add(new Cctv(i, j, map[i][j]));
                }
            }
        }

        int cctvCount = cctvList.size();
        dfs(0, new int[cctvCount]);

        System.out.println(min);
    }

    private static void dfs(int cnt, int[] res) {
        if (cnt == cctvList.size()) {
//			System.out.println(Arrays.toString(res));
            int blind = cctvOn(res);
            min = Math.min(min, blind);
            return;
        }

        Cctv cctv = cctvList.get(cnt);
        for (int d : cctvDir[cctv.type]) {
            res[cnt] = d;
            dfs(cnt + 1, res);
        }
    }

    private static int cctvOn(int[] res) {
        int[][] tmpMap = new int[N][M];
        // map 복사
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                tmpMap[i][j] = map[i][j];
            }
        }

        int size = cctvList.size();
        for (int i = 0; i < size; i++) {
            Cctv cctv = cctvList.get(i);
            int[] dirs = dirType[res[i]];
            for (int d : dirs) {
                int r = cctv.r;
                int c = cctv.c;

                while (true) {
                    if (r < 0 || r >= N || c < 0 || c >= M)
                        break;
                    if (tmpMap[r][c] == 6)
                        break;

                    if (tmpMap[r][c] == 0)
                        tmpMap[r][c] = 7;

                    r += dr[d];
                    c += dc[d];
                }

            }
        }

        int result = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (tmpMap[i][j] == 0)
                    result++;
            }
        }

        return result;
    }
}
