package programmers;

import java.util.Arrays;

public class Solution_PRG_문자열내마음대로정렬하기 {
    public String[] solution(String[] strings, int n) {
        Arrays.sort(strings, (o1, o2) -> {
            if (o1.charAt(n) == o2.charAt(n))
                return o1.compareTo(o2);
            else
                return o1.charAt(n) - o2.charAt(n);
        });

        return strings;
    }

    public static void main(String[] args) {
        Solution_PRG_문자열내마음대로정렬하기 sol = new Solution_PRG_문자열내마음대로정렬하기();
        String[] strings = {"sun", "bed", "car"};
        int n = 1;
//    String[] strings = {"cdx", "abcd", "abce"};
//    int n = 2;
        System.out.println(Arrays.toString(sol.solution(strings, n)));
    }
}
