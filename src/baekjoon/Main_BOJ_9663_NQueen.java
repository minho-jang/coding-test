package baekjoon;

import java.io.*;

public class Main_BOJ_9663_NQueen {
  static long answer;
  static int N;
  static int[][] map;
  static int[] dr = {-1, -1, -1, 0, 0, 1, 1, 1};
  static int[] dc = {-1, 0, 1, -1, 1, -1, 0, 1};
  
  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    N = Integer.parseInt(br.readLine());
    
    map = new int[N][N];
    putQueen(0);
    
    System.out.println(answer);
  }
  
  private static void putQueen(int count) {
    if (count == N) {
      answer++;
      return;
    }
    
    for (int i=0; i<N; i++) {
      if (map[count][i] == 0) {
        checkQueenAttack(count, i, 1);
        putQueen(count+1);
        checkQueenAttack(count, i, -1);
      }
    }
  }
  
  private static void checkQueenAttack(int r, int c, int updown) {
    map[r][c] += updown;
    for (int d=0; d<8; d++) {
      int nr = r + dr[d];
      int nc = c + dc[d];
      while(!isOut(nr, nc)) {
        map[nr][nc] += updown;
        nr += dr[d];
        nc += dc[d];
      }
    }
  }

  private static boolean isOut(int nr, int nc) {
    return nr<0 || nr>=N || nc<0 || nc>=N;
  }
}
