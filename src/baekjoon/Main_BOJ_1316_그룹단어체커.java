package baekjoon;

import java.io.*;
import java.util.Arrays;

public class Main_BOJ_1316_그룹단어체커 {
  public static void main(String[] args) throws Exception {
    System.setIn(new FileInputStream("input.txt"));
    
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
    int answer = 0;
    int N = Integer.parseInt(br.readLine());
    for (int i=0; i<N; i++) {
      String inputLine = br.readLine();
      if (isGroupWord(inputLine))
        answer++;
    }
    
    System.out.println(answer);
  }

  private static boolean isGroupWord(String str) {
    int len = str.length();
    boolean[] visited = new boolean[26];
    Arrays.fill(visited, false);
    
    int prev = 0;
    for (int i=0; i<len; i++) {
      int idx = str.charAt(i) - 'a';
      if (!visited[idx] || prev == idx)
        visited[idx] = true;
      else
        return false;
      prev = idx;
    }
    
    return true;
  }
}
