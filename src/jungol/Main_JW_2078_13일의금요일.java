package jungol;

import java.util.Scanner;

public class Main_JW_2078_13일의금요일 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int[] answer = new int[7];
        int dayCount = 1;
        for (int i = 0; i < N; i++) {

            int[] days = {0, 31, 0, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
            if (isLeapYear(1900 + i))
                days[2] = 29;
            else
                days[2] = 28;

            for (int j = 1; j <= 12; j++) {
                answer[(dayCount + 12 - 1) % 7]++;
                dayCount += days[j];
            }

        }

        for (int i = 0; i < 7; i++) {
            System.out.print(answer[i] + " ");
        }

        sc.close();
    }

    private static boolean isLeapYear(int y) {
        if (y % 4 == 0 && y % 100 != 0)
            return true;
        if (y % 400 == 0)
            return true;
        return false;
    }
}
