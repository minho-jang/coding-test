package programmers;

public class Solution_PRG_WeeklyChallenge_부족한금액계산하기 {
	public long solution(int price, int money, int count) {
		long answer = -1;

		long need = (long)price * ((count * (count+1)) / 2);
		answer = need - money;
		if (answer < 0) {
			answer = 0;
		}

		return answer;
	}
}
