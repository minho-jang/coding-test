package baekjoon;

import java.io.*;
import java.util.*;

public class Main_BOJ_16197_두동전 {
  static int[] dr = {-1, 1, 0, 0};
  static int[] dc = {0, 0, -1, 1};
  static class Pos {
    int r, c, cnt;
    Pos(int r, int c, int cnt) {
      this.r = r;
      this.c = c;
      this.cnt = cnt;
    }
  }
  static int N, M;
  
  public static void main(String[] args) throws Exception {
    System.setIn(new FileInputStream("input.txt"));
    
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer stk;
    
    stk = new StringTokenizer(br.readLine());
    N = Integer.parseInt(stk.nextToken());
    M = Integer.parseInt(stk.nextToken());
    
    char[][] map = new char[N][M];
    Pos coin1=null, coin2=null;
    for (int i=0; i<N; i++) {
      map[i] = br.readLine().toCharArray();
      for (int j=0; j<M; j++) {
        if (map[i][j] == 'o') {
          map[i][j] = '.';
          if (coin1 == null) {
            coin1 = new Pos(i, j, 0);
          } else {
            coin2 = new Pos(i, j, 0);
          }
        }
      }
    }
    
    // BFS
    Queue<Pos> queue = new LinkedList<>();
    queue.add(coin1);
    queue.add(coin2);
    
    int answer = -1;
    while(!queue.isEmpty()) {
      Pos polled1 = queue.poll();
      Pos polled2 = queue.poll();
      
      if (polled1.cnt >= 10 || polled2.cnt >= 10) {
        answer = -1;
        break;
      }
      
      for (int d=0; d<4; d++) {
        int nr1 = polled1.r + dr[d];
        int nc1 = polled1.c + dc[d];
        int nr2 = polled2.r + dr[d];
        int nc2 = polled2.c + dc[d];
        
        if (isOut(nr1, nc1) && isOut(nr2, nc2)) { // 동전 둘 다 나가리
          continue;
          
        } else {
          if (isOut(nr1, nc1)) {        // 1번 동전 나가리
            answer = polled1.cnt + 1;
            queue.clear();              // while문 빠져나가기 위해
            break;
            
          } else if (isOut(nr2, nc2)) { // 2번 동전 나가리
            answer = polled2.cnt + 1;
            queue.clear();
            break;
          }
        }
        
        // 이동할 곳이 벽이면, 제자리 
        if (map[nr1][nc1] == '#') {   
          nr1 = polled1.r;
          nc1 = polled1.c;
        }
        if (map[nr2][nc2] == '#') {
          nr2 = polled2.r;
          nc2 = polled2.c;
        }
        
        queue.add(new Pos(nr1, nc1, polled1.cnt+1));
        queue.add(new Pos(nr2, nc2, polled2.cnt+1));
      }
    }
    
    System.out.println(answer);
  }
  
  private static boolean isOut(int r, int c) {
    return r<0 || r>=N || c<0 || c>=M;
  }
}
