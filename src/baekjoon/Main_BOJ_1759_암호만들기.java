package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_BOJ_1759_암호만들기 {
    final static char[] mo = {'a', 'e', 'i', 'u', 'o'};

    static int L, C;
    static char[] c;
    static StringBuilder answer = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk;

        stk = new StringTokenizer(br.readLine());
        L = Integer.parseInt(stk.nextToken());    // 암호 길이
        C = Integer.parseInt(stk.nextToken());    // 문자 수

        stk = new StringTokenizer(br.readLine());
        c = new char[C];
        for (int i = 0; i < C; i++) {
            c[i] = stk.nextToken().charAt(0);
        }
        Arrays.sort(c);

        comb(0, 0, new char[L], new int[2]);

        System.out.println(answer.toString());
    }

    private static void comb(int start, int count, char[] result, int[] status) {
        if (count == L) {
            if (status[0] >= 2 && status[1] >= 1) {
                answer.append(result)
                        .append("\n");
            }

            return;
        }

        for (int i = start; i < C; i++) {
            result[count] = c[i];

            if (isMo(c[i])) {    // 모음 확인
                status[1]++;
                comb(i + 1, count + 1, result, status);
                status[1]--;
            } else {
                status[0]++;
                comb(i + 1, count + 1, result, status);
                status[0]--;
            }
        }
    }

    private static boolean isMo(char ch) {
        for (int j = 0; j < 5; j++) {
            if (mo[j] == ch) {
                return true;
            }
        }

        return false;
    }
}
