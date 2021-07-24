package minho_jang.coding_test;
/**
 * 프로그래머스 코딩 테스트 연습
 * 2020.12.21.
 * <p>
 * LEVEL 2
 * 기능개발
 * 출처: https://programmers.co.kr/learn/courses/30/lessons/42586
 *
 * @author minho
 */

import java.util.ArrayList;

public class Solution16 {
    public int[] solution(int[] progresses, int[] speeds) {
        int[] answer = {};

        int len = progresses.length;
        // 각각 스피드로 며칠 후에 배포가능한지 계산한 배열
        ArrayList<Integer> releasePossibleDay = new ArrayList<>();
        for (int i = 0; i < len; i++) {
            int day = 0;
            int progress = progresses[i];
            int speed = speeds[i];
            while (progress < 100) {
                progress += speed;
                day += 1;
            }
            releasePossibleDay.add(day);
        }

        int maxDay = releasePossibleDay.get(0);
        int count = 1;
        ArrayList<Integer> answerArrlst = new ArrayList<>();
        for (int i = 1; i < releasePossibleDay.size(); i++) {
            int day = releasePossibleDay.get(i);
            if (day > maxDay) {
                maxDay = day;
                answerArrlst.add(count);
                count = 1;
            } else {
                count += 1;
            }
        }
        answerArrlst.add(count);

        answer = new int[answerArrlst.size()];
        for (int i = 0; i < answer.length; i++) {
            answer[i] = answerArrlst.get(i);
        }

        return answer;
    }
}