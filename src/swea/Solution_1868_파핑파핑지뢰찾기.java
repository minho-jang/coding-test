package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Solution_1868_파핑파핑지뢰찾기 {
    static int[] dr = {-1, 1, 0, 0, -1, 1, -1, 1};
    static int[] dc = {0, 0, -1, 1, 1, 1, -1, -1};
    static boolean[][] visited;
    static char[][] map;
    static int N, answer;
    static int[][] bombCount;

    static class Pos {
        int r, c;

        public Pos(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {

            answer = 0;
            N = Integer.parseInt(br.readLine());

            visited = new boolean[N][N];
            map = new char[N][N];
            for (int i = 0; i < N; i++) {
                map[i] = br.readLine().toCharArray();
                for (int j = 0; j < N; j++) {
                    if (map[i][j] == '*')
                        visited[i][j] = true;
                }
            }

            // 주변 지뢰 개수 count 배열
            bombCount = new int[N][N];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    int cnt = 0;
                    for (int d = 0; d < 8; d++) {
                        int nr = i + dr[d];
                        int nc = j + dc[d];
                        if (nr < 0 || nr >= N || nc < 0 || nc >= N) continue;
                        if (map[nr][nc] == '*') cnt++;
                    }
                    bombCount[i][j] = cnt;
                }
            }

            // 0인 곳 우선클릭
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (!visited[i][j] && bombCount[i][j] == 0) {
                        bfs(new Pos(i, j));
                        answer++;
                    }
                }
            }

            // 다른 부분 클릭
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (!visited[i][j]) {
                        visited[i][j] = true;
                        answer++;
                    }
                }
            }

            System.out.println("#" + tc + " " + answer);

        }
    }

    private static void bfs(Pos cur) {
        Queue<Pos> queue = new LinkedList<>();

        visited[cur.r][cur.c] = true;
        queue.add(cur);

        while (!queue.isEmpty()) {
            Pos p = queue.poll();
            for (int d = 0; d < 8; d++) {
                int nr = p.r + dr[d];
                int nc = p.c + dc[d];

                if (nr < 0 || nr >= N || nc < 0 || nc >= N) continue;
                if (visited[nr][nc]) continue;

                visited[nr][nc] = true;
                if (bombCount[nr][nc] == 0)
                    queue.offer(new Pos(nr, nc));
            }
        }
    }
}