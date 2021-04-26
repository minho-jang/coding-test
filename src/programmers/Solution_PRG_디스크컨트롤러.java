package programmers;

import java.util.*;

class Solution_PRG_디스크컨트롤러 {
    public int solution(int[][] jobs) {
        int answer = 0;
        
        Arrays.sort(jobs, (o1, o2) -> {
            return Integer.compare(o1[0], o2[0]);
        });
        
        PriorityQueue<int[]> queue = new PriorityQueue<>((o1, o2) -> {
            return Integer.compare(o1[1], o2[1]);
        });
        
        int remainTimeOfJob = 0;
        int curJobIdx = 0;
        int time = 0;
        while (true) {
            if (curJobIdx==jobs.length && queue.isEmpty()) 
                break;
            
            while (curJobIdx<jobs.length && time == jobs[curJobIdx][0]) 
                queue.offer(jobs[curJobIdx++]);
            
            if (remainTimeOfJob == 0 && !queue.isEmpty()) {
                int[] polled = queue.poll();
                answer += time-polled[0]+polled[1];
                remainTimeOfJob = polled[1];
            }
            
            if (remainTimeOfJob != 0)
                remainTimeOfJob--;
            time++;
        }
        
        return answer / jobs.length;
    }
}