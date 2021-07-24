package swea;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_4008_숫자만들기 {
    static int[] numCards, operatorCards;
    static int[] visited;
    static int N, min, max;

    public static void main(String[] args) throws NumberFormatException, IOException {
        System.setIn(new FileInputStream("input.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk;
        StringBuilder answerSb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {

            min = 987654321;
            max = -987654321;
            N = Integer.parseInt(br.readLine());

            stk = new StringTokenizer(br.readLine());
            operatorCards = new int[5];
            for (int i = 0; i < 4; i++) {
                operatorCards[i + 1] = Integer.parseInt(stk.nextToken());
            }

            stk = new StringTokenizer(br.readLine());
            numCards = new int[N];
            visited = new int[N - 1];
            for (int i = 0; i < N; i++) {
                numCards[i] = Integer.parseInt(stk.nextToken());
            }

            selectOperator(1);

            answerSb.append("#").append(tc).append(" ").append(max - min).append("\n");
        }

        System.out.print(answerSb);
    }

    private static void selectOperator(int operator) {
        if (operator == 5) {
//			System.out.println("\t" + Arrays.toString(visited));

            int result = numCards[0];
            for (int i = 0; i < N - 1; i++) {
                result = getResult(result, numCards[i + 1], visited[i]);
            }

            min = Math.min(min, result);
            max = Math.max(max, result);

            return;
        }

        // 해당 operator 조합
        comb(0, 0, new int[operatorCards[operator]], operator);
    }

    private static void comb(int cnt, int start, int[] res, int operator) {
        if (cnt == operatorCards[operator]) {
//			System.out.println(operator + " > " + Arrays.toString(res));

            selectOperator(operator + 1);
            return;
        }

        for (int i = start; i < N - 1; i++) {
            if (visited[i] > 0)
                continue;

            visited[i] = operator;
            res[cnt] = i;
            comb(cnt + 1, i + 1, res, operator);
            visited[i] = 0;
        }
    }

    private static int getResult(int operand1, int operand2, int operator) {
        if (operator == 1) {
            return operand1 + operand2;
        } else if (operator == 2) {
            return operand1 - operand2;
        } else if (operator == 3) {
            return operand1 * operand2;
        } else if (operator == 4) {
            return operand1 / operand2;
        }
        return -1;
    }
}


/*	시간초과

public class Solution_4008_숫자만들기2 {
	static int[] numCards, operatorCards;
	static boolean[] visited;
	static int N, min, max;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("input.txt"));
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk;
		StringBuilder answerSb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			
			min = 987654321;
			max = -987654321;
			N = Integer.parseInt(br.readLine());
			
			stk = new StringTokenizer(br.readLine());
			operatorCards = new int[N-1];
			int idx = 0;
			for (int i = 0; i < 4; i++) {
				int num = Integer.parseInt(stk.nextToken());
				for (int j = 0; j < num; j++) {
					operatorCards[idx++] = i;
				}
			}
			
			stk = new StringTokenizer(br.readLine());
			numCards = new int[N];
			visited = new boolean[N-1];
			for (int i = 0; i < N; i++) {
				numCards[i] = Integer.parseInt(stk.nextToken());
			}
			
			dfs(0, new int[N-1]);
//			System.out.println(max + " " + min);
			
			answerSb.append("#").append(tc).append(" ").append(max-min).append("\n");
		}
		
		System.out.print(answerSb);
	}

	private static void dfs(int cnt, int[] res) {
		if (cnt == N-1) {
//			System.out.println(">> " + Arrays.toString(res));
			
			int result = numCards[0];
			for (int i = 0; i < N-1; i++) {
				result = getResult(result, res[i], i);
			}
			
//			System.out.println(">> result = " + result);
			max = Integer.max(result, max);
			min = Integer.min(result, min);
			return;
		}
		
		for (int i = 0; i < N-1; i++) {
			if (visited[i])
				continue;
			visited[i] = true;
			res[cnt] = i;
			dfs(cnt+1, res);
			visited[i] = false;
		}
	}

	private static int getResult(int result, int idx, int order) {
		int operator = operatorCards[idx];
		int operand = numCards[order+1];
		
//		System.out.print("====" + result + " " + operand + " " + operator);
//		System.out.println();
		
		if (operator == 0) {
			return result + operand;
		} else if (operator == 1) {
			return result - operand;
		} else if (operator == 2) {
			return result * operand;
		} else if (operator == 3) {
			return result / operand;
		}
		return -1;
	}
}


*/