package programmers;

import java.util.Arrays;

public class Solution_PRG_WeeklyChallenge_복서정렬하기 {
	public int[] solution(int[] weights, String[] head2head) {
		int N = weights.length;
		Boxer[] boxerArr = new Boxer[N];
		for (int i = 0; i < N; i++) {
			int index = i + 1;
			int weight = weights[i];
			boxerArr[i] = new Boxer(index, weight);
		}

		for (int i = 0; i < N; i++) {
			String record = head2head[i];
			for (int j = 0; j < i; j++) {
				char score = record.charAt(j);
				if (score == 'W') {
					boxerArr[i].win(boxerArr[j]);
					boxerArr[j].lose();
				} else if (score == 'L') {
					boxerArr[i].lose();
					boxerArr[j].win(boxerArr[i]);
				}
			}
		}

		return Arrays.stream(boxerArr).sorted().mapToInt(b -> b.index).toArray();
	}

	class Boxer implements Comparable<Boxer> {
		int index;
		int weight;
		int winCount;
		int gameCount;
		int largeWinCount;

		Boxer(int index, int weight) {
			this.index = index;
			this.weight = weight;
		}

		void win(Boxer opposite) {
			winCount++;
			if (weight < opposite.weight) {
				largeWinCount++;
			}
			gameCount++;
		}

		void lose() {
			gameCount++;
		}

		double winRate() {
			if (gameCount == 0) {
				return 0;
			}

			return (double) winCount / gameCount;
		}

		@Override
		public int compareTo(Boxer o) {
			int winCompare = Double.compare(this.winRate(), o.winRate()) * (-1);
			if (winCompare != 0) {
				return winCompare;
			}

			int largeWinCompare = Integer.compare(this.largeWinCount, o.largeWinCount) * (-1);
			if (largeWinCompare != 0) {
				return largeWinCompare;
			}

			int weightCompare = Integer.compare(this.weight, o.weight) * (-1);
			if (weightCompare != 0) {
				return weightCompare;
			}

			return Integer.compare(this.index, o.index);
		}
	}
}
