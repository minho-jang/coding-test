package baekjoon;

import java.util.Scanner;

public class Main_BOJ_6591_이항쇼다운 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		while(true) {
			
			long n = sc.nextInt();
			long k = sc.nextInt();
			
			if (n==0 && k==0)
				break;
			
			System.out.println(comb(n, Math.min(k, n-k)));
			
		}
		
		sc.close();
	}
	
	private static long comb(long n, long k) {
		long result = 1L;
		
		for (int i=1; i<=k; i++) {
			result *= n--;
			result /= i;
		}
		
		return result;
	}
}
