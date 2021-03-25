package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_BOJ_1205_등수구하기 {
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk;
		
		stk = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(stk.nextToken());
		int newScore = Integer.parseInt(stk.nextToken());
		int P = Integer.parseInt(stk.nextToken());
		
		int[] scores = new int[N];
		
		if (N > 0) {
			stk = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				scores[i] = Integer.parseInt(stk.nextToken());
			}
		} else {
			System.out.println(1);
			return;
		}
		
		if (N == P && scores[N-1] >= newScore) {
			System.out.println(-1);
			return;
		}
		
		// 새로운 점수 인덱스 찾기
		int answer = 1;
		for (int i = N-1; i >= 0; i--) {
			if (scores[i] >= newScore) {
				
				if (scores[i] > newScore) {
					answer = i+2;
					
				} else {
					for (int j = i-1; j >= 0; j--) {
						if (scores[i] < scores[j]) {
							answer=j+2;
							break;
						}
					}
				}
				
				break;
			}
		}
		
		System.out.println(answer);
	}
}
