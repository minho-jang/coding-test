package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;

public class Main_BOJ_5052_전화번호목록 {
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input.txt"));

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder answerSb = new StringBuilder();

		int t = Integer.parseInt(br.readLine());
		for (int i = 0; i < t; i++) {
			int n = Integer.parseInt(br.readLine());
			String[] numbers = new String[n];
			for (int j = 0; j < n; j++) {
				numbers[j] = br.readLine();
			}
			String result = solve(numbers);
			answerSb.append(result).append("\n");
		}

		System.out.print(answerSb.toString());
	}

	private static String solve(String[] numbers) {
		Trie trie = new Trie();
		for (String number : numbers) {
			trie.insert(number);
		}

		for (String number : numbers) {
			if (trie.startsWith(number))
				return "NO";
		}
		return "YES";
	}

	static class Trie {
		TrieNode root;

		Trie() {
			root = new TrieNode();
		}

		void insert(String word) {
			TrieNode now = root;
			for (int i = 0; i < word.length(); i++) {
				char c = word.charAt(i);
				now = now.child.computeIfAbsent(c, key -> new TrieNode());
			}
			now.isLast = true;
		}

		boolean startsWith(String word) {
			TrieNode now = root;
			for (int i = 0; i < word.length(); i++) {
				char c = word.charAt(i);
				if (now.child.containsKey(c))
					now = now.child.get(c);
				else
					return false;
			}

			if (now.child.isEmpty())
				return false;
			else
				return true;
		}
	}

	static class TrieNode {
		HashMap<Character, TrieNode> child;
		boolean isLast;

		TrieNode() {
			this.child = new HashMap<>();
			this.isLast = false;
		}
	}

	// Trie 쓰지 않는 방법
//  private static String solve(String[] numbers) {
//    HashSet<String> set = new HashSet<>();
//    for (String number : numbers)
//      set.add(number);
//    
//    for (String number : numbers) {
//      int len = number.length();
//      for (int i=1; i<len; i++) {
//        if (set.contains(number.substring(0, i))) 
//          return "NO";
//      }
//    }
//    
//    return "YES";
//  }
}
