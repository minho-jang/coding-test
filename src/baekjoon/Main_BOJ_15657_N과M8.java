package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_BOJ_15657_N과M8 {
	private static int[] arr;
	private static int M, N;
	private static StringBuilder answer;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk;
		answer = new StringBuilder();

		stk = new StringTokenizer(br.readLine());
		N = Integer.parseInt(stk.nextToken());
		M = Integer.parseInt(stk.nextToken());

		stk = new StringTokenizer(br.readLine());
		arr = new int[N];
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(stk.nextToken());
		}

		Arrays.sort(arr);

		perm(1, new int[M + 1]);

		System.out.println(answer.toString());
	}

	private static void perm(int cnt, int[] res) {
		if (cnt == M + 1) {
			for (int i = 1; i <= M; i++) {
				answer.append(res[i]).append(" ");
			}
			answer.append("\n");
			return;
		}

		for (int i = 0; i < N; i++) {
			if (arr[i] >= res[cnt - 1]) {
				res[cnt] = arr[i];
				perm(cnt + 1, res);
			}
		}

	}
}
