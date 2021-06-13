package baekjoon;

import java.util.*;
import java.io.*;

public class Main_BOJ_4386_별자리만들기 {
  static class Node {
    double x, y;
    double distance;
    int idx;
    Node(double x, double y, double distance, int idx) {
      this.x = x;
      this.y = y;
      this.distance = distance;
      this.idx = idx;
    }
  }
  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer stk;
    
    int N = Integer.parseInt(br.readLine());
    double[][] arr = new double[N][2];
    for (int i=0; i<N; i++) {
      stk = new StringTokenizer(br.readLine());
      arr[i][0] = Double.parseDouble(stk.nextToken());
      arr[i][1] = Double.parseDouble(stk.nextToken());
    }
    
    // Prim
    boolean[] visited = new boolean[N];
    PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> {
      if (o1.distance - o2.distance < 0) 
        return -1;
      else if (o1.distance - o2.distance > 0) 
        return 1;
      else
        return 0;
    });
    
    pq.add(new Node(arr[0][0], arr[0][1], 0, 0));
    
    double cost = 0.0;
    while(!pq.isEmpty()) {
      Node minNode = pq.poll();
      if (minNode == null)        break;
      if (visited[minNode.idx])   continue;
      
      visited[minNode.idx] = true;
      cost += minNode.distance;
      
      for (int i=0; i<N; i++) {
        if (!visited[i]) {
          pq.add(new Node(arr[i][0], arr[i][1], getDistance(minNode.x, minNode.y, arr[i][0], arr[i][1]), i));
        }
      }
    }
    
    System.out.println(cost);
    
  }
  private static double getDistance(double x, double y, double a, double b) {
    return Math.sqrt(Math.pow(a-x, 2) + Math.pow(b-y, 2));
  }
}
