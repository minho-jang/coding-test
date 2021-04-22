package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Solution_2117_홈방범서비스 {
	static int[][] map;
	static int N, M;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	static ArrayList<Pos> houseList;
	static class Pos {
		int r, c;
		public Pos(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk;
		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			
			stk = new StringTokenizer(br.readLine());
			N = Integer.parseInt(stk.nextToken());
			M = Integer.parseInt(stk.nextToken());
			
			houseList = new ArrayList<>();
			map = new int[N][N];
			for (int i = 0; i < N; i++) {
				stk = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(stk.nextToken());
					if (map[i][j] == 1) {
						houseList.add(new Pos(i,j));
					}
				}
			}
			
			int answer = 0;
			for (int k = 1; k <= N+1; k++) {
				int cost = getCost(k);
				
				for (int i = 0; i < N; i++) {
					for (int j = 0; j < N; j++) {
						
						int cnt = getHouseCount(new Pos(i, j), k);
						if (cnt*M - cost >= 0) {
							answer = Math.max(answer, cnt);
						}
						
					}
				}
			}
			
			System.out.println("#" + tc + " " + answer);
		}
	}

	private static int getHouseCount(Pos center, int k) {
		// 각 집을 탐색
		int count = 0;
		for (Pos p : houseList) {
			int d = getDistance(center, p);
			if (d < k)
				count++;
		}
		
		return count;
	}

	private static int getDistance(Pos a, Pos b) {
		return Math.abs(a.r-b.r) + Math.abs(a.c-b.c);
	}

	private static int getCost(int k) {
		return k*k + (k-1)*(k-1);
	}
}
