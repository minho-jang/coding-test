package programmers;

import java.util.PriorityQueue;

public class Solution_PRG_더맵게 {
    public int solution(int[] scoville, int K) {
        int answer = 0;

        PriorityQueue<Integer> queue = new PriorityQueue<>((o1, o2) -> Integer.compare(o1, o2));
        for (int scv : scoville)
            queue.add(scv);

        int count = 0;
        while (true) {
            int polled = queue.poll();
            if (polled >= K)
                break;

            if (queue.isEmpty()) {
                count = -1;
                break;
            }

            int polledSecond = queue.poll();
            queue.add(polled + (polledSecond * 2));
            count++;
        }

        answer = count;
        return answer;
    }

    public static void main(String[] args) {
        Solution_PRG_더맵게 sol = new Solution_PRG_더맵게();
        int[] scoville = {1, 2, 3, 9, 10, 12};
        int K = 7;
        System.out.println(sol.solution(scoville, K));
    }
}
