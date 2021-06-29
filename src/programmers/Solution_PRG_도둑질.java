package programmers;

public class Solution_PRG_도둑질 {
  public int solution(int[] money) {
    int answer = 0;
    
    int N = money.length;
    int[] dp = new int[N];
    dp[0] = money[0];
    dp[1] = money[0];
    for (int i=2; i<N-1; i++) {
      dp[i] = Math.max(dp[i-2] + money[i], dp[i-1]);
    }
    
    int[] dp2 = new int[N];
    dp2[0] = 0;
    dp2[1] = money[1];
    for (int i=2; i<N; i++) {
      dp2[i] = Math.max(dp2[i-2] + money[i], dp2[i-1]);
    }
    
    answer = Math.max(dp[N-2], dp2[N-1]);
    
    return answer;
  }
  
  public static void main(String[] args) {
    Solution_PRG_도둑질 sol = new Solution_PRG_도둑질();
    
    int[] money = {5, 1, 100, 6, 1, 100};
    System.out.println(sol.solution(money));
  }
}
