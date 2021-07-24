package programmers;

import java.util.Arrays;

public class Solution_PRG_징검다리 {
    public int solution(int distance, int[] rocks, int n) {
        int answer = 0;

        Arrays.sort(rocks);

        int left = 0;
        int right = distance;

        while (right - left >= 0) {
            int mid = (left + right) / 2;

            int prevStone = 0;
            int removedCount = 0;
            for (int i = 0; i < rocks.length; i++) {
                if (rocks[i] - prevStone < mid)
                    removedCount++;
                else
                    prevStone = rocks[i];
            }

            if (distance - prevStone < mid)
                removedCount++;

            if (removedCount > n)
                right = mid - 1;
            else {
                answer = mid;
                left = mid + 1;
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        Solution_PRG_징검다리 sol = new Solution_PRG_징검다리();

        int distance = 25;
        int[] rocks = {2, 14, 11, 21, 17};
        int n = 2;
        System.out.println(sol.solution(distance, rocks, n));
    }
}
