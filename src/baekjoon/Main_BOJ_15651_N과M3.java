package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BOJ_15651_N과M3 {
	private static int N, M;
	private static StringBuilder answer;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(stk.nextToken());
		M = Integer.parseInt(stk.nextToken());
		answer = new StringBuilder();
		
		perm(0, new int[M]);
		
		System.out.println(answer.toString());
	}

	private static void perm(int cnt, int[] res) {
		if (cnt == M) {
			for (int i = 0; i < M; i++) {
				answer.append(res[i]).append(" ");
			}
			answer.append("\n");
			
			return;
		}
		
		for (int i = 0; i < N; i++) {
			res[cnt] = i+1;
			perm(cnt+1, res);
		}
	}
}
