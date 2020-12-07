package minho_jang.coding_test;
/**
 * 프로그래머스 코딩 테스트 연습
 * 2020.12.07.
 * 
 * LEVEL 1
 * 가운데 글자 가져오기 
 * 출처: https://programmers.co.kr/learn/courses/30/lessons/12903
 * 
 * @author minho
 *
 */
public class Solution6 {
	public String solution(String s) {
        StringBuffer answer = new StringBuffer();
        
        int center = s.length() / 2;
        if (s.length() % 2 == 1) {
            answer.append(s.charAt(center));
        } else {
            answer.append(s.charAt(center-1));
            answer.append(s.charAt(center));
        }
        
        return answer.toString();
    }
}
