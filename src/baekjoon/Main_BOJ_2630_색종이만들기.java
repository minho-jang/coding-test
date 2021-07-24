package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BOJ_2630_색종이만들기 {
    static int[][] map;
    static int countOne, countZero;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("input.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk;

        int N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            stk = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(stk.nextToken());
            }
        }

        divideAndConquer(0, 0, N);

        System.out.println(countZero);
        System.out.println(countOne);
    }

    private static void divideAndConquer(int r, int c, int n) {
//    System.out.println(r +" "+ c +" "+ n +" "+ depth);
        boolean hasOne = true;
        for (int i = r; i < r + n; i++) {
            for (int j = c; j < c + n; j++) {
                if (map[i][j] != map[r][c]) {
                    hasOne = false;
                    break;
                }
            }
        }

        if (!hasOne && n != 1) {
            divideAndConquer(r, c, n / 2);
            divideAndConquer(r + n / 2, c, n / 2);
            divideAndConquer(r, c + n / 2, n / 2);
            divideAndConquer(r + n / 2, c + n / 2, n / 2);

        } else {
            count(map[r][c]);

        }
    }

    private static void count(int num) {
        if (num == 1)
            countOne++;
        else
            countZero++;
    }
}
