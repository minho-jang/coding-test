package programmers;

import java.util.Arrays;
import java.util.HashMap;

public class Solution_PRG_2020KakaoIntern_보석쇼핑 {
	public static void main(String[] args) {
		Solution_PRG_2020KakaoIntern_보석쇼핑 s = new Solution_PRG_2020KakaoIntern_보석쇼핑();
//		String[] gems = {"DIA", "RUBY", "RUBY", "DIA", "DIA", "EMERALD", "SAPPHIRE", "DIA"};
//		String[] gems = {"XYZ", "XYZ", "XYZ"};
//		String[] gems = {"AA", "AB", "AC", "AA", "AC"};
//		String[] gems = {"ZZZ", "YYY", "NNNN", "YYY", "BBB"};
//		String[] gems = {"A", "B", "A", "C", "A"};
		String[] gems = {"A", "B", "B", "B", "C", "C", "A", "A", "B", "C"};
		System.out.println(Arrays.toString(s.solution(gems)));
	}

	public int[] solution(String[] gems) {
		int[] answer = new int[2];

		HashMap<String, Integer> gemMap = new HashMap<>();
		int len = gems.length;
		int gemIdx = 0;
		for (int i = 0; i < len; i++) {
			String gem = gems[i];
			if (!gemMap.containsKey(gem))
				gemMap.put(gem, gemIdx++);
		}

		int N = gemMap.size();

		int start = 0;
		int end = N - 1;

		int minLen = len + 1;
		while (end < len) {
			int cnt = 0;
			int[] visited = new int[N];
			for (int i = start; i <= end; i++) {
				int gi = gemMap.get(gems[i]);
				if (visited[gi] == 0)
					cnt++;
				visited[gi]++;
			}

			while (cnt != N) {
				end++;
				if (end >= len)
					break;

				int gi = gemMap.get(gems[end]);
				if (visited[gi] == 0)
					cnt++;
				visited[gi]++;
			}
			if (cnt != N)
				break;

			while (cnt == N) {
				int gi = gemMap.get(gems[start]);
				if (visited[gi] == 1)
					break;
				visited[gi]--;
				start++;
			}

			if (minLen > end - start + 1) {
				minLen = end - start + 1;
				answer[0] = start + 1;
				answer[1] = end + 1;
			}

			visited[gemMap.get(gems[start])]--;
			cnt--;
			start = start + 1;
			end = start + N - 1;
		}

		return answer;
	}
}
