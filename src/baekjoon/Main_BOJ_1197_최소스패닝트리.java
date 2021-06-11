package baekjoon;

import java.util.*;
import java.io.*;

public class Main_BOJ_1197_최소스패닝트리 {
  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer stk;
    
    stk = new StringTokenizer(br.readLine());
    int V = Integer.parseInt(stk.nextToken());
    int E = Integer.parseInt(stk.nextToken());
    int[][] eArr = new int[E][3];
    for (int i=0; i<E; i++) {
      stk = new StringTokenizer(br.readLine());
      eArr[i][0] = Integer.parseInt(stk.nextToken())-1;
      eArr[i][1] = Integer.parseInt(stk.nextToken())-1;
      eArr[i][2] = Integer.parseInt(stk.nextToken());
    }
    
//    int answer = getWeightByKruskal(V, E, eArr);
    int answer = getWeightByPrim(V, E, eArr);
    
    System.out.println(answer);
    
  }

  // 시작 정점부터 이동 가능한 간선 중 가장 작은 간선을 선택
  private static int getWeightByPrim(int V, int E, int[][] eArr) {
    final int MAX = 987654321;
    int weight = 0;
    
    LinkedList<int[]>[] matrix = new LinkedList[V];
    for (int i=0; i<V; i++) {
      matrix[i] = new LinkedList<>();
    }
    for (int[] e : eArr) {
      matrix[e[0]].add(new int[] {e[1], e[2]});
      matrix[e[1]].add(new int[] {e[0], e[2]});
    }
    
    boolean[] visited = new boolean[V];
    int[] distance = new int[V];
    Arrays.fill(distance, MAX);
    
    int curIdx = 0;
    distance[curIdx] = 0;
    
    while (!isAllTrue(visited)) {
      int min = MAX;
      int minIdx = 0;
      for (int i=0; i<V; i++) {
        if (!visited[i] && min > distance[i]) {
          min = distance[i];
          minIdx = i;
        }
      }
      
      visited[minIdx] = true;
      weight += min;
      curIdx = minIdx;
      
      for (int[] n : matrix[curIdx]) {
        if (!visited[n[0]] && distance[n[0]] > n[1]) {
          distance[n[0]] = n[1];
        }
      }
    }
    
    return weight;
  }

  private static boolean isAllTrue(boolean[] visited) {
    for (boolean b : visited) 
      if (!b) return false;
    
    return true;
  }

  // 오름차순 정렬 후, 정점 중복 없이 간선 선택
  private static int getWeightByKruskal(int V, int E, int[][] eArr) {
    Arrays.sort(eArr, (o1, o2) -> o1[2] - o2[2]);
    int weight = 0;
    init(V);
    for (int[] e : eArr) {
      if (union(e[0], e[1])) {
        weight += e[2];
      }
    }
    return weight;
  }

  private static int[] parent;
  private static void init(int V) {
    parent = new int[V];
    for (int i=0; i<V; i++) 
      parent[i] = i;
  }
  private static int find(int i) {
    if (parent[i] == i)
      return i;
    else
      return parent[i] = find(parent[i]);
  }

  private static boolean union(int i, int j) {
    int pi = find(i);
    int pj = find(j);
    if (pi != pj) {
      parent[pj] = pi;
      return true;
      
    } else {
      return false;
    }
  }
}
