package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main_BOJ_1708_블록껍질 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk;

		int N = Integer.parseInt(br.readLine());

		stk = new StringTokenizer(br.readLine());
		int minX = Integer.parseInt(stk.nextToken());
		int minY = Integer.parseInt(stk.nextToken());

		Pos[] points = new Pos[N];
		points[0] = new Pos(minX, minY);
		for (int i = 1; i < N; i++) {
			stk = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(stk.nextToken());
			int y = Integer.parseInt(stk.nextToken());
			points[i] = new Pos(x, y);
			if (minY > y) {
				minY = y;
				minX = x;
			} else if (minY == y && minX > x) {
				minY = y;
				minX = x;
			}
		}

		Pos startPos = new Pos(minX, minY);

		// start부터 각도 + 거리로 정렬
		Arrays.sort(points, (o1, o2) -> {
			double d1 = getDegree(startPos, o1);
			double d2 = getDegree(startPos, o2);
			if (d1 != d2) {
				return Double.compare(d1, d2);

			} else {
				double dist1 = getDistance(startPos, o1);
				double dist2 = getDistance(startPos, o2);
				return Double.compare(dist1, dist2);
			}
		});


		// 그라함스 스캔 알고리즘
		Stack<Pos> stack = new Stack<>();
		stack.push(points[0]);
		stack.push(points[1]);

		for (int i = 2; i < N; i++) {

			while (stack.size() >= 2) {
				Pos popped = stack.pop();

				// peek >> pop >> points[i] 벡터외적
				Pos v1 = new Pos(popped.x - stack.peek().x, popped.y - stack.peek().y);
				Pos v2 = new Pos(points[i].x - popped.x, points[i].y - popped.y);

				int result = crossProduct(v1, v2);

				if (result > 0) {
					stack.push(popped);
					break;
				}
			}

			stack.push(points[i]);
		}

		System.out.println(stack.size());

	}

	private static double getDistance(Pos p1, Pos p2) {
		double dx = p2.x - p1.x;
		double dy = p2.y - p1.y;
		return Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));
	}

	private static double getDegree(Pos p1, Pos p2) {
		double dx = p2.x - p1.x;
		double dy = p2.y - p1.y;
		double r = Math.toDegrees(Math.atan2(dy, dx));

		return r < 0 ? r + 360.0 : r;
	}

	private static int crossProduct(Pos v1, Pos v2) {
		return v1.x * v2.y - v1.y * v2.x;
	}

	static class Pos {
		int x, y;

		public Pos(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}
}
