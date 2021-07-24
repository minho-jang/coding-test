package programmers;

import java.util.Arrays;

public class Solution_PRG_소수찾기 {
    final int MAX = 10000000;
    boolean[] isPrime;
    int N, answer;
    char[] numbersChar;
    boolean[] visited;

    public int solution(String numbers) {
        this.N = numbers.length();
        this.numbersChar = numbers.toCharArray();
        this.visited = new boolean[N];

        isPrime = new boolean[MAX];
        Arrays.fill(isPrime, true);
        isPrime[0] = isPrime[1] = false;

        int idx = 2;
        while (idx < MAX) {
            if (isPrime[idx]) {
                int removed = idx * 2;
                while (removed < MAX) {
                    isPrime[removed] = false;
                    removed += idx;
                }
            }
            idx++;
        }

        subset(0, 0);

        return answer;
    }

    private void subset(int count, int used) {
        if (count == N) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < N; i++) {
                if (visited[i])
                    sb.append(numbersChar[i]);
            }

            if (used > 0) {
                perm(sb.toString());
//        System.out.println();
            }

            return;
        }

        visited[count] = true;
        subset(count + 1, used + 1);
        visited[count] = false;
        subset(count + 1, used);
    }

    private void perm(String s) {
        perm(s, 0, new boolean[s.length()], new char[s.length()]);
    }

    private void perm(String s, int count, boolean[] visited, char[] res) {
        if (count == s.length()) {
            int assemble = Integer.parseInt(new String(res));
//      System.out.println(assemble);

            if (isPrime[assemble]) {
                isPrime[assemble] = false;
                answer++;
            }
            return;
        }

        for (int i = 0; i < s.length(); i++) {
            if (!visited[i]) {
                visited[i] = true;
                res[count] = s.charAt(i);
                perm(s, count + 1, visited, res);
                visited[i] = false;
            }
        }
    }

    public static void main(String[] args) {
        Solution_PRG_소수찾기 sol = new Solution_PRG_소수찾기();
        String numbers = "3333";
        System.out.println(sol.solution(numbers));
    }
}
