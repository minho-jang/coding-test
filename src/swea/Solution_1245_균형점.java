package swea;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Solution_1245_균형점 {
    static final double MIN_DIFF = 1e-12;
    static double[] answer;
    static int N;
    static int[][] posAndWeight;

    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();
        for (int tc = 1; tc <= T; tc++) {

            N = sc.nextInt();
            answer = new double[N - 1];
            posAndWeight = new int[N][2];

            for (int i = 0; i < N; i++)
                posAndWeight[i][0] = sc.nextInt();
            for (int i = 0; i < N; i++)
                posAndWeight[i][1] = sc.nextInt();

            Arrays.sort(posAndWeight, (o1, o2) -> {
                return Integer.compare(o1[0], o2[0]);
            });

            for (int i = 0; i < N - 1; i++)
                binarySearch(i, (double) posAndWeight[i][0], (double) posAndWeight[i + 1][0]);

            System.out.print("#" + tc + " ");
            for (int i = 0; i < N - 1; i++)
                System.out.print(String.format("%.10f ", answer[i]));
            System.out.println();
        }

        sc.close();
    }

    private static void binarySearch(int idx, double start, double end) {
        if (Math.abs(end - start) < MIN_DIFF) {
            answer[idx] = start;
            return;
        }

        double mid = (start + end) / (double) 2;

        double leftPower = 0.0;
        for (int i = 0; posAndWeight[i][0] < mid; i++)
            leftPower += F(posAndWeight[i][1], mid - posAndWeight[i][0]);

        double rightPower = 0.0;
        for (int i = N - 1; posAndWeight[i][0] > mid; i--)
            rightPower += F(posAndWeight[i][1], posAndWeight[i][0] - mid);

        if (leftPower > rightPower)    // 왼쪽이 세면, 왼쪽에서 멀어져야 -> 오른쪽 탐색
            binarySearch(idx, mid, end);
        else if (leftPower < rightPower)
            binarySearch(idx, start, mid);
        else
            answer[idx] = mid;
    }

    static double F(double m1, double d) {
        return m1 / (d * d);
    }
}
