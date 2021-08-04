package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main_BOJ_2636_치즈 {
	static int[] dr = {0, 1, 0, -1};
	static int[] dc = {1, 0, -1, 0};
	static int[][] visited, map;
	static int R, C;
	static boolean isConnectOut;
	static List<Integer> holeList;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk;

		stk = new StringTokenizer(br.readLine());
		R = Integer.parseInt(stk.nextToken());
		C = Integer.parseInt(stk.nextToken());
		map = new int[R - 2][C - 2];

		int preCheeze = 0;
		br.readLine();
		for (int i = 1; i < R - 1; i++) {
			stk = new StringTokenizer(br.readLine());
			stk.nextToken();
			for (int j = 1; j < C - 1; j++) {
				map[i - 1][j - 1] = Integer.parseInt(stk.nextToken());
				if (map[i - 1][j - 1] == 1) preCheeze++;
			}
			stk.nextToken();
		}
		br.readLine();
//		System.out.println();
//		for (int i = 0; i < R-2; i++) {
//			System.out.println(Arrays.toString(map[i]));
//		}

		int hour = 0;

		while (preCheeze != 0) {
			// DFS. 빈공간 넘버링. 빈공간 중에서 구멍은 리스트에 추가.
			holeList = new ArrayList<>();
			int holeNum = 1;
			visited = new int[R - 2][C - 2];
			for (int i = 0; i < R - 2; i++) {
				for (int j = 0; j < C - 2; j++) {

					if (visited[i][j] > 0)
						continue;

					if (map[i][j] == 0) {
						isConnectOut = false;
						dfs(i, j, holeNum);
						if (!isConnectOut)
							holeList.add(holeNum);

						holeNum++;
					}
				}
			}

//			System.out.println();
//			for (int i = 0; i < R-2; i++) {
//				System.out.println(Arrays.toString(visited[i]));
//			}
//			System.out.println(holeList);

			// 녹아 없어질 부분 -1 표시.
			for (int i = 0; i < R - 2; i++) {
				for (int j = 0; j < C - 2; j++) {

					if (visited[i][j] == 0) {    // 치즈인 부분에 대해서
						// 상하좌우가 구멍이 아닌 공기와 닿는 곳이면 -1
						for (int d = 0; d < 4; d++) {
							int nr = i + dr[d];
							int nc = j + dc[d];

							if (nr < 0 || nr >= R - 2 || nc < 0 || nc >= C - 2) {    // 범위를 넘어가면 공기와 닿음
								visited[i][j] = -1;
								continue;
							}

							if (visited[nr][nc] > 0) {
								int v = visited[nr][nc];

								if (!isInHoleList(v)) {    // 구멍이 아닌 겉부분
									visited[i][j] = -1;
								}
							}
						}
					}
				}
			}
//			System.out.println();
//			for (int i = 0; i < R-2; i++) {
//				System.out.println(Arrays.toString(visited[i]));
//			}

			// visited에서 0인 부분은 남은 치즈이다. map[][] 업데이트해준다.
			int count = 0;
			for (int i = 0; i < R - 2; i++) {
				for (int j = 0; j < C - 2; j++) {
					if (visited[i][j] == 0) {
						map[i][j] = 1;
						count++;
					} else {
						map[i][j] = 0;
					}
				}
			}

			hour++;
			if (count == 0)        // 다 녹아 없어졌다면 preCheeze를 업데이트하기 전에 break;
				break;

			preCheeze = count;
		}

		System.out.println(hour);
		System.out.println(preCheeze);
	}

	private static boolean isInHoleList(int v) {
		int size = holeList.size();
		for (int i = 0; i < size; i++) {
			if (holeList.get(i) == v) {
				return true;
			}
		}
		return false;
	}

	private static void dfs(int r, int c, int num) {
		visited[r][c] = num;
		for (int d = 0; d < 4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];

			if (nr < 0 || nr >= R - 2 || nc < 0 || nc >= C - 2) {
				isConnectOut = true;
				continue;
			}

			if (visited[nr][nc] > 0)
				continue;

			if (map[nr][nc] == 0)
				dfs(nr, nc, num);
		}
	}

	static class Pos {
		int r, c;

		Pos() {
		}

		Pos(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
}
