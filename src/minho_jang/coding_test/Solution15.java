package minho_jang.coding_test;
/**
 * 프로그래머스 코딩 테스트 연습
 * 2020.12.21.
 * <p>
 * LEVEL 2
 * 삼각달팽이
 * 출처: https://programmers.co.kr/learn/courses/30/lessons/68645
 *
 * @author minho
 */

public class Solution15 {
    public int[] solution(int n) {
        int[] answer = {};

        int[][] tmp = new int[n][n];

        // 채워질 부분만 -1로 초기화
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                tmp[i][j] = -1;
            }
        }

        int max = getMax(n);
        int row = 0;
        int col = 0;
        int count = 2;

        // 첫 칸
        tmp[0][0] = 1;
        while (count <= max) {
            // 세로
            while (row + 1 < n && tmp[row + 1][col] < 0) {
                row += 1;
                tmp[row][col] = count;
                count += 1;
            }
            // 가로
            while (col + 1 < n && tmp[row][col + 1] < 0) {
                col += 1;
                tmp[row][col] = count;
                count += 1;
            }
            // 대각선
            while (col - 1 > 0 && row - 1 > 0 && tmp[row - 1][col - 1] < 0) {
                col -= 1;
                row -= 1;
                tmp[row][col] = count;
                count += 1;
            }
        }

        // 출력
        // for (int i = 0; i < tmp.length; i++) {
        //     for (int j = 0; j < tmp[i].length; j++) {
        //         System.out.print(tmp[i][j] + " ");
        //     }
        //     System.out.println();
        // }

        answer = new int[max];
        count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                answer[count] = tmp[i][j];
                count += 1;
            }
        }

        return answer;
    }

    int getMax(int n) {
        if (n == 1)
            return 1;
        else
            return getMax(n - 1) + n;
    }
}