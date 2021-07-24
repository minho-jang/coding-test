package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_BOJ_2887_행성터널 {
    static class Edge {
        int from, to, distance;

        Edge(int from, int to, int distance) {
            this.from = from;
            this.to = to;
            this.distance = distance;
        }
    }

    static class Node {
        int idx;
        int x, y, z;

        Node(int idx, int x, int y, int z) {
            this.idx = idx;
            this.x = x;
            this.y = y;
            this.z = z;
        }
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("input.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk;

        int N = Integer.parseInt(br.readLine());

        Node[] nodes = new Node[N];
        for (int i = 0; i < N; i++) {
            stk = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(stk.nextToken());
            int y = Integer.parseInt(stk.nextToken());
            int z = Integer.parseInt(stk.nextToken());
            nodes[i] = new Node(i, x, y, z);
        }

        PriorityQueue<Edge> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.distance, o2.distance));
        // x좌표 정렬
        Arrays.sort(nodes, (o1, o2) -> Integer.compare(o1.x, o2.x));
        for (int i = 1; i < N; i++) {
            pq.add(new Edge(nodes[i].idx, nodes[i - 1].idx, nodes[i].x - nodes[i - 1].x));
        }
        // y좌표 정렬
        Arrays.sort(nodes, (o1, o2) -> Integer.compare(o1.y, o2.y));
        for (int i = 1; i < N; i++) {
            pq.add(new Edge(nodes[i].idx, nodes[i - 1].idx, nodes[i].y - nodes[i - 1].y));
        }
        // z좌표 정렬
        Arrays.sort(nodes, (o1, o2) -> Integer.compare(o1.z, o2.z));
        for (int i = 1; i < N; i++) {
            pq.add(new Edge(nodes[i].idx, nodes[i - 1].idx, nodes[i].z - nodes[i - 1].z));
        }

        // Kruskal
        init(N);
        int count = 0;
        int cost = 0;
        while (!pq.isEmpty()) {
            if (count >= N - 1) break;

            Edge e = pq.poll();
            if (union(e.from, e.to)) {
                cost += e.distance;
                count++;
            }
        }
        System.out.println(cost);
    }

    private static int[] parent;

    private static void init(int N) {
        parent = new int[N];
        for (int i = 0; i < N; i++)
            parent[i] = i;
    }

    private static boolean union(int a, int b) {
        int pa = find(a);
        int pb = find(b);
        if (pa != pb) {
            parent[pb] = pa;
            return true;
        }
        return false;
    }

    private static int find(int a) {
        if (parent[a] == a)
            return a;
        else
            return parent[a] = find(parent[a]);
    }
}
