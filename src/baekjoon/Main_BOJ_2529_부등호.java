package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;

// 22:32
class Main_BOJ_2529_부등호 {

	static String[] conditionArr;
	static long min, max;
	static int k;

	public static void main(String args[]) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		k = Integer.parseInt(br.readLine());
		conditionArr = br.readLine().split(" ");

		min = 98765432100L;
		max = 0;
		perm(0, new boolean[10], new int[k + 1]);

		String maxStr = String.format("%010d", max).substring(10 - k - 1);
		String minStr = String.format("%010d", min).substring(10 - k - 1);
		System.out.println(maxStr);
		System.out.println(minStr);

	}

	static void perm(int cnt, boolean[] visited, int[] res) {
		if (cnt == k + 1) {
			if (isGood(res)) {
				long result = arrToLong(res);

				if (max < result) max = result;
				if (min > result) min = result;
			}

			return;
		}

		for (int i = 0; i < 10; i++) {
			if (visited[i])
				continue;
			visited[i] = true;
			res[cnt] = i;
			perm(cnt + 1, visited, res);
			visited[i] = false;
		}
	}

	static boolean isGood(int[] arr) {

		for (int i = 0; i < k; i++) {
			char condition = conditionArr[i].charAt(0);
			if (condition == '>') {
				if (arr[i] <= arr[i + 1])
					return false;

			} else if (condition == '<') {
				if (arr[i] >= arr[i + 1])
					return false;

			}
		}

		return true;
	}

	static long arrToLong(int[] arr) {
		StringBuilder resultSb = new StringBuilder();
		for (int i : arr)
			resultSb.append(i);
		return Long.parseLong(resultSb.toString());
	}
}