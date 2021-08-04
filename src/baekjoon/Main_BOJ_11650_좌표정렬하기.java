package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_BOJ_11650_좌표정렬하기 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk;
		StringBuilder answer = new StringBuilder();

		int N = Integer.parseInt(br.readLine());
		Integer[][] coors = new Integer[N][2];
		for (int i = 0; i < N; i++) {
			stk = new StringTokenizer(br.readLine());
			coors[i][0] = Integer.parseInt(stk.nextToken());
			coors[i][1] = Integer.parseInt(stk.nextToken());
		}

		Arrays.sort(coors, (o1, o2) -> {
			if (o1[0].intValue() == o2[0].intValue())    // Integer wrapper class 사용 주의!!
				return o1[1].compareTo(o2[1]);
			else
				return o1[0].compareTo(o2[0]);
		});

		for (int i = 0; i < N; i++) {
			answer.append(coors[i][0] + " " + coors[i][1] + "\n");
		}

		System.out.println(answer.toString());
	}
}