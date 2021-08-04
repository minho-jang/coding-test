package programmers;

import java.util.*;

class Solution_PRG_2021Kakao_순위검색 {
	public static void main(String[] args) {
		Solution_PRG_2021Kakao_순위검색 s = new Solution_PRG_2021Kakao_순위검색();
//		String[] info = {
//			"java backend junior pizza 150",
//			"python frontend senior chicken 210",
//			"python frontend senior chicken 150",
//			"cpp backend senior pizza 260",
//			"java backend junior chicken 80",
//			"python backend senior chicken 50"
//		};
//		String[] query = {
//			"java and backend and junior and pizza 100",
//			"python and frontend and senior and chicken 200",
//			"cpp and - and senior and pizza 250",
//			"- and backend and senior and - 150",
//			"- and - and - and chicken 100",
//			"- and - and - and - 150"
//		};

		String[] info = {
				"cpp backend senior pizza 260",
				"cpp backend senior pizza 260",
				"cpp backend senior pizza 260",
				"java backend junior chicken 80",
				"python backend senior chicken 50"
		};
		String[] query = {
				"java and backend and junior and pizza 100",
				"python and frontend and senior and chicken 200",
				"cpp and - and senior and pizza 250",
				"- and backend and senior and - 150"
		};

		int[] ans = s.solution(info, query);
		System.out.println(Arrays.toString(ans));
	}

	public int[] solution(String[] info, String[] query) {
		int[] answer = new int[query.length];

		StringTokenizer stk;
		HashMap<String, List<Integer>> map = new HashMap<>();
		for (String i : info) {
			stk = new StringTokenizer(i);
			String[] splitted = i.split(" ");
			int score = Integer.parseInt(splitted[4]);

			for (int j = 0; j < 16; j++) {    // 비트마스크
				StringBuilder key = new StringBuilder();
				for (int k = 0; k < 4; k++) {    // 4자리
					if (((j << k) & 8) == 8) {
						key.append(toIntCode(splitted[k], k));
					} else {
						key.append(toIntCode("-", k));
					}
				}

				if (map.containsKey(key.toString())) {
					map.get(key.toString()).add(score);
				} else {
					List<Integer> scoreList = new ArrayList<>();
					scoreList.add(score);
					map.put(key.toString(), scoreList);
				}
			}
		}

		map.forEach((key, value) -> {
			Collections.sort(value);

			System.out.print(key.toString() + " : ");
			for (int i : value) {
				System.out.print(i + " ");
			}
			System.out.println();
		});
		System.out.println();

		int qIdx = 0;
		for (String q : query) {
			stk = new StringTokenizer(q);
			StringBuilder key = new StringBuilder();
			key.append(toIntCode(stk.nextToken(), 0));
			stk.nextToken();
			key.append(toIntCode(stk.nextToken(), 1));
			stk.nextToken();
			key.append(toIntCode(stk.nextToken(), 2));
			stk.nextToken();
			key.append(toIntCode(stk.nextToken(), 3));

			int score = Integer.parseInt(stk.nextToken());

			System.out.println(key.toString() + " " + score);
			int count = 0;
			List<Integer> tmp = map.get(key.toString());
			if (tmp != null) {
				// 이진탐색
				int size = tmp.size();
				int start = 0, end = size, mid = size / 2;
				do {
					mid = start + ((end - start) / 2);
					if (tmp.get(mid) >= score) {
						end = mid;

					} else if (tmp.get(mid) < score) {
						if (start == mid)
							start = end;
						else
							start = mid;

					}
				} while (end - start > 0);
				count = size - end;
			}

			answer[qIdx++] = count;
		}

		return answer;
	}

	private int toIntCode(String s, int i) {
		if (s.equals("-"))
			return 0;

		switch (i) {
			case 0:    // 언어
				if (s.equals("java")) return 1;
				else if (s.equals("python")) return 2;
				else if (s.equals("cpp")) return 3;
				break;

			case 1: // 직무
				if (s.equals("backend")) return 1;
				else if (s.equals("frontend")) return 2;
				break;

			case 2:    // 연령
				if (s.equals("junior")) return 1;
				else if (s.equals("senior")) return 2;
				break;

			case 3:    // 음식
				if (s.equals("pizza")) return 1;
				else if (s.equals("chicken")) return 2;
				break;
		}

		return 0;
	}
}