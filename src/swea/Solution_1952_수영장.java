package swea;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution_1952_수영장 {
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input2.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk;
		StringBuilder answer = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			stk = new StringTokenizer(br.readLine());
			int[] cost = new int[4];
			for (int i = 0; i < 4; i++) {
				cost[i] = Integer.parseInt(stk.nextToken());
			}
			
			stk = new StringTokenizer(br.readLine());
			int[] plan = new int[13];
			for (int i = 1; i < 13; i++) {
				plan[i] = Integer.parseInt(stk.nextToken());
			}
			
			int[] dp = new int[13];
			for (int i = 1; i < 13; i++) {
				if (i < 3) {
					dp[i] = Math.min(dp[i-1]+cost[0]*plan[i], dp[i-1]+cost[1]);
					
				} else {
					int tmp = Math.min(dp[i-1]+cost[0]*plan[i], dp[i-1]+cost[1]);
					dp[i] = Math.min(tmp, dp[i-3]+cost[2]);
					
				}
			}
			
//			System.out.println(Arrays.toString(dp));
			
			int ans = Math.min(dp[12], cost[3]);
			
			answer.append("#")
				.append(tc)
				.append(" ")
				.append(ans)
				.append("\n");
		}
		
		System.out.println(answer.toString());
		
	}
}
