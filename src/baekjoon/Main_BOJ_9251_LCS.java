package baekjoon;

import java.io.*;

public class Main_BOJ_9251_LCS {
  static char[] A;
  static char[] B;
  static int answer;
  
  public static void main(String[] args) throws Exception {
    System.setIn(new FileInputStream("input.txt"));
    
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
    A = br.readLine().toCharArray();
    B = br.readLine().toCharArray();
    
    dfs(0, 0, 0);
    lcs();
    
    System.out.println(answer);
  }

  private static void lcs() {
    int[][] dp = new int[A.length+1][B.length+1];
    
    for (int i=0; i<A.length+1; i++) {
      for (int j=0; j<B.length+1; j++) {
        if (i == 0 || j == 0) {
          dp[i][j] = 0;
          
        } else if (A[i-1] == B[j-1]) {
          dp[i][j] = dp[i-1][j-1] + 1;
          
        } else {
          dp[i][j] = Math.max(dp[i][j-1], dp[i-1][j]);
          
        }
      }
    }
    
    answer = dp[A.length][B.length];
  }

  private static void dfs(int a, int b, int count) {
    if (a >= A.length || b >= B.length) {
      answer = Math.max(answer, count);
      return;
    }
    
    if (A[a] == B[b]) {
      dfs(a+1, b+1, count+1);
      
    } else {
      dfs(a, b+1, count);
      dfs(a+1, b, count);
      
    }
  }
}
