package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_BOJ_16562_친구비 {
	static int N;
	static int[] parents;
	
	private static void make() {
		for (int i = 0; i < N; i++) {
			parents[i] = i;
		}
	}
	private static int find(int a) {
		if (a == parents[a])
			return a;
		else
			return parents[a] = find(parents[a]);
	}
	private static void union(int a, int b) {
		int pa = find(a);
		int pb = find(b);
		
		parents[pb] = pa;	// b를 a집합에 합체
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk;
		
		stk = new StringTokenizer(br.readLine());
		N = Integer.parseInt(stk.nextToken());	// 학생 수
		int M = Integer.parseInt(stk.nextToken());	// 관계 수
		int money = Integer.parseInt(stk.nextToken());	// 가지고 있는 돈
		
		stk = new StringTokenizer(br.readLine());
		int[] friendMoney = new int[N];	// 친구비
		for (int i = 0; i < N; i++) {
			friendMoney[i] = Integer.parseInt(stk.nextToken());	
		}
		
		parents = new int[N];
		make();
		for (int i = 0; i < M; i++) {
			stk = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(stk.nextToken())-1;
			int v = Integer.parseInt(stk.nextToken())-1;
			
			union(u, v);
		}
		
		int[] minMoney = new int[N];
		Arrays.fill(minMoney, -1);
		for (int i = 0; i < N; i++) {
			int root = find(i);
			if (minMoney[root] < 0)
				minMoney[root] = friendMoney[i];
			else {
				if (minMoney[root] > friendMoney[i])
					minMoney[root] = friendMoney[i];
			}
		}
		
		int needMoney = 0;
		for (int i = 0; i < N; i++) {
			if (minMoney[i] >= 0)	needMoney += minMoney[i];
		}
		
		if (needMoney > money)	System.out.println("Oh no");
		else					System.out.println(needMoney);
	}
}
