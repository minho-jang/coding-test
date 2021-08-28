package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_BOJ_5014_스타트링크 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(br.readLine());

		int totalFloor = Integer.parseInt(stk.nextToken());
		int start = Integer.parseInt(stk.nextToken());
		int end = Integer.parseInt(stk.nextToken());
		int jumpUp = Integer.parseInt(stk.nextToken());
		int jumpDown = Integer.parseInt(stk.nextToken());

		boolean[] visited = new boolean[totalFloor + 1];
		Queue<Node> queue = new LinkedList<>();

		visited[start] = true;
		queue.add(new Node(start, 0));

		int answer = -1;
		while (!queue.isEmpty()) {
			Node polled = queue.poll();
			if (polled.floor == end) {
				answer = polled.count;
				break;
			}

			int upNext = polled.floor + jumpUp;
			if ((1 <= upNext && upNext <= totalFloor) && !visited[upNext]) {
				visited[upNext] = true;
				queue.add(new Node(upNext, polled.count + 1));
			}

			int downNext = polled.floor - jumpDown;
			if ((1 <= downNext && downNext <= totalFloor) && !visited[downNext]) {
				visited[downNext] = true;
				queue.add(new Node(downNext, polled.count + 1));
			}
		}

		if (answer == -1) {
			System.out.println("use the stairs");
		} else {
			System.out.println(answer);
		}
	}
}

class Node {
	int floor, count;

	Node(int floor, int count) {
		this.floor = floor;
		this.count = count;
	}
}
