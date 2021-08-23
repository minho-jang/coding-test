package programmers;

public class Solution_PRG_WeeklyChallenge_상호평가 {
	public String solution(int[][] scores) {
		StringBuilder answerSb = new StringBuilder();

		for (int i = 0; i < scores.length; i++) {
			int[] col = columnOf(scores, i);
			int min = getMin(col);
			int max = getMax(col);
			boolean excludeScore = (min == col[i] && isUnique(col, min)) || (max == col[i] && isUnique(col, max));

			double avg = getAvg(col, excludeScore, i);
			answerSb.append(getGrade(avg));
		}

		return answerSb.toString();
	}

	private int[] columnOf(int[][] arr, int colNum) {
		int[] col = new int[arr.length];
		for (int i = 0; i < arr.length; i++) {
			col[i] = arr[i][colNum];
		}
		return col;
	}

	private int getMin(int[] arr) {
		int min = Integer.MAX_VALUE;
		for (int i = 0; i < arr.length; i++) {
			if (min > arr[i]) {
				min = arr[i];
			}
		}
		return min;
	}

	private int getMax(int[] arr) {
		int max = Integer.MIN_VALUE;
		for (int i = 0; i < arr.length; i++) {
			if (max < arr[i]) {
				max = arr[i];
			}
		}
		return max;
	}

	private boolean isUnique(int[] arr, int target) {
		int count = 0;
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] == target) {
				count += 1;
			}
		}
		return count == 1;
	}

	private double getAvg(int[] arr, boolean isExclusive, int target) {
		int sum = 0;
		for (int i = 0; i < arr.length; i++) {
			sum += arr[i];
		}

		if (isExclusive) {
			sum -= arr[target];
		}

		double avg = 0.0;
		if (isExclusive) {
			avg = sum / (arr.length - 1);
		} else {
			avg = sum / arr.length;
		}

		return avg;
	}

	private String getGrade(double avg) {
		if (avg >= 90) {
			return "A";
		} else if (avg >= 80) {
			return "B";
		} else if (avg >= 70) {
			return "C";
		} else if (avg >= 50) {
			return "D";
		} else {
			return "F";
		}
	}
}
