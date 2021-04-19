package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class Main_BOJ_2239_스도쿠 {
	static class Pos {
		int r, c;
		public Pos(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}
	}

	static boolean[][] rowCondition, colCondition, boxCondition;
	static ArrayList<Pos> emptyList = new ArrayList<>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int[][] sudoku = new int[9][9];
		rowCondition = new boolean[9][9+1];
		colCondition = new boolean[9][9+1];
		boxCondition = new boolean[9][9+1];
		for (int i = 0; i < 9; i++) {
			char[] inputLine = br.readLine().toCharArray();
			for (int j = 0; j < 9; j++) {
				sudoku[i][j] = inputLine[j] - '0';
				
				if (sudoku[i][j] == 0)	{
					emptyList.add(new Pos(i,j));
				} else {
					rowCondition[i][sudoku[i][j]] = true;
					colCondition[j][sudoku[i][j]] = true;
					boxCondition[getBoxNum(i,j)][sudoku[i][j]] = true;
				}
			}
		}
		
		dfs(0, 0, sudoku);
	}

	private static void dfs(int idx, int depth, int[][] sudoku) {
		if (depth == emptyList.size()) {
			for (int i = 0; i < 9; i++) {
				for (int j = 0; j < 9; j++) {
					System.out.print(sudoku[i][j]);
				}
				System.out.println();
			}
			
			System.exit(0);
			
			return;
		}
		
//		System.out.println(">> depth = " + depth);

		Pos p = emptyList.get(idx);
		int row = p.r;
		int col = p.c;
		int boxNum = getBoxNum(row,col);
		
		for (int j2 = 1; j2 <= 9; j2++) {	// 1~9 시도
			if (rowCondition[row][j2])
				continue;
			if (colCondition[col][j2])
				continue;
			if (boxCondition[boxNum][j2])
				continue;
			
//			System.out.println("(" + row + ", " + col + ", " + j2 + ")");
			sudoku[row][col] = j2;
			rowCondition[row][j2] = true;
			colCondition[col][j2] = true;
			boxCondition[boxNum][j2] = true;
			dfs(idx+1, depth+1, sudoku);
			
			rowCondition[row][j2] = false;
			colCondition[col][j2] = false;
			boxCondition[boxNum][j2] = false;
		}
	}

	private static int getBoxNum(int i, int j) {
		int num = 0;
		
		if (i >= 0 && i < 3) 		num=0;
		else if (i >= 3 && i < 6)  	num=3;
		else if (i >= 6 && i < 9) 	num=6;
		
		if (j >= 0 && j < 3)  		num += 0;
		else if (j >= 3 && j < 6) 	num += 1;
		else if (j >= 6 && j < 9)  	num += 2;
		
		return num;
	}
}
