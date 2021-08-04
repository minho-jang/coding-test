package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class Main_BOJ_1958_LCS3 {
	static char[] A, B, C;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input.txt"));

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		A = br.readLine().toCharArray();
		B = br.readLine().toCharArray();
		C = br.readLine().toCharArray();

		int lenA = A.length;
		int lenB = B.length;
		int lenC = C.length;
		int[][][] dp = new int[lenA + 1][lenB + 1][lenC + 1];

		for (int i = 0; i < lenA + 1; i++) {
			for (int j = 0; j < lenB + 1; j++) {
				for (int k = 0; k < lenC + 1; k++) {

					if (i == 0 || j == 0 || k == 0)
						dp[i][j][k] = 0;

					else if (A[i - 1] == B[j - 1] && B[j - 1] == C[k - 1])
						dp[i][j][k] = dp[i - 1][j - 1][k - 1] + 1;

					else {
						int max = Math.max(dp[i - 1][j][k], dp[i][j - 1][k]);
						max = Math.max(max, dp[i][j][k - 1]);
						dp[i][j][k] = max;
					}

				}
			}
		}

		System.out.println(dp[lenA][lenB][lenC]);
	}
}
