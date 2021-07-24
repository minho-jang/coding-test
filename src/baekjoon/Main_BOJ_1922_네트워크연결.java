package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_BOJ_1922_네트워크연결 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk;

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        int[][] edgeList = new int[M][3];
        for (int i = 0; i < M; i++) {
            stk = new StringTokenizer(br.readLine());
            edgeList[i][0] = Integer.parseInt(stk.nextToken()) - 1;
            edgeList[i][1] = Integer.parseInt(stk.nextToken()) - 1;
            edgeList[i][2] = Integer.parseInt(stk.nextToken());
        }

        int answer = 0;
        // 크루스칼
        init(N);
        Arrays.sort(edgeList, (o1, o2) -> o1[2] - o2[2]);
        for (int i = 0; i < M; i++) {
            if (union(edgeList[i][0], edgeList[i][1])) {
                answer += edgeList[i][2];
            }
        }

        System.out.println(answer);
    }

    private static boolean union(int a, int b) {
        int pa = find(a);
        int pb = find(b);
        if (pa != pb) {
            parent[pb] = pa;
            return true;
        }
        return false;
    }

    private static int find(int a) {
        if (parent[a] == a)
            return a;
        else
            return parent[a] = find(parent[a]);
    }

    private static void init(int num) {
        parent = new int[num];
        for (int i = 0; i < num; i++) {
            parent[i] = i;
        }
    }

    private static int[] parent;
}
