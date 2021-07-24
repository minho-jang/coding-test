package swea;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_5644_무선충전 {
    static int[] dc = {0, -1, 0, 1, 0};
    static int[] dr = {0, 0, 1, 0, -1};

    //				       무     상   우    하    좌
    public static void main(String[] args) throws NumberFormatException, IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk;
        StringBuilder answerSb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {

            stk = new StringTokenizer(br.readLine());
            int M = Integer.parseInt(stk.nextToken());
            int BC = Integer.parseInt(stk.nextToken());

            stk = new StringTokenizer(br.readLine());
            int[] moveA = new int[M + 1];
            moveA[0] = 0;
            for (int i = 1; i <= M; i++) {
                moveA[i] = Integer.parseInt(stk.nextToken());
            }

            stk = new StringTokenizer(br.readLine());
            int[] moveB = new int[M + 1];
            moveB[0] = 0;
            for (int i = 1; i <= M; i++) {
                moveB[i] = Integer.parseInt(stk.nextToken());
            }

            int[][] bcInfo = new int[BC][4];
            for (int i = 0; i < BC; i++) {
                stk = new StringTokenizer(br.readLine());
                bcInfo[i][0] = Integer.parseInt(stk.nextToken()) - 1;
                bcInfo[i][1] = Integer.parseInt(stk.nextToken()) - 1;
                bcInfo[i][2] = Integer.parseInt(stk.nextToken());
                bcInfo[i][3] = Integer.parseInt(stk.nextToken());
            }

            int sum = 0;
            int[] posA = {0, 0};
            int[] posB = {9, 9};
            for (int time = 0; time <= M; time++) {
                posA[0] += dr[moveA[time]];
                posA[1] += dc[moveA[time]];
                posB[0] += dr[moveB[time]];
                posB[1] += dc[moveB[time]];

                int chargeMax = 0;
                for (int i = 0; i < BC; i++) {
                    int chargeA = getCharge(posA, bcInfo[i]);

                    for (int j = 0; j < BC; j++) {
                        int chargeB = getCharge(posB, bcInfo[j]);

                        if (i == j && chargeA != 0 && chargeB != 0) {
                            continue;
                        }

                        if (chargeMax < chargeA + chargeB)
                            chargeMax = chargeA + chargeB;
                    }
                }

                sum += chargeMax;
            }

            answerSb.append("#").append(tc).append(" ").append(sum).append("\n");
        }

        System.out.println(answerSb.toString());
    }

    private static int getCharge(int[] pos, int[] bc) {
        int distance = Math.abs(pos[0] - bc[0]) + Math.abs(pos[1] - bc[1]);
        if (distance <= bc[2])
            return bc[3];
        else
            return 0;
    }
}
