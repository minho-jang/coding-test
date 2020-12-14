package minho_jang.coding_test;
/**
 * 프로그래머스 코딩 테스트 연습
 * 2020.12.14.
 * 
 * LEVEL 1
 * [1차] 비밀지도  
 * 출처: https://programmers.co.kr/learn/courses/30/lessons/17681
 * 
 * @author minho
 *
 */
import java.util.*;
public class Solution12 {
	public String[] solution(int n, int[] arr1, int[] arr2) {
        String[] answer = new String[n];
        
        ArrayList<String> answerArrlst = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int encrypted = arr1[i] | arr2[i];
            String decrypted = intToBinary(encrypted, n);
            answerArrlst.add(decrypted);
        }
        
        for (int i = 0; i < answerArrlst.size(); i++) {
            String s = answerArrlst.get(i);
            StringBuffer sb = new StringBuffer();
            for (int j = 0; j < s.length(); j++) {
                char c = s.charAt(j);
                if (c == '1') {
                    sb.append('#');
                } else {
                    sb.append(' ');
                }
            }
            answer[i] = sb.toString();
        }
        
        return answer;
    }
    
    private String intToBinary(int num, int n) {
        int q = num;
        StringBuffer sb = new StringBuffer();
        while (q / 2 != 0) {
            String r = Integer.toString(q % 2);
            sb.append(r);
            
            q /= 2;
        }
        sb.append('1');
        if (sb.length() < n) {
            int count = n - sb.length();
            for (int i = 0; i < count; i++) {
                sb.append('0');
            }
        }
        
        StringBuffer revSb = new StringBuffer();
        for (int i = sb.length() - 1; i >= 0; i--) {
            revSb.append(sb.charAt(i));
        }
        
        return revSb.toString();
    }
}