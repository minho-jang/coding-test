package programmers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Solution_PRG_2017KakaoCode_브라이언의고민 {
	List<String> answerList = new ArrayList<>();

	public String solution(String sentence) {
		answerList.clear();
		boolean[] visited = new boolean[26];

		if (hasBlank(sentence))
			return "invalid";

		boolean[] finish = new boolean[sentence.length()];
		int cursor = 0;
		StringBuilder sb = new StringBuilder();

		while (cursor < sentence.length()) {
			if (isLower(sentence.charAt(cursor))) {
				char specialChar = sentence.charAt(cursor);
				if (visited[specialChar - 'a'])
					return "invalid";
				visited[specialChar - 'a'] = true;

				int nextCursor = hasTwoSpecialChar(sentence, specialChar, cursor);
				if (nextCursor > 0) {
					addToAnswerList(sb.toString());

					String extracted = extractStringByRuleTwo(sentence.substring(cursor, nextCursor + 1));
					if (extracted.isEmpty() || extracted.equals("invalid"))
						return "invalid";

					if (extracted.length() >= 2 && isLower(extracted.charAt(1))) {
						if (visited[extracted.charAt(1) - 'a'])
							return "invalid";
						visited[extracted.charAt(1) - 'a'] = true;

						String reExtracted = extractStringByRuleOne(extracted);
						if (reExtracted.isEmpty() || reExtracted.equals("invalid"))
							return "invalid";

						addToAnswerList(reExtracted);
					} else if (!isAllUpperCase(extracted)) {
						return "invalid";
					} else {
						addToAnswerList(extracted);
					}

					for (int i = cursor; i <= nextCursor; i++)
						finish[i] = true;

				} else {
					if (sb.length() >= 1) {
						addToAnswerList(sb.substring(0, sb.length() - 1));
						finish[cursor - 1] = false;
					}

					nextCursor = getLastCharByRuleOne(sentence, specialChar, cursor);
					if (cursor == 0 || finish[cursor - 1] || nextCursor >= sentence.length())
						return "invalid";

					String extracted = extractStringByRuleOne(sentence.substring(cursor - 1, nextCursor + 1));
					if (extracted.isEmpty() || extracted.equals("invalid"))
						return "invalid";

					if (isLower(extracted.charAt(0))) {
						if (visited[extracted.charAt(0) - 'a'])
							return "invalid";
						visited[extracted.charAt(0) - 'a'] = true;

						String reExtracted = extractStringByRuleTwo(extracted);
						if (reExtracted.isEmpty() || reExtracted.equals("invalid"))
							return "invalid";

						addToAnswerList(reExtracted);
					} else {
						addToAnswerList(extracted);
					}

					for (int i = cursor - 1; i <= nextCursor; i++)
						finish[i] = true;
				}

				sb = new StringBuilder();
				cursor = nextCursor + 1;

			} else {
				finish[cursor] = true;
				sb.append(sentence.charAt(cursor++));
			}
		}

		addToAnswerList(sb.toString());
		return answerList.stream()
				.filter(str -> str.length() > 0)
				.collect(Collectors.joining(" "))
				.strip();
	}

	private boolean hasBlank(String sentence) {
		for (char c : sentence.toCharArray())
			if (c == ' ')
				return true;
		return false;
	}

	private void addToAnswerList(String str) {
		if (isAllUpperCase(str))
			answerList.add(str);
	}

	private boolean isAllUpperCase(String str) {
		for (char c : str.toCharArray())
			if ('a' <= c && c <= 'z')
				return false;

		return true;
	}

	private int getLastCharByRuleOne(String sentence, char specialChar, int cursor) {
		int lastIndexSpecialChar = 0;
		for (int i = cursor; i < sentence.length(); i++) {
			if (sentence.charAt(i) == specialChar) {
				lastIndexSpecialChar = i;
			}
		}

		return lastIndexSpecialChar + 1;
	}

	private String extractStringByRuleOne(String str) {
		char specialChar = str.charAt(1);
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < str.length(); i++)
			if (i % 2 == 0)
				sb.append(str.charAt(i));
			else if (!isLower(str.charAt(i)) || str.charAt(i) != specialChar)
				return "invalid";

		return sb.toString();
	}

	private String extractStringByRuleTwo(String str) {
		char first = str.charAt(0);
		char last = str.charAt(str.length() - 1);
		if (!isLower(first) || !isLower(last) || first != last)
			return "invalid";

		return str.substring(1, str.length() - 1);
	}

	private int hasTwoSpecialChar(String sentence, char specialChar, int cursor) {
		int lastIndexSpecialChar = 0;
		int count = 0;
		for (int i = cursor; i < sentence.length(); i++) {
			if (sentence.charAt(i) == specialChar) {
				lastIndexSpecialChar = i;
				count++;
			}
		}

		if (count == 2)
			return lastIndexSpecialChar;
		return -1;
	}

	private boolean isLower(char c) {
		return 'a' <= c && c <= 'z';
	}

	public static void main(String[] args) {
		Solution_PRG_2017KakaoCode_브라이언의고민 sol = new Solution_PRG_2017KakaoCode_브라이언의고민();

		System.out.println(sol.solution("HaEaLaLaObWORLDb").equals("HELLO WORLD"));
		System.out.println(sol.solution("SpIpGpOpNpGJqOqA").equals("SIGONG J O A"));
		System.out.println(sol.solution("AxAxAxAoBoBoB").equals("invalid"));
		System.out.println(sol.solution("aIaAM").equals("I AM"));
		System.out.println(sol.solution("AAAaBaAbBBBBbCcBdBdBdBcCeBfBeGgGGjGjGRvRvRvRvRvR")
				.equals("AAA B A BBBB C BBBB C BB GG G G G RRRRRR"));
		System.out.println(sol.solution("aaA").equals("invalid"));
		System.out.println(sol.solution("Aaa").equals("invalid"));
		System.out.println(sol.solution("HaEaLaLaOWaOaRaLaD").equals("invalid"));
		System.out.println(sol.solution("aHELLOWORLDa").equals("HELLOWORLD"));
		System.out.println(sol.solution("HaEaLaLObWORLDb").equals("HELL O WORLD"));
		System.out.println(sol.solution("HaEaLaLaObWORLDb").equals("HELLO WORLD"));
		System.out.println(sol.solution("aHbEbLbLbOacWdOdRdLdDc").equals("HELLO WORLD"));
		System.out.println(sol.solution("abAba").equals("invalid"));
		System.out.println(sol.solution("HELLO WORLD").equals("invalid"));
		System.out.println(sol.solution("xAaAbAaAx").equals("invalid"));
		System.out.println(sol.solution("AbAaAbAaCa").equals("invalid"));
		System.out.println(sol.solution("AbAaAbAaC").equals("invalid"));
		System.out.println(sol.solution("aHbEbLbLbOacWdOdRdLdDcaHbEbLbLbOacWdOdRdLdDc").equals("invalid"));
		System.out.println(sol.solution("aaaaa").equals("invalid"));
		System.out.println(sol.solution("A").equals("A"));
		System.out.println(sol.solution("aAa").equals("A"));
		System.out.println(sol.solution("a").equals("invalid"));
		System.out.println(sol.solution("aa").equals("invalid"));
		System.out.println(sol.solution("aaa").equals("invalid"));
		System.out.println(sol.solution("aba").equals("invalid"));
	}
}