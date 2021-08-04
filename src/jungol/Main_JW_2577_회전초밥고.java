package jungol;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_JW_2577_회전초밥고 {
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

/*
plate[]: 접시
0 1 2 3  4 5 6 7
7 9 7 30 2 7 9 25

sushi[]: 초밥의 종류
0 1 2 ... 7 8 9 ... 25 ... 30
0 0 0 ... 0 0 0 ... 0  ... 0

  쿠폰 미리 더해놓는다. (c=30)
sushi[c] = 1

>> i=0, k=4
	선택 : 7 9 7 30 (start=0, end=3)
	sushi[]
  0 1 2 ... 7 8 9 ... 25 ... 30
  0 0 0 ... 2 0 1 ... 0  ... 2
	kindCount = 3
	
>> i=1, k=4
	선택 : 9 7 30 2 (start=1, end=4)
	sushi[]
  0 1 2 ... 7 8 9 ... 25 ... 30
  0 0 1 ... 1 0 1 ... 0  ... 2
  7이 줄었지만 0이 아니고, 2가 늘었는데 1이므로 종류가 1 증가
	kindCount = 4

*/
