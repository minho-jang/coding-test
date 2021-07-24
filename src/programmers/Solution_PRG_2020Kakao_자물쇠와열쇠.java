package programmers;

import java.util.Arrays;

class Solution_PRG_2020Kakao_자물쇠와열쇠 {
    public boolean solution(int[][] key, int[][] lock) {
        boolean answer = false;

        for (int i = 0; i < 4; i++) {
            if (i > 0)
                key = rotate90(key);
            if (isFit(key, lock)) {
                answer = true;
                break;
            }
        }

        return answer;
    }

    int[][] rotate90(int[][] arr) {
        int N = arr.length;
        int[][] rotated = new int[N][N];

        for (int c = 0; c < N; c++) {
            for (int r = 0; r < N; r++) {
                rotated[r][c] = arr[N - c - 1][r];
            }
        }

        return rotated;
    }

    boolean isFit(int[][] key, int[][] lock) {
        int keyN = key.length;
        int lockN = lock.length;

        int N = 2 * keyN + lockN - 2;
        int[][] bigLock = new int[N][N];
        for (int i = 0; i < N; i++) {
            Arrays.fill(bigLock[i], 1);
        }
        for (int i = 0; i < lockN; i++) {
            for (int j = 0; j < lockN; j++) {
                bigLock[i + keyN - 1][j + keyN - 1] = lock[i][j];
            }
        }

        for (int i = 0; i < keyN + lockN - 1; i++) {
            for (int j = 0; j < keyN + lockN - 1; j++) {

                for (int ki = 0; ki < keyN; ki++) {
                    for (int kj = 0; kj < keyN; kj++) {
                        if (key[ki][kj] == 1)
                            bigLock[i + ki][j + kj] += 1;
                    }
                }

                if (isAllRight(bigLock, lockN, keyN))
                    return true;

                for (int ki = 0; ki < keyN; ki++) {
                    for (int kj = 0; kj < keyN; kj++) {
                        if (key[ki][kj] == 1)
                            bigLock[i + ki][j + kj] -= 1;
                    }
                }

            }
        }

        return false;
    }

    boolean isAllRight(int[][] lock, int lockN, int keyN) {
        for (int i = 0; i < lockN; i++) {
            for (int j = 0; j < lockN; j++) {
                if (lock[i + keyN - 1][j + keyN - 1] != 1)
                    return false;
            }
        }

        return true;
    }
}