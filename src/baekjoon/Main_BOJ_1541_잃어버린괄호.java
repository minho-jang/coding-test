package baekjoon;

import java.io.*;

public class Main_BOJ_1541_잃어버린괄호 {
  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String expression = br.readLine();
    
    boolean isFirst = true;
    long answer = 0;
    String[] onlyPlus = expression.split("-");
    for (String ex : onlyPlus) {
      int sum = 0;
      if (ex.contains("+")) {
        String[] nums = ex.split("\\+");
        for (String num : nums)
          sum += Integer.parseInt(num);
        
      } else {
        sum = Integer.parseInt(ex);
      }
      
      if (isFirst) {
        answer = sum;
        isFirst = false;
      } else 
        answer -= sum;
    }
    
    System.out.println(answer);
  }
}
