package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BOJ_16198_에너지모으기 {
    static int N;
    static long answer;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk;

        N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        stk = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(stk.nextToken());
        }

        dfs(arr, 0L);

        System.out.println(answer);
    }

    private static void dfs(int[] arr, long energy) {
        if (arr.length == 2) {
            answer = Math.max(answer, energy);
            return;
        }

        for (int i = 1; i < arr.length - 1; i++) {
            int[] newArr = removeEleByIndex(arr, i);
            dfs(newArr, energy + (arr[i - 1] * arr[i + 1]));
        }
    }

    private static int[] removeEleByIndex(int[] arr, int idx) {
        int[] newArr = new int[arr.length - 1];
        int newArrIdx = 0;
        for (int i = 0; i < arr.length; i++) {
            if (idx != i)
                newArr[newArrIdx++] = arr[i];
        }
        return newArr;
    }
}
