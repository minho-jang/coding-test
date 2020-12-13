package minho_jang.coding_test;
/**
 * 프로그래머스 코딩 테스트 연습
 * 2020.12.13.
 * 
 * LEVEL 1
 * 내
 * 출처: https://programmers.co.kr/learn/courses/30/lessons/70128
 * 
 * @author minho
 *
 */
//import java.util.*;
public class Solution10 {
	public int solution(int[] a, int[] b) {
        int answer = 0;
        
        int length = a.length;
        for (int i = 0; i < length; i++) {
            answer += a[i] * b[i];
        }
        
        return answer;
    }
}