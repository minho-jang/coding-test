package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_BOJ_18513_샘터 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk;

        stk = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(stk.nextToken());
        int K = Integer.parseInt(stk.nextToken());

        stk = new StringTokenizer(br.readLine());
        HashSet<Integer> visitedSet = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < N; i++) {
            int input = Integer.parseInt(stk.nextToken());
            visitedSet.add(input);
            queue.add(input);
        }

        // BFS
        long answer = 0;
        int cnt = 0;
        int level = 0;
        while (!queue.isEmpty()) {
            boolean finish = false;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                if (++cnt > K + N) {
                    finish = true;
                    break;
                }
                answer += level;

                int polled = queue.poll();
                if (visitedSet.add(polled - 1)) {
                    queue.offer(polled - 1);
                }
                if (visitedSet.add(polled + 1)) {
                    queue.offer(polled + 1);
                }
            }
            if (finish)
                break;
            level++;
        }

        System.out.println(answer);
    }
}
