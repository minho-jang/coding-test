package minho_jang.coding_test;
/**
 * 프로그래머스 코딩 테스트 연습
 * 2021.01.11.
 * <p>
 * LEVEL 2
 * 가장 큰 수
 * 출처: https://programmers.co.kr/learn/courses/30/lessons/42746
 *
 * @author minho
 * <p>
 * TODO not solved.
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Solution23 {
	public String solution(int[] numbers) {
		StringBuffer answer = new StringBuffer();

		ArrayList<String> numberStringList = new ArrayList<>();
		for (int i : numbers) {
			numberStringList.add(Integer.toString(i));
		}

		// Collections.sort(numberStringList, Collections.reverseOrder());
		Collections.sort(numberStringList, new Comparator<String>() {
			@Override
			public int compare(String a, String b) {
				if (a.length() < b.length()) {
					return 1;
				} else if (a.length() > b.length()) {
					return -1;
				} else {
					return a.compareTo(b);
				}
			}
		});

		for (int i = numberStringList.size() - 1; i >= 0; i--) {
			String s = numberStringList.get(i);
			answer.append(s);
			System.out.println(s);
		}

		return answer.toString();
	}
}