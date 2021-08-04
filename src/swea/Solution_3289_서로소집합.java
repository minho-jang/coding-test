package swea;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution_3289_서로소집합 {
	static int N;
	static int[] parents;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk;

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			StringBuilder answer = new StringBuilder();
			answer.append("#").append(tc).append(" ");

			stk = new StringTokenizer(br.readLine());
			N = Integer.parseInt(stk.nextToken());
			parents = new int[N + 1];
			Arrays.fill(parents, -1);

			int M = Integer.parseInt(stk.nextToken());
			for (int i = 0; i < M; i++) {
				stk = new StringTokenizer(br.readLine());
				int isUnion = Integer.parseInt(stk.nextToken());
				int a = Integer.parseInt(stk.nextToken());
				int b = Integer.parseInt(stk.nextToken());

				if (isUnion == 1) {
					if (findSet(a) == findSet(b))
						answer.append("1");
					else
						answer.append("0");

				} else if (isUnion == 0) {
					union(a, b);

				}
			}//for

			System.out.println(answer.toString());
		}//tc
	}

	private static int findSet(int a) {
		if (parents[a] == -1)
			return a;
		return parents[a] = findSet(parents[a]);
	}

	private static void union(int a, int b) {
		int aRoot = findSet(a);
		int bRoot = findSet(b);

		if (aRoot != bRoot)
			parents[bRoot] = aRoot;
	}
}
