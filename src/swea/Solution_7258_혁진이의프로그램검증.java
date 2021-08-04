package swea;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution_7258_혁진이의프로그램검증 {
	static int[] dr = {0, 1, 0, -1};
	static int[] dc = {1, 0, -1, 0};

	static Queue<int[]> queue;
	static int R, C, answer, memory, commandCount;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input.txt"));

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk;
		StringBuilder answerSb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			answer = 0;
			queue = new LinkedList<>();
			memory = 0;
			commandCount = 0;

			stk = new StringTokenizer(br.readLine());
			R = Integer.parseInt(stk.nextToken());
			C = Integer.parseInt(stk.nextToken());

			char[][] program = new char[R][C];
			for (int i = 0; i < R; i++) {
				program[i] = br.readLine().toCharArray();
			}

			queue.offer(new int[]{0, 0, 0});
			excute(program);

			answerSb.append("#")
					.append(tc)
					.append(" ")
					.append(answer == 1 ? "YES" : "NO")
					.append("\n");
		}

		System.out.println(answerSb.toString());
	}

	static void excute(char[][] program) {

		while (!queue.isEmpty()) {
			int[] polled = queue.poll();
//			System.out.println(polled[0] + " " + polled[1] + " " + polled[2] + "  " + "mem=" + memory);
			commandCount++;

			if (commandCount > 1000) {
				return;
			}

			char c = program[polled[0]][polled[1]];
			if (c == '@') {
				answer = 1;
				return;

			} else if (c == '?') {
				for (int i = 0; i < 4; i++) {
					queue.offer(move(polled[0], polled[1], i));
				}

			} else if (c == '>') {
				queue.offer(move(polled[0], polled[1], 0));

			} else if (c == 'v') {
				queue.offer(move(polled[0], polled[1], 1));

			} else if (c == '<') {
				queue.offer(move(polled[0], polled[1], 2));

			} else if (c == '^') {
				queue.offer(move(polled[0], polled[1], 3));

			} else if (c == '_') {
				if (memory == 0) {
					queue.offer(move(polled[0], polled[1], 0));
				} else {
					queue.offer(move(polled[0], polled[1], 2));
				}

			} else if (c == '|') {
				if (memory == 0) {
					queue.offer(move(polled[0], polled[1], 1));
				} else {
					queue.offer(move(polled[0], polled[1], 3));
				}

			} else if (c == '+') {
				memory++;
				memory = memory >= 16 ? memory - 16 : memory;
				queue.offer(move(polled[0], polled[1], polled[2]));

			} else if (c == '-') {
				memory--;
				memory = memory < 0 ? memory + 16 : memory;
				queue.offer(move(polled[0], polled[1], polled[2]));

			} else if (c >= '0' && c <= '9') {
				memory = c - '0';
				queue.offer(move(polled[0], polled[1], polled[2]));

			} else if (c == '.') {
				queue.offer(move(polled[0], polled[1], polled[2]));

			}
		}

	}

	static int[] move(int r, int c, int d) {
		int nextR = r + dr[d];
		int nextC = c + dc[d];

		nextR = nextR < 0 ? nextR + R : nextR;
		nextR = nextR >= R ? nextR - R : nextR;
		nextC = nextC < 0 ? nextC + C : nextC;
		nextC = nextC >= C ? nextC - C : nextC;

		return new int[]{nextR, nextC, d};
	}
}
