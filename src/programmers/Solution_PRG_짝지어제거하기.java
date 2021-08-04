package programmers;

import java.util.Stack;

public class Solution_PRG_짝지어제거하기 {

	public static void main(String[] args) {
		Solution_PRG_짝지어제거하기 sol = new Solution_PRG_짝지어제거하기();

		String s = "cdcd";
		System.out.println(sol.solution(s));
	}

	public int solution(String s) {
		int answer = -1;

		Stack<Character> stack = new Stack<>();
		for (int i = 0; i < s.length(); i++) {
			char cur = s.charAt(i);
			if (stack.isEmpty()) {
				stack.push(cur);
				continue;
			}

			char peeked = stack.peek();
			if (peeked == cur)
				stack.pop();
			else
				stack.push(cur);
		}

		if (stack.isEmpty())
			answer = 1;
		else
			answer = 0;

		return answer;
	}

}
