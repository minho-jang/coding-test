package baekjoon;

import java.util.*;
import java.io.*;

public class Main_BOJ_4574_스도미노쿠 {
  static int[][] answer;
  static int[][] sudoku;
  static boolean[][] used;
  static int[] dr = {0, 0, -1, 1};
  static int[] dc = {-1, 1, 0, 0};
  static BufferedReader br;
  static StringTokenizer stk;
  static int N;
  
  public static void main(String[] args) throws Exception {
    init();
    int idx = 1;
    while (input()) {
      dfs(0);
      printAnswer(idx++);
    }
  }
  
  private static void dfs(int count) {
    if (answer[0][0] != 0)     return;
    if (count == 36-N) {
      for (int i=0; i<9; i++) {
        for (int j=0; j<9; j++) {
          answer[i][j] = sudoku[i][j];
        }
      }
      return;
    }
    
    for (int i=0; i<9; i++) {
      for (int j=0; j<9; j++) {
        if (sudoku[i][j] == 0) {
          
          for (int a=1; a<=9; a++) {
            for (int b=1; b<=9; b++) {
              if (!used[a][b]) {
                
                int r = i;
                int c = j;
                for (int d=0; d<4; d++) {
                  int nr = r + dr[d];
                  int nc = c + dc[d];
                  if (isOut(nr, nc))        continue;
                  if (sudoku[nr][nc] != 0)  continue;
                  
                  sudoku[r][c] = a;
                  sudoku[nr][nc] = b;
                  used[a][b] = used[b][a] = true;
                  if (isValidSudoku(r, c) && isValidSudoku(nr, nc))
                    dfs(count+1);
                  sudoku[r][c] = 0;
                  sudoku[nr][nc] = 0;
                  used[a][b] = used[b][a] = false;
                }
                
              }
            }
          }
          
          return;
        }
      }
    }
    
  }

  private static boolean isValidSudoku(int r, int c) {
    return isValidSudokuRow(r) && isValidSudokuCol(c) && isValidSudokuArea(r,c);
  }

  private static boolean isValidSudokuArea(int r, int c) {
    int startR = (r / 3) * 3;
    int startC = (c / 3) * 3;
    
    boolean[] visited = new boolean[10];
    for (int i=startR; i<startR+3; i++) {
      for (int j=startC; j<startC+3; j++) {
        if (sudoku[i][j] != 0 && visited[sudoku[i][j]]) 
          return false;
          
        visited[sudoku[i][j]] = true;
      }
    }
    
    return true;
  }

  private static boolean isValidSudokuCol(int c) {
    boolean[] visited = new boolean[10];
    for (int i=0; i<9; i++) {
      if (sudoku[i][c] != 0 && visited[sudoku[i][c]]) 
        return false;
        
      visited[sudoku[i][c]] = true;
    }
    return true;
  }

  private static boolean isValidSudokuRow(int r) {
    boolean[] visited = new boolean[10];
    for (int i=0; i<9; i++) {
      if (sudoku[r][i] != 0 && visited[sudoku[r][i]]) 
        return false;
        
      visited[sudoku[r][i]] = true;
    }
    return true;
  }

  private static boolean isOut(int nr, int nc) {
    return nr<0 || nr>=9 || nc<0 || nc>=9;
  }

  private static boolean input() throws Exception {
    N = Integer.parseInt(br.readLine());
    if (N == 0) 
      return false;
    
    sudoku = new int[9][9];
    used = new boolean[9+1][9+1];
    for (int i=0; i<10; i++)  used[0][i] = true;
    for (int i=0; i<10; i++)  used[i][0] = true;
    for (int i=0; i<10; i++)  used[i][i] = true;  
    
    answer = new int[9][9];
    
    for (int i=0; i<N; i++) {
      stk = new StringTokenizer(br.readLine());
      int num1 = Integer.parseInt(stk.nextToken());
      String pos1 = stk.nextToken(); 
      checkNumPosInSudoku(num1, pos1);      
      int num2 = Integer.parseInt(stk.nextToken());
      String pos2 = stk.nextToken(); 
      checkNumPosInSudoku(num2, pos2);
      
      used[num1][num2] = used[num2][num1] = true;
    }
    
    stk = new StringTokenizer(br.readLine());
    for (int i=0; i<9; i++) {
      int num = i+1;
      String pos = stk.nextToken();
      checkNumPosInSudoku(num, pos);
    }
    
    return true;
  }

  private static void init() throws FileNotFoundException {
    System.setIn(new FileInputStream("input.txt"));
    br = new BufferedReader(new InputStreamReader(System.in));
  }
  
  private static void checkNumPosInSudoku(int num, String pos) {
    int r = pos.charAt(0) - 'A';
    int c = pos.charAt(1) - '0' - 1;
    sudoku[r][c] = num;
  }
  
  private static void printAnswer(int idx) {
    System.out.println("Puzzle " + idx);
    printArray(answer);
  }

  private static void printArray(int[][] arr) {
    StringBuilder sb = new StringBuilder();
    for (int i=0; i<arr.length; i++) {
      for (int j=0; j<arr[0].length; j++) {
        sb.append(arr[i][j]);
      }
      sb.append("\n");
    }
    System.out.print(sb.toString());
  }
}
