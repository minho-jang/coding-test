package programmers;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class Solution_PRG_2021Kakao_광고삽입 {
	public String solution(String play_time, String adv_time, String[] logs) {
		int playTime = strToInt(play_time);
		int advTime = strToInt(adv_time);
		int[] memo = new int[playTime + 1];

		for (String log : logs) {
			StringTokenizer stk = new StringTokenizer(log, "-");
			int start = strToInt(stk.nextToken());
			int end = strToInt(stk.nextToken());

			for (int i = start; i < end; i++) {
				memo[i] += 1;
			}
		}

		long sum = 0;
		for (int i = 0; i < advTime; i++) {
			sum += memo[i];
		}

		long max = sum;
		int maxTime = 0;
		for (int start = 1; start <= playTime - advTime; start++) {
			sum -= memo[start - 1];
			sum += memo[start + advTime - 1];

			if (max < sum) {
				max = sum;
				maxTime = start;
			}
		}

		return intToStr(maxTime);
	}

	int strToInt(String str) {
		StringTokenizer stk = new StringTokenizer(str, ":");
		int hour = Integer.parseInt(stk.nextToken());
		int min = Integer.parseInt(stk.nextToken());
		int sec = Integer.parseInt(stk.nextToken());
		return (hour * 60 * 60) + (min * 60) + sec;
	}

	String intToStr(int i) {
		int sec = i % 60;
		i /= 60;
		int min = i % 60;
		i /= 60;
		int hour = i;
		StringBuilder sb = new StringBuilder();
		return sb
				.append(twoDigitStr(hour))
				.append(":")
				.append(twoDigitStr(min))
				.append(":")
				.append(twoDigitStr(sec))
				.toString();
	}

	String twoDigitStr(int i) {
		if (i >= 10) {
			return i + "";
		} else {
			return "0" + i;
		}
	}

	public static void main(String[] args) {
		Solution_PRG_2021Kakao_광고삽입 sol = new Solution_PRG_2021Kakao_광고삽입();

		String play_time = "99:59:59";
		String adv_time = "00:14:15";
		String[] logs = {"01:20:15-01:45:14", "00:40:31-01:00:00", "00:25:50-00:48:29", "01:30:59-01:53:29", "01:37:44-02:02:30"};
		// return "01:30:59"

		System.out.println(sol.solution(play_time, adv_time, logs));
	}
}

/*

public String solution(String play_time, String adv_time, String[] logs) {
	String answer = "";

	int videoLength = strToSec(play_time);
	int[] watch = new int[videoLength + 1];
	int N = logs.length;

	for (int i = 0; i < N; i++) {
		String[] splitted = logs[i].split("-");
		int startTimeSec = strToSec(splitted[0]);
		int endTimeSec = strToSec(splitted[1]);
		for (int t = startTimeSec; t < endTimeSec; t++) {
			watch[t]++;
		}
	}

	int advLength = strToSec(adv_time);
	long maxUserSum = 0L;
	int maxStartTime = 0;

	long userSum = 0L;
	for (int i = 0; i < advLength; i++)
		userSum += watch[i];
	maxUserSum = userSum;

	int start = 0;
	int end = advLength - 1;
	while (end < videoLength) {
		userSum -= watch[start++];
		userSum += watch[++end];

		if (maxUserSum < userSum) {
			maxUserSum = userSum;
			maxStartTime = start;
		}
	}

	answer = secToStr(maxStartTime);

	return answer;
}

int strToSec(String timeStr) {
	String[] splitted = timeStr.split(":");
	int hour = Integer.parseInt(splitted[0]);
	int min = Integer.parseInt(splitted[1]);
	int sec = Integer.parseInt(splitted[2]);
	return hour * 60 * 60 + min * 60 + sec;
}

String secToStr(int sec) {
	int hour = sec / (60 * 60);
	sec -= (60 * 60) * hour;
	int min = sec / 60;
	sec -= 60 * min;
	return String.format("%02d:%02d:%02d", hour, min, sec);
}

 */