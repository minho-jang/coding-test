package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_BOJ_17471_게리맨더링 {
    static int N, answer;
    static ArrayList<Integer>[] adjList;
    static int[] populations;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk;

        answer = 987654321;
        N = Integer.parseInt(br.readLine());

        stk = new StringTokenizer(br.readLine());
        populations = new int[N];
        for (int i = 0; i < N; i++) {
            populations[i] = Integer.parseInt(stk.nextToken());
        }

        adjList = new ArrayList[N];
        for (int i = 0; i < N; i++) {
            stk = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(stk.nextToken());
            adjList[i] = new ArrayList<>();
            for (int j = 0; j < num; j++) {
                adjList[i].add(Integer.parseInt(stk.nextToken()) - 1);
            }
        }

        subset(0, new boolean[N]);

        if (answer == 987654321)
            answer = -1;

        System.out.println(answer);
    }

    private static void subset(int cnt, boolean[] visited) {
        if (cnt == N) {
            boolean[] reverseVisited = new boolean[N];
            for (int i = 0; i < N; i++) {
                reverseVisited[i] = !visited[i];
            }

            int popA = getPopulation(visited);
            int popB = getPopulation(reverseVisited);

            if (popA > 0 && popB > 0)
                answer = Math.min(answer, Math.abs(popA - popB));

            return;
        }

        visited[cnt] = true;
        subset(cnt + 1, visited);
        visited[cnt] = false;
        subset(cnt + 1, visited);
    }

    // area의 연결성을 확인하면서 인구합 구하기. area에서 true인 곳을 탐색하므로 visited의 반대개념
    private static int getPopulation(boolean[] area) {
        boolean[] notVisited = Arrays.copyOf(area, N);

        // BFS 시작 찾기
        int start = -1;
        for (int i = 0; i < N; i++) {
            if (notVisited[i]) {
                start = i;
                break;
            }
        }

        if (start == -1)    // area에 소속된 구역이 하나도 없어. 모두 false인 경우
            return -1;

        int sum = 0;        // 인구 수
        Queue<Integer> queue = new LinkedList<>();

        sum += populations[start];
        notVisited[start] = false;
        queue.add(start);

        // BFS
        while (!queue.isEmpty()) {
            int polled = queue.poll();
            for (int ni : adjList[polled]) {    // 인접 리스트 활용
                if (!notVisited[ni])
                    continue;

                sum += populations[ni];
                notVisited[ni] = false;
                queue.add(ni);
            }
        }

        for (int i = 0; i < N; i++) {
            if (notVisited[i]) {    // BFS 끝났는데 true인게 남아있어. 비연결
                return -1;
            }
        }

        return sum;
    }
}

/*

public class Main_BOJ_17471_게리맨더링 {
	static int N, answer;
	static int[][] adjMatrix;
	static boolean[] isConnected;
	static int[] peopleNum;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk;
		
		N = Integer.parseInt(br.readLine());
		stk = new StringTokenizer(br.readLine());
		peopleNum = new int[N];
		for (int i = 0; i < N; i++) {
			peopleNum[i] = Integer.parseInt(stk.nextToken());
		}
		
		adjMatrix = new int[N][N];
		for (int i = 0; i < N; i++) {
			stk = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(stk.nextToken());
			for (int j = 0; j < n; j++) {
				int v = Integer.parseInt(stk.nextToken());
				adjMatrix[i][v-1] = 1;
			}
		}
		
		answer = Integer.MAX_VALUE;
		subset(0, new boolean[N]);
		
		if (answer==Integer.MAX_VALUE)	System.out.println(-1);
		else							System.out.println(answer);
	}
	
	private static void subset(int cnt, boolean[] visited) {
		if (cnt == N) {
			List<Integer> set1 = new ArrayList<>();
			List<Integer> set2 = new ArrayList<>();
			boolean[] visited2 = new boolean[N];
			for (int i = 0; i < N; i++) {
				if (visited[i])  set1.add(i);
				else  {
					set2.add(i);
					visited2[i] = true;
				}
			}
			
			if (set1.size() == 0 || set2.size() == 0) 
				return;
			
			isConnected = Arrays.copyOf(visited2, N);
			isConnected[set1.get(0)] = true;
			int sumSet1 = dfs(set1, set1.get(0));
			if (!allConnected(isConnected))
				return;
			
			isConnected = Arrays.copyOf(visited, N);
			isConnected[set2.get(0)] = true;
			int sumSet2 = dfs(set2, set2.get(0));
			if (!allConnected(isConnected))
				return;
			
			int diff = Math.abs(sumSet2 - sumSet1);
//			System.out.println(set1);
//			System.out.println(set2);
//			System.out.println(sumSet1 + " " + sumSet2);
//			System.out.println();
			answer = Math.min(answer, diff);
			
			return;
		}
		
		visited[cnt] = true;
		subset(cnt+1, visited);
		visited[cnt] = false;
		subset(cnt+1, visited);
	}
	
	private static boolean allConnected(boolean[] visited) {
		boolean flag = true;
		for (int i=0, size=visited.length; i < size; i++) {
			if (!visited[i])
				flag = false;
		}
		return flag;
	}

	private static int dfs(List<Integer> list, int current) {
		int sum = peopleNum[current];
		for (int i : list) {
			if (!isConnected[i] && adjMatrix[current][i] > 0) {
				isConnected[i] = true;
				sum += dfs(list, i);
			}
		}
		
		return sum;	
	}
}


*/