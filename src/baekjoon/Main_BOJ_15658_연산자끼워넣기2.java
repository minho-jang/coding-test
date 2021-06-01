package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_BOJ_15658_연산자끼워넣기2 {
  static int min, max, N;
  static int[] arr;
  
  public static void main(String[] args) throws NumberFormatException, IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer stk;
    
    N = Integer.parseInt(br.readLine());
    arr = new int[N];
    stk = new StringTokenizer(br.readLine());
    for(int i=0; i<N; i++) {
      arr[i] = Integer.parseInt(stk.nextToken());
    }
    int[] operators = new int[4];
    stk = new StringTokenizer(br.readLine());
    for (int i=0; i<4; i++) {
      operators[i] = Integer.parseInt(stk.nextToken());
    }
    
    min = Integer.MAX_VALUE;
    max = Integer.MIN_VALUE;
    perm(0, operators, arr[0]);
    
    System.out.println(max);
    System.out.println(min);
  }
  
  static void perm(int cnt, int[] operators, int result) {
    if (cnt == N-1) {
      if (min > result)   min = result;
      if (max < result)   max = result;
      
      return;
    }
    
    int o = 0;
    while(o < 4) {
      if (operators[o] != 0) {
        int res = calc(result, o, arr[cnt+1]);
        operators[o]--;
        perm(cnt+1, operators, res);
        operators[o]++;
      }
      o++;
    }
  }
  
  static int calc(int a, int o, int b) {
    if (o==0) {
      return a+b;
    }else if (o==1) {
      return a-b;
    } else if (o==2) {
      return a*b;
    } else if (o==3) {
      return a/b;
    } else {
      System.err.println("calc() ERROR");
      return -1;
    }
  }
}
