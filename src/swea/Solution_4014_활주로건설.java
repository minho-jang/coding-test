package swea;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_4014_활주로건설 {
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("input.txt"));

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk;
		StringBuilder answerSb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {

			stk = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(stk.nextToken());
			int X = Integer.parseInt(stk.nextToken());

			int[][] map = new int[N][N];
			for (int i = 0; i < N; i++) {
				stk = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(stk.nextToken());
				}
			}

			int answer = 0;

			// 행검사
			for (int row = 0; row < N; row++) {
				int visited = 1;
				int prev = map[row][0];
				boolean impossible = false;
				for (int i = 1; i < N; i++) {
					if (prev == map[row][i]) {
						visited++;
						
					} else if (prev - map[row][i] == 1) { // prev가 더 높아 -> 오른쪽에 경사로 설치
						int currentHeight = map[row][i];
						for (int j = 0; j < X; j++) {
							if (i + j >= N || map[row][i + j] != currentHeight) {
								impossible = true;
							}
						}
						if (impossible)
							break;
						
						i += (X-1);
						prev = map[row][i];
						visited = 0;

					} else if (map[row][i] - prev == 1) { // prev가 더 낮아 -> 왼쪽에 경사로 설치
						if (visited < X) {
							impossible = true;
							break;
						}
						
						prev = map[row][i];
						visited = 1;
						
					} else {
						impossible = true;
						break;
					}
				}

				if (!impossible) {
					answer++;
				}
			}

			// 열검사
			for (int col = 0; col < N; col++) {
				int visited = 1;
				int prev = map[0][col];
				boolean impossible = false;
				for (int i = 1; i < N; i++) {
					if (prev == map[i][col]) {
						visited++;
						
					} else if (prev - map[i][col] == 1) { 
						int currentHeight = map[i][col];
						for (int j = 0; j < X; j++) {
							if (i + j >= N || map[i+j][col] != currentHeight) {
								impossible = true;
							}
						}
						if (impossible)
							break;
						
						i += (X-1);
						prev = map[i][col];
						visited = 0;

					} else if (map[i][col] - prev == 1) { 
						if (visited < X) {
							impossible = true;
							break;
						}
						
						prev = map[i][col];
						visited = 1;
						
					} else {
						impossible = true;
						break;
					}
				}

				if (!impossible) {
					answer++;
				}
			}

			answerSb.append("#").append(tc).append(" ").append(answer).append("\n");
		}

		System.out.println(answerSb.toString());
	}
}
