package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_BOJ_14502_연구실 {
    static int[] dr = {0, 1, 0, -1};
    static int[] dc = {1, 0, -1, 0};

    static List<Pos> empty, virus;
    static int[][] map;
    static int N, M, answer;

    static class Pos {
        int r, c;

        public Pos(int r, int c) {
            this.r = r;
            this.c = c;
        }

        @Override
        public String toString() {
            return "[" + r + ", " + c + "]";
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk;

        stk = new StringTokenizer(br.readLine());
        N = Integer.parseInt(stk.nextToken());
        M = Integer.parseInt(stk.nextToken());

        virus = new ArrayList<>();
        empty = new ArrayList<>();
        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            stk = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(stk.nextToken());
                if (map[i][j] == 0)
                    empty.add(new Pos(i, j));
                else if (map[i][j] == 2)
                    virus.add(new Pos(i, j));
            }
        }

        comb(0, 0, new Pos[3], empty.size());

        System.out.println(answer);
    }

    private static void comb(int cnt, int start, Pos[] res, int emptySize) {
        if (cnt == 3) {
            // 조합 완성

            // map 복사
            int[][] tmpMap = new int[N][M];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    tmpMap[i][j] = map[i][j];
                }
            }

            // 벽 세우기
            for (int i = 0; i < 3; i++) {
                tmpMap[res[i].r][res[i].c] = 1;
            }

            // 바이러스 퍼뜨리기 (BFS)
            Queue<Pos> queue = new LinkedList<>();
            // 초기 바이러스 위치 삽입
            for (Pos p : virus)
                queue.offer(p);

            while (!queue.isEmpty()) {
                Pos pos = queue.poll();
                for (int d = 0; d < 4; d++) {
                    int nr = pos.r + dr[d];
                    int nc = pos.c + dc[d];

                    if (nr < 0 || nr >= N || nc < 0 || nc >= M)
                        continue;
                    if (tmpMap[nr][nc] == 0) {
                        tmpMap[nr][nc] = 2;
                        queue.offer(new Pos(nr, nc));
                    }
                }
            }

            // 빈칸 개수 세기
            int emptySum = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (tmpMap[i][j] == 0)
                        emptySum++;
                }
            }

            if (answer < emptySum)
                answer = emptySum;

            return;
        }

        for (int i = start; i < emptySize; i++) {
            res[cnt] = empty.get(i);
            comb(cnt + 1, i + 1, res, emptySize);
        }
    }
}
