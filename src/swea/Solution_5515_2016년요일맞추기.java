package swea;

import java.util.Scanner;

public class Solution_5515_2016년요일맞추기 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int[] days = {0, 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
		int T = sc.nextInt();
		for (int i = 1; i <= T; i++) {
			
			int month = sc.nextInt();
			int day = sc.nextInt();
			
			int dayCount = 0;
			for (int j = 1; j < month; j++) 
				dayCount += days[j];
			dayCount += day-1;
			
			int answer = (4 + (dayCount % 7)) % 7;
			
			System.out.println("#" + i + " " + answer);
		}
		
		sc.close();
	}
}
