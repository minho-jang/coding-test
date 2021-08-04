package swea;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution_5656_벽돌깨기 {
	private static int N, W, H, answer;
	private static int[] dr = {0, 0, -1, 1};
	private static int[] dc = {1, -1, 0, 0};

	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("input.txt"));

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk;
		StringBuilder answerSb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {

			stk = new StringTokenizer(br.readLine());
			N = Integer.parseInt(stk.nextToken());
			W = Integer.parseInt(stk.nextToken());
			H = Integer.parseInt(stk.nextToken());
			answer = 987654321;

			int[][] map = new int[H][W];
			for (int i = 0; i < H; i++) {
				stk = new StringTokenizer(br.readLine());
				for (int j = 0; j < W; j++) {
					map[i][j] = Integer.parseInt(stk.nextToken());
				}
			}

			int[] res = new int[N + 1];
			for (int w = 0; w < W; w++) {
				res[0] = w;
				throwBrick(1, w, map, res);
			}

			answerSb.append("#").append(tc).append(" ").append(answer).append("\n");
		}

		System.out.print(answerSb.toString());
	}

	private static void throwBrick(int cnt, int w, int[][] map, int[] res) {
		if (cnt == N + 1) {
			int sum = 0;
			for (int i = 0; i < H; i++) {
				for (int j = 0; j < W; j++) {
					if (map[i][j] > 0)
						sum++;
				}
			}

//			System.out.println(Arrays.toString(res));
//			for (int i = 0; i < H; i++) {
//				System.out.println(Arrays.toString(map[i]));
//			}
//			System.out.println("sum = " + sum);
//			System.out.println();

			answer = Math.min(answer, sum);
			return;
		}

		int[][] brickMap = new int[H][W];
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				brickMap[i][j] = map[i][j];
			}
		}

		// 벽돌 부시기
		int startC = w;
		int startR = 0;
		for (int row = 0; row < H; row++) {    // 칼럼이 w인 곳의 벽돌 시작점 찾기
			if (brickMap[row][startC] != 0) {
				startR = row;
				break;
			}
		}

		// BFS
		Queue<Integer> queue = new LinkedList<>();

		queue.add(startR);
		queue.add(startC);
		while (!queue.isEmpty()) {
			int r = queue.remove();
			int c = queue.remove();
			int bombSize = brickMap[r][c];
			brickMap[r][c] = 0;

			for (int d = 0; d < 4; d++) {
				int nr = r, nc = c;
				int len = 1;
				while (len < bombSize) {
					nr += dr[d];
					nc += dc[d];
					if (nr < 0 || nr >= H || nc < 0 || nc >= W)
						break;

					if (bombSize > 0) {
						queue.add(nr);
						queue.add(nc);
					}
					len++;
				}
			}
		}

		// 빈공간 채우기
		for (int col = 0; col < W; col++) {
			int brickCount = 0;
			for (int row = 0; row < H; row++) {
				if (brickMap[row][col] == 0 && brickCount > 0) {
					int k = 0;
					while (k < brickCount) {
						brickMap[row - k][col] = brickMap[row - k - 1][col];
						k++;
					}
					brickMap[row - k][col] = 0;

				} else if (brickMap[row][col] > 0) {
					brickCount++;
				}
			}
		}

		for (int i = 0; i < W; i++) {
			res[cnt] = i;
			throwBrick(cnt + 1, i, brickMap, res);
		}
	}
}
