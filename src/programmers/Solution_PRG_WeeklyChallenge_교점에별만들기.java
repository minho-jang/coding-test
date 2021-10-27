package programmers;

import java.util.*;

public class Solution_PRG_WeeklyChallenge_교점에별만들기 {
	HashSet<Pos> posSet = new HashSet<>();

	static class Pos {
		long x, y;

		Pos(long x, long y) {
			this.x = x;
			this.y = y;
		}

		public String toString() {
			return "[" + x + ", " + y + "]";
		}
	}

	public String[] solution(int[][] line) {
		for (int i = 0; i < line.length; i++) {
			for (int j = i + 1; j < line.length; j++) {
				getCrossPos(line[i], line[j]);
			}
		}

		long minX = Long.MAX_VALUE, maxX = Long.MIN_VALUE;
		long minY = Long.MAX_VALUE, maxY = Long.MIN_VALUE;
		for (Pos pos : posSet) {
			if (pos.x > maxX)
				maxX = pos.x;
			if (pos.x < minX)
				minX = pos.x;
			if (pos.y > maxY)
				maxY = pos.y;
			if (pos.y < minY)
				minY = pos.y;
		}

		int diffX = (int) (maxX - minX);
		int diffY = (int) (maxY - minY);
		char[][] map = new char[diffY + 1][diffX + 1];
		initMap(map);
		for (Pos pos : posSet) {
			int x = (int) (pos.x - minX);
			int y = (int) (pos.y - minY);

			map[y][x] = '*';
		}

		String[] answer = new String[map.length];
		for (int i = 0; i < map.length; i++) {
			answer[map.length - i - 1] = new String(map[i]);
		}
		return answer;
	}

	private void initMap(char[][] map) {
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				map[i][j] = '.';
			}
		}
	}

	private void getCrossPos(int[] line1, int[] line2) {
		double A, B, C, D, E, F;
		A = line1[0];
		B = line1[1];
		E = line1[2];
		C = line2[0];
		D = line2[1];
		F = line2[2];

		double adbc = A * D - B * C;
		if (adbc != 0) {
			double x = (B * F - E * D) / adbc;
			double y = (E * C - A * F) / adbc;
			long ix = (long) x, iy = (long) y;
			if (x == ix && y == iy) {
				posSet.add(new Pos(ix, iy));
			}
		}
	}

	public static void main(String[] args) {
		Solution_PRG_WeeklyChallenge_교점에별만들기 sol = new Solution_PRG_WeeklyChallenge_교점에별만들기();
		int[][] line = {
				{1, -1, 0},
				{0, 1, 100000},
				{1, 0, 100000},
				{0, 1, 99999},
		};
		String[] result = sol.solution(line);
		for (String r : result) {
			System.out.println(r);
		}
	}
}
