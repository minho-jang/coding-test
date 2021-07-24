package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BOJ_14500_테트로미노 {
    static int N, M, answer;
    static int[][] map;
    static boolean[][] visited;

    // ㅗ에서 꼬다리를 기준으로 4칸의 상대적 좌표
    static int[][][] OH = {
            {{0, 0}, {1, -1}, {1, 0}, {1, 1}},   // ㅗ
            {{0, 0}, {-1, -1}, {0, -1}, {1, -1}},   // ㅏ
            {{0, 0}, {-1, 1}, {0, 1}, {1, 1}},   // ㅓ
            {{0, 0}, {-1, -1}, {-1, 0}, {-1, 1}},   // ㅜ
    };
    // 4방탐색 좌표
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk;

        stk = new StringTokenizer(br.readLine());
        N = Integer.parseInt(stk.nextToken());
        M = Integer.parseInt(stk.nextToken());

        map = new int[N][M];
        visited = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            stk = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(stk.nextToken());
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                // 각 좌표별로, 4칸만 DFS (ㅗ를 제외한 나머지 확인 가능)
                visited[i][j] = true;
                dfs(i, j, 0, 0);
                visited[i][j] = false;

                // ㅗ 확인
                checkOh(i, j);
            }
        }

        System.out.println(answer);
    }

    private static void checkOh(int r, int c) {
        for (int i = 0; i < 4; i++) {
            int[][] oh = OH[i];             // ㅗ, ㅓ, ㅏ, ㅜ 중 하나
            int result = 0;

            for (int j = 0; j < 4; j++) {   // (r,c)를 기준으로 ㅗ를 만드는데
                int nr = r + oh[j][0];
                int nc = c + oh[j][1];
                if (isOut(nr, nc)) break;    // 범위를 벗어나면 빠져나가

                result += map[nr][nc];        // 해당 모양의 점수 확인
            }

            answer = Math.max(result, answer);
        }
    }

    private static boolean isOut(int r, int c) {
        return r < 0 || r >= N || c < 0 || c >= M;
    }

    private static void dfs(int r, int c, int depth, int score) {
        if (depth == 4) {
            answer = Math.max(answer, score);
            return;
        }

        // DFS 하면서 점수 누적
        score += map[r][c];

        for (int d = 0; d < 4; d++) {
            int nr = r + dr[d];
            int nc = c + dc[d];
            if (isOut(nr, nc)) continue;
            if (visited[nr][nc]) continue;

            visited[nr][nc] = true;
            dfs(nr, nc, depth + 1, score);
            visited[nr][nc] = false;
        }
    }
}
