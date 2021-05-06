package programmers;

import java.util.ArrayList;

class Solution_PRG_2020KakaoIntern_수식최대화 {
	static long answer;
	static ArrayList<Long> operands;
	static ArrayList<Character> operators;
	static char[] operator = { '+', '-', '*' };

	public long solution(String expression) {
		operands = new ArrayList<>();
		operators = new ArrayList<>();

		StringBuilder operandSb = new StringBuilder();
		for (int i = 0; i < expression.length(); i++) {
			char c = expression.charAt(i);
			if (c == operator[0] || c == operator[1] || c == operator[2]) {
				operands.add(Long.parseLong(operandSb.toString()));
				operators.add(c);
				operandSb = new StringBuilder();
			} else {
				operandSb.append(c);
			}
		}
		operands.add(Long.parseLong(operandSb.toString()));
		
		orderComb(0, new boolean[3], new int[3]);

		return answer;
	}

	void orderComb(int cnt, boolean[] visited, int[] res) {
		if (cnt == 3) {
			// System.out.println(Arrays.toString(res));
			calcByOrder(res);
			return;
		}

		for (int i = 0; i < 3; i++) {
			if (visited[i])
				continue;
			visited[i] = true;
			res[cnt] = i;
			orderComb(cnt + 1, visited, res);
			visited[i] = false;
		}
	}

	void calcByOrder(int[] order) {
		ArrayList<Long> tmpOperands = new ArrayList<>();
		for (long i : operands)
			tmpOperands.add(i);
		ArrayList<Character> tmpOperators = new ArrayList<>();
		for (char c : operators)
			tmpOperators.add(c);

		for (int i = 0; i < 3; i++) {
			char c = operator[order[i]];
			int j = 0;
			while (tmpOperators.size() > j) {
				if (tmpOperators.get(j) == c) {
					tmpOperators.remove(j);
					long o1 = tmpOperands.remove(j);
					long o2 = tmpOperands.remove(j);
					long result = calc(o1, c, o2);
					tmpOperands.add(j, result);
				} else
					j++;
			}
		}

		answer = Math.max(answer, Math.abs(tmpOperands.get(0)));
	}

	long calc(long o1, char operator, long o2) {
		if (operator == '+') {
			return o1 + o2;
		} else if (operator == '-') {
			return o1 - o2;
		} else if (operator == '*') {
			return o1 * o2;
		} else
			return 0;
	}
	
	public static void main(String[] args) {
		Solution_PRG_2020KakaoIntern_수식최대화 s = new Solution_PRG_2020KakaoIntern_수식최대화();
		String expression = "100-200*300-500+20";
		System.out.println(s.solution(expression));
	}
}