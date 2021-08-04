package programmers;

import java.util.HashMap;

public class Solution_PRG_2021KakaoIntern_숫자문자열과영단어 {
	private HashMap<String, Integer> map;

	public static void main(String[] args) {
		Solution_PRG_2021KakaoIntern_숫자문자열과영단어 sol = new Solution_PRG_2021KakaoIntern_숫자문자열과영단어();
		String s = "one4seveneight";
//    String s = "23four5six7";
//    String s = "2three45sixseven";
//    String s = "123";
		System.out.println(sol.solution(s));
	}

	public int solution(String s) {
		makeMap();

		StringBuilder answerSb = new StringBuilder();
		StringBuilder stackedSb = new StringBuilder();

		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);

			if ('0' <= c && c <= '9')
				answerSb.append(c);
			else {
				stackedSb.append(c);
				if (map.containsKey(stackedSb.toString())) {
					answerSb.append(map.get(stackedSb.toString()));
					stackedSb.setLength(0);
				}
			}
		}

		return Integer.parseInt(answerSb.toString());
	}

	private void makeMap() {
		map = new HashMap<>();
		map.put("zero", 0);
		map.put("one", 1);
		map.put("two", 2);
		map.put("three", 3);
		map.put("four", 4);
		map.put("five", 5);
		map.put("six", 6);
		map.put("seven", 7);
		map.put("eight", 8);
		map.put("nine", 9);
	}
}
