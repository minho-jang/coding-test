package programmers;

import java.util.Arrays;

public class Solution_PRG_입국심사 {
	public static void main(String[] args) {
		Solution_PRG_입국심사 sol = new Solution_PRG_입국심사();

		int n = 6;
		int[] times = {7, 10};
		System.out.println(sol.solution(n, times));
	}

	public long solution(int n, int[] times) {
		long answer = 0;

		Arrays.sort(times);

		long min = 1;
		long max = (long) times[times.length - 1] * n;

		while (max - min >= 0) {
			long mid = (min + max) / 2;

			long sum = 0;
			for (int time : times)
				sum += (mid / time);

			if (sum >= n) {
				answer = mid;
				max = mid - 1;
			} else
				min = mid + 1;
		}

		return answer;
	}
}
