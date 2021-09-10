package programmers;

class Solution_PRG_2021Kakao_신규아이디추천 {
	public String solution(String new_id) {
		String answer = new_id;

		answer = answer.toLowerCase();
		answer = filterInvalidChar(answer);
		answer = removeRepeatedDot(answer);
		answer = removeEdgeDot(answer);
		answer = addAIfEmpty(answer);
		answer = sliceFifteen(answer);
		answer = repeatIfLengthLessThanTwo(answer);

		return answer;
	}

	private String repeatIfLengthLessThanTwo(String s) {
		StringBuilder sb = new StringBuilder(s);
		if (sb.length() <= 2) {
			while (sb.length() != 3) {
				sb.append(sb.charAt(s.length() - 1));
			}
		}
		return sb.toString();
	}

	private String sliceFifteen(String s) {
		if (s.length() >= 15) {
			s = s.substring(0, 15);
		}
		return removeEdgeDot(s);
	}

	private String addAIfEmpty(String s) {
		if (s.length() == 0) {
			return "a";
		} else {
			return s;
		}
	}

	private String removeEdgeDot(String s) {
		if (s.length() > 0 && s.charAt(0) == '.') {
			s = s.substring(1);
		}
		if (s.length() > 0 && s.charAt(s.length() - 1) == '.') {
			s = s.substring(0, s.length() - 1);
		}

		return s;
	}

	private String removeRepeatedDot(String s) {
		StringBuilder sb = new StringBuilder();
		boolean dotRepeat = false;
		for (char c : s.toCharArray()) {
			if (c == '.') {
				if (!dotRepeat) {
					sb.append(c);
					dotRepeat = true;
				}

			} else {
				sb.append(c);
				dotRepeat = false;
			}
		}
		return sb.toString();
	}

	private String filterInvalidChar(String s) {
		StringBuilder sb = new StringBuilder();
		for (char c : s.toCharArray()) {
			if (isValidCharacter(c)) {
				sb.append(c);
			}
		}
		return sb.toString();
	}

	private boolean isValidCharacter(char c) {
		if ('a' <= c && c <= 'z') {
			return true;
		} else if ('0' <= c && c <= '9') {
			return true;
		} else if (c == '-' || c == '_' || c == '.') {
			return true;
		}

		return false;
	}

	public static void main(String[] args) {
		Solution_PRG_2021Kakao_신규아이디추천 sol = new Solution_PRG_2021Kakao_신규아이디추천();

		String new_id = "abcdefghijklmn.p";
		System.out.println(sol.solution(new_id));
	}
}


/*

class Solution_PRG_2021Kakao_신규아이디추천 {
	public String solution(String new_id) {
		StringBuilder newId = new StringBuilder(new_id);

//        System.out.println(newId.toString());
		// 1단계
		for (int i = 0, size = newId.length(); i < size; i++) {
			char c = newId.charAt(i);
			if (c >= 'A' && c <= 'Z') {
				newId.replace(i, i + 1, Character.toString((char) (c + ('a' - 'A'))));
			}
		}

//        System.out.println(newId.toString());
		// 2단계
		for (int i = 0; i < newId.length(); i++) {
			char c = newId.charAt(i);
			if ((c >= 'a' && c <= 'z') ||
					(c >= '0' && c <= '9') ||
					c == '-' || c == '_' || c == '.') {
				continue;
			} else {
				newId.replace(i, i + 1, "");
				i--;
			}
		}

//        System.out.println(newId.toString());
		// 3단계
		if (newId.length() > 0) {
			char prev = newId.charAt(0);
			for (int i = 1; i < newId.length(); i++) {
				char c = newId.charAt(i);
				if (prev == '.' && c == '.') {
					newId.replace(i, i + 1, "");
					i--;
					continue;
				}
				prev = c;
			}
		}

//        System.out.println(newId.toString());
		// 4단계
		if (newId.length() > 0 && newId.charAt(0) == '.')
			newId.replace(0, 1, "");
		if (newId.length() > 0 && newId.charAt(newId.length() - 1) == '.')
			newId.replace(newId.length() - 1, newId.length(), "");

//        System.out.println(newId.toString());
		// 5단계
		if (newId.length() == 0) {
			newId.append("a");
		}

//        System.out.println(newId.toString());
		// 6단계
		if (newId.length() >= 16) {
			newId = new StringBuilder(newId.substring(0, 15));
			if (newId.charAt(newId.length() - 1) == '.')
				newId.replace(newId.length() - 1, newId.length(), "");
		}

//        System.out.println(newId.toString());
		// 7단계
		if (newId.length() <= 2) {
			char c = newId.charAt(newId.length() - 1);
			while (newId.length() < 3) {
				newId.append(c);
			}
		}

		return newId.toString();
	}
}

 */