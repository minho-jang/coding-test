package minho_jang.coding_test;
/**
 * 프로그래머스 코딩 테스트 연습
 * 2020.12.18.
 * <p>
 * LEVEL 2
 * 프린터
 * 출처: https://programmers.co.kr/learn/courses/30/lessons/42587
 *
 * @author minho
 */

import java.util.LinkedList;
import java.util.Queue;

public class Solution13 {
    public int solution(int[] priorities, int location) {
        int answer = 0;

        Queue<Printing> printQueue = new LinkedList<>();
        for (int i = 0; i < priorities.length; i++) {
            int index = i;
            int priority = priorities[i];
            Printing print = new Printing(index, priority);
            printQueue.offer(print);
        }

        int order = 0;
        while (true) {
            Printing p = printQueue.poll();
            int maxPriority = getMaxPriority(printQueue);
            if (p.priority < maxPriority) {
                printQueue.offer(p);
            } else {
                if (p.index == location) {
                    answer = order + 1;
                    break;
                }
                order++;
            }
        }

        return answer;
    }

    int getMaxPriority(Queue<Printing> lst) {
        int max = 0;
        for (Printing p : lst) {
            if (p.priority > max) {
                max = p.priority;
            }
        }

        return max;
    }

    class Printing {
        int index;
        int priority;

        Printing(int i, int p) {
            this.index = i;
            this.priority = p;
        }
    }
}