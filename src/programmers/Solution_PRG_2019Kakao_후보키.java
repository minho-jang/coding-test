package programmers;

import java.util.*;

public class Solution_PRG_2019Kakao_후보키 {
  int colN, answer;
  ArrayList<ArrayList<Integer>> candidateKeys;
  String[][] relation;
  
  public int solution(String[][] relation) {
    this.relation = relation;
    this.colN = relation[0].length;
    this.candidateKeys = new ArrayList<>();
    
    for (int i=1; i<=colN; i++) {
      comb(0, 0, new int[i], i);
    }
    
    return answer;
  }

  private void comb(int count, int start, int[] res, int N) {
    if (count == N) {
      ArrayList<Integer> selected = new ArrayList<>();
      for (int i=0; i<N; i++)
        selected.add(res[i]);
      
      if (isSuperKey(selected)) {         // 유일성 
        if (isCandidateKey(selected)) {   // 최소성
          System.out.println(selected);
          candidateKeys.add(selected);
          answer++;
        }
      }
      
      return;
    }
    
    for (int i=start; i<colN; i++) {
      res[count] = i;
      comb(count+1, i+1, res, N);
    }
  }

  private boolean isCandidateKey(ArrayList<Integer> selected) {
    // 이전에 구한 후보키를 포함하는지 확인
    
    for (ArrayList<Integer> candidateKey : candidateKeys) {
      int count = 0;
      for (int target : candidateKey) {
        for (int col : selected) {
          if (target == col)
            count++;
        }
      }
      
      if (count == candidateKey.size())
        return false;
    }
    
    return true;
  }

  private boolean isSuperKey(ArrayList<Integer> selected) {
    // 튜플을 유일하게 식별하는지 확인
    
    HashSet<String> set = new HashSet<>();
    for (String[] row : relation) {
      StringBuilder key = new StringBuilder();
      for (int col : selected)
        key.append(row[col]).append(" ");
      
      if (set.contains(key.toString()))
        return false;
      else
        set.add(key.toString());
    }
    
    return true;
  }

  public static void main(String[] args) {
    Solution_PRG_2019Kakao_후보키 sol = new Solution_PRG_2019Kakao_후보키();
//    String[][] relation = {
//        {"100","ryan","music","2"},
//        {"200","apeach","math","2"},
//        {"300","tube","computer","3"},
//        {"400","con","computer","4"},
//        {"500","muzi","music","3"},
//        {"600","apeach","music","2"}
//    };
    String[][] relation = {
        {"100", "con"}
    };
    System.out.println(sol.solution(relation));
  }
}
