package minho_jang.coding_test;
/**
 * 프로그래머스 코딩 테스트 연습
 * 2020.12.04.
 * 
 * LEVEL 1
 * 2016년 
 * 출처: https://programmers.co.kr/learn/courses/30/lessons/12901
 * 
 * @author minho
 *
 */
public class Solution3 {
	public String solution(int a, int b) {
        String answer = "";
        String[] week = {"FRI", "SAT", "SUN", "MON", "TUE", "WED", "THU"};
        int[] days = {31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

        int daySum = 0;
        for (int i = 0; i < a - 1; i++) {
            daySum += days[i];
        }
        daySum += b - 1;
        
        answer = week[daySum % 7];
        
        return answer;
    }
}
