package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_BOJ_11399_ATM {
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk;

        int N = Integer.parseInt(br.readLine());
        int[] timeArr = new int[N];
        stk = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            timeArr[i] = Integer.parseInt(stk.nextToken());
        }

        Arrays.sort(timeArr);

        int answer = 0;
        for (int i = 1; i <= N; i++) {
            answer += timeArr[i - 1] * (N - (i - 1));
        }

        System.out.println(answer);
    }
}
