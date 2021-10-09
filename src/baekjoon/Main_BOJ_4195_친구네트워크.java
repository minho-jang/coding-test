package baekjoon;

import java.io.*;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main_BOJ_4195_친구네트워크 {
	static HashMap<String, String> friendMap;
	static HashMap<String, Integer> friendCountMap;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("input.txt"));

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int T = Integer.parseInt(br.readLine());
		for (int tc = 0; tc < T; tc++) {

			int N = Integer.parseInt(br.readLine());
			friendMap = new HashMap<>();
			friendCountMap = new HashMap<>();
			for (int i = 0; i < N; i++) {
				StringTokenizer stk = new StringTokenizer(br.readLine());
				String f1 = stk.nextToken();
				String f2 = stk.nextToken();

				init(f1);
				init(f2);
				int result = union(f1, f2);
				bw.write(String.valueOf(result));
				bw.newLine();
			}

		}

		bw.flush();

		br.close();
		bw.close();
	}

	private static int union(String f1, String f2) {
		String root1 = findRoot(f1);
		String root2 = findRoot(f2);
		if (root1.equals(root2)) {
			return friendCountMap.get(root1);

		} else {
			friendMap.put(root2, root1);
			return friendCountMap.compute(root1, (s, i) -> i + friendCountMap.get(root2));
		}
	}

	private static void init(String friend) {
		if (!friendMap.containsKey(friend)) {
			friendMap.put(friend, friend);
			friendCountMap.put(friend, 1);
		}
	}

	private static String findRoot(String friend) {
		if (friend.equals(friendMap.get(friend))) {
			return friend;

		} else {
			String next = friendMap.get(friend);
			String root = findRoot(next);
			friendMap.put(friend, root);
			return root;
		}
	}
}
