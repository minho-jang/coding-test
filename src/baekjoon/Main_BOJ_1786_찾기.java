package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main_BOJ_1786_찾기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        char[] T = br.readLine().toCharArray();
        char[] P = br.readLine().toCharArray();

        int tLen = T.length;
        int pLen = P.length;
        int[] fail = new int[pLen];
        for (int i = 1, j = 0; i < pLen; i++) {
            while (j > 0 && P[i] != P[j])
                j = fail[j - 1];

            if (P[i] == P[j])
                fail[i] = ++j;
        }

        int cnt = 0;
        List<Integer> idxList = new ArrayList<>();
        for (int i = 0, j = 0; i < tLen; i++) {
            while (j > 0 && T[i] != P[j])
                j = fail[j - 1];

            if (T[i] == P[j]) {
                if (j == pLen - 1) {
                    cnt++;
                    idxList.add((i + 1) - pLen + 1);
                    j = fail[j];
                } else
                    j++;
            }
        }

        // indexOf로 하면 시간초과
//		while(true) {
//			idx = text.indexOf(pattern, idx);
//			if (idx < 0)	break;
//			
//			cnt++;
//			idxList.add(++idx);
//		}

        System.out.println(cnt);
        for (int i : idxList) {
            System.out.print(i + " ");
        }
    }
}
