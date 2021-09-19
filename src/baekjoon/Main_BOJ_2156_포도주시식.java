package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main_BOJ_2156_포도주시식 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());
		int[] wine = new int[n];
		for (int i = 0; i < n; i++) {
			wine[i] = Integer.parseInt(br.readLine());
		}

		if (n == 1) {
			System.out.println(wine[0]);
			return;
		}

		int[] memo = new int[3];
		memo[0] = 0;
		memo[1] = wine[0];
		memo[2] = wine[0];

		for (int i = 1; i < n; i++) {
			int[] tmp = new int[3];

			tmp[0] = Arrays.stream(memo).max().getAsInt();
			tmp[1] = memo[0] + wine[i];
			tmp[2] = memo[1] + wine[i];

			memo = tmp;
		}

		int answer = Arrays.stream(memo).max().getAsInt();
		System.out.println(answer);
	}
}