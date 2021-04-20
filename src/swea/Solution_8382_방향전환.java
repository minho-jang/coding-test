package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_8382_방향전환 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk;
		StringBuilder answerSb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			
			stk = new StringTokenizer(br.readLine());
			int[] p1 = {Integer.parseInt(stk.nextToken()), Integer.parseInt(stk.nextToken())};
			int[] p2 = {Integer.parseInt(stk.nextToken()), Integer.parseInt(stk.nextToken())};
			
			int rowDiff = Math.abs(p1[0] - p2[0]);
			int colDiff = Math.abs(p1[1] - p2[1]);
			
			int maxEdge = 0, rest = 0;
			if (rowDiff > colDiff) {
				maxEdge = colDiff;
				rest = rowDiff - colDiff;
			} else {
				maxEdge = rowDiff; 
				rest = colDiff - rowDiff;
			}
			
			int answer = 0;
			answer += maxEdge * 2;
			answer += (rest%2==1) ? 2*rest - 1 : 2*rest;
			
			answerSb.append("#").append(tc).append(" ").append(answer).append("\n");
		}
		
		System.out.print(answerSb.toString());
	}
}

/*
3
0 0 1 4
-1 -1 0 0
0 0 0 2
*/