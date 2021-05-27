package baekjoon;

import java.io.*;
import java.util.*;

public class Main_BOJ_6603_로또 {
  static int[] S;
  static int k;
  static StringBuilder answer;
  
  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer stk;
    answer = new StringBuilder();
    
    String inputLine = null;
    while (true) {
      inputLine = br.readLine();
      stk = new StringTokenizer(inputLine);
      k = Integer.parseInt(stk.nextToken());
      if (k == 0) break;
      S = new int[k];
      for (int i=0; i<k; i++) {
        S[i] = Integer.parseInt(stk.nextToken());
      }
      
      comb(0, 0, new int[6]);
      answer.append("\n");
    }
    
    System.out.print(answer);
  }
  
  static void comb(int start, int cnt, int[] res) {
    if (cnt == 6) {
      for (int i=0; i<res.length; i++) {
        answer.append(res[i]).append(" ");
      }
      answer.append("\n");
      
      return;
    }
    
    for (int i=start; i<k; i++) {
      res[cnt] = S[i];
      comb(i+1, cnt+1, res);
    }
  }
}
