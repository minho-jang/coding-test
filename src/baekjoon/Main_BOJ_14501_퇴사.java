package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BOJ_14501_퇴사 {
	static int[][] arr;
	static int N;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk;

		N = Integer.parseInt(br.readLine());
		arr = new int[2][N];
		for (int i = 0; i < N; i++) {
			stk = new StringTokenizer(br.readLine());
			arr[0][i] = Integer.parseInt(stk.nextToken());
			arr[1][i] = Integer.parseInt(stk.nextToken());
		}

		int max = dfs(0, 0);

		System.out.println(max);
	}

	static int dfs(int depth, int T) {
		if (depth == N) {
			if (T > 0)
				return -100000;
			return 0;
		}

		if (T == 0) {
			return Math.max(arr[1][depth] + dfs(depth + 1, arr[0][depth] - 1), dfs(depth + 1, T));
		} else {
			return dfs(depth + 1, T - 1);
		}

	}
}
