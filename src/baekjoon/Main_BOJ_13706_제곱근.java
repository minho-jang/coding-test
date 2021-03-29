package baekjoon;

import java.math.BigInteger;
import java.util.Scanner;

public class Main_BOJ_13706_제곱근 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		BigInteger N = sc.nextBigInteger();
		
		// 이진 탐색
		BigInteger TWO = new BigInteger("2");
		
		BigInteger start = new BigInteger("0");
		BigInteger end = N;
		
		while(true) {
			BigInteger mid = start.add(end).divide(TWO);
			
			BigInteger square = mid.multiply(mid);
			
			if (square.equals(N)) {
				System.out.println(mid.toString());
				break;
			}
			
			if (N.compareTo(square) < 0) {
				end = mid;
				
			} else {
				start = mid;
				
			}
		}//while
		
		sc.close();
	}
}
