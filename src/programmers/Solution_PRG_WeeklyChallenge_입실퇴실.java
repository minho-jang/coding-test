package programmers;

import java.util.Arrays;

public class Solution_PRG_WeeklyChallenge_입실퇴실 {
	public int[] solution(int[] enter, int[] leave) {
		int N = enter.length;
		boolean[] isIn = new boolean[N + 1];
		int[] answer = new int[N];

		int enterIdx = 0;
		int leaveIdx = 0;
		while (leaveIdx < N) {
			if (isIn[leave[leaveIdx]]) {
				isIn[leave[leaveIdx++]] = false;

			} else {
				for (int i = 1; i < isIn.length; i++) {
					if (isIn[i]) {
						answer[i - 1]++;
						answer[enter[enterIdx] - 1]++;
					}
				}
				isIn[enter[enterIdx++]] = true;
			}
		}

		return answer;
	}

	public static void main(String[] args) {
		Solution_PRG_WeeklyChallenge_입실퇴실 sol = new Solution_PRG_WeeklyChallenge_입실퇴실();

		int[] enter = {1, 3, 2};
		int[] leave = {1, 2, 3};

		System.out.println(Arrays.toString(sol.solution(enter, leave)));
	}
}
