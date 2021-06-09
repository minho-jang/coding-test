package baekjoon;

import java.io.*;
import java.util.*;

public class Main_BOJ_3190_뱀 {
  static int[] dr = {-1, 1, 0, 0};
  static int[] dc = {0, 0, -1, 1};
  static int N, K, L;
  static int[][] map, direction;
  
  static class Snake {
    Pos pos;
    Snake parent;
    Snake(Pos pos, Snake parent) {
      this.pos = pos;
      this.parent = parent; 
    }
  }
  static class Pos {
    int r,c;
    Pos(int r, int c) {
      this.r = r;
      this.c = c;
    }
  }
  
  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer stk;
    
    // input
    N = Integer.parseInt(br.readLine());
    K = Integer.parseInt(br.readLine());
    map = new int[N][N];
    for (int i=0; i<K; i++) {
      stk = new StringTokenizer(br.readLine());
      int appleR = Integer.parseInt(stk.nextToken());
      int appleC = Integer.parseInt(stk.nextToken());
      map[appleR-1][appleC-1] = 1;
    }
    L = Integer.parseInt(br.readLine());
    direction = new int[L][2];
    for (int i=0; i<L; i++) {
      stk = new StringTokenizer(br.readLine());
      direction[i][0] = Integer.parseInt(stk.nextToken());
      String d = stk.nextToken();
      if (d.equals("D"))        direction[i][1] = 1;    // 오른쪽=1
      else if (d.equals("L"))   direction[i][1] = 0;    // 왼쪽=0
    }
    
    // 게임 시작
    int directionIdx = 0;
    int snakeDirection = 3;   // 오른쪽
    Snake head = new Snake(new Pos(0,0), null);
    Snake tail = head;
    
    int time = 0;
    while(true) {
//      System.out.println("time=" + time);
//      printMap(tail);
      
      if (directionIdx < L && direction[directionIdx][0] == time) {
        snakeDirection = changeDirection(snakeDirection, direction[directionIdx][1]);
        directionIdx++;
      }
      
      int nr = head.pos.r + dr[snakeDirection];
      int nc = head.pos.c + dc[snakeDirection];
      
      if (isOut(nr, nc))    break;
      if (isMe(nr, nc, tail))  break;
      
      if (map[nr][nc] == 1) {   // 사과 먹음 = 이동안하고 머리만 길어짐
        Snake tmp = new Snake(new Pos(nr, nc), null);
        head.parent = tmp;
        head = tmp;
        map[nr][nc] = 0;
        
      } else {    // 뱀 이동
        Snake tmp = tail;
        while (tmp.parent != null) {
          tmp.pos = tmp.parent.pos;
          tmp = tmp.parent;
        }
        tmp.pos = new Pos(nr, nc);
        
      }
      
      time++;
    }
    
    System.out.println(time+1);
  }
  
  private static void printMap(Snake tail) {
    int[][] tmpMap = new int[N][N];
    
    // map 복사
    for (int i=0; i<N; i++) {
      for (int j=0; j<N; j++) {
        tmpMap[i][j] = map[i][j];
      }
    }
    
    // 뱀 -1로 표시
    Snake tmp = tail;
    while (tmp.parent != null) {
      tmpMap[tmp.pos.r][tmp.pos.c] = -1;
      tmp = tmp.parent;
    }
    tmpMap[tmp.pos.r][tmp.pos.c] = -1;
    
    // tmpMap 출력
    for (int i=0; i<N; i++) {
      for (int j=0; j<N; j++) {
        System.out.print(tmpMap[i][j] + " ");
      }
      System.out.println();
    }
    System.out.println();
  }

  private static boolean isMe(int r, int c, Snake tail) {
    boolean yn = false;
    
    // (r,c)가 뱀에 부딪히는지 확인
    Snake tmp = tail;
    while (tmp.parent != null) {
      if (tmp.pos.r == r && tmp.pos.c == c) {
        yn = true;
        break;
      }
      tmp = tmp.parent;
    }
    
    return yn;
  }

  private static int changeDirection(int snakeDirection, int d) {
    if (d == 0) {         // 현재 기준 왼쪽
      if (snakeDirection == 0)          // 현재 위쪽
        return 2;
      else if (snakeDirection == 1)     // 현재 아래쪽
        return 3;
      else if (snakeDirection == 2)     // 현재 왼쪽
        return 1;
      else                              // 현재 오른쪽
        return 0;
      
    } else {  // 현재 기준 오른쪽
      if (snakeDirection == 0)          // 현재 위쪽
        return 3;
      else if (snakeDirection == 1)     // 현재 아래쪽
        return 2;
      else if (snakeDirection == 2)     // 현재 왼쪽
        return 0;
      else                              // 현재 오른쪽
        return 1;
      
    }
  }

  private static boolean isOut(int nr, int nc) {
    return nr<0 || nr>=N || nc<0 || nc>=N;
  }
}
