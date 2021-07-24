package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main_BOJ_15663_N과M9 {
    private static StringBuilder answer;
    private static int[] arr;
    private static int N, M;
    private static boolean[] visited;
    private static HashSet<String> set;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk;
        answer = new StringBuilder();
        set = new HashSet<>();

        stk = new StringTokenizer(br.readLine());
        N = Integer.parseInt(stk.nextToken());
        M = Integer.parseInt(stk.nextToken());

        visited = new boolean[N];
        stk = new StringTokenizer(br.readLine());
        arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(stk.nextToken());
        }

        Arrays.sort(arr);

        perm(0, new int[M], new String());

        System.out.println(answer.toString());
    }

    private static void perm(int cnt, int[] res, String str) {
        if (cnt == M) {
            if (!set.contains(str)) {
                for (int i = 0; i < M; i++) {
                    answer.append(res[i]).append(" ");
                }
                answer.append("\n");
                set.add(str);
            }

            return;
        }

        for (int i = 0; i < N; i++) {
            if (!visited[i]) {
                visited[i] = true;
                res[cnt] = arr[i];
                perm(cnt + 1, res, str.concat(arr[i] + " "));
                visited[i] = false;
            }
        }
    }
}
