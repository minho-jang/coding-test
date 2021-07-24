package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_BOJ_1647_도시분할계획 {
    static class Node {
        int from, to, weight;

        Node(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk;

        stk = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(stk.nextToken());    // 집의 개수
        int M = Integer.parseInt(stk.nextToken());    // 길의 개수

        PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> o1.weight - o2.weight);
        for (int i = 0; i < M; i++) {
            stk = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(stk.nextToken()) - 1;
            int to = Integer.parseInt(stk.nextToken()) - 1;
            int weight = Integer.parseInt(stk.nextToken());
            pq.add(new Node(from, to, weight));
        }

        // 크루스칼
        init(N);
        int count = 0;
        int answer = 0;
        while (count < N - 2) {
            Node minEdge = pq.poll();

            if (union(minEdge.from, minEdge.to)) {
                count++;
                answer += minEdge.weight;
            }
        }

        System.out.println(answer);
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

/*
Prim으로 하면 2100ms, Kruskal로 하면 1236ms

public class Main_BOJ_1647_도시분할계획_Prim {
  static class Node {
    int from, to, weight;
    Node(int from, int to, int weight) {
      this.from = from;
      this.to = to;
      this.weight = weight;
    }
  }
  public static void main(String[] args) throws Exception {
    System.setIn(new FileInputStream("input.txt"));
    
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer stk;
    
    stk = new StringTokenizer(br.readLine());
    int N = Integer.parseInt(stk.nextToken());    // 집의 개수
    int M = Integer.parseInt(stk.nextToken());    // 길의 개수
    ArrayList<Node>[] edgeList = new ArrayList[N];
    for (int i=0; i<N; i++)
      edgeList[i] = new ArrayList<>();
    
    for (int i=0; i<M; i++) {
      stk = new StringTokenizer(br.readLine());
      int from = Integer.parseInt(stk.nextToken())-1;
      int to = Integer.parseInt(stk.nextToken())-1;
      int weight = Integer.parseInt(stk.nextToken());
      edgeList[from].add(new Node(from, to, weight));
      edgeList[to].add(new Node(to, from, weight));
    }
    
    // 프림
    boolean[] visited = new boolean[N];
    PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> o1.weight - o2.weight);
    visited[0] = true;
    for (Node n : edgeList[0])
      pq.add(n);
    
    int max = 0;
    int answer = 0;
    for (int i=0; i<M; i++) {
      Node minEdge = pq.poll();
      if (minEdge == null)      break;
      if (visited[minEdge.to])  continue;
      
      visited[minEdge.to] = true;
      answer += minEdge.weight;
      if (max < minEdge.weight)
        max = minEdge.weight;
      
      for (Node n : edgeList[minEdge.to]) {
        if (!visited[n.to]) {
          pq.add(n);
        }
      }
    }
    
    System.out.println(answer - max);
  }
}

*/