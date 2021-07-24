package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BOJ_1182_부분수열의합 {
    static int[] arr;
    static int N, S, answer;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk;

        stk = new StringTokenizer(br.readLine());
        N = Integer.parseInt(stk.nextToken());
        S = Integer.parseInt(stk.nextToken());

        stk = new StringTokenizer(br.readLine());
        arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(stk.nextToken());
        }

        subset(0, new boolean[N]);
        System.out.println(answer);
    }

    static void subset(int cnt, boolean[] visited) {
        if (cnt == N) {
            int sum = 0;
            boolean isAllFalse = true;
            for (int i = 0; i < N; i++) {
                if (visited[i]) {
                    isAllFalse = false;
                    sum += arr[i];
                }
            }
            if (!isAllFalse && sum == S) answer++;
            return;
        }

        visited[cnt] = true;
        subset(cnt + 1, visited);
        visited[cnt] = false;
        subset(cnt + 1, visited);
    }
}
