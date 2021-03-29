package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main_BOJ_1755_숫자놀이 {
	
	static String[] numToChar = {
			"zero", "one", "two", "three", "four", "five",
			"six", "seven", "eight", "nine", "ten"};			// 숫자를 문자열로 변환하기 위한 String 배열 선언
	
	static class NumAndString {	// 숫자와 문자열을 함께 다루는 클래스 정의
		int num;				// 예) 20
		String str;				// 예) "two zero"
		
		public NumAndString(int num, String str) {	// 생성자
			this.num = num;
			this.str = str;
		}
	}
	
	public static void main(String[] args) throws IOException {
		
		//////// input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));	// 입력 받는 BufferedReader 선언 및 생성
		StringTokenizer stk;	// 입력 문자열을 다루는 StringTokenizer 선언
		
		stk = new StringTokenizer(br.readLine());	// 한 줄을 읽어서 StringTokenizer 생성
		int M = Integer.parseInt(stk.nextToken());	// 하한을 나타내는 M
		int N = Integer.parseInt(stk.nextToken());	// 상한을 나타내는 N
		
		//////// logic
		ArrayList<NumAndString> strList = new ArrayList<>();	// NumAndString을 담는 리스트
		for (int i = M; i <= N; i++) {							// M이상 N이하 반복
			StringBuilder numToStr = new StringBuilder();		// 정수 i의 문자열을 담는 StringBuilder 선언
			if (i >= 10) {				// 두 자리 수이면
				int front = i / 10;		// 십의 자리 저장
				int back = i % 10;		// 일의 자리 저장
				numToStr.append(numToChar[front])		// 십의 자리 문자열로 변환 및 저장
					.append(" ")						// 띄어쓰기 삽입
					.append(numToChar[back]);			// 일의 자리 문자열로 변환 및 저장
			} else {					// 한 자리 수이면
				numToStr.append(numToChar[i]);			// 일의 자리 문자열로 변환 및 저장 
			}
			
			strList.add(new NumAndString(i, numToStr.toString()));	// 문자열과 숫자를 NumAndString으로 만들어서 리스트에 저장
		}
		
		Collections.sort(strList, (o1, o2) -> o1.str.compareTo(o2.str));	// NumAndString에서 문자열에 따라 사전순으로 정렬
		
		//////// answer print
		for (int i=0, size=strList.size(); i < size; i++) {		// 정렬된 리스트에 대해서
			if (i > 0 && i % 10 == 0) {							// 첫 출력이 아니면서, 10개 이상 출력했으면 
				System.out.println();							// 줄바꿈
			}
			
			System.out.print(strList.get(i).num + " ");			// NumAndString에서 숫자를 출력 
		}
	}
}