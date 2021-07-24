package minho_jang.coding_test;
/**
 * 프로그래머스 코딩 테스트 연습
 * 2020.12.04.
 * <p>
 * LEVEL 1
 * 같은 숫자는 싫어
 * 출처: https://programmers.co.kr/learn/courses/30/lessons/12906
 *
 * @author minho
 */

import java.util.ArrayList;

public class Solution4 {
    public int[] solution(int[] arr) {
        int[] answer = {};

        ArrayList<Integer> arrlst = new ArrayList<>();
        arrlst.add(arr[0]);
        for (int i = 1; i < arr.length; i++) {
            if (arrlst.get(arrlst.size() - 1) != arr[i]) {
                arrlst.add(arr[i]);
            }
        }

        answer = new int[arrlst.size()];
        for (int i = 0; i < arrlst.size(); i++) {
            answer[i] = arrlst.get(i);
        }

        return answer;
    }
}
