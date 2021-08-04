package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_BOJ_12886_돌그룹 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk;

		stk = new StringTokenizer(br.readLine());
		int[] origin = new int[3];
		int[] arr = new int[3];
		for (int i = 0; i < 3; i++) {
			arr[i] = Integer.parseInt(stk.nextToken());
			origin[i] = arr[i];
		}

		Queue<Node> queue = new LinkedList<>();
		queue.add(new Node(arr, 0));

		final int MAX = 11;
		int answer = 0;
		while (!queue.isEmpty()) {
			Node n = queue.poll();
			if (n.allEquals()) {
				answer = 1;
				break;
			}
			if (n.cnt > MAX) {
				answer = 0;
				break;
			}

			Node n1 = new Node(new int[]{n.arr[0] + n.arr[0], n.arr[1] - n.arr[0], n.arr[2]}, n.cnt + 1);
			Node n2 = new Node(new int[]{n.arr[0] + n.arr[0], n.arr[1], n.arr[2] - n.arr[0]}, n.cnt + 1);
			Node n3 = new Node(new int[]{n.arr[0], n.arr[1] + n.arr[1], n.arr[2] - n.arr[1]}, n.cnt + 1);
			queue.add(n1);
			queue.add(n2);
			queue.add(n3);
		}

		System.out.println(answer);
	}

	static class Node {
		int[] arr;
		int cnt;

		Node(int[] arr, int cnt) {
			Arrays.sort(arr);
			this.arr = arr;
			this.cnt = cnt;
		}

		boolean allEquals() {
			return arr[0] == arr[1] && arr[1] == arr[2];
		}
	}
}
