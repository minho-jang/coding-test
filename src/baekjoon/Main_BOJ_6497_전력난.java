package baekjoon;

import java.util.*;
import java.io.*;

public class Main_BOJ_6497_전력난 {
  
  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer stk;
    
    while (true) {
      stk = new StringTokenizer(br.readLine());
      int m = Integer.parseInt(stk.nextToken());  // 집
      int n = Integer.parseInt(stk.nextToken());  // 길
      if (m == 0 && n ==0)  break;
      
      int[][] roads = new int[n][3];
      int roadSum = 0;
      for (int i=0; i<n; i++) {
        stk = new StringTokenizer(br.readLine());
        roads[i][0] = Integer.parseInt(stk.nextToken());
        roads[i][1] = Integer.parseInt(stk.nextToken());
        roads[i][2] = Integer.parseInt(stk.nextToken());
        roadSum += roads[i][2];
      }
      
      // Kruskal
      Arrays.sort(roads, (o1, o2) -> o1[2] - o2[2]);
      init(m);
      int cost = 0;
      int count = 0;
      for (int i=0; i<n; i++) {
        if (union(roads[i][0], roads[i][1])) {
          cost += roads[i][2];
          count++;
          if (count == m-1)   break;
        }
      }
      
      System.out.println(roadSum - cost);
    }
    
  }

  private static int[] parent;
  private static void init(int n) {
    parent = new int[n];
    for (int i=0; i<n; i++)
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
