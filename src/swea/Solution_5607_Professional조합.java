package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_5607_Professional조합 {
	static final int MOD = 1234567891;
	static final int MAX_N = 1000000;
	
	static long[] facto;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk;
		
		facto = new long[MAX_N+1];
		facto[0] = 1;
		for (int i = 1; i < MAX_N+1; i++) {
			facto[i] = (facto[i-1] * i) % MOD;
		}
		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			
			stk = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(stk.nextToken());
			int R = Integer.parseInt(stk.nextToken());
			
			long answer = comb(N, R);
			System.out.println("#" + tc + " " + answer);
			
		}
	}

	private static long comb(int n, int r) {
		if (r == 0)
			return 1L;
		
		return (((facto[n] * 
				power(facto[n-r], MOD-2)) % MOD *
				power(facto[r], MOD-2)) % MOD) % MOD;
	}

	private static long power(long x, int y) {
		long result = 1L;
		while (y > 0) {
			if (y % 2 == 1)
				result = (result * x) % MOD;
			y = y >> 1;
			x = (x*x) % MOD;
		}
		return result;
	}
}
