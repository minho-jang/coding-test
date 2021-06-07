package baekjoon;

import java.io.*;
import java.util.*;

public class Main_BOJ_14500_테트로미노 {
  static int answer, N, M;
  static int[][] arr;
  static boolean[][] visited;
  static int[] dr = { -1, 1, 0, 0 };
  static int[] dc = { 0, 0, -1, 1 };

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer stk;

    stk = new StringTokenizer(br.readLine());
    N = Integer.parseInt(stk.nextToken());
    M = Integer.parseInt(stk.nextToken());
    arr = new int[N][M];
    visited = new boolean[N][M];

    for (int i = 0; i < N; i++) {
      stk = new StringTokenizer(br.readLine());
      for (int j = 0; j < M; j++) {
        arr[i][j] = Integer.parseInt(stk.nextToken());
      }
    }

    for (int i = 0; i < N; i++) {
      for (int j = 0; j < M; j++) {
        dfs(0, i, j, 0); // 'ㅗ' 모양을 제외한 테트로미노 확인
        checkOH(i, j); // 'ㅗ' 모양 확인
      }
    }

    System.out.println(answer);
  }

  // r, c 위치를 기준으로 'ㅗ'모양 확인. 튀어나온 부분이 (r, c)라고 가정
  private static void checkOH(int r, int c) {
    int result = 0;

    try {
      // ㅗ
      result = arr[r][c];
      result += arr[r + 1][c - 1];
      result += arr[r + 1][c];
      result += arr[r + 1][c + 1];
      answer = Math.max(answer, result);
    } catch (Exception e) {
//    System.out.println(e.getMessage());
    }
    try {
      // ㅓ
      result = arr[r][c];
      result += arr[r - 1][c + 1];
      result += arr[r][c + 1];
      result += arr[r + 1][c + 1];
      answer = Math.max(answer, result);
    } catch (Exception e) {
//    System.out.println(e.getMessage());
    }
    try {
      // ㅜ
      result = arr[r][c];
      result += arr[r - 1][c - 1];
      result += arr[r - 1][c];
      result += arr[r - 1][c + 1];
      answer = Math.max(answer, result);
    } catch (Exception e) {
//    System.out.println(e.getMessage());
    }
    try {
      // ㅏ
      result = arr[r][c];
      result += arr[r - 1][c - 1];
      result += arr[r][c - 1];
      result += arr[r + 1][c - 1];
      answer = Math.max(answer, result);

    } catch (Exception e) {
//      System.out.println(e.getMessage());
    }
  }

  private static void dfs(int depth, int r, int c, int score) {
    if (depth == 4) {
      answer = Math.max(answer, score);
      return;
    }

    for (int d = 0; d < 4; d++) {
      int nr = r + dr[d];
      int nc = c + dc[d];
      if (nr < 0 || nr >= N || nc < 0 || nc >= M)
        continue;
      if (visited[nr][nc])
        continue;

      visited[nr][nc] = true;
      dfs(depth + 1, nr, nc, score + arr[nr][nc]);
      visited[nr][nc] = false;
    }
  }
}
