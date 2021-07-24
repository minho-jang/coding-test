package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main_BOJ_14225_부분수열의합 {
    static HashSet<Integer> set;
    static int N, max;
    static int[] S;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk;

        N = Integer.parseInt(br.readLine());
        S = new int[N];
        stk = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            S[i] = Integer.parseInt(stk.nextToken());
        }

        set = new HashSet<>();
        max = 0;
        subset(0, new boolean[N]);

        int answer = 0;
        for (int i = 1; i <= max + 1; i++) {
            if (!set.contains(i)) {
                answer = i;
                break;
            }
        }

        System.out.println(answer);
    }

    static void subset(int cnt, boolean[] visited) {
        if (cnt == N) {
            int sum = 0;
            for (int i = 0; i < N; i++) {
                if (visited[i]) {
                    sum += S[i];
                }
            }
            if (max < sum) max = sum;
            if (set.add(sum)) {
//        System.out.println(sum);
            }
            return;
        }

        visited[cnt] = true;
        subset(cnt + 1, visited);
        visited[cnt] = false;
        subset(cnt + 1, visited);
    }
}
