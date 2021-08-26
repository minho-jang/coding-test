package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_BOJ_10021_WateringTheFields {
	static class Pos {
		int idx, x, y;

		Pos(int idx, int x, int y) {
			this.idx = idx;
			this.x = x;
			this.y = y;
		}
	}

	static class Edge {
		Pos start, end;
		double distance;

		Edge(Pos start, Pos end) {
			this.start = start;
			this.end = end;
			this.distance = Math.pow(end.x - start.x, 2) + Math.pow(end.y - start.y, 2);
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk;

		stk = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(stk.nextToken());
		int C = Integer.parseInt(stk.nextToken());

		Pos[] fields = new Pos[N];
		for (int i = 0; i < N; i++) {
			stk = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(stk.nextToken());
			int y = Integer.parseInt(stk.nextToken());
			fields[i] = new Pos(i, x, y);
		}

		boolean[] visited = new boolean[N];
		PriorityQueue<Edge> pq = new PriorityQueue<>((e1, e2) -> Double.compare(e1.distance, e2.distance));

		visited[0] = true;
		for (int i = 0; i < N; i++) {
			if (i != 0) {
				pq.add(new Edge(fields[0], fields[i]));
			}
		}

		int answer = 0;
		int count = 1;
		while (count != N) {
			if (pq.isEmpty()) {
				answer = -1;
				break;
			}

			Edge minEdge = pq.poll();

			if (minEdge.distance < C) {
				continue;
			}
			if (visited[minEdge.end.idx]) {
				continue;
			}

			visited[minEdge.end.idx] = true;
			answer += minEdge.distance;
			count++;

			for (int i = 0; i < N; i++) {
				if (i != minEdge.end.idx) {
					pq.add(new Edge(fields[minEdge.end.idx], fields[i]));
				}
			}
		}

		System.out.println(answer);
	}
}

/*
3 11
0 2
5 0
4 3
return 46

3 20
0 2
5 0
4 3
return -1

4 5
0 1
2 2
3 1
4 0
return 22

8 0
0 1
1 0
2 2
1 1
3 1
4 2
4 0
5 1
return 12
 */