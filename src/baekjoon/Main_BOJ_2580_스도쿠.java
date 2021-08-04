package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_BOJ_2580_스도쿠 {
	static ArrayList<Pos> emptySpace;
	static int[][] sudoku;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk;

		emptySpace = new ArrayList<>();
		sudoku = new int[9][9];
		for (int i = 0; i < 9; i++) {
			stk = new StringTokenizer(br.readLine());
			for (int j = 0; j < 9; j++) {
				sudoku[i][j] = Integer.parseInt(stk.nextToken());
				if (sudoku[i][j] == 0) {
					emptySpace.add(new Pos(i, j));
				}
			}
		}

		System.out.println("size=" + emptySpace.size());
		dfs(0);

		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				System.out.print(sudoku[i][j] + " ");
			}
			System.out.println();
		}
	}

	private static boolean dfs(int posIdx) {
		if (posIdx >= emptySpace.size())
			return true;

		Pos p = emptySpace.get(posIdx);

		for (int i = 1; i <= 9; i++) {
			if (OK(p, i)) {
				sudoku[p.r][p.c] = i;

				if (dfs(posIdx + 1))
					return true;

				sudoku[p.r][p.c] = 0;
			}
		}

		return false;
	}

	// p 위치에서 num를 넣어도 무방한가
	private static boolean OK(Pos p, int num) {
		boolean[] flag = new boolean[9 + 1];

		// 행 고정
		for (int i = 0; i < 9; i++)
			flag[sudoku[p.r][i]] = true;
		if (flag[num])
			return false;

		Arrays.fill(flag, false);
		// 열 고정
		for (int i = 0; i < 9; i++)
			flag[sudoku[i][p.c]] = true;
		if (flag[num])
			return false;

		Arrays.fill(flag, false);
		// 3x3 확인
		int boxNum = (p.r / 3) * 3 + (p.c / 3);
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				int r = i + (boxNum / 3) * 3;
				int c = j + (boxNum % 3) * 3;
				flag[sudoku[r][c]] = true;
			}
		}
		if (flag[num])
			return false;

		return true;
	}

	static class Pos {
		int r, c;

		public Pos(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
}
