package minho_jang.coding_test;
/**
 * 프로그래머스 코딩 테스트 연습
 * 2020.12.03.
 * 
 * LEVEL 1
 * 3진법 뒤집기 
 * 출처: https://programmers.co.kr/learn/courses/30/lessons/68935
 * 
 * @author minho
 *
 */
public class Solution2 {
	public int solution(int n) {
        int answer = 0;
        
        String third = intToThird(n);
        answer = thirdToInt(third);
        
        return answer;
    }
    
    String intToThird(int n) {
        StringBuffer sb = new StringBuffer();
        while (n != 0) {
            int r = n % 3;
            sb.append(Integer.toString(r));
            n /= 3;
        }
        
        StringBuffer revSb = new StringBuffer();
        for (int i = sb.length()-1; i >= 0; i--) {
            char c = sb.charAt(i);
            revSb.append(c);
        }
        
        return revSb.toString();
    }
    
    int thirdToInt(String s) {
        StringBuffer sb = new StringBuffer(s);
        int sum = 0;
        for (int i = sb.length()-1; i >= 0; i--) {
            char c = sb.charAt(i);
            if (c == '0') {
                sum += 0 * Math.pow(3, i);
            } else if (c == '1') {
                sum += 1 * Math.pow(3, i);
            } else if (c == '2') {
                sum += 2 * Math.pow(3, i);
            }
        }
        
        return sum;
    }
}
