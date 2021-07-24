package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_BOJ_5427_불 {
    private static int[] dr = {1, 0, -1, 0};
    private static int[] dc = {0, 1, 0, -1};

    static class Pos {
        int r, c;

        public Pos(int r, int c) {
            super();
            this.r = r;
            this.c = c;
        }
    }

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk;
        StringBuilder answerSb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {

            stk = new StringTokenizer(br.readLine());
            int M = Integer.parseInt(stk.nextToken());
            int N = Integer.parseInt(stk.nextToken());

            boolean[][] visited = new boolean[N][M];
            ArrayList<Pos> fire = new ArrayList<>();
            Pos me = null;
            char[][] map = new char[N][M];
            for (int j = 0; j < N; j++) {
                map[j] = br.readLine().toCharArray();
                for (int j2 = 0; j2 < M; j2++) {
                    if (map[j][j2] == '@') {
                        me = new Pos(j, j2);
                    } else if (map[j][j2] == '*') {
                        fire.add(new Pos(j, j2));
                    } else if (map[j][j2] == '#') {
                        visited[j][j2] = true;
                    }
                }
            }

            // BFS. visited는 이미 위에서 선언해서 벽('#')을 true 처리함.
            Queue<Pos> queueMe = new LinkedList<>();
            Queue<Pos> queueFire = new LinkedList<>();

            visited[me.r][me.c] = true;
            queueMe.offer(me);

            for (Pos p : fire) {
                visited[p.r][p.c] = true;
                queueFire.offer(p);
            }

            boolean escape = false;
            int time = 0;
            while (!queueMe.isEmpty()) {

                int queueFireSize = queueFire.size();
                for (int j = 0; j < queueFireSize; j++) {
                    Pos polled = queueFire.poll();
                    for (int d = 0; d < 4; d++) {
                        int nr = polled.r + dr[d];
                        int nc = polled.c + dc[d];

                        if (nr < 0 || nr >= N || nc < 0 || nc >= M)
                            continue;
                        if (visited[nr][nc])
                            continue;

                        visited[nr][nc] = true;
                        queueFire.offer(new Pos(nr, nc));
                    }
                }

                int queueMeSize = queueMe.size();
                for (int j = 0; j < queueMeSize; j++) {
                    Pos polled = queueMe.poll();

                    for (int d = 0; d < 4; d++) {
                        int nr = polled.r + dr[d];
                        int nc = polled.c + dc[d];

                        if (nr < 0 || nr >= N || nc < 0 || nc >= M) {
                            escape = true;
                            break;
                        }
                        if (visited[nr][nc])
                            continue;

                        visited[nr][nc] = true;
                        queueMe.offer(new Pos(nr, nc));
                    }

                    if (escape) break;
                }
                if (escape) break;

//				for (int j = 0; j < N; j++) {
//					System.out.println(Arrays.toString(visited[j]));
//				}
//				System.out.println();
                time++;
            }

            if (escape) {
                answerSb.append(time + 1).append("\n");
            } else {
                answerSb.append("IMPOSSIBLE").append("\n");
            }

        }

        System.out.println(answerSb.toString());
    }
}

/*

1
7 4
###.###
#....*#
#@....#
.######

*/