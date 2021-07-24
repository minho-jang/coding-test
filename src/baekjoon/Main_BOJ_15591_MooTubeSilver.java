package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_BOJ_15591_MooTubeSilver {
    static class Node {
        int num, weight;

        Node(int num, int weight) {
            this.num = num;
            this.weight = weight;
        }
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("input.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk;

        stk = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(stk.nextToken());
        int Q = Integer.parseInt(stk.nextToken());

        ArrayList<Node>[] adjList = new ArrayList[N];
        for (int i = 0; i < N; i++)
            adjList[i] = new ArrayList<>();

        for (int i = 0; i < N - 1; i++) {
            stk = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(stk.nextToken()) - 1;
            int to = Integer.parseInt(stk.nextToken()) - 1;
            int weight = Integer.parseInt(stk.nextToken());
            adjList[from].add(new Node(to, weight));
            adjList[to].add(new Node(from, weight));
        }

        int[][] result = new int[N][N];

        // 각 점에서 BFS
        for (int i = 0; i < N; i++) {
            int start = i;

            boolean[] visited = new boolean[N];
            Queue<Integer> queue = new LinkedList<>();

            visited[start] = true;
            queue.add(start);
            queue.add(Integer.MAX_VALUE);

            while (!queue.isEmpty()) {
                int now = queue.poll();
                int min = queue.poll();

                for (Node next : adjList[now]) {
                    if (visited[next.num]) continue;
                    visited[next.num] = true;

                    int m = Math.min(next.weight, min);
                    result[start][next.num] = m;
                    queue.add(next.num);
                    queue.add(m);
                }
            }
        }

        StringBuilder answerSb = new StringBuilder();
        for (int i = 0; i < Q; i++) {
            stk = new StringTokenizer(br.readLine());
            int k = Integer.parseInt(stk.nextToken());
            int v = Integer.parseInt(stk.nextToken()) - 1;

            int count = 0;
            for (int j = 0; j < N; j++) {
                if (j != v && result[v][j] >= k)
                    count++;
            }
            answerSb.append(count).append("\n");
        }

        System.out.print(answerSb.toString());
    }
}
