package minho_jang.coding_test;
/**
 * 프로그래머스 코딩 테스트 연습
 * 2021.01.07.
 * 
 * LEVEL 2
 * 조이스틱 
 * 출처: https://programmers.co.kr/learn/courses/30/lessons/42860
 * 
 * @author minho
 */
import java.util.*;
public class Solution21 {
    public int solution(String name) {
        int answer = 0;
        
        StringBuffer nameSB = new StringBuffer(name);
        
        StringBuffer Astring = new StringBuffer();
        for (int i = 0; i < name.length(); i++) {
        	Astring.append("A");
        }
            
        int idx = 0;
        int direction = 1;
        while(true) {
        	System.out.println(nameSB);
            if (nameSB.toString().equals(Astring.toString())) {
                break;
            }
            
            if (idx < 0) {
                idx += nameSB.length();
            } else if (idx >= nameSB.length()) {
            	idx -= nameSB.length();
            }
            
            char c = nameSB.charAt(idx);
            if (c != 'A') {
            	int forward = c - 'A';
                int backward = 'Z' - c + 1;
                answer += (forward > backward ? backward : forward);
                answer += 1;
                // A가 아닌 문자를 A로 치환 
                nameSB.replace(idx, idx+1, "A");
                idx += direction;
            } else {
                // 'A'를 만났을 때, 이전 상태에서 'A'가 아닌 문자를 만나는 게 어디가 더 가까운가

            	// 정방향 
            	if (direction == 1) {
            		idx -= (idx == 0 ? 0 : 1);
            		int forward = getNotADistance(nameSB, idx, 1);
                    int backward = getNotADistance(nameSB, idx, -1);
//                    System.out.println(idx + " " + forward + " " + backward);
                    
                    if (forward < backward) {
                        // 앞(정방향)으로 가야 이득
                        answer += forward;
                        idx += forward;
                        idx += 1;
                        direction *= 1;
                    } else {
                        // 뒤(역방향)로 가야 이득. 방향전환 
                        answer += backward;
                        idx -= backward;
                        idx -= 1;
                        direction *= -1;
                    }
                    
                // 역방향 
            	} else if (direction == -1) {
            		idx += (idx == 0 ? 0 : 1);
            		int forward = getNotADistance(nameSB, idx, -1);
                    int backward = getNotADistance(nameSB, idx, 1);
//                    System.out.println(idx + " " + forward + " " + backward);
                    
                    if (forward < backward) {
                        // 앞(역방향)으로 가야 이득
                        answer += forward;
                        idx -= forward;
                        idx -= 1;
                        direction *= 1;
                    } else {
                        // 뒤(정방향)로 가야 이득. 방향전환 
                        answer += backward;
                        idx += backward;
                        idx += 1;
                        direction *= -1;
                    }
            	}
                
            }
        }
        
        // 첫 글자가 A가 아닌 경우 이동 count 제외 
        if (nameSB.charAt(0) != 'A') {
        	answer -= 1;
        }
        
        // 마지막 글자 치환 후 이동 count 제외 
        return answer - 1;
    }
    
    int getNotADistance(StringBuffer sb, int idx, int direction) {
        int count = 0;
        while(true) {
            if (count == sb.length()) {
                break;
            }
            
            idx += direction;
            if (idx < 0) {
                idx += sb.length();
            } else if (idx >= sb.length()) {
            	idx -= sb.length();
            }
            
            char c = sb.charAt(idx);
            if (c != 'A') {
                break;
            }
            
            count++;
        }
        
        return count;
    }
}