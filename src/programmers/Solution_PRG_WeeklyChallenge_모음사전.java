package programmers;

import java.util.*;
import java.util.stream.*;

public class Solution_PRG_WeeklyChallenge_모음사전 {
	String[] letters = {"A", "E", "I", "O", "U", ""};
	Set<String> set;

	public int solution(String word) {
		set = new HashSet<>();
		combination("", 0);

		List<String> dict = set.stream().sorted().collect(Collectors.toList());

		return IntStream.range(0, dict.size())
				.filter(i -> word.equals(dict.get(i)))
				.findFirst()
				.orElse(0) + 1;
	}

	private void combination(String str, int size) {
		if (size == 5) {
			if (str.length() > 0) {
				set.add(str);
			}
			return;
		}

		for (String letter : letters) {
			combination(str + letter, size + 1);
		}
	}

	public static void main(String[] args) {
		Solution_PRG_WeeklyChallenge_모음사전 sol = new Solution_PRG_WeeklyChallenge_모음사전();

		String word = "EIO";
		System.out.println(sol.solution(word));
	}
}
