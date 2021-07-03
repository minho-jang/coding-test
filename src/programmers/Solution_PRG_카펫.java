package programmers;

import java.util.Arrays;

public class Solution_PRG_카펫 {
  public int[] solution(int brown, int yellow) {
    int[] answer = new int[2];
    
    for (int x=3; x<=Math.sqrt(brown+yellow); x++) {
      if ((brown + yellow) % x == 0) {
        int y = (brown + yellow) / x;
        if (2*x + 2*y - 4 == brown) {
          answer[0] = y;
          answer[1] = x;
          break;
        }
      }
    }
    
    return answer;
  }
  
  public static void main(String[] args) {
    Solution_PRG_카펫 sol = new Solution_PRG_카펫();
    int brown = 24;
    int yellow = 24;
    System.out.println(Arrays.toString(sol.solution(brown, yellow)));
  }
}
