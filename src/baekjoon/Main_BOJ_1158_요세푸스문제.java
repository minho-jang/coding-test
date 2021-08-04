package baekjoon;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main_BOJ_1158_요세푸스문제 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt();
		int K = sc.nextInt();

		Queue<Integer> queue = new LinkedList<>();
		for (int i = 1; i <= N; i++) {
			queue.offer(i);
		}

		int idx = 0;
		int[] answer = new int[N];
		while (!queue.isEmpty()) {
			int cnt = 1;
			while (cnt++ < K) {
				queue.offer(queue.poll());
			}
			answer[idx++] = queue.poll();
		}

		System.out.print("<");
		for (int i = 0; i < N - 1; i++) {
			System.out.print(answer[i] + ", ");
		}
		System.out.print(answer[N - 1] + ">");
		sc.close();
	}
}
