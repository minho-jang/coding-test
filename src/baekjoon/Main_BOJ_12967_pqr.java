package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main_BOJ_12967_pqr {
    static int N, K, answer = 0;
    static ArrayList<Integer> primeList;

    static class Node {
        HashMap<Integer, Power> powers;

        Node(HashMap<Integer, Power> powers) {
            this.powers = powers;
        }

        Node(int n) {
            powers = new HashMap<>();

            // 소인수분해
            for (int i = 0; i < primeList.size(); i++) {
                int p = primeList.get(i);
                Power pw = new Power(i, p, 0);
                while (n % p == 0) {
                    pw.exp++;
                    n /= p;
                }
                if (pw.exp > 0)
                    powers.put(i, pw);
            }
        }

        public Node() {
            powers = new HashMap<>();
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            for (int key : powers.keySet()) {
                sb.append(powers.get(key) + " ");
            }
            return sb.toString();
        }

        public Node multiply(Node node) {
            Node tmp = new Node();
            for (int key : this.powers.keySet()) {
                Power got = this.powers.get(key);
                Power clone = new Power(got.no, got.base, got.exp);
                tmp.powers.put(got.no, clone);
            }

            for (int key : node.powers.keySet()) {
                Power nodePw = node.powers.get(key);
                if (tmp.powers.get(nodePw.no) == null) {
                    tmp.powers.put(nodePw.no, new Power(nodePw.no, nodePw.base, nodePw.exp));

                } else
                    tmp.powers.get(nodePw.no).exp += nodePw.exp;
            }

            return tmp;
        }
    }

    static class Power {
        int no;
        int base;
        int exp;

        Power(int i, int b, int e) {
            this.no = i;
            this.base = b;
            this.exp = e;
        }

        public String toString() {
            return base + "^" + exp;
        }
    }

    static Node[] arr;
    static Node kNode;

    public static void main(String[] args) throws IOException {
        makePrime();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk;

        stk = new StringTokenizer(br.readLine());
        N = Integer.parseInt(stk.nextToken());
        K = Integer.parseInt(stk.nextToken());
        kNode = new Node(K);
//		System.out.println("K = " + kNode);

        arr = new Node[N];
        stk = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = new Node(Integer.parseInt(stk.nextToken()));
        }

        comb(0, 0, new Node(1));

        System.out.println(answer);
    }

    private static void comb(int cnt, int start, Node result) {
        if (cnt == 3) {
//			System.out.println("result = " + result);

            boolean isOK = true;
            for (int key : kNode.powers.keySet()) {
                Power p = kNode.powers.get(key);
                if (result.powers.get(p.no) == null ||
                        result.powers.get(p.no).exp < p.exp) {
                    isOK = false;
                    break;
                }
            }
            if (isOK) {
//				System.out.println(result);
                answer++;
            }

            return;
        } else {
            for (int key : result.powers.keySet()) {
                Power p = result.powers.get(key);
                if (kNode.powers.get(p.no) == null) {
//					System.out.println(result);
                    return;
                }
            }
        }

        for (int i = start; i < N; i++) {
            comb(cnt + 1, i + 1, result.multiply(arr[i]));
        }
    }

    private static void makePrime() {
        primeList = new ArrayList<>();
        final int UPPER_LIMIT = 1000000;
        boolean[] check = new boolean[UPPER_LIMIT + 1];

        for (int i = 2; i <= UPPER_LIMIT; i++) {

            if (check[i])
                continue;
            primeList.add(i);
            int n = i + i;
            while (n <= UPPER_LIMIT) {
                check[n] = true;
                n += i;
            }

        }
    }
}


/*
public class Main_BOJ_12967_pqr2 {
	static int N, K, answer = 0;
	static int[] arr;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk;
		
		stk = new StringTokenizer(br.readLine());
		N = Integer.parseInt(stk.nextToken());
		K = Integer.parseInt(stk.nextToken());
		
		stk = new StringTokenizer(br.readLine());
		arr = new int[N];
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(stk.nextToken());
		}
		
		comb(0, 0, new int[3]);
		
		System.out.println(answer);
	}
	
	private static void comb(int cnt, int start, int[] result) {
		if (cnt == 3) {
			System.out.println(Arrays.toString(result));
			
			BigInteger sum = BigInteger.ONE;
			for (int i = 0; i < 3; i++) {
				sum = sum.multiply(BigInteger.valueOf(result[i]));
			}
			
			if (sum.mod(BigInteger.valueOf(K)).compareTo(BigInteger.ZERO) == 0)	
				answer++;
			
			return;
		}
		
		for (int i = start; i < N; i++) {
			result[cnt] = arr[i];
			comb(cnt+1, i+1, result);
		}
	}
}
*/
