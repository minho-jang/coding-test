package programmers;

import java.util.Stack;

class Solution_PRG_2020Kakao_괄호변환 {
	public String solution(String p) {
		String answer = convert(p);

		return answer;
	}

	String convert(String str) {
		if ("".equals(str))
			return "";

		// u, v 분리
		StringBuilder tmpU = new StringBuilder();
		int cnt = 0;
		int i = 0;
		for (i = 0; i < str.length(); i++) {
			char c = str.charAt(i);
			if (c == '(')
				cnt++;
			else if (c == ')')
				cnt--;
			tmpU.append(c);

			if (cnt == 0)
				break;
		}
		String u = tmpU.toString();
		String v = str.substring(i + 1);

		if (isGood(u)) {
			return u + convert(v);

		} else {
			StringBuilder result = new StringBuilder();

			result.append('(');
			result.append(convert(v));
			result.append(')');

			u = u.substring(1, u.length() - 1);
			u = reverse(u);
			result.append(u);
			return result.toString();
		}
	}

	// str이 "올바른 괄호 문자열" 인가
	boolean isGood(String str) {
		Stack<Integer> stack = new Stack<>();
		for (int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);
			if (c == '(')
				stack.push(0);
			else if (stack.isEmpty() && c == ')')
				return false;
			else if (c == ')')
				stack.pop();
		}
		return stack.isEmpty();
	}

	// 괄호 방향을 뒤집은 문자열 반환
	String reverse(String str) {
		StringBuilder result = new StringBuilder();
		for (int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);
			if (c == '(')
				result.append(')');
			else if (c == ')')
				result.append('(');
		}
		return result.toString();
	}
}