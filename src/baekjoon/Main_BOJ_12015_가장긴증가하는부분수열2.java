package baekjoon;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_BOJ_12015_가장긴증가하는부분수열2 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		StringTokenizer stk = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(stk.nextToken());
		}

		int[] memo = new int[N];
		Arrays.fill(memo, Integer.MAX_VALUE);
		memo[0] = arr[0];

		int answer = 1;
		for (int i = 1; i < N; i++) {
			boolean isAddLis = lowerBound(memo, arr[i]);
			if (isAddLis) {
				answer += 1;
			}
		}

		bw.write(answer + "");
		bw.flush();

		br.close();
		bw.close();
	}

	private static boolean lowerBound(int[] memo, int num) {
		int start = 0, end = memo.length - 1;

		while (end - start > 1) {
			int mid = (start + end) / 2;
			if (num > memo[mid]) {
				start = mid;
			} else {
				end = mid;
			}
		}

		int pos = start;
		if (memo[start] < num) {
			pos = start + 1;
		}

		int tmp = memo[pos];
		memo[pos] = num;
		return tmp == Integer.MAX_VALUE;
	}
}
