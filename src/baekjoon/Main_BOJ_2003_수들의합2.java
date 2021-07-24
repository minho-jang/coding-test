package baekjoon;

import java.util.Scanner;

public class Main_BOJ_2003_수들의합2 {
    static int[] arr;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int M = sc.nextInt();
        arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = sc.nextInt();
        }

        int answer = 0;
        int start = 0;
        int end = 0;
        int sum = arr[0];

        while (true) {
            if (sum == M)
                answer++;

            if (sum <= M) {
                if (end == N - 1)
                    break;
                sum += arr[++end];

            } else if (sum > M) {
                sum -= arr[start++];
            }
        }

        System.out.println(answer);
        sc.close();
    }
}
