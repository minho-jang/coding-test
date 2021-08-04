package minho_jang.coding_test;
/**
 * 프로그래머스 코딩 테스트 연습
 * 2020.12.14.
 * <p>
 * LEVEL 1
 * [1차] 다트 게임
 * 출처: https://programmers.co.kr/learn/courses/30/lessons/17682
 *
 * @author minho
 */

import java.util.ArrayList;

public class Solution11 {
	public int solution(String dartResult) {
		int answer = 0;

		ArrayList<DartGame> games = new ArrayList<>();
		int len = dartResult.length();
		int jump = 0;
		for (int i = 0; i < len; i += jump) {
			jump = 0;
			char score = dartResult.charAt(i);
			DartGame game = new DartGame();
			// score
			if (score == '1') {
				game.score = 1;
				jump++;
				if (dartResult.charAt(i + 1) == '0') {
					game.score = 10;
					jump++;
				}
			} else {
				game.score = score - '0';
				jump++;
			}
			// bonus
			game.bonus = dartResult.charAt(i + jump);
			jump++;
			// option
			if (i + jump < len) {
				char option = dartResult.charAt(i + jump);
				if (option == '*' || option == '#') {
					game.option = option;
					jump++;
				}
			}

			games.add(game);
		}


		for (int i = 0; i < games.size(); i++) {
			DartGame dg = games.get(i);
			int total = 0;
			if (dg.bonus == 'S') {
				total = (int) Math.pow(dg.score, 1);
			} else if (dg.bonus == 'D') {
				total = (int) Math.pow(dg.score, 2);
			} else {
				total = (int) Math.pow(dg.score, 3);
			}

			if (dg.option == '*') {
				total *= 2;
				if (i != 0) {
					DartGame tmp = games.get(i - 1);
					tmp.total *= 2;
				}
			} else if (dg.option == '#') {
				total *= -1;
			}

			dg.total = total;
		}

		for (DartGame dg : games) {
			answer += dg.total;
		}

		return answer;
	}

	class DartGame {
		int score;
		char bonus;
		char option = '\n';
		int total;

		void printGame() {
			if (option == '\n') {
				System.out.println(score + " " + bonus);
			} else {
				System.out.println(score + " " + bonus + " " + option);
			}

		}
	}
}