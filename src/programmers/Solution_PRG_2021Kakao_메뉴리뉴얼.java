package programmers;

import java.util.*;

public class Solution_PRG_2021Kakao_메뉴리뉴얼 {
	public static void main(String[] args) {
		Solution_PRG_2021Kakao_메뉴리뉴얼 sol = new Solution_PRG_2021Kakao_메뉴리뉴얼();

//		String[] orders = {"ABCFG", "AC", "CDE", "ACDE", "BCFG", "ACDEH"};
//		int[] course = {2, 3, 4};
//		String[] orders = {"ABCDE", "AB", "CD", "ADE", "XYZ", "XYZ", "ACD"};
//		int[] course = {2, 3, 5};
		String[] orders = {"XYZ", "XWY", "WXA"};
		int[] course = {2, 3, 4};
		System.out.println(Arrays.toString(sol.solution(orders, course)));
	}

	HashMap<String, Integer> map;

	public String[] solution(String[] orders, int[] course) {
		List<String> answerList = new ArrayList<>();

		for (int num : course) {
			map = new HashMap<>();
			for (String order : orders) {
				menuCombination(order, num);
			}
			answerList.addAll(orderMany());
		}

		Collections.sort(answerList);
		return answerList.toArray(String[]::new);
	}

	private List<String> orderMany() {
		int max = 2;
		List<String> result = new ArrayList<>();

		for (Map.Entry<String, Integer> entry : map.entrySet()) {
			if (entry.getValue() > max) {
				max = entry.getValue();
				result.clear();
				result.add(entry.getKey());
			} else if (entry.getValue() == max) {
				result.add(entry.getKey());
			}
		}

		return result;
	}

	private void menuCombination(String order, int num) {
		char[] orderToCharArr = order.toCharArray();
		Arrays.sort(orderToCharArr);
		comb(orderToCharArr, num, 0, 0, new char[num]);
	}

	private void comb(char[] arr, int num, int start, int count, char[] res) {
		if (count == num) {
			String result = new String(res);

			if (map.containsKey(result)) {
				map.computeIfPresent(result, (k, v) -> v + 1);
			} else {
				map.put(result, 1);
			}
			return;
		}

		for (int i = start; i < arr.length; i++) {
			res[count] = arr[i];
			comb(arr, num, i + 1, count + 1, res);
		}
	}
}


/*

static ArrayList<CombinationCount> combinationSet;

static void makeCombination(int cnt, int start, char[] arr, int R, char[] res, int guestNum) {
	if (cnt == R) {
		// System.out.println(Arrays.toString(res));

		for (CombinationCount cc : combinationSet) {
			if (cc.comb.equals(new String(res))) {
				cc.count++;
				return;
			}
		}

		combinationSet.add(new CombinationCount(new String(res)));

		return;
	}

	for (int i = start; i < arr.length; i++) {
		res[cnt] = arr[i];
		makeCombination(cnt + 1, i + 1, arr, R, res, guestNum);
	}
}

public String[] solution(String[] orders, int[] course) {
	String[] answer = {};
	ArrayList<String> answerAL = new ArrayList<>();

	char[][] ordersChar = new char[orders.length][];
	for (int i = 0; i < orders.length; i++) {
		ordersChar[i] = orders[i].toCharArray();
		Arrays.sort(ordersChar[i]);
	}

	for (int menuNum : course) {
		combinationSet = new ArrayList<>();

		for (int i = 0; i < ordersChar.length; i++) {
			if (ordersChar[i].length >= menuNum)
				makeCombination(0, 0, ordersChar[i], menuNum, new char[menuNum], i);
		}

		Collections.sort(combinationSet, (o1, o2) -> {
			if (o1.count == o2.count)
				return o1.comb.compareTo(o2.comb);
			else
				return o2.count - o1.count;
		});

		if (combinationSet.isEmpty()) continue;

		int max = combinationSet.get(0).count;
		for (CombinationCount cc : combinationSet) {
			if (max < 2) break;
			if (cc.count < max) break;
			answerAL.add(cc.comb);
		}
	}

	Collections.sort(answerAL);

	answer = new String[answerAL.size()];
	for (int i = 0; i < answerAL.size(); i++) {
		answer[i] = answerAL.get(i);
	}
	return answer;
}

static class CombinationCount {
	String comb;
	int count;

	CombinationCount(String comb) {
		this.comb = comb;
		this.count = 1;
	}

	public String toString() {
		return comb + "(" + count + ")";
	}
}

 */