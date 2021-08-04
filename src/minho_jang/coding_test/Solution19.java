package minho_jang.coding_test;
/**
 * 프로그래머스 코딩 테스트 연습
 * 2021.01.06.
 * <p>
 * LEVEL 3
 * 기둥과 보 설치
 * 출처: https://programmers.co.kr/learn/courses/30/lessons/60061
 *
 * @author minho
 * TODO not solved. 기둥 배열과 보 배열을 나누어서 해볼
 */

import java.util.ArrayList;

public class Solution19 {
	final static int NOTHING = 0;
	final static int GIDUNG = 1;
	final static int BO = 2;
	final static int GIDUNG_BO = 3;

	static void install(int[][] frame, int posX, int posY, int what) {
		int tmp = frame[posX][posY];
		if (tmp == NOTHING) {
			frame[posX][posY] = what;
		} else if (tmp == GIDUNG || tmp == BO) {
			frame[posX][posY] = GIDUNG_BO;
		}
	}

	static void remove(int[][] frame, int posX, int posY, int what) {
		if (frame[posX][posY] == GIDUNG_BO) {
			if (what == GIDUNG)
				frame[posX][posY] = BO;
			else if (what == BO)
				frame[posX][posY] = GIDUNG;
		} else {
			frame[posX][posY] = NOTHING;
		}
	}

	public int[][] solution(int n, int[][] build_frame) {
		int[][] answer = {};

		int[][] frame = new int[n + 3][n + 3];
		for (int i = 0; i < frame.length; i++) {
			for (int j = 0; j < frame[i].length; j++) {
				frame[i][j] = NOTHING;
			}
		}

		// int count = 0;
		for (int[] build : build_frame) {
			int x = build[0] + 1;
			int y = build[1] + 1;
			int a = build[2];
			int b = build[3];

			// System.out.println("[" + (++count) + "] " + (x-1) + " " + (y-1) + " " + a + " " + b);

			if (b == 0) {   // 삭제
				// 기둥
				if (a == 0) {
					// (x, y+1) 기둥이 괜찮은지 확인
					if (frame[x][y + 1] == GIDUNG || frame[x][y + 1] == GIDUNG_BO) {
						// 보의 한쪽 끝 부분 위에 없는지
						if ((frame[x - 1][y + 1] != BO && frame[x - 1][y + 1] != GIDUNG_BO) && (frame[x][y + 1] != BO && frame[x][y + 1] != GIDUNG_BO)) {
							continue;
						}
					}
					// (x, y+1)에 있는 보가 괜찮은지 확인
					else if (frame[x][y + 1] == BO || frame[x][y + 1] == GIDUNG_BO) {
						// 한쪽 끝에 기둥이 없고, 연결된 보도 없고
						if ((frame[x - 1][y + 1] != BO && frame[x - 1][y + 1] != GIDUNG_BO) && (frame[x + 1][y + 1] != BO && frame[x + 1][y + 1] != GIDUNG_BO) && (frame[x + 1][y] != GIDUNG && frame[x + 1][y] != GIDUNG_BO)) {
							continue;
						}
					}
					// (x-1, y+1)에 있는 보가 괜찮은지 확인
					else if (frame[x - 1][y + 1] == BO || frame[x - 1][y + 1] == GIDUNG_BO) {
						// 한쪽 끝에 기둥이 없고, 연결된 보도 없고
						if ((frame[x - 1][y] != GIDUNG && frame[x - 1][y] != GIDUNG_BO) && ((frame[x - 2][y + 1] != BO && frame[x - 2][y + 1] != GIDUNG_BO) && (frame[x][y + 1] != BO && frame[x][y + 1] != GIDUNG_BO))) {
							continue;
						}
					}

					remove(frame, x, y, GIDUNG);
				}
				// 보
				else if (a == 1) {
					// (x+1, y) and (x-1, y)에 있는 보가 괜찮은지 확인
					if (frame[x + 1][y] == BO || frame[x + 1][y] == GIDUNG_BO) {
						// 아래 기둥이 없는지
						if ((frame[x + 1][y - 1] != GIDUNG && frame[x + 1][y - 1] != GIDUNG_BO) && (frame[x + 2][y - 1] != GIDUNG && frame[x + 2][y - 1] != GIDUNG_BO)) {
							continue;
						}
					} else if (frame[x - 1][y] == BO || frame[x - 1][y] == GIDUNG_BO) {
						// 아래 기둥이 없는지
						if ((frame[x - 1][y - 1] != GIDUNG && frame[x - 1][y - 1] != GIDUNG_BO) && (frame[x][y - 1] != GIDUNG && frame[x][y - 1] != GIDUNG_BO)) {
							continue;
						}
					}
					// (x, y)에 있는 기둥이 괜찮은지 확인
					else if (frame[x][y] == GIDUNG || frame[x][y] == GIDUNG_BO) {
						// 아래(x, y-1)에 기둥이 없고, (x-1, y)에 보가 없고
						if ((frame[x][y - 1] != GIDUNG && frame[x][y - 1] != GIDUNG_BO) && frame[x - 1][y] != BO && frame[x - 1][y] != GIDUNG_BO) {
							continue;
						}
					}
					// (x+1, y)에 있는 기둥이 괜찮은지 확인
					else if (frame[x + 1][y] == GIDUNG || frame[x + 1][y] == GIDUNG_BO) {
						// 아래(x+1, y-1)에 기둥이 없고, (x+2, y)에 보가 없고
						if ((frame[x + 1][y - 1] != GIDUNG && frame[x + 1][y - 1] != GIDUNG_BO) && (frame[x + 2][y] != BO && frame[x + 2][y] != GIDUNG_BO)) {
							continue;
						}
					}

					remove(frame, x, y, BO);
				}
			} else if (b == 1) {    // 설치
				// 기둥
				if (a == 0) {
					// 기둥 설치 조건 1: 바닥에 붙어
					if (y - 1 == 0) {
						install(frame, x, y, GIDUNG);
					}
					// 기둥 설치 조건 2: 보의 한쪽 끝 부분 위
					else if (frame[x - 1][y] == BO || frame[x - 1][y] == GIDUNG_BO || frame[x][y] == BO || frame[x][y] == GIDUNG_BO) {
						install(frame, x, y, GIDUNG);
					}
					// 기둥 설치 조건 3: 다른 기둥 위
					else if (frame[x][y - 1] == GIDUNG || frame[x][y - 1] == GIDUNG_BO) {
						install(frame, x, y, GIDUNG);
					}
				}
				// 보
				else if (a == 1) {
					// 보 설치 조건 1: 한쪽 끝 부분이 기둥 위
					if (frame[x][y - 1] == GIDUNG || frame[x][y - 1] == GIDUNG_BO || frame[x + 1][y - 1] == GIDUNG || frame[x + 1][y - 1] == GIDUNG_BO) {
						install(frame, x, y, BO);
					}
					// 보 설치 조건 2: 양쪽 끝 부분이 다른 보와 동시에 연결
					else if ((frame[x - 1][y] == BO || frame[x - 1][y] == GIDUNG_BO) && (frame[x + 1][y] == BO || frame[x + 1][y] == GIDUNG_BO)) {
						install(frame, x, y, BO);
					}
				}
			}
		}

		// for (int i = frame.length-1; i >= 0; i--) {
		//     for (int j = 0; j < frame[i].length; j++) {
		//         System.out.print(frame[j][i] + " ");
		//     }
		//     System.out.println();
		// }

		ArrayList<int[]> answerList = new ArrayList<>();
		int len = frame.length;
		for (int i = 0; i < len; i++) {
			for (int j = 0; j < len; j++) {
				int tmp = frame[i][j];
				if (tmp != 0) {

					if (tmp == GIDUNG) {
						int[] ans = new int[3];
						ans[0] = i - 1;
						ans[1] = j - 1;
						ans[2] = 0;
						answerList.add(ans);
					} else if (tmp == BO) {
						int[] ans = new int[3];
						ans[0] = i - 1;
						ans[1] = j - 1;
						ans[2] = 1;
						answerList.add(ans);
					} else if (tmp == GIDUNG_BO) {
						int[] ans1 = new int[3];
						ans1[0] = i - 1;
						ans1[1] = j - 1;
						ans1[2] = 0;
						answerList.add(ans1);
						int[] ans2 = new int[3];
						ans2[0] = i - 1;
						ans2[1] = j - 1;
						ans2[2] = 1;
						answerList.add(ans2);
					}
				}
			}
		}

		answer = new int[answerList.size()][3];
		for (int i = 0; i < answerList.size(); i++) {
			answer[i] = answerList.get(i);
		}

		return answer;
	}
}