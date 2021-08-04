package programmers;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Solution_PRG_2021KakaoIntern_거리두기확인하기 {
	private int R, C;
	private int[] dr = {1, -1, 0, 0};
	private int[] dc = {0, 0, 1, -1};
	private boolean[][] visited;

	public static void main(String[] args) {
		Solution_PRG_2021KakaoIntern_거리두기확인하기 sol = new Solution_PRG_2021KakaoIntern_거리두기확인하기();
		String[][] places = {
				{"POOOP", "OXXOX", "OPXPX", "OOXOX", "POXXP"},
				{"POOPX", "OXPXP", "PXXXO", "OXXXO", "OOOPP"},
				{"PXOPX", "OXOXP", "OXPOX", "OXXOP", "PXPOX"},
				{"OOOXX", "XOOOX", "OOOXX", "OXOOX", "OOOOO"},
				{"PXPXP", "XPXPX", "PXPXP", "XPXPX", "PXPXP"}
		};
		System.out.println(Arrays.toString(sol.solution(places)));
	}

	public int[] solution(String[][] places) {
		int[] answer = new int[places.length];

		for (int i = 0; i < places.length; i++) {
			int ans = 1;
			boolean hit = false;
			String[] place = places[i];

			for (int j = 0; !hit && j < place.length; j++) {
				for (int k = 0; !hit && k < place[j].length(); k++) {
					if (place[j].charAt(k) == 'P') {
						if (isNotEnoughDistance(place, j, k)) {
							ans = 0;
							hit = true;
						}
					}
				}
			}

			answer[i] = ans;
		}

		return answer;
	}

	private boolean isNotEnoughDistance(String[] map, int r, int c) {
		System.out.println(Arrays.toString(map));

		R = map.length;
		C = map[0].length();
		visited = new boolean[R][C];
		for (int i = 0; i < R; i++)
			Arrays.fill(visited[i], false);

		Queue<Integer> queue = new LinkedList<>();

		visited[r][c] = true;
		queue.add(r);
		queue.add(c);
		queue.add(0);

		while (!queue.isEmpty()) {
			int curR = queue.poll();
			int curC = queue.poll();
			int curDistance = queue.poll();
			System.out.println(curR + " " + curC + " " + curDistance);

			for (int d = 0; d < 4; d++) {
				int nr = curR + dr[d];
				int nc = curC + dc[d];
				int nd = curDistance + 1;

				if (isOut(nr, nc)) continue;
				if (visited[nr][nc]) continue;
				if (nd > 2) continue;
				if (map[nr].charAt(nc) == 'X') continue;
				if (map[nr].charAt(nc) == 'P') return true;

				visited[nr][nc] = true;
				queue.add(nr);
				queue.add(nc);
				queue.add(nd);
			}
		}

		return false;
	}

	private boolean isOut(int nr, int nc) {
		return nr < 0 || nr >= R || nc < 0 || nc >= C;
	}
}
