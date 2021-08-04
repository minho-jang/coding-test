package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BOJ_15961_회전초밥 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk;

		stk = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(stk.nextToken());
		int d = Integer.parseInt(stk.nextToken());
		int k = Integer.parseInt(stk.nextToken());
		int c = Integer.parseInt(stk.nextToken());
		int[] plates = new int[N];
		for (int i = 0; i < N; i++) {
			plates[i] = Integer.parseInt(br.readLine());
		}

		int answer = 0;
		int[] sushi = new int[d + 1];
		sushi[c] = 1;

		int kindCount = 1;
		for (int i = 0; i < N; i++) {
			if (i == 0) {
				for (int j = 0; j < k; j++) {
					sushi[plates[j]]++;
					if (sushi[plates[j]] == 1)
						kindCount++;
				}
				answer = kindCount;
				continue;
			}

			int start = i;
			int end = start + k - 1 >= N ? (start + k - 1) % N : start + k - 1;

			sushi[plates[start - 1]]--;
			if (sushi[plates[start - 1]] == 0)
				kindCount--;

			sushi[plates[end]]++;
			if (sushi[plates[end]] == 1)
				kindCount++;

			answer = Integer.max(answer, kindCount);
		}

		System.out.println(answer);
	}
}
