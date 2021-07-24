package programmers;

class Solution_PRG_정수삼각형 {
    public int solution(int[][] triangle) {
        int answer = 0;

        int height = triangle.length - 1;
        int[] dp = new int[height + 1];

        // 초기값은 삼각형의 맨 밑층
        for (int i = 0; i <= height; i++)
            dp[i] = triangle[height][i];

        // 맨 밑층 부터 한 단계씩 올라가면서, 최선의 선택을 고른다.
        // 현 위치 (i-1, j)에서  선택할 수 있는 dp[j]와 dp[j-1]중 큰 값을 선택하고, 거기에 현재 값 triangle[i-1][j]을 더한게 최선의 선택이다.
        for (int i = height; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                dp[j] = Math.max(dp[j], dp[j + 1]) + triangle[i - 1][j];
            }
        }

        // dp[0]에 (0,0)에서 선택할 수 있는 최선의 선택을 한 값이 저장되어 있다.
        answer = dp[0];

        return answer;
    }
}