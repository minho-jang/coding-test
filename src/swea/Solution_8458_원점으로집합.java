package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_8458_원점으로집합 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk;
		StringBuilder answerSb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {

			int answer = 0;
			int N = Integer.parseInt(br.readLine());

			int maxDistance = 0;
			int[] distance = new int[N];
			for (int i = 0; i < N; i++) {
				stk = new StringTokenizer(br.readLine());
				int d = Math.abs(Integer.parseInt(stk.nextToken())) + Math.abs(Integer.parseInt(stk.nextToken()));
				distance[i] = d;
				if (d > maxDistance)
					maxDistance = d;
			}

			if (maxDistance == 0) {
				answerSb.append("#").append(tc).append(" ").append(0).append("\n");
				continue;
			}

			// 홀짝 섞여있는지 확인
			int r = distance[0] % 2;
			for (int i = 1; i < N; i++) {
				if (r != distance[i] % 2) {
					answer = -1;
					break;
				}
			}

			if (answer == 0) {
				// 각 i에 대하여 확인
				for (int i = 0; i < 66000; i++) {
					long max = 0L + i * (i + 1L) / 2L;
					if (r != max % 2)
						continue;
					if (maxDistance > max)
						continue;

					answer = i;
					break;
				}
			}

			answerSb.append("#").append(tc).append(" ").append(answer).append("\n");
		}

		System.out.print(answerSb.toString());
	}
}