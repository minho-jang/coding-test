package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BOJ_14888_연산자끼워넣기 {
	static int min, max, N;
	static int[] arr, operators;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk;
		
		N = Integer.parseInt(br.readLine());
		
		stk = new StringTokenizer(br.readLine());
		arr = new int[N];
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(stk.nextToken());					
		}
		
		stk = new StringTokenizer(br.readLine());
		operators = new int[4];
		for (int i = 0; i < 4; i++) {
			operators[i] = Integer.parseInt(stk.nextToken());
		}
		
		min = 987654321;
		max = -987654321;
		dfs(arr[0], 0);
		
		System.out.println(max);
		System.out.println(min);
	}

	private static void dfs(int result, int depth) {
		if (depth == N-1) {
			max = Math.max(max, result);
			min = Math.min(min, result);
			return;
		}
		
		for (int idx = 0; idx < 4; idx++) {
			if (operators[idx] > 0) {
				operators[idx]--;
				dfs(getResult(result, arr[depth+1], idx), depth+1);
				operators[idx]++;
			}
		}
	}

	private static int getResult(int o1, int o2, int oper) {
		int r = 0;
		
		switch(oper) {
		case 0:
			r = o1 + o2;
			break;
		case 1:
			r = o1 - o2;
			break;
		case 2:
			r = o1 * o2;
			break;
		case 3:
			r = o1 / o2;
			break;
		}
		
		return r;
	}
}
