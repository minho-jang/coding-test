package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main_BOJ_2629_양팔저울 {
	static final int MAX_WEIGHT = 30 * 500;		// 추를 이용해 만들 수 있는 최대의 무게
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		//////// input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));	// 입력 받는 BufferedReader 선언 및 생성
		StringTokenizer stk;	// 입력 문자열을 다루는 StringTokenizer 선언
		
		int N = Integer.parseInt(br.readLine());	// 추의 개수 N
		int[] weights = new int[N];					// 추의 무게를 담을 int 배열 weights
		stk = new StringTokenizer(br.readLine());	// 한 줄을 읽어서 StringTokenizer 생성
		for (int i = 0; i < N; i++) {							// 추의 개수 N 만큼
			weights[i] = Integer.parseInt(stk.nextToken());		// 배열 weights에 입력된 추의 무게를 저장
		}
		
		//////// logic
		// 미리 확인 가능한 무게를 모두 계산한다.
		// 사용가능한 추를 하나씩 증가시키며, 이전의 계산 결과를 이용해 현재의 결과를 구한다.
		// 현재 확인 가능한 무게는 이전 결과에다가 현재 사용할 추의 무게를 더하거나 뺀 무게이다.
		// 입력 예시 2와 같이 추 무게 배열이 {2, 3, 3, 3}과 같다면 아래와 같은 과정으로 확인 가능한 무게를 찾는다.
		
		// {2}			2									---- (1)
		// {2,3}		2 3 5 1								---- (2)
		// {2,3,3}		2 3 5 1 6 8 4
		// {2,3,3,3}	2 3 5 1 6 8 4 9 11 7
		
		// (2) 과정에서 (1)의 결과인 [2]에다가 3 그자체로 쓰거나, 3을 더하거나, 3을 뺀 무게를 확인할 수있다.
		// 따라서 [2]에 [3(그대로 씀), 5(더함), 1(뺌)]이 추가된다.
		
		boolean[] check = new boolean[MAX_WEIGHT + 1];		// 확인 가능한 추의 무게 만큼 boolean 배열 check
		ArrayList<Integer> memoList = new ArrayList<>();		// 계산 결과를 저장해 놓을 리스트
		
		for (int i = 0; i < N; i++) {		// 추를 하나씩 증가시키며 확인 
			int w = weights[i];				// 현재 사용할 추 무게 w
			
			for (int j=0, size=memoList.size(); j < size; j++) {	// 이전 계산 결과를 사용
				int memo = memoList.get(j);		// 이전 계산 결과 memo
				
				int tmp = memo + w;				// memo에 w를 더한 값 tmp
				if (!check[tmp]) {				// tmp가 처음 등장하는 값이라면
					check[tmp] = true;			// check배열에 true 표시 
					memoList.add(tmp);			// 계산 결과 저장
				}
				
				int tmp2 = Math.abs(memo - w);	// memo에 w를 뺀 값 tmp2
				if (!check[tmp2]) {				// tmp2가 처음 등장하는 값이라면
					check[tmp2] = true;			// check배열에 true 표시 
					memoList.add(tmp2);			// 계산 결과 저장
				}
			}
			
			if (!check[w]) {			// w가 처음 등장하는 값이라면
				check[w] = true;		// check배열에 true 표시 
				memoList.add(w);		// 계산 결과 저장
			}
		}
		
		// 확인가능한 무게 print
//		for (int i = 0; i < 15001; i++) {
//			if (check[i])
//				System.out.print(i + " ");
//		}
//		System.out.println();
		
		//////// input and answer print
		StringBuilder answer = new StringBuilder();			// 답을 출력할 StringBuilder 생성
		int M = Integer.parseInt(br.readLine());			// 확인할 구슬 개수 M
		stk = new StringTokenizer(br.readLine());			// 한 줄을 읽어서 StringTokenizer 생성
		
		for (int i = 0; i < M; i++) {						// 구슬 무게 입력을 받기 위해 M만큼 반복
			int w = Integer.parseInt(stk.nextToken());		// 구슬의 무게 w
			if (w <= MAX_WEIGHT && check[w])				// w가 최대 무게보다 낮고, 확인가능한 무게라면 
				answer.append("Y ");						// answer에 Y 덧붙인다
			else											// 그렇지 않으면 (확인 가능한 최대 무게보다 크거나, 확인할 수 없는 무게)
				answer.append("N ");						// answer에 N 덧붙인다
		}
		
		System.out.println(answer.toString());				// answer 출력
	}
}