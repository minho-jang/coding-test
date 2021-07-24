package minho_jang.coding_test;

/**
 * 프로그래머스 코딩 테스트 연습
 * 2020.12.10.
 * <p>
 * LEVEL 1
 * 키패드 누르기
 * 출처: https://programmers.co.kr/learn/courses/30/lessons/67256
 *
 * @author minho
 */
public class Solution8 {
    public String solution(int[] numbers, String hand) {
        String answer = "";

        boolean isRight = (hand.equals("right")) ? true : false;
        StringBuffer sb = new StringBuffer();
        int leftPos = -1;
        int rightPos = -1;

        for (int i : numbers) {
            if (i == 1 || i == 4 || i == 7) {
                leftPos = i;
                sb.append("L");
            } else if (i == 3 || i == 6 || i == 9) {
                rightPos = i;
                sb.append("R");
            } else {
                int leftDistance = getDistance(leftPos, i);
                int rightDistance = getDistance(rightPos, i);

                if (leftDistance < rightDistance) {
                    leftPos = i;
                    sb.append("L");
                } else if (leftDistance > rightDistance) {
                    rightPos = i;
                    sb.append("R");
                } else {
                    if (isRight) {
                        rightPos = i;
                        sb.append("R");
                    } else {
                        leftPos = i;
                        sb.append("L");
                    }
                }
            }
        }

        answer = sb.toString();
        return answer;
    }

    private int getDistance(int start, int end) {
        int distance = 0;
        int[][] numberMap = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9},
                {-1, 0, -1}
        };

        int startX = 0;
        int startY = 0;
        int endX = 0;
        int endY = 0;
        for (int i = 0; i < numberMap.length; i++) {
            for (int j = 0; j < numberMap[i].length; j++) {
                if (numberMap[i][j] == start) {
                    startX = i;
                    startY = j;
                }
                if (numberMap[i][j] == end) {
                    endX = i;
                    endY = j;
                }
            }
        }

        int distanceX = Math.abs(startX - endX);
        int distanceY = Math.abs(startY - endY);
        distance = distanceX + distanceY;
        return distance;
    }
}
