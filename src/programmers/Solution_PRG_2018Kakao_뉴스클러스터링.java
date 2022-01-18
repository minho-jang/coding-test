package programmers;

import java.util.ArrayList;
import java.util.List;

public class Solution_PRG_2018Kakao_뉴스클러스터링 {
	public int solution(String str1, String str2) {
		str1 = str1.toLowerCase();
		str2 = str2.toLowerCase();

		List<String> str1WordSet = convertStrToWordSet(str1);
		List<String> str2WordSet = convertStrToWordSet(str2);

		boolean[] used1 = new boolean[str1WordSet.size()];
		boolean[] used2 = new boolean[str2WordSet.size()];

		List<String> intersection = new ArrayList<>();
		for (int i = 0; i < str1WordSet.size(); i++) {
			if (used1[i]) continue;
			String s1 = str1WordSet.get(i);

			for (int j = 0; j < str2WordSet.size(); j++) {
				if (used2[j]) continue;
				String s2 = str2WordSet.get(j);

				if (s1.equals(s2)) {
					used1[i] = used2[j] = true;
					intersection.add(s1);
					break;
				}
			}
		}

		List<String> union = new ArrayList<>(intersection);
		for (int i = 0; i < str1WordSet.size(); i++) {
			if (!used1[i])
				union.add(str1WordSet.get(i));
		}
		for (int i = 0; i < str2WordSet.size(); i++) {
			if (!used2[i])
				union.add(str2WordSet.get(i));
		}

		if (union.size() == 0)
			return 65536;

		double similarity = (double) (intersection.size()) / union.size();
		return (int) (similarity * 65536);
	}

	private List<String> convertStrToWordSet(String str) {
		List<String> result = new ArrayList<>();

		for (int i = 0; i < str.length() - 1; i++) {
			String slice = str.substring(i, i + 2);
			if (isValid(slice))
				result.add(slice);
		}

		return result;
	}

	private boolean isValid(String str) {
		for (char c : str.toCharArray()) {
			if ('a' > c || 'z' < c)
				return false;
		}
		return true;
	}

	public static void main(String[] args) {
		Solution_PRG_2018Kakao_뉴스클러스터링 sol = new Solution_PRG_2018Kakao_뉴스클러스터링();

//		String str1 = "FRANCE";
//		String str2 = "french";
//		String str1 = "handshake";
//		String str2 = "shake hands";
		String str1 = "E=M*C^2";
		String str2 = "e=m*c^2";
		System.out.println(sol.solution(str1, str2));
	}
}
