package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_BOJ_12871_무한문자열 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String s = br.readLine();
		String t = br.readLine();

		StringBuilder longSb, shortSb;
		if (s.length() > t.length()) {
			longSb = new StringBuilder(s);
			shortSb = new StringBuilder(t);
		} else {
			longSb = new StringBuilder(t);
			shortSb = new StringBuilder(s);
		}
		int llen = longSb.length();
		int slen = shortSb.length();

		StringBuilder tmp1 = new StringBuilder();
		for (int i = 0; i < llen; i++) {
			tmp1.append(shortSb);
		}
		StringBuilder tmp2 = new StringBuilder();
		for (int i = 0; i < slen; i++) {
			tmp2.append(longSb);
		}

//		System.out.println(tmp1);
//		System.out.println(tmp2);

		if (tmp1.toString().equals(tmp2.toString()))
			System.out.println(1);
		else
			System.out.println(0);
	}
}
