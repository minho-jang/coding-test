package programmers;

public class Solution_PRG_N으로표현 {
	final int MAX = 100000;
	int N, number, answer;
	int[] initNumbers;

	public static void main(String[] args) {
		Solution_PRG_N으로표현 sol = new Solution_PRG_N으로표현();

		int N = 4;
		int number = 17;
		System.out.println(sol.solution(N, number));
	}

	public int solution(int N, int number) {
		this.N = N;
		this.number = number;
		this.initNumbers = new int[5];

		int multiple = 0;
		for (int i = 0; i < 5; i++) {
			initNumbers[i] = multiple = multiple * 10 + N;
		}

		answer = MAX;
		dfs(0, 0);
		if (answer == MAX)
			return -1;
		else
			return answer;
	}

	private void dfs(int num, int cnt) {
		if (cnt > 8)
			return;
		if (num == number) {
			answer = Math.min(answer, cnt);
			return;
		}

		for (int i = 0; i < 5; i++) {
			dfs(num + initNumbers[i], cnt + i + 1);
			dfs(num - initNumbers[i], cnt + i + 1);
			dfs(num / initNumbers[i], cnt + i + 1);
			dfs(num * initNumbers[i], cnt + i + 1);
		}
	}
}
