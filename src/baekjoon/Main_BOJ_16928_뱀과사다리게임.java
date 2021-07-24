package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_BOJ_16928_뱀과사다리게임 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk;

        stk = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(stk.nextToken());
        int M = Integer.parseInt(stk.nextToken());
        int[] ladders = new int[101];
        int[] snakes = new int[101];
        for (int i = 0; i < N; i++) {
            stk = new StringTokenizer(br.readLine());
            ladders[Integer.parseInt(stk.nextToken())] = Integer.parseInt(stk.nextToken());
        }
        for (int i = 0; i < M; i++) {
            stk = new StringTokenizer(br.readLine());
            snakes[Integer.parseInt(stk.nextToken())] = Integer.parseInt(stk.nextToken());
        }

        boolean[] visited = new boolean[101];
        Queue<Integer> queue = new LinkedList<>();

        queue.add(1);
        visited[1] = true;

        int cnt = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int polled = queue.remove();
                if (polled == 100) {
                    System.out.println(cnt);
                    return;
                }

                for (int j = 1; j <= 6; j++) {
                    if (polled + j > 100 || visited[polled + j])
                        continue;

                    visited[polled + j] = true;

                    if (ladders[polled + j] > 0) {
                        visited[ladders[polled + j]] = true;
                        queue.add(ladders[polled + j]);

                    } else if (snakes[polled + j] > 0) {
                        visited[snakes[polled + j]] = true;
                        queue.add(snakes[polled + j]);

                    } else {
                        queue.add(polled + j);
                    }
                }
            }
            cnt++;
        }
    }
}
