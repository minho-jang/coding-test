package programmers;

import java.util.HashMap;
import java.util.Map;

public class Solution_PRG_WeeklyChallenge_직업군추천하기 {
	public String solution(String[] table, String[] languages, int[] preference) {
		String maxJob = "";
		int maxScore = 0;

		for (int i = 0; i < 5; i++) {
			String[] split = table[i].split(" ");
			String jobName = split[0];
			Map<String, Integer> map = getMapLanguageScore(split);
			int score = getScore(map, languages, preference);

			if (score > maxScore) {
				maxScore = score;
				maxJob = jobName;
			} else if (score == maxScore && maxJob.compareTo(jobName) > 0) {
				maxJob = jobName;
			}
		}

		return maxJob;
	}

	private Map<String, Integer> getMapLanguageScore(String[] arr) {
		Map<String, Integer> map = new HashMap<>();
		for (int i = 1; i < 6; i++) {
			map.put(arr[i], 5 - i + 1);
		}
		return map;
	}

	private int getScore(Map<String, Integer> map, String[] languages, int[] preference) {
		int score = 0;
		for (int i = 0; i < languages.length; i++) {
			String lang = languages[i];
			if (map.containsKey(lang)) {
				score += map.get(lang) * preference[i];
			}
		}

		return score;
	}

	public static void main(String[] args) {
		Solution_PRG_WeeklyChallenge_직업군추천하기 sol = new Solution_PRG_WeeklyChallenge_직업군추천하기();

//		String[] table = {
//				"SI JAVA JAVASCRIPT SQL PYTHON C#",
//				"CONTENTS JAVASCRIPT JAVA PYTHON SQL C++",
//				"HARDWARE C C++ PYTHON JAVA JAVASCRIPT",
//				"PORTAL JAVA JAVASCRIPT PYTHON KOTLIN PHP",
//				"GAME C++ C# JAVASCRIPT C JAVA"
//		};
//		String[] languages = {"PYTHON", "C++", "SQL"};
//		int[] preference = {7, 5, 5};
		// return HARDWARE

		String[] table = {
				"SI JAVA JAVASCRIPT SQL PYTHON C#",
				"CONTENTS JAVASCRIPT JAVA PYTHON SQL C++",
				"HARDWARE C C++ PYTHON JAVA JAVASCRIPT",
				"PORTAL JAVA JAVASCRIPT PYTHON KOTLIN PHP",
				"GAME C++ C# JAVASCRIPT C JAVA"
		};
		String[] languages = {"JAVA", "JAVASCRIPT"};
		int[] preference = {7, 5};
		// return PORTAL

		System.out.println(sol.solution(table, languages, preference));
	}
}
