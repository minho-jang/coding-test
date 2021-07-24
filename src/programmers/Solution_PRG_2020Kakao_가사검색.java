package programmers;

import java.util.Arrays;
import java.util.HashMap;

public class Solution_PRG_2020Kakao_가사검색 {
    public int[] solution(String[] words, String[] queries) {

        HashMap<String, Integer> cache = new HashMap<>();

        Trie[] root = new Trie[10001];
        Trie[] tail = new Trie[10001];

        for (String word : words) {
            int len = word.length();
            if (root[len] == null) {
                root[len] = new Trie();
                tail[len] = new Trie();
            }

            root[len].insert(word);
            tail[len].insert(new StringBuffer(word).reverse().toString());
        }

        int queryN = queries.length;
        int[] answer = new int[queryN];
        for (int i = 0; i < queryN; i++) {
            int result = 0;

            String query = queries[i];
            int len = query.length();

            if (cache.containsKey(query))
                result = cache.get(query);

            else if (query.charAt(len - 1) != '?') {    // 접미어 검사
                if (tail[len] == null)
                    result = 0;
                else
                    result = tail[len].search(new StringBuffer(query.substring(query.lastIndexOf('?') + 1)).reverse().toString());
                cache.put(query, result);

            } else if (query.charAt(0) != '?') {    // 접두사 검사
                if (root[len] == null)
                    result = 0;
                else
                    result = root[len].search(query.substring(0, query.indexOf('?')));
                cache.put(query, result);

            } else {    // 전부 물음표
                if (root[len] == null)
                    result = 0;
                else
                    result = root[len].count;
                cache.put(query, result);
            }

            answer[i] = result;
        }

        return answer;
    }

    static class Trie {
        int count;
        Trie[] child;

        Trie() {
            child = new Trie[26];
            count = 0;
        }

        // 시간이 오래걸리는 방식 (재귀)
//		void insert(String word) {
//			if (word.length() == 0)
//				return;
//			
//			this.count++;
//			
//			int c = word.charAt(0) - 'a';
//			if (child[c] == null)
//				child[c] = new Trie();
//			
//			child[c].insert(word.substring(1));
//		}

        void insert(String word) {
            Trie current = this;
            for (char c : word.toCharArray()) {
                current.count++;
                if (current.child[c - 'a'] == null)
                    current.child[c - 'a'] = new Trie();
                current = current.child[c - 'a'];
            }
        }

        // insert와 마찬가지로 시간이 오래 걸리는 방식 (재귀)
//		int search(String query) {
//			if (query.length() == 0)
//				return this.count;
//			
//			int c = query.charAt(0) - 'a';
//			if (child[c] == null)
//				return 0;
//			
//			return child[c].search(query.substring(1));
//		}

        int search(String query) {
            Trie current = this;
            for (char c : query.toCharArray()) {
                if (current.child[c - 'a'] == null)
                    return 0;
                current = current.child[c - 'a'];
            }
            return current.count;
        }
    }

    public static void main(String[] args) {
        Solution_PRG_2020Kakao_가사검색 s = new Solution_PRG_2020Kakao_가사검색();

        String[] words = {"frodo", "front", "frost", "frozen", "frame", "kakao"};
        String[] queries = {"fro??", "????o", "fr???", "fro???", "pro?"};

        System.out.println(Arrays.toString(s.solution(words, queries)));
    }
}
