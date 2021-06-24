package baekjoon;

import java.util.*;
import java.io.*;

public class Main_BOJ_9012_괄호 {  
  public static void main(String[] args) throws Exception {
    System.setIn(new FileInputStream("input.txt"));
    
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder answerSb = new StringBuilder();
    
    int N = Integer.parseInt(br.readLine());
    for (int i=0; i<N; i++) {
      String inputLine = br.readLine();
      if (isValid(inputLine))
        answerSb.append("YES\n");
      else
        answerSb.append("NO\n");
    }
    
    System.out.print(answerSb.toString());
  }

  private static boolean isValid(String inputLine) {
    Stack<Character> stack = new Stack<>();
    for (char c : inputLine.toCharArray()) {
      if (c == '(') 
        stack.add('(');
      else {
        if (stack.isEmpty())
          return false;
        stack.pop();
      }
    }
    
    if (stack.isEmpty())
      return true;
    else
      return false;
  }
}
