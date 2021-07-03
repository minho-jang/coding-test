package programmers;

import java.util.*;

public class Solution_PRG_가장큰수 {
  public String solution(int[] numbers) {
    String[] numbersStrArr = new String[numbers.length];
    for (int i=0; i<numbers.length; i++) {
      numbersStrArr[i] = Integer.toString(numbers[i]);
    }
    
    Arrays.sort(numbersStrArr, (o1, o2) -> {
      int a = Integer.parseInt(o1 + o2);
      int b = Integer.parseInt(o2 + o1);
      return b - a;
    });
    
    StringBuilder answerSb = new StringBuilder();
    for (int i=0; i<numbers.length; i++) {
      answerSb.append(numbersStrArr[i]);
    }
    
    String answer = answerSb.toString();
    int start = 0;
    int idx = 0;
    while(idx < answer.length() && answer.charAt(idx++) == '0')  start++;
    if (start == idx)
      return "0";
    
    return answer;
  }
  
  public static void main(String[] args) {
    Solution_PRG_가장큰수 sol = new Solution_PRG_가장큰수();
    int[] numbers = {6, 10, 2};
//    int[] numbers = {5, 9, 30, 3, 34};
//    int[] numbers = {3, 32, 33, 34, 5, 9, 0, 1000, 330, 1, 2, 3, 4, 5};
//    int[] numbers = {90, 908, 89, 898, 10, 101, 1, 8, 9};
//    int[] numbers = {10, 101};
//    int[] numbers = {101, 10};
//    int[] numbers = {0, 0, 0};
    System.out.println(sol.solution(numbers));
  }
}
