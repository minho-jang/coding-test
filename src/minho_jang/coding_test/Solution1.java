package minho_jang.coding_test;
/**
 * 프로그래머스 코딩 테스트 연습
 * 2020.12.03.
 * 
 * LEVEL 1
 * 문자열 다루기 기본
 * 출처: https://programmers.co.kr/learn/courses/30/lessons/12918
 * 
 * @author minho
 *
 */
public class Solution1 {
	public boolean solution(String s) {
        boolean answer = true;
        
        if (s.length() == 4 || s.length() == 6) {
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) <= '9' && s.charAt(i) >= '0') {
                    continue;
                } else {
                    answer = false;
                    break;
                }
            }   
        } else {
            answer = false;
        }
        
        return answer;
    }
}
