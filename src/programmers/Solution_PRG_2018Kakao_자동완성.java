package programmers;

import java.util.*;

public class Solution_PRG_2018Kakao_자동완성 {
  public int solution(String[] words) {
    int answer = 0;
    
    Trie trie = new Trie();
    for (String word : words) {
      trie.insert(word);
    }
    
    for (String word : words) {
      int count = 0;
//      System.out.println(word);
      for (int i=1; i<=word.length(); i++) {
        if (trie.hasLastOne(word.substring(0, i))) {
//          System.out.println(word.substring(0, i));
          count = i;
          break;
        }
      }
      if (count == 0)
        answer += word.length();
      else
        answer += count;
//      System.out.println();
    }
    
    return answer;
  }
  
  class Trie {
    TrieNode root;
    Trie() {
      this.root = new TrieNode(); 
    }
    
    void insert(String word) {
      TrieNode parent = root;
      for (int i=0; i<word.length(); i++) {
        char c = word.charAt(i);
        parent = parent.child.computeIfAbsent(c, key -> new TrieNode());
        parent.containCount++;
      }
      parent.isLast = true;
    }
    
    boolean hasLastOne(String key) {
      TrieNode now = root;
      for (int i=0; i<key.length(); i++) {
        char c = key.charAt(i);
        now = now.child.get(c);
      }
      
      return now.containCount == 1;
    }
  }
  
  class TrieNode {
    int containCount;
    boolean isLast;
    HashMap<Character, TrieNode> child;
    TrieNode() {
      this.containCount = 0;
      this.isLast = false;
      this.child = new HashMap<>();
    }
  }
  
  public static void main(String[] args) {
    Solution_PRG_2018Kakao_자동완성 sol = new Solution_PRG_2018Kakao_자동완성();
//    String[] words = {"go","gone","guild"};
//    String[] words = {"abc","def","ghi","jklm"};
    String[] words = {"word","war","warrior","world"};
    System.out.println(sol.solution(words));
  }
}
