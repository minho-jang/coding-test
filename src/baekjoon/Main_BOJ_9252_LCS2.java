package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class Main_BOJ_9252_LCS2 {
    static char[] A, B;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("input.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        A = br.readLine().toCharArray();
        B = br.readLine().toCharArray();

        int lenA = A.length;
        int lenB = B.length;
        int[][] dp = new int[lenA + 1][lenB + 1];
        for (int i = 0; i < lenA + 1; i++) {
            for (int j = 0; j < lenB + 1; j++) {
                if (i == 0 || j == 0)
                    dp[i][j] = 0;

                else if (A[i - 1] == B[j - 1])
                    dp[i][j] = dp[i - 1][j - 1] + 1;

                else
                    dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
            }
        }

        StringBuilder lcs = new StringBuilder();

        int r = lenA;
        int c = lenB;
        while (r != 0 && c != 0) {
            int now = dp[r][c];
            if (dp[r - 1][c] == now)
                r = r - 1;

            else if (dp[r][c - 1] == now)
                c = c - 1;

            else if (dp[r - 1][c - 1] == now - 1) {
                lcs.append(A[r - 1]);
                r = r - 1;
                c = c - 1;
            }
        }

        System.out.print(dp[lenA][lenB] + "\n" + lcs.reverse().toString());
    }
}
