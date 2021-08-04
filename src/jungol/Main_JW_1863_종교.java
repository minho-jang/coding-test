package jungol;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main_JW_1863_종교 {
	static int[] parents;
	static int[] rank;
	static int N;

	private static void make() {
		for (int i = 1; i <= N; i++) {
			parents[i] = i;
			rank[i] = 0;
		}
	}

	private static int find(int a) {
		if (parents[a] == a)
			return a;

		return parents[a] = find(parents[a]);
	}

	private static boolean union(int a, int b) {
		int pa = find(a);
		int pb = find(b);

		if (pa == pb)
			return false;

		if (rank[pa] > rank[pb])
			parents[pb] = pa;
		else {
			parents[pa] = pb;
			if (rank[pa] == rank[pb])
				rank[pb]++;
		}
		return true;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk;

		stk = new StringTokenizer(br.readLine());
		N = Integer.parseInt(stk.nextToken());
		parents = new int[N + 1];
		rank = new int[N + 1];

		make();

		int M = Integer.parseInt(stk.nextToken());
		for (int i = 0; i < M; i++) {
			stk = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(stk.nextToken());
			int b = Integer.parseInt(stk.nextToken());
			union(a, b);
		}

		// 랭크 안써도 런타임 에러 나지 않음
//		int count = 0;
//		for (int i = 1; i <= N; i++) {
//			if (i == parents[i])
//				count++;
//		}
//		
//		System.out.println(count);

		// 랭크를 쓰지 않으면 런타임 에러. 매번 find() 호출하면서 StackOverFlow가 발생한다.
		HashSet<Integer> set = new HashSet<>();
		for (int i = 1; i <= N; i++) {
			set.add(find(i));
		}

		System.out.println(set.size());
	}
}
