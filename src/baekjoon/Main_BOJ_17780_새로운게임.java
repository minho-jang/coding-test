package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main_BOJ_17780_새로운게임 {
	static class Cell {
		List<Horse> stack;
		Pos position;

		Cell(int r, int c) {
			stack = new ArrayList<>();
			position = new Pos(r, c);
		}

		void addHorse(Horse horse) {
			stack.add(horse);
		}

		Horse getFirstHorse() {
			if (stack.size() == 0) {
				return null;
			} else {
				return stack.get(0);
			}
		}

		void changeFirstHorseDirection(int dir) {
			Horse first = getFirstHorse();
			if (first != null) {
				first.direction = dir;
			}
		}

		void clear() {
			stack.clear();
		}

		void push(Cell cell) {
			cell.setPositionOfHorses(this.position);
			stack.addAll(cell.stack);
			cell.clear();
		}

		void reversePush(Cell cell) {
			cell.setPositionOfHorses(this.position);
			Collections.reverse(cell.stack);
			stack.addAll(cell.stack);
			cell.clear();
		}

		void setPositionOfHorses(Pos pos) {
			for (Horse h : stack) {
				h.position = pos;
			}
		}

		public String toString() {
			Horse h = getFirstHorse();
			if (h != null) {
				return "[" + h.index + "," + h.direction + "]";
			}
			return "[0]";
		}
	}

	static class Horse {
		int index;
		int direction;
		Pos position;

		Horse(int i, int d, Pos p) {
			this.index = i;
			this.direction = d;
			this.position = p;
		}
	}

	static class Pos {
		int row, col;

		Pos(int r, int c) {
			this.row = r;
			this.col = c;
		}
	}

	static int N, K;
	static int[][] map;
	static Cell[][] statusMap;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk;

		stk = new StringTokenizer(br.readLine());
		N = Integer.parseInt(stk.nextToken());
		K = Integer.parseInt(stk.nextToken());

		statusMap = new Cell[N][N];
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			stk = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(stk.nextToken());
				statusMap[i][j] = new Cell(i, j);
			}
		}

		for (int i = 0; i < K; i++) {
			stk = new StringTokenizer(br.readLine());
			int idx = i + 1;
			int row = Integer.parseInt(stk.nextToken()) - 1;
			int col = Integer.parseInt(stk.nextToken()) - 1;
			int dir = Integer.parseInt(stk.nextToken()) - 1;
			Horse horse = new Horse(idx, dir, new Pos(row, col));
			statusMap[row][col].addHorse(horse);
		}

		int answer = -1;
		int time = 0;
		while (answer == -1 && time < 1000) {
			for (int i = 1; i <= K; i++) {
				Cell current = getHorseByIndex(i);
				if (current == null) {
					continue;
				}

				move(current, false);
			}

			time++;

			for (int i = 1; i <= K; i++) {
				Cell current = getHorseByIndex(i);
				if (current != null && current.stack.size() >= 4) {
					answer = time;
					break;
				}
			}
		}

		System.out.println(answer);
	}

	private static Cell getHorseByIndex(int idx) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				Horse h = statusMap[i][j].getFirstHorse();
				if (h != null && h.index == idx) {
					return statusMap[i][j];
				}
			}
		}
		return null;
	}

	private static final int[] dr = {0, 0, -1, 1};
	private static final int[] dc = {1, -1, 0, 0};

	private static void move(Cell cell, boolean isMovedByWall) {
		final int RED = 1;
		final int BLUE = 2;
		Horse horse = cell.getFirstHorse();
		int nextRow = horse.position.row + dr[horse.direction];
		int nextCol = horse.position.col + dc[horse.direction];

		if (isOut(nextRow, nextCol) || map[nextRow][nextCol] == BLUE) {
			if (isMovedByWall) {
				return;
			}
			int nextDirection = getOppositeDirection(horse.direction);
			cell.changeFirstHorseDirection(nextDirection);
			move(cell, true);

		} else if (map[nextRow][nextCol] == RED) {
			statusMap[nextRow][nextCol].reversePush(cell);

		} else {
			statusMap[nextRow][nextCol].push(cell);
		}
	}

	private static int getOppositeDirection(int d) {
		if (d == 0) {
			return 1;
		} else if (d == 1) {
			return 0;
		} else if (d == 2) {
			return 3;
		} else if (d == 3) {
			return 2;
		} else {
			return -1;
		}
	}

	private static boolean isOut(int r, int c) {
		return r < 0 || r >= N || c < 0 || c >= N;
	}

	private static void printMap() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.print(statusMap[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}
}
