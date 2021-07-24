package minho_jang.coding_test;
/**
 * 프로그래머스 코딩 테스트 연습
 * 2020.12.07.
 * <p>
 * LEVEL 1
 * 나누어 떨어지는 숫자 배열
 * 출처: https://programmers.co.kr/learn/courses/30/lessons/12910
 *
 * @author minho
 */

import java.util.ArrayList;
import java.util.Collections;

public class Solution5 {
    public int[] solution(int[] arr, int divisor) {
        int[] answer = {};

        ArrayList<Integer> arrlst = new ArrayList<>();
        for (int el : arr) {
            if (el % divisor == 0) {
                arrlst.add(el);
            }
        }

        if (arrlst.size() == 0) {
            answer = new int[1];
            answer[0] = -1;
        } else {
            Collections.sort(arrlst);
            answer = new int[arrlst.size()];
            for (int i = 0; i < arrlst.size(); i++) {
                answer[i] = arrlst.get(i);
            }
        }

        return answer;
    }
}
