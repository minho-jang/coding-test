package programmers;

import java.util.LinkedList;
import java.util.Queue;

class Solution_PRG_2020KakaoIntern_경주로건설 {
    static final int INF = 987654321;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    // 					상    하      좌    우
    static int N;

    static class Pos {
        int r, c, d;

        Pos(int r, int c, int d) {
            this.r = r;
            this.c = c;
            this.d = d;
        }
    }

    public int solution(int[][] board) {
        int answer = INF;

        N = board.length;
        int[][][] dp = new int[N][N][4]; // 각 위치에서, 각 방향마다, 비용이 다르다
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                for (int d = 0; d < 4; d++) {
                    dp[i][j][d] = INF; // 최소값으로 업데이트하기 위해 최대값으로 초기화
                }
            }
        }

        // BFS
        Queue<Pos> queue = new LinkedList<>();

        // 처음에 출발할 때 (0,0)은 0으로 초기화
        for (int d = 0; d < 4; d++) {
            dp[0][0][d] = 0;
        }
        // 출발할 때 방향이 항상 오른쪽 또는 아래쪽이므로,
        // queue에 해당 위치에 대해서 미리 계산하여 넣어준다.
        // (아래 로직에 의해 출발위치에서부터 BFS를 돌릴 수 없다. 출발위치에서의 dp가 정해지지 않기 때문)
        if (board[1][0] == 0) {
            dp[1][0][1] = 100;
            queue.add(new Pos(1, 0, 1));
        }
        if (board[0][1] == 0) {
            dp[0][1][3] = 100;
            queue.add(new Pos(0, 1, 3));
        }

        // dp[a][b][d] = (a,b) 위치로 d 방향으로 왔을 때 최소 비용

        // 1. (r,c)에서 사방 탐색을 하는데
        // 2. 방향 d에 대하여 선택되는 다음 위치 (nr,nc)라고 할 때,
        // 3. dp[nr][nc][d] = (r,c)에서 (nr,nc)로 갈 때,
        // 			직선이면 dp[r][c][d2]+100, 커브이면 dp[r][c][d2]+600

        while (!queue.isEmpty()) {
            Pos polled = queue.poll();

            int r = polled.r;
            int c = polled.c;
            if (r == N - 1 && c == N - 1) {
                continue;
            }

            for (int d = 0; d < 4; d++) { // 1. (r,c)에서 사방 탐색을 하는데
                int nr = r + dr[d];
                int nc = c + dc[d]; // 2. 방향 d에 대하여 선택되는 다음 위치 (nr,nc)라고 할 때,

                if (d == cross(polled.d))
                    continue;
                if (isOut(nr, nc))
                    continue;
                if (board[nr][nc] == 1)
                    continue;

                // 3. dp[nr][nc][d]
                // 3-1. (r,c)에서 (nr,nc)로 갈 때, 직선이면 dp[r][c][d2]+100, 커브이면 dp[r][c][d2]+600
                // 3-2. 3-1과 기존 dp[nr][nc][d] 값 중 최소값으로 업데이트.
                // 3-3. 업데이트 된다면, 이어서 탐색을 더한다.

                int min = INF;
                for (int d2 = 0; d2 < 4; d2++) {
                    if (d2 == cross(d))
                        continue;
                    // 3-1. (r,c)에서 (nr,nc)로 갈 때, 직선이면 dp[r][c][d2]+100, 커브이면 dp[r][c][d2]+600
                    min = Math.min(min, dp[r][c][d2] + cost(d, d2));
                }

                if (dp[nr][nc][d] > min) {
                    dp[nr][nc][d] = min;            // 3-2. 3-1과 기존 dp[nr][nc][d] 값 중 최소값으로 업데이트.
                    queue.add(new Pos(nr, nc, d));    // 3-3. 업데이트 된다면, 이어서 탐색을 더한다.
                }
            }
        }

        for (int d = 0; d < 4; d++)
            answer = Math.min(answer, dp[N - 1][N - 1][d]);
        return answer;
    }

    // 반대편이 어디인가. (상<>하, 좌<>우)
    private int cross(int d) {
        if (d == 0)
            return 1;
        else if (d == 1)
            return 0;
        else if (d == 2)
            return 3;
        else
            return 2;
    }

    // 범위를 벗어나는가
    boolean isOut(int r, int c) {
        return r < 0 || r >= N || c < 0 || c >= N;
    }

    // 직선이면 100, 커브이면 600(직선+커브)
    int cost(int d1, int d2) {
        if (d1 == 0 || d1 == 1) {
            if (d2 == 2 || d2 == 3)
                return 600;
            else
                return 100;
        } else {
            if (d2 == 0 || d2 == 1)
                return 600;
            else
                return 100;
        }
    }

    public static void main(String[] args) {
        Solution_PRG_2020KakaoIntern_경주로건설 s = new Solution_PRG_2020KakaoIntern_경주로건설();
//		int[][] board = {
//				{0,0,0},
//				{0,0,0},
//				{0,0,0}
//				};
        int[][] board = {
                {0, 0, 0, 0, 0, 0},
                {0, 1, 1, 1, 1, 0},
                {0, 0, 1, 0, 0, 0},
                {1, 0, 0, 1, 0, 1},
                {0, 1, 0, 0, 0, 1},
                {0, 0, 0, 0, 0, 0},};
        System.out.println(s.solution(board));
    }
}