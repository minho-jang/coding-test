package minho_jang.coding_test;
/**
 * 프로그래머스 코딩 테스트 연습
 * 2020.12.10.
 * 
 * LEVEL 1
 * 문자열 내림차순으로 배치하기 
 * 출처: https://programmers.co.kr/learn/courses/30/lessons/12917
 * 
 * @author minho
 *
 */
import java.util.*;
public class Solution7 {
	public String solution(String s) {  
        String answer = "";
        
        ArrayList<Integer> charArrlst = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            int charToInt = s.charAt(i);
            charArrlst.add(charToInt);
        }
        
        Collections.sort(charArrlst, Collections.reverseOrder());
        StringBuffer sb = new StringBuffer();
        for (int i : charArrlst) {
            char c = (char) i;
            sb.append(c);
        }
        
        answer = sb.toString();
        return answer;
    }
}
