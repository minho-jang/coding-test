package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BOJ_2564_경비원 {
	private static int width, height;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk;

		stk = new StringTokenizer(br.readLine());
		width = Integer.parseInt(stk.nextToken());
		height = Integer.parseInt(stk.nextToken());

		int N = Integer.parseInt(br.readLine());
		int[][] input = new int[N + 1][2];
		for (int i = 0; i < N + 1; i++) {
			stk = new StringTokenizer(br.readLine());
			int dir = Integer.parseInt(stk.nextToken());    // 방향
			int offset = Integer.parseInt(stk.nextToken());    // offset
			if (dir == 1) {
				input[i][0] = offset;
				input[i][1] = height;
			} else if (dir == 2) {
				input[i][0] = offset;
				input[i][1] = 0;
			} else if (dir == 3) {
				input[i][0] = 0;
				input[i][1] = height - offset;
			} else if (dir == 4) {
				input[i][0] = width;
				input[i][1] = height - offset;
			}
		}

		int[] dong = new int[2];
		dong[0] = input[N][0];
		dong[1] = input[N][1];

		int distanceSum = 0;
		for (int i = 0; i < N; i++) {
			int[] store = input[i];
			int d = getDistance(store, dong);
//			System.out.println(Arrays.toString(store) + " " + Arrays.toString(dong) + " " + d);
			distanceSum += d;
		}

		System.out.println(distanceSum);
	}

	private static int getDistance(int[] store, int[] dong) {

		if (dong[0] == width) {    // 동근이 동쪽
			if (store[1] == height) {    // 북
				return height - dong[1] + width - store[0];
			} else if (store[1] == 0) {    // 남
				return dong[1] + width - store[0];
			} else if (store[0] == width) {    // 동
				return Math.abs(store[1] - dong[1]);
			} else if (store[0] == 0) {    // 서
				return Math.min(height - dong[1] + width + height - store[1], dong[1] + width + store[1]);
			}

		} else if (dong[0] == 0) {    // 동근이 서쪽
			if (store[1] == height) {    // 북
				return height - dong[1] + store[0];
			} else if (store[1] == 0) {    // 남
				return dong[1] + store[0];
			} else if (store[0] == width) {    // 동
				return Math.min(height - dong[1] + width + height - store[1], dong[1] + width + store[1]);
			} else if (store[0] == 0) {    // 서
				return Math.abs(store[1] - dong[1]);
			}

		} else if (dong[1] == 0) {    // 동근이 남쪽
			if (store[1] == height) {    // 북
				return Math.min(width - dong[0] + height + width - store[0], dong[0] + height + store[0]);
			} else if (store[1] == 0) {    // 남
				return Math.abs(store[0] - dong[0]);
			} else if (store[0] == width) {    // 동
				return width - dong[0] + store[1];
			} else if (store[0] == 0) {    // 서
				return dong[0] + store[1];
			}

		} else if (dong[1] == height) {    // 동근이 북쪽
			if (store[1] == height) {    // 북
				return Math.abs(store[0] - dong[0]);
			} else if (store[1] == 0) {    // 남
				return Math.min(width - dong[0] + height + width - store[0], dong[0] + height + store[0]);
			} else if (store[0] == width) {    // 동
				return width - dong[0] + height - store[1];
			} else if (store[0] == 0) {    // 서
				return dong[0] + height - store[1];
			}
		}

		return 0;
	}
}
