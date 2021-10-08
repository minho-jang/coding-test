package programmers;

public class Solution_PRG_WeeklyChallenge_최소직사각형 {
	public int solution(int[][] sizes) {
		int answer = 0;

		int[][] mySizes = new int[sizes.length][2];
		for (int i = 0; i < sizes.length; i++) {
			int[] size = sizes[i];
			mySizes[i][0] = Math.max(size[0], size[1]);
			mySizes[i][1] = Math.min(size[0], size[1]);
		}

		int width = getMax(mySizes, 0);
		int height = getMax(mySizes, 1);

		return width * height;
	}

	int getMax(int[][] arr, int col) {
		int max = 0;
		for (int i = 0; i < arr.length; i++) {
			if (max < arr[i][col]) {
				max = arr[i][col];
			}
		}
		return max;
	}
}
