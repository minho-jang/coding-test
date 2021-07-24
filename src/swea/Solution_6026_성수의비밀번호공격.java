package swea;

import java.util.Scanner;

public class Solution_6026_성수의비밀번호공격 {
    static final int MOD = 1000000007;
    static long[] facto;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        facto = new long[101];
        facto[0] = 1;
        for (int i = 1; i < 101; i++) {
            facto[i] = (facto[i - 1] * i) % MOD;
        }

        int T = sc.nextInt();

        for (int tc = 1; tc <= T; tc++) {

            int N = sc.nextInt();
            int M = sc.nextInt();

            long result = F(M, N);
            if (result < 0)
                result += MOD;

            System.out.println("#" + tc + " " + result);
        }

        sc.close();
    }

    private static long F(int m, int n) {
        long tot = 0L;

        for (int i = 0; i < n; i++) {
            tot = (tot + ((i % 2 == 0 ? 1L : -1L) * pow(n - i, m) * comb(n, i) % MOD) % MOD) % MOD;
        }

        return tot % MOD;
    }

    private static long comb(int n, int r) {
        return ((facto[n] * pow(facto[r], MOD - 2) % MOD) * pow(facto[n - r], MOD - 2)) % MOD;
    }

    private static long pow(long x, long y) {
        long result = 1L;

        while (y > 0) {
            if (y % 2 == 1)
                result = (result * x) % MOD;
            y = y >> 1;
            x = (x * x) % MOD;
        }

        return result % MOD;
    }
}
