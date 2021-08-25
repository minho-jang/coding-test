package programmers;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Solution_PRG_WeeklyChallenge_퍼즐조각채우기 {
	class Pos {
		int r, c;

		Pos(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

	class Block {
		int index;
		List<Pos> positions;

		Block(int index, List<Pos> positions) {
			this.index = index;
			this.positions = positions;
		}
	}

	private List<Block> blocks = new ArrayList<>();
	private int maxIndexOfBlock = 0;
	private boolean[] used;
	private int answer = 0;

	public int solution(int[][] game_board, int[][] table) {
		printArr(table);

		indexingBlocks(table);
		for (int i = 0; i < 4; i++) {
			table = rotate(table);
			addBlocksInTable(table);
		}

		printArr(table);

		doGame(game_board);

		return answer;
	}

	private void indexingBlocks(int[][] arr) {
		int index = 2;
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[0].length; j++) {
				if (arr[i][j] == 1) {
					fillIndex(arr, i, j, index);
					index++;
				}
			}
		}
		maxIndexOfBlock = index;
		used = new boolean[maxIndexOfBlock];
	}

	private final int[] dr = {-1, 1, 0, 0};
	private final int[] dc = {0, 0, -1, 1};

	private void fillIndex(int[][] arr, int row, int col, int index) {
		// BFS
		final int R = arr.length;
		final int C = arr[0].length;
		boolean[][] visited = new boolean[R][C];
		Queue<Integer> queue = new LinkedList<>();

		arr[row][col] = index;
		visited[row][col] = true;
		queue.add(row);
		queue.add(col);

		while (!queue.isEmpty()) {
			int r = queue.poll();
			int c = queue.poll();
			for (int d = 0; d < 4; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];

				if (nr < 0 || nr >= R || nc < 0 || nc >= C ||
						visited[nr][nc] ||
						arr[nr][nc] == 0) {
					continue;
				}

				arr[nr][nc] = index;
				visited[nr][nc] = true;
				queue.add(nr);
				queue.add(nc);
			}
		}
	}

	private int[][] rotate(int[][] arr) {
		final int R = arr.length;
		final int C = arr[0].length;

		int[][] result = new int[R][C];
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				result[i][j] = arr[C - j - 1][i];
			}
		}

		return result;
	}

	private void addBlocksInTable(int[][] table) {
		final int R = table.length;
		final int C = table[0].length;

		boolean[] visited = new boolean[maxIndexOfBlock];
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (table[i][j] > 1 && !visited[table[i][j]]) {
					int idx = table[i][j];
					visited[idx] = true;
					List<Pos> positions = getPositions(table, idx);
					blocks.add(new Block(idx, positions));
				}
			}
		}
	}

	private List<Pos> getPositions(int[][] arr, int index) {
		List<Pos> result = new ArrayList<>();
		final int R = arr.length;
		final int C = arr[0].length;

		Pos start = null;
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (arr[i][j] == index) {
					if (start == null) {
						start = new Pos(i, j);
						result.add(new Pos(0, 0));
					} else {
						result.add(new Pos(i - start.r, j - start.c));
					}
				}
			}
		}

		return result;
	}

	private void doGame(int[][] gameBoard) {
		final int R = gameBoard.length;
		final int C = gameBoard[0].length;

		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				tryBlockAtPoint(gameBoard, i, j);
			}
		}
	}

	private void tryBlockAtPoint(int[][] gameBoard, int row, int col) {
		for (Block block : blocks) {
			if (!used[block.index] && isFitBlock(gameBoard, row, col, block)) {
				used[block.index] = true;
				int result = insertBlock(gameBoard, row, col, block);
				answer += result;
			}
		}
	}

	private boolean isFitBlock(int[][] arr, int row, int col, Block block) {
		boolean result = false;

		if (checkAllEmpty(arr, row, col, block)) {
			insertBlock(arr, row, col, block);
			result = checkNoEmptyAround(arr, row, col, block);
			deleteBlock(arr, row, col, block);
		}

		return result;
	}

	private int insertBlock(int[][] arr, int row, int col, Block block) {
		for (Pos pos : block.positions) {
			int nr = row + pos.r;
			int nc = col + pos.c;
			arr[nr][nc] = block.index;
		}

		return block.positions.size();
	}

	private boolean checkNoEmptyAround(int[][] arr, int row, int col, Block block) {
		final int R = arr.length;
		final int C = arr[0].length;

		for (Pos pos : block.positions) {
			for (int d = 0; d < 4; d++) {
				int nr = row + pos.r + dr[d];
				int nc = col + pos.c + dc[d];
				if (nr < 0 || nr >= R || nc < 0 || nc >= C) {
					continue;
				}
				if (arr[nr][nc] == 0) {
					return false;
				}
			}
		}

		return true;
	}

	private void deleteBlock(int[][] arr, int row, int col, Block block) {
		for (Pos pos : block.positions) {
			int nr = row + pos.r;
			int nc = col + pos.c;
			arr[nr][nc] = 0;
		}
	}

	private boolean checkAllEmpty(int[][] arr, int row, int col, Block block) {
		final int R = arr.length;
		final int C = arr[0].length;

		for (Pos pos : block.positions) {
			int nr = row + pos.r;
			int nc = col + pos.c;
			if (nr < 0 || nr >= R || nc < 0 || nc >= C) {
				return false;
			}
			if (arr[nr][nc] != 0) {
				return false;
			}
		}

		return true;
	}

	private void printArr(int[][] arr) {
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[0].length; j++) {
				System.out.print(arr[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}

	public static void main(String[] args) {
		Solution_PRG_WeeklyChallenge_퍼즐조각채우기 sol = new Solution_PRG_WeeklyChallenge_퍼즐조각채우기();

		int[][] game_board = {
				{1, 1, 0, 0, 1, 0},
				{0, 0, 1, 0, 1, 0},
				{0, 1, 1, 0, 0, 1},
				{1, 1, 0, 1, 1, 1},
				{1, 0, 0, 0, 1, 0},
				{0, 1, 1, 1, 0, 0}
		};
		int[][] table = {
				{1, 0, 0, 1, 1, 0},
				{1, 0, 1, 0, 1, 0},
				{0, 1, 1, 0, 1, 1},
				{0, 0, 1, 0, 0, 0},
				{1, 1, 0, 1, 1, 0},
				{0, 1, 0, 0, 0, 0}
		};
		// return 14

//		int[][] game_board = {
//				{0, 0, 0},
//				{1, 1, 0},
//				{1, 1, 1}
//		};
//		int[][] table = {
//				{1, 1, 1},
//				{1, 0, 0},
//				{0, 0, 0}
//		};
		// return 0

//		int[][] game_board = {
//				{0, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 0, 1, 0, 0, 0, 0},
//				{1, 0, 0, 0, 1, 0, 1, 0, 1, 0, 1, 0, 0, 1, 0, 1, 1, 1},
//				{0, 1, 1, 1, 0, 0, 1, 0, 1, 0, 0, 1, 1, 0, 1, 0, 0, 0},
//				{0, 0, 0, 0, 1, 1, 0, 0, 1, 1, 0, 1, 0, 0, 1, 0, 0, 0},
//				{0, 1, 1, 1, 0, 0, 1, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1},
//				{1, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0},
//				{0, 0, 0, 1, 1, 1, 0, 0, 1, 1, 0, 1, 1, 1, 1, 0, 0, 1},
//				{1, 1, 1, 0, 0, 0, 1, 1, 0, 0, 1, 0, 0, 0, 0, 1, 1, 0},
//				{0, 0, 1, 0, 1, 1, 1, 0, 0, 1, 0, 1, 1, 1, 1, 0, 0, 0},
//				{1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 0, 1, 0, 0, 0, 1, 1, 1},
//				{0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 1, 0, 0, 1, 0, 1, 0},
//				{1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 0, 1, 0, 0, 1, 0},
//				{0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 1, 0, 1, 1, 0, 0},
//				{1, 0, 1, 1, 0, 1, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 1},
//				{1, 0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 0},
//				{0, 1, 1, 0, 0, 1, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0},
//				{0, 0, 0, 1, 0, 1, 0, 1, 0, 0, 1, 1, 1, 1, 1, 1, 1, 0},
//				{0, 1, 0, 1, 1, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0}
//		};
//		int[][] table = {
//				{1, 1, 1, 1, 1, 1, 0, 1, 0, 1, 1, 0, 0, 1, 0, 0, 1, 0},
//				{0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 1, 0, 1, 1, 0, 1, 1, 0},
//				{1, 0, 1, 1, 0, 1, 0, 1, 0, 1, 1, 0, 1, 0, 1, 1, 0, 1},
//				{1, 1, 0, 1, 1, 1, 0, 1, 0, 1, 0, 1, 1, 0, 1, 0, 0, 1},
//				{1, 1, 1, 0, 0, 0, 1, 0, 1, 0, 1, 0, 0, 1, 0, 0, 1, 1},
//				{0, 0, 0, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 0, 1, 0, 0, 0},
//				{1, 1, 1, 0, 0, 0, 0, 0, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1},
//				{0, 0, 1, 0, 1, 1, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0},
//				{1, 0, 1, 0, 0, 0, 0, 1, 0, 1, 1, 0, 1, 1, 0, 1, 1, 1},
//				{1, 0, 1, 0, 1, 1, 1, 1, 0, 1, 1, 0, 0, 0, 1, 1, 1, 0},
//				{1, 1, 0, 1, 0, 0, 0, 0, 1, 0, 0, 1, 1, 1, 0, 0, 0, 0},
//				{0, 0, 1, 1, 1, 1, 0, 1, 1, 0, 1, 0, 0, 0, 1, 1, 0, 1},
//				{1, 1, 0, 1, 0, 0, 1, 0, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1},
//				{1, 1, 0, 0, 1, 1, 1, 0, 1, 1, 0, 1, 0, 1, 0, 1, 0, 1},
//				{0, 0, 1, 1, 0, 1, 1, 0, 1, 0, 1, 1, 0, 0, 0, 1, 0, 0},
//				{1, 1, 1, 0, 1, 0, 0, 1, 0, 1, 1, 0, 0, 1, 0, 1, 0, 1},
//				{0, 0, 0, 0, 1, 0, 1, 1, 1, 0, 0, 1, 0, 1, 1, 0, 1, 1},
//				{0, 1, 1, 1, 1, 0, 0, 1, 0, 0, 1, 1, 0, 1, 0, 0, 1, 1}
//		};
		// return 73

//		int[][] game_board = {
//				{0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0},
//				{1, 1, 1, 1, 1, 1, 0, 0, 0, 1, 0, 0},
//				{0, 0, 0, 0, 0, 1, 0, 1, 0, 1, 1, 0},
//				{1, 0, 1, 1, 1, 0, 1, 0, 1, 0, 0, 1},
//				{0, 1, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0},
//				{0, 0, 1, 1, 1, 0, 1, 0, 1, 1, 0, 1},
//				{0, 1, 0, 0, 0, 1, 1, 0, 0, 0, 1, 0},
//				{0, 0, 1, 0, 1, 0, 0, 1, 1, 1, 0, 0},
//				{1, 1, 0, 0, 1, 0, 0, 1, 1, 1, 1, 0},
//				{0, 0, 1, 1, 0, 1, 0, 1, 1, 1, 0, 0},
//				{0, 0, 1, 0, 0, 1, 0, 1, 1, 0, 1, 1},
//				{0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0}
//		};
//		int[][] table = {
//				{1, 1, 1, 0, 1, 1, 1, 0, 0, 0, 1, 1},
//				{1, 1, 0, 0, 0, 0, 1, 1, 1, 0, 1, 1},
//				{1, 0, 1, 0, 1, 0, 0, 0, 0, 1, 1, 0},
//				{0, 0, 1, 1, 1, 0, 0, 1, 1, 0, 0, 0},
//				{1, 1, 0, 1, 0, 0, 0, 1, 1, 1, 0, 0},
//				{1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0},
//				{1, 0, 0, 1, 0, 1, 1, 1, 0, 0, 0, 1},
//				{1, 1, 0, 1, 0, 1, 1, 1, 0, 0, 0, 1},
//				{0, 0, 0, 1, 1, 0, 0, 0, 1, 1, 0, 1},
//				{1, 1, 0, 1, 1, 0, 1, 0, 0, 1, 0, 1},
//				{1, 1, 1, 0, 0, 0, 1, 0, 1, 1, 0, 1},
//				{1, 0, 0, 1, 1, 1, 1, 0, 0, 1, 0, 1}
//		};
		// return 54

		System.out.println(sol.solution(game_board, table));
	}
}
