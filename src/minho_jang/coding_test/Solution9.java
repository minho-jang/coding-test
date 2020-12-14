package minho_jang.coding_test;
/**
 * 프로그래머스 코딩 테스트 연습
 * 2020.12.13.
 * 
 * LEVEL 1
 * 시저 암호 
 * 출처: https://programmers.co.kr/learn/courses/30/lessons/12926
 * 
 * @author minho
 *
 */
public class Solution9 {
	public String solution(String s, int n) {
        String answer = "";
        
        // A  B  ... Z  | a  b  ... z
        // 65 66 ... 90 | 97 98 ... 122
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c >= 'A' && c <= 'Z') {
                c += n;
                c = (char)(((c - 'A') % 26) + 'A');
            } else if (c >= 'a' && c <= 'z') {
                c += n;
                c = (char)(((c - 'a') % 26) + 'a');
            } 
            sb.append(c);
        }
        
        answer = sb.toString();
        return answer;
    }
}
