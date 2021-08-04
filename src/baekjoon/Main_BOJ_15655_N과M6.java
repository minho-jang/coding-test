package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_BOJ_15655_N과M6 {
	private static int N;
	private static int M;
	private static int[] arr;
	private static StringBuilder answer;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk;

		stk = new StringTokenizer(br.readLine());
		N = Integer.parseInt(stk.nextToken());
		M = Integer.parseInt(stk.nextToken());

		answer = new StringBuilder();
		arr = new int[N];
		stk = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(stk.nextToken());
		}

		Arrays.sort(arr);

		comb(0, 0, new int[M]);

		System.out.println(answer.toString());
	}

	private static void comb(int cnt, int start, int[] res) {
		if (cnt == M) {
			for (int i = 0; i < M; i++) {
				answer.append(res[i]).append(" ");
			}
			answer.append("\n");
			return;
		}

		for (int i = start; i < N; i++) {
			res[cnt] = arr[i];
			comb(cnt + 1, i + 1, res);
		}

	}
}
