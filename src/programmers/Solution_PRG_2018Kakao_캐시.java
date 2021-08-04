package programmers;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class Solution_PRG_2018Kakao_캐시 {
	public static void main(String[] args) {
		Solution_PRG_2018Kakao_캐시 sol = new Solution_PRG_2018Kakao_캐시();

//    int cacheSize = 3;
//    String[] cities = {"Jeju", "Pangyo", "Seoul", "NewYork", "LA", "Jeju", "Pangyo", "Seoul", "NewYork", "LA"};
		int cacheSize = 2;
		String[] cities = {"Jeju", "Pangyo", "NewYork", "newyork"};

		System.out.println(sol.solution(cacheSize, cities));
	}

	public int solution(int cacheSize, String[] cities) {
		int answer = 0;

		HashMap<String, Integer> map = new HashMap<>();
		for (String cityOrigin : cities) {
			String city = cityOrigin.toLowerCase();
			if (map.containsKey(city)) {
				map.compute(city, (k, v) -> 0);
				answer += 1;

			} else {
				map.put(city, 0);
				answer += 5;

				if (map.size() > cacheSize) {
					String maxKey = map.entrySet().stream().max(Comparator.comparingInt(Map.Entry::getValue)).get().getKey();
					map.remove(maxKey);
				}
			}

			for (Map.Entry<String, Integer> next : map.entrySet()) {
				next.setValue(next.getValue() + 1);
			}
		}

		return answer;
	}
}
