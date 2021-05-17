package baekjoon;

import java.io.*;
import java.util.*;

public class Main_BOJ_1339_단어수학 {
  
  static int N;
  static int[] alphabetCount;
  static String[] words;
  static long max;
  
  public static void main(String args[]) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
    N = Integer.parseInt(br.readLine());
    
    int count = 0;
    alphabetCount = new int[26];
    words = new String[N];
    for (int i=0; i<N; i++) {
      words[i] = br.readLine();
      for (int j=0; j<words[i].length(); j++) {
        int alpha = words[i].charAt(j) - 'A';
        if (alphabetCount[alpha] == 0)
          count++;
        alphabetCount[alpha]++;
      }
    }
    
    perm(0, new boolean[26], new int[count]);
    
    System.out.println(max);
  }
  
  static void perm(int cnt, boolean[] visited, int[] res) {
    if (cnt == res.length) {
      int[] alphaToNum = new int[26];
      
      int idx = 0;
      for (int i=0; i<26; i++) {
        if (alphabetCount[i] != 0) {
          alphaToNum[i] = res[idx++];
        }
      }
      
      long result = 0L;
      for (String word : words) {
        long wtl = wordToLong(word, alphaToNum);
        result += wtl;
      }
      
      if (result > max)
        max = result;
      
      return;
    }
    
    for (int i=0; i<10; i++) {
      if (visited[i])
        continue;
      visited[i] = true;
      res[cnt] = i;
      perm(cnt+1, visited, res);
      visited[i] = false;
    }
  }
  
  static long wordToLong(String word, int[] charToNum) {
    StringBuilder resultSb = new StringBuilder();
    for (char c : word.toCharArray()) {
      resultSb.append(charToNum[c-'A']);
    }
    return Long.parseLong(resultSb.toString());
  }
}
