package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

// A -> B
public class Main_BOJ_16953_AtoB {
    static int answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk;

        stk = new StringTokenizer(br.readLine());
        long src = Integer.parseInt(stk.nextToken());
        long dest = Integer.parseInt(stk.nextToken());

        answer = -1;

        // BFS
        int level = 0;
        Deque<Long> queue = new ArrayDeque<>();
        queue.offer(src);
        while (!queue.isEmpty()) {
//			System.out.println("level: " + level);

            boolean hit = false;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                long polled = queue.poll();
//				System.out.print(polled + " ");
                if (polled == dest) {
                    hit = true;
                    break;
                }
                if (polled > dest) {
                    continue;
                }

                queue.offer(polled * 10 + 1);
                queue.offer(polled * 2);
            }
//			System.out.println();

            if (hit) {
                answer = level;
                break;
            }

            level++;
        }

        if (answer >= 0)
            answer++;
        System.out.println(answer);
    }
}

/*
DFS 풀이

// A -> B
public class Main_BOJ_16953_AtoB2 {
	static int answer;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk;
		
		stk = new StringTokenizer(br.readLine());
		long src = Integer.parseInt(stk.nextToken());
		long dest = Integer.parseInt(stk.nextToken());
		
		answer = -1;
		dfs(src, dest, 0);
		
		if (answer >= 0)
			answer++;
		System.out.println(answer);
	}
	
	private static void dfs(long cur, long dest, int count) {
		if (cur > dest)
			return;
		if (cur == dest) {
			if (answer >= 0)
				answer = Math.min(answer, count);
			else
				answer = count;
			return;
		}
		
		dfs(cur*2, dest, count+1);
		dfs(cur*10+1, dest, count+1);
	}
}
*/
