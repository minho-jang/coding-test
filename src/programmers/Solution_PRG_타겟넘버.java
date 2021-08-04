package programmers;

public class Solution_PRG_타겟넘버 {
	static int[] numbers;
	static int answer, N, target;

	private static void dfs(int cnt, int result) {
		if (cnt == N) {
			if (result == target)
				answer++;
			return;
		}

		dfs(cnt + 1, result + numbers[cnt]);
		dfs(cnt + 1, result - numbers[cnt]);
	}

	public int solution(int[] numbers, int target) {
		this.numbers = numbers;
		this.N = numbers.length;
		this.target = target;

		dfs(0, 0);

		return answer;
	}
}

