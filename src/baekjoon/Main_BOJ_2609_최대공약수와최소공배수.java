package baekjoon;

import java.util.Scanner;

public class Main_BOJ_2609_최대공약수와최소공배수 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();

        // 최대공약수
        int gcm = 0;
        int lg = Math.max(N, M);
        int sm = Math.min(N, M);
        while (true) {
            int R = lg % sm;
            if (R == 0) {
                gcm = sm;
                break;
            } else {
                lg = sm;
                sm = R;
            }
        }
        System.out.println(gcm);

        // 최소공배수
        int lcm = N * M / gcm;
        System.out.println(lcm);

        sc.close();
    }
}

/*

public class Main_BOJ_2609_최대공약수와최대공배수 {
	private static ArrayList<Integer> primes;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int M = sc.nextInt();
		
		makePrime();
		
		// 최대 공약수
		int tmpN = N;
		int tmpM = M;
		int gcm = 1;
		while (true) {
			boolean flag = false;
			for (int p : primes) {
				if (tmpN % p == 0 && tmpM % p == 0) {
					gcm *= p;
					tmpN /= p;
					tmpM /= p;
					flag = true;
					break;
				}
			}
			if (!flag)
				break;
		}
		System.out.println(gcm);
		
		// 최소공배수
		int lcm = gcm * tmpM * tmpN;
		System.out.println(lcm);
		
		sc.close();
	}

	private static void makePrime() {
		primes = new ArrayList<>();
		int MAX = 10000;
		boolean[] check = new boolean[MAX+1];
		for (int i = 2; i <= MAX; i++) {
			if (! check[i]) {
				primes.add(i);
				for (int j = i+i; j <= MAX; j+=i) {
					check[j] = true;
				}
			}
		}
	}
}

*/
