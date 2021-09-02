package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_BOJ_18500_미네랄2 {
	private static int R, C;
	private static char[][] map;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk;

		stk = new StringTokenizer(br.readLine());
		R = Integer.parseInt(stk.nextToken());
		C = Integer.parseInt(stk.nextToken());

		map = new char[R][C];
		for (int i = 0; i < R; i++) {
			String line = br.readLine();
			for (int j = 0; j < C; j++) {
				map[i][j] = line.charAt(j);
			}
		}

		int N = Integer.parseInt(br.readLine());
		int[] stickHeights = new int[N];
		stk = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			stickHeights[i] = Integer.parseInt(stk.nextToken());
		}

		boolean isLeftTurn = true;
		for (int stickHeight : stickHeights) {
//			printMap(0);

			Pos mineralHit = getMineralHit(stickHeight, isLeftTurn);
			if (mineralHit != null) {
				removeMineral(mineralHit);
				moveByGravity(mineralHit);
			}

			isLeftTurn = !isLeftTurn;
		}

		printMap();
	}

	private static Pos getMineralHit(int height, boolean isLeftTurn) {
		int row = R - height;
		if (isLeftTurn) {
			for (int i = 0; i < C; i++) {
				if (map[row][i] != '.') {
					return new Pos(row, i);
				}
			}
		} else {
			for (int i = C - 1; i >= 0; i--) {
				if (map[row][i] != '.') {
					return new Pos(row, i);
				}
			}
		}

		return null;
	}

	private static void removeMineral(Pos pos) {
		map[pos.r][pos.c] = '.';
	}

	private static void moveByGravity(Pos removedPos) {
		for (int d = 0; d < 4; d++) {
			int nr = removedPos.r + dr[d];
			int nc = removedPos.c + dc[d];
			if (!isOut(nr, nc) && map[nr][nc] != '.') {
				List<Pos> clusterPosList = checkCluster(nr, nc);

				if (clusterPosList.size() > 0) {
					clusterPosList.sort((o1, o2) -> o2.r - o1.r);
					while (isDownClear(clusterPosList)) {
						moveClusterDown(clusterPosList);
					}
				}
			}
		}
	}

	final private static int[] dr = {-1, 1, 0, 0};
	final private static int[] dc = {0, 0, -1, 1};

	private static List<Pos> checkCluster(int row, int col) {
		List<Pos> clusterPosList = new ArrayList<>();
		boolean[][] visited = new boolean[R][C];
		Queue<Pos> queue = new LinkedList<>();

		visited[row][col] = true;
		queue.add(new Pos(row, col));

		while (!queue.isEmpty()) {
			Pos polled = queue.poll();
			if (polled.r == R - 1) {
				clusterPosList.clear();
				break;
			}

			clusterPosList.add(polled);

			for (int d = 0; d < 4; d++) {
				int nr = polled.r + dr[d];
				int nc = polled.c + dc[d];
				if (!isOut(nr, nc) && map[nr][nc] != '.' && !visited[nr][nc]) {
					visited[nr][nc] = true;
					queue.add(new Pos(nr, nc));
				}
			}
		}

		return clusterPosList;
	}

	private static boolean isDownClear(List<Pos> clusterPosList) {
		int bottomRowNum = clusterPosList.get(0).r;
		if (bottomRowNum == R - 1) {
			return false;
		}

		boolean[][] isClusterArea = new boolean[R][C];
		for (Pos pos : clusterPosList) {
			isClusterArea[pos.r][pos.c] = true;
		}

		for (Pos pos : clusterPosList) {
			if (!isClusterArea[pos.r + 1][pos.c] && map[pos.r + 1][pos.c] != '.') {
				return false;
			}
		}

		return true;
	}

	private static boolean isOut(int r, int c) {
		return r < 0 || r >= R || c < 0 || c >= C;
	}

	private static void moveClusterDown(List<Pos> posList) {
		for (Pos pos : posList) {
			map[pos.r][pos.c] = '.';
			map[pos.r + 1][pos.c] = 'x';
		}
		for (Pos pos : posList) {
			pos.r = pos.r + 1;
		}
	}

	private static void printMap() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				sb.append(map[i][j] != '.' ? 'x' : '.');
			}
			sb.append("\n");
		}
		System.out.print(sb);
	}

	static class Pos {
		int r, c;

		Pos(int r, int c) {
			this.r = r;
			this.c = c;
		}

		public String toString() {
			return "(" + r + "," + c + ")";
		}
	}
}
