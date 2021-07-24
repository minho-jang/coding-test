package minho_jang.coding_test;
/**
 * 프로그래머스 코딩 테스트 연습
 * 2021.01.06.
 * <p>
 * LEVEL 2
 * 더 맵게
 * 출처: https://programmers.co.kr/learn/courses/30/lessons/42626
 *
 * @author minho
 * TODO not solved. 실행 시간을 고려할 것.
 */

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Solution20 {
    final static int SCOVILLE_MAX = 1000001;

    public int solution(int[] scoville, int K) {
        int answer = 0;

        LinkedList<Integer> scvList = new LinkedList<>();
        for (int i : scoville) {
            scvList.addLast(i);
        }
        // 정렬
        Collections.sort(scvList);

        int count = 0;
        while (true) {
            if (scvList.size() == 1 && scvList.get(0) < K) {
                count = -1;
                break;
            } else if (scvList.get(0) >= K) {
                break;
            }

            // 가장 낮은 2개 선택
            int lowest = scvList.remove(0);
            int lower = scvList.remove(0);

            // 새로운 음식 삽입
            boolean isAdded = false;
            int newFood = lowest + (lower * 2);
            pushNewFood(scvList, 0, scvList.size() - 1, newFood);

            // for (int i = 0; i < scvList.size(); i++) {
            //     if (scvList.get(i) >= newFood) {
            //         scvList.add(i, newFood);
            //         isAdded = true;
            //         break;
            //     }
            // }
            // if (!isAdded) {
            // 	scvList.addLast(newFood);
            // }

            count++;
        }

        answer = count;
        return answer;
    }

    void pushNewFood(LinkedList<Integer> lst, int start, int end, int el) {
        List<Integer> tmp = lst.subList(start, end + 1);

        if (tmp.size() == 1) {
            if (tmp.get(0) >= el)
                lst.add(start, el);
            else {
                if (end == lst.size() - 1)
                    lst.add(el);
                else
                    lst.add(end + 1, el);
            }

            return;
        } else if (tmp.size() == 0) {
            lst.add(el);
            return;
        }

        int center = (start + end) / 2;
        if (el < lst.get(center)) {
            pushNewFood(lst, start, center, el);
        } else if (el > lst.get(center)) {
            pushNewFood(lst, center + 1, end, el);
        } else {
            pushNewFood(lst, center, center, el);
        }
    }
}