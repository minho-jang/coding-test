package programmers;

import java.util.*;

public class Solution_PRG_2021KakaoIntern_표편집 {
  private LinkedList<Integer> table;
  private int cursor;
  private Stack<Integer> stack;

  public String solution(int n, int k, String[] cmd) {
    table = new LinkedList<>();
    cursor = k;
    stack = new Stack<>();
    for (int i = 0; i < n; i++)
      table.add(i);

    for (String command : cmd)
      doCmd(command);

    StringBuilder answerSb = new StringBuilder();
    for (int i=0; i<n; i++)
      answerSb.append("X");

    for (int data : table)
      answerSb.replace(data, data+1, "O");

    return answerSb.toString();
  }

  private void doCmd(String command) {
    char behavior = command.charAt(0);
    if (behavior == 'U') {
      int move = command.charAt(2) - '0';
      moveUp(move);

    } else if (behavior == 'D') {
      int move = command.charAt(2) - '0';
      moveDown(move);

    } else if (behavior == 'C') {
      deleteData();

    } else if (behavior == 'Z') {
      restoreData();
    }

//    System.out.print(table);
//    System.out.println("\t" + cursor + "\t\t" + command);
  }

  private void restoreData() {
    int pos = stack.pop();
    int data = stack.pop();
    table.add(pos, data);
    if (pos <= cursor)
      moveDown(1);
  }

  private void deleteData() {
    int removed = table.remove(cursor);
    stack.push(removed);
    stack.push(cursor);
    if (cursor == table.size())
      moveUp(1);
  }

  private void moveUp(int move) {
    if (cursor - move < 0)
      cursor = 0;
    else
      cursor -= move;
  }

  private void moveDown(int move) {
    if (cursor + move >= table.size())
      cursor = table.size() - 1;
    else
      cursor += move;
  }

  public static void main(String[] args) {
    Solution_PRG_2021KakaoIntern_표편집 sol = new Solution_PRG_2021KakaoIntern_표편집();
//    int n = 8;
//    int k = 2;
//    String[] cmd = {"D 2","C","U 3","C","D 4","C","U 2","Z","Z"};
//    String[] cmd = {"D 2","C","U 3","C","D 4","C","U 2","Z","Z","U 1","C"};

    int n = 5;
    int k = 0;
    String[] cmd = {"C", "C", "Z", "Z", "C", "C", "Z", "Z", "C", "C", "C", "C", "Z", "Z", "C", "Z", "Z"};

    System.out.println(sol.solution(n, k, cmd));
  }
}
