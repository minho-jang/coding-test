package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Main_BOJ_14725_개미굴 {
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input.txt"));

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk;

		Trie trie = new Trie();

		int N = Integer.parseInt(br.readLine());
		for (int i = 0; i < N; i++) {

			stk = new StringTokenizer(br.readLine());
			int K = Integer.parseInt(stk.nextToken());

			String[] words = new String[K];
			for (int j = 0; j < K; j++)
				words[j] = stk.nextToken();

			trie.insert(words);

		}

		trie.print(trie.root, 0);
	}

	static class Trie {
		Node root;

		Trie() {
			root = new Node();
		}

		void insert(String[] words) {
			Node now = root;
			for (String word : words) {
				now = now.child.computeIfAbsent(word, key -> new Node());
			}
		}

		void print(Node n, int count) {
			for (String key : n.child.keySet()) {
				StringBuilder sb = new StringBuilder();

				for (int i = 0; i < count; i++)
					sb.append("--");
				sb.append(key).append("\n");
				System.out.print(sb.toString());

				print(n.child.get(key), count + 1);
			}
		}
	}

	static class Node {
		TreeMap<String, Node> child;

		Node() {
			child = new TreeMap<>();
		}
	}
}
