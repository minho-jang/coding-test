package programmers;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashSet;

class Solution_PRG_2019KakaoWinterIntern_튜플 {
	static final int MAX_LENGTH = 500;

	public static void main(String[] args) {
		Solution_PRG_2019KakaoWinterIntern_튜플 sol = new Solution_PRG_2019KakaoWinterIntern_튜플();

		// String s = "{{2},{2,1},{2,1,3},{2,1,3,4}}";	// [2,1,3,4]
		String s = "{{4,2,3},{3},{2,3,4,1},{2,3}}"; // [3,2,4,1]

		System.out.println(Arrays.toString(sol.solution(s)));
	}

	public int[] solution(String s) {
		// 양끝 중괄호 제거
		s = s.substring(1, s.length() - 1);

		// 원소 개수 별로 집합이 존재한다.
		String[] setByCount = new String[MAX_LENGTH + 1];

		// 문자열을 탐색하면서,
		// 원소가 1개인 것은 setByCount[1]에, 원소가 2개인 것은 setByCount[2]에 저장한다.
		int strCursor = 0;
		int lengthOfSet = 0;

		while (strCursor < s.length()) {
			char c = s.charAt(strCursor);

			if (c == '{') {
				StringBuilder sb = new StringBuilder();
				int elementCount = 1;
				while (true) {
					c = s.charAt(++strCursor);
					if (c == '}')
						break;
					if (c == ',')
						elementCount++; // 콤마 개수가 집합 속 숫자 개수
					sb.append(c);
				}
				setByCount[elementCount] = sb.toString();
				lengthOfSet++;
			}

			strCursor++;
		}

		// setByCount[]를 1부터 반복하면서
		// 숫자들을 linkedSet에 삽입한다. 순서는 유지하면서 중복은 사라진다.
		LinkedHashSet<Integer> linkedSet = new LinkedHashSet<>();
		for (int i = 1; i <= lengthOfSet; i++) {
			String[] splitted = setByCount[i].split(",");
			for (String spl : splitted) {
				linkedSet.add(Integer.parseInt(spl));
			}
		}

		// linkedSet을 answer[]로 옮긴다.
		int answerIdx = 0;
		int[] answer = new int[lengthOfSet];
		Iterator<Integer> it = linkedSet.iterator();
		while (it.hasNext())
			answer[answerIdx++] = it.next();

		return answer;
	}
}