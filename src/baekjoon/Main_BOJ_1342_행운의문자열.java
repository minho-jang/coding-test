package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_BOJ_1342_행운의문자열 {
	static int N, answer;
	static boolean[] visited;
	static char[] chArr;
//	static HashSet<String> answerSet;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		chArr = br.readLine().toCharArray();
		N = chArr.length;
		visited = new boolean[N];
//		answerSet = new HashSet<>();	
		
		perm(0, new char[N]);
		
		// HashSet으로 중복 제거를 통해 경우의 수를 세는 것은 메모리 초과
		// 순열의 특징을 이용. 중복되는 알파벳 개수 팩토리얼로 나눠준다.
		
		// 팩토리얼 값 미리 계산
		int[] facto = new int[11];
		facto[0] = 1;
		facto[1] = 1;
		for (int i = 2; i < 11; i++) {
			facto[i] = facto[i-1] * i;
		}
		
		// 알파벳 빈도수 확인
		int[] alphabet = new int[26];
		for (char c : chArr) {
			alphabet[c-'a']++;
		}
		
		// 알파벳 빈도수의 팩토리얼 값으로 나눠줌으로 써 중복되는 경우의 수 제거
		int div = 1;
		for (int a : alphabet) {
			if (a > 1)				// 알파벳이 2개 이상 등장했다면
				div *= facto[a];	// 나눠야할 값을 곱한다
		}
		
		System.out.println(answer/div);
	}
	
	private static void perm(int cnt, char[] res) {
		if (cnt == N) {
//			answerSet.add(new String(res));
			answer++;
			return;
		}
		
		for (int i = 0; i < N; i++) {
			if (visited[i])	
				continue;
			
			if (cnt > 0 && res[cnt-1] == chArr[i]) 
				continue;
			
			visited[i] = true;
			res[cnt] = chArr[i];
			perm(cnt+1, res);
			visited[i] = false;
			
		}
	}
}
