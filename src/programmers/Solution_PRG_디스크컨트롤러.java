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
      if (curJobIdx == jobs.length && queue.isEmpty())
        break;

      while (curJobIdx < jobs.length && time == jobs[curJobIdx][0])
        queue.offer(jobs[curJobIdx++]);

      if (remainTimeOfJob == 0 && !queue.isEmpty()) {
        int[] polled = queue.poll();
        answer += time - polled[0] + polled[1];
        remainTimeOfJob = polled[1];
      }

      if (remainTimeOfJob != 0)
        remainTimeOfJob--;
      time++;
    }

    return answer / jobs.length;
  }

// 다시 풀어보기 
//  class Node {
//    int processTime, startTime;
//    Node(int startTime, int processTime) {
//      this.startTime = startTime;
//      this.processTime = processTime;
//    }
//  }
//  public int solution(int[][] jobs) {
//    int answer = 0;
//    
//    Arrays.sort(jobs, (o1, o2) -> Integer.compare(o1[0], o2[0]));
//    PriorityQueue<Node> queue = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.processTime, o2.processTime));
//    
//    int sum = 0;
//    int isProcessing = 0;
//    int idx = 0;
//    int time = 0;
//    while(true) {
////      System.out.println("time = " + time);
//      if (idx == jobs.length && queue.isEmpty())
//        break;
//      
//      while (idx < jobs.length && jobs[idx][0] == time) {
//        queue.add(new Node(jobs[idx][0], jobs[idx][1]));
//        idx++;
//      }
//      
//      if (isProcessing == 0) {
//        if (queue.size() > 0) {
//          Node polled = queue.poll();
//          isProcessing = polled.processTime - 1;
//          int result = time + polled.processTime - polled.startTime;
//          sum += result;
//        }
//      } else {
//        isProcessing--;
//      }
//      
//      time++;
//    }
//    
//    answer = sum / jobs.length;
//    return answer;
//  }
}


