package baekjoon;

import java.util.Scanner;

public class Main_BOJ_2846_오르막길 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = sc.nextInt();
        }

        int answer = 0;
        int diff = 0;
        int start = arr[0];
        for (int i = 1; i < N; i++) {
            if (arr[i] > arr[i - 1]) {
                diff = arr[i] - start;
            } else {
                answer = Math.max(answer, diff);
                start = arr[i];
            }
        }
        answer = Math.max(answer, diff);

        System.out.println(answer);
    }
}
