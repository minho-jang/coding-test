package minho_jang.coding_test;
/**
 * 프로그래머스 코딩 테스트 연습
 * 2021.01.03.
 * <p>
 * LEVEL 2
 * 괄호변환
 * 출처: https://programmers.co.kr/learn/courses/30/lessons/60058
 *
 * @author minho
 */

import java.util.Stack;

public class Solution17 {
    public String solution(String p) {
        String answer = "";

        if (hasPerfectBracket(p)) {
            answer = p;
        } else {
            answer = correctBracket(p);
        }

        return answer;
    }

    String correctBracket(String w) {
        if ("".equals(w)) {
            return "";
        }

        StringBuffer u = new StringBuffer();
        StringBuffer v = new StringBuffer();

        // 균형잡힌 괄호 문자열 u
        int count = 0;
        int i = 0;
        for (i = 0; i < w.length(); i++) {
            char c = w.charAt(i);
            if (i == 0) {
                if (c == '(') {
                    count += 1;
                } else {
                    count -= 1;
                }
                u.append(c);
                continue;
            } else {
                if (c == '(') {
                    count += 1;
                } else {
                    count -= 1;
                }
                u.append(c);
            }

            if (count == 0) {
                break;
            }
        }
        // u를 제외한 문자열 v
        v.append(w.substring(i + 1));

        System.out.println("u: " + u);
        System.out.println("v: " + v);

        if (hasPerfectBracket(u.toString())) {
            String newV = correctBracket(v.toString());
            u.append(newV);
            return u.toString();
        } else {
            StringBuffer newBracket = new StringBuffer("(");
            String newV = correctBracket(v.toString());

            newBracket.append(newV);
            newBracket.append(")");

            String removedFirstLast = u.substring(1, u.length() - 1);
            u = flipBracket(removedFirstLast);

            newBracket.append(u);
            return newBracket.toString();
        }
    }

    boolean hasPerfectBracket(String w) {
        try {
            Stack<Character> stack = new Stack<>();
            for (int i = 0; i < w.length(); i++) {
                char c = w.charAt(i);
                if (c == '(') {
                    stack.push(c);
                } else {
                    stack.pop();
                }
            }
            return stack.empty();
        } catch (Exception e) {
            return false;
        }
    }

    StringBuffer flipBracket(String w) {
        StringBuffer flipped = new StringBuffer();
        for (int i = 0; i < w.length(); i++) {
            char c = w.charAt(i);
            if (c == '(') {
                flipped.append(')');
            } else {
                flipped.append('(');
            }
        }

        return flipped;
    }
}