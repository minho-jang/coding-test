package swea;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution_1238_Contact {
	final static int MAX_N = 100;
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk;
		StringBuilder answer = new StringBuilder();
		
		int T = 10;
		for (int tc = 1; tc <= T; tc++) {
			stk = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(stk.nextToken());
			int start = Integer.parseInt(stk.nextToken());
			
			stk = new StringTokenizer(br.readLine());
			int vNum = N/2;

			int[][] links = new int[vNum][2];
			for (int i = 0; i < vNum; i++) {
				links[i][0] = Integer.parseInt(stk.nextToken());
				links[i][1] = Integer.parseInt(stk.nextToken());
			}
			
			boolean[] visited = new boolean[MAX_N+1];
			Queue<NumDepth> queue = new LinkedList<>();
			queue.offer(new NumDepth(start, 1));
			visited[start] = true;
			
			List<NumDepth> nList = new ArrayList<>();
			while(!queue.isEmpty()) {
				NumDepth polled = queue.poll();
				
				nList.add(polled);
				
				for (int i = 0; i < vNum; i++) {
					if (!visited[links[i][1]] && links[i][0] == polled.num) {
						visited[links[i][1]] = true;
						queue.offer(new NumDepth(links[i][1], polled.depth+1));
					}
				}
			}
			
			Collections.sort(nList, (o1, o2) -> {
				if (o1.depth != o2.depth) {
					return Integer.compare(o2.depth, o1.depth);
				} else
					return Integer.compare(o2.num, o1.num);
			});
					
			answer.append("#")
				.append(tc)
				.append(" ")
				.append(nList.get(0).num)
				.append("\n");
		}
		
		System.out.println(answer.toString());
	}
	
	static class NumDepth {
		int num;
		int depth;
		
		NumDepth(int num, int depth) {
			this.num = num;
			this.depth = depth;
		}
		
		public String toString() {
			return num + "(" + depth + ")";
		}
	}
}

