package programmers;

public class Solution_PRG_124나라의숫자 {
    public String solution(int n) {
        StringBuilder answer = new StringBuilder();

        while (n != 0) {
            int q = n / 3;
            int r = n % 3;

            if (r == 0) {
                n = q - 1;
                answer.append(4);

            } else {
                n = q;
                if (r == 1) answer.append(1);
                else if (r == 2) answer.append(2);
            }
        }

        StringBuilder answerRev = new StringBuilder();

        for (int i = answer.length() - 1; i >= 0; i--) {
            answerRev.append(answer.charAt(i));
        }

        return answerRev.toString();
    }
}