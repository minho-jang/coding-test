package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_BOJ_9019_DSLR {
    final static int MAX = 10000;

    static class Node {
        int num;
        String commands;

        Node(int num, String commands) {
            this.num = num;
            this.commands = commands;
        }
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("input.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk;
        StringBuilder answerSb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < T; tc++) {
            stk = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(stk.nextToken());
            int end = Integer.parseInt(stk.nextToken());

            boolean[] visited = new boolean[MAX];
            Queue<Node> queue = new LinkedList<>();
            visited[start] = true;
            queue.add(new Node(start, ""));

            String answer = "";
            while (!queue.isEmpty()) {
                Node polled = queue.poll();
                if (polled.num == end) {
                    answer = polled.commands;
                    break;
                }

                int result = 0;
                result = D(polled.num);
                if (!visited[result]) {
                    visited[result] = true;
                    queue.add(new Node(result, polled.commands + "D"));
                }
                result = S(polled.num);
                if (!visited[result]) {
                    visited[result] = true;
                    queue.add(new Node(result, polled.commands + "S"));
                }
                result = L(polled.num);
                if (!visited[result]) {
                    visited[result] = true;
                    queue.add(new Node(result, polled.commands + "L"));
                }
                result = R(polled.num);
                if (!visited[result]) {
                    visited[result] = true;
                    queue.add(new Node(result, polled.commands + "R"));
                }
            }

            answerSb.append(answer).append("\n");
        }

        System.out.print(answerSb.toString());
    }

    private static int R(int num) {
        return num / 10 + (num % 10) * 1000;
    }

    private static int L(int num) {
        return num / 1000 + (num % 1000) * 10;
    }

    private static int S(int num) {
        return num == 0 ? 9999 : num - 1;
    }

    private static int D(int num) {
        return (num * 2) % MAX;
    }
}
