package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Solution_2383_점심식사시간 {
    static class Pos {
        int i, j;

        public Pos(int i, int j) {
            super();
            this.i = i;
            this.j = j;
        }

        @Override
        public String toString() {
            return "Pos [i=" + i + ", j=" + j + "]";
        }
    }

    static class Stairs {
        Pos p;
        int num, k;

        public Stairs(int num, Pos p, int k) {
            super();
            this.num = num;
            this.p = p;
            this.k = k;
        }

        @Override
        public String toString() {
            return "Stairs [p=" + p + ", num=" + num + ", k=" + k + "]";
        }
    }

    private static int[][] distance;
    private static ArrayList<Stairs> stairs;
    private static int answer;

    public static void main(String[] args) throws NumberFormatException, IOException {
//		System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk;
        StringBuilder answerSb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            answer = 987654321;
            int N = Integer.parseInt(br.readLine());
            ArrayList<Pos> person = new ArrayList<>();
            stairs = new ArrayList<>();
            int stairsIdx = 0;
            for (int i = 0; i < N; i++) {
                stk = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    int input = Integer.parseInt(stk.nextToken());
                    if (input == 1) {
                        person.add(new Pos(i, j));
                    } else if (input > 1) {
                        stairs.add(new Stairs(stairsIdx++, new Pos(i, j), input));
                    }
                }
            }

            int personNum = person.size();
            int stairsNum = stairs.size();    // always 2
            distance = new int[personNum][stairsNum];
            for (int i = 0; i < stairsNum; i++) {
                Pos stairsPos = stairs.get(i).p;
                for (int j = 0; j < personNum; j++) {
                    distance[j][i] = getDistance(stairsPos, person.get(j));
                }
            }

            subset(0, new boolean[personNum]);

            answerSb.append("#").append(tc).append(" ")
                    .append(answer).append("\n");
        }

        System.out.println(answerSb.toString());
    }

    private static void subset(int cnt, boolean[] res) {
        if (cnt == res.length) {
            int M = res.length;
            boolean[] stairs1 = res;
            boolean[] stairs2 = new boolean[M];
            for (int i = 0; i < M; i++) {
                if (!stairs1[i])
                    stairs2[i] = true;
            }

            int time1 = moveToStairs(stairs1, stairs.get(0));
            int time2 = moveToStairs(stairs2, stairs.get(1));

            int max = Math.max(time1, time2);
            answer = Math.min(max, answer);
            return;
        }

        res[cnt] = false;
        subset(cnt + 1, res);
        res[cnt] = true;
        subset(cnt + 1, res);
    }

    private static int moveToStairs(boolean[] personSelect, Stairs stairs) {
        int time = 0;

        int DIST_MAX = 30;
        int[] dist = new int[DIST_MAX];
        for (int i = 0; i < personSelect.length; i++) {
            if (personSelect[i]) {
                dist[distance[i][stairs.num]]++;
            }
        }

        for (int i = 0; i < DIST_MAX - stairs.k + 1; i++) {
            int sum = 0;
            for (int j = 0; j < stairs.k; j++) {
                sum += dist[i + j];
            }
            if (sum > 3) {
                // 계단에 최대 3명만 들어갈 수 있다.
                int diff = sum - 3;
                dist[i + stairs.k - 1] -= diff;
                dist[i + stairs.k] += diff;
            }
        }

        for (int i = 0; i < DIST_MAX; i++) {
            if (dist[i] > 0)
                time = i;
        }

        return time + stairs.k + 1;
    }

    private static int getDistance(Pos a, Pos b) {
        return Math.abs(a.i - b.i) + Math.abs(a.j - b.j);
    }
}
/*

1
5
0 1 1 0 0
0 0 1 0 3
0 1 0 1 0
0 0 0 0 0
1 0 5 0 0

*/
