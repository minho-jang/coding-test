package programmers;

import java.util.*;

public class Solution_PRG_2021KakaoIntern_표편집 {
  private int cursor, tableSize;
  private Stack<Integer> stack;

  public String solution(int n, int k, String[] cmd) {
    cursor = k;
    tableSize = n;
    stack = new Stack<>();

    for (String command : cmd)
      doCmd(command);

    StringBuilder answerSb = new StringBuilder();
    for (int i=0; i<tableSize; i++)
      answerSb.append("O");
    while(!stack.isEmpty())
      answerSb.insert(stack.pop(), "X");

    return answerSb.toString();
  }

  private void doCmd(String command) {
    char behavior = command.charAt(0);
    if (behavior == 'U') {
      int move = Integer.parseInt(command.substring(2));
      moveUp(move);

    } else if (behavior == 'D') {
      int move = Integer.parseInt(command.substring(2));
      moveDown(move);

    } else if (behavior == 'C') {
      deleteData();

    } else if (behavior == 'Z') {
      restoreData();
    }
  }

  private void restoreData() {
    int pos = stack.pop();
    tableSize++;
    if (pos <= cursor)
      moveDown(1);
  }

  private void deleteData() {
    stack.push(cursor);
    tableSize--;
    if (cursor == tableSize)
      moveUp(1);
  }

  private void moveUp(int move) {
    cursor -= move;
  }

  private void moveDown(int move) {
    cursor += move;
  }

  public static void main(String[] args) {
    Solution_PRG_2021KakaoIntern_표편집 sol = new Solution_PRG_2021KakaoIntern_표편집();
    int n = 8;
    int k = 2;
    String[] cmd = {"D 2","C","U 3","C","D 4","C","U 2","Z","Z"};
//    String[] cmd = {"D 2","C","U 3","C","D 4","C","U 2","Z","Z","U 1","C"};

    System.out.println(sol.solution(n, k, cmd));
  }
}
