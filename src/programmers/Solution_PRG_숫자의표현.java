package programmers;

public class Solution_PRG_숫자의표현 {
    public int solution(int n) {
        int answer = 0;

        int start = 1;
        int end = 1;
        int sum = 1;
        while (true) {
            if (start == n) {
                answer++;
                break;
            }

            if (sum < n) {
                sum += ++end;

            } else if (sum > n) {
                sum -= start++;

            } else {
                answer++;
                sum += ++end;

            }
        }

        return answer;
    }
}