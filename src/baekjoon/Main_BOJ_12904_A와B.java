package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_BOJ_12904_A와B {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String src = br.readLine();
        String dest = br.readLine();
        StringBuilder destSb = new StringBuilder(dest);
        int destLen = dest.length();
        int srcLen = src.length();
        for (int i = destLen - 1; i >= srcLen; i--) {
            if (destSb.charAt(i) == 'A') {
                destSb.deleteCharAt(i);

            } else if (destSb.charAt(i) == 'B') {
                destSb.deleteCharAt(i);
                destSb.reverse();

            }
//			System.out.println(destSb.toString());
        }

        if (destSb.toString().equals(src)) {
            System.out.println(1);
        } else
            System.out.println(0);
    }
}
