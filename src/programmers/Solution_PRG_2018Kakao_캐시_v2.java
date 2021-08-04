package programmers;

import java.util.HashMap;

public class Solution_PRG_2018Kakao_캐시_v2 {
	public static void main(String[] args) {
		Solution_PRG_2018Kakao_캐시_v2 sol = new Solution_PRG_2018Kakao_캐시_v2();

//    int cacheSize = 3;
//    String[] cities = {"Jeju", "Pangyo", "Seoul", "NewYork", "LA", "Jeju", "Pangyo", "Seoul", "NewYork", "LA"};
//    int cacheSize = 2;
//    String[] cities = {"Jeju", "Pangyo", "NewYork", "newyork"};
		int cacheSize = 3;
		String[] cities = {"A", "B", "A", "A"};

		System.out.println(sol.solution(cacheSize, cities));
	}

	public int solution(int cacheSize, String[] cities) {
		int answer = 0;

		DoubleLinkedList lruCache = new DoubleLinkedList(cacheSize);

		for (String city : cities) {
			answer += lruCache.add(city.toLowerCase());
			lruCache.printCache();
		}

		return answer;
	}

	static class DoubleLinkedList {
		int size;
		int count;
		HashMap<String, Node> map = new HashMap<>();
		Node head;
		Node tail;

		DoubleLinkedList(int size) {
			this.size = size;
		}

		int add(String data) {
			if (size == 0)
				return 5;

			if (map.containsKey(data)) {
				update(data);
				return 1;
			}

			if (size <= count)
				deleteLast();

			Node node = new Node(data);
			add(node);
			count++;
			map.put(node.key, node);

			return 5;
		}

		void add(Node node) {
			if (count == 0) {
				head = node;
				tail = node;

			} else {
				head.prev = node;
				node.next = head;
				head = node;
			}
		}

		void update(String key) {
			if (map.containsKey(key) && count > 1) {
				Node node = map.get(key);
				if (node.prev == null)
					return;

				if (node.next == null) {
					tail = node.prev;
					tail.next = null;

				} else {
					node.prev.next = node.next;
					node.next.prev = node.prev;

				}
				head.prev = node;
				node.next = head;
				head = node;
				head.prev = null;
			}
		}

		void deleteLast() {
			if (count <= 0)
				return;

			if (count == 1) {
				map.clear();
				head = null;
				tail = null;

			} else {
				map.remove(tail.key);

				tail = tail.prev;
				tail.next = null;
			}

			count--;
		}

		void printCache() {
			Node node = this.head;
			while (node != null) {
				System.out.print(node.key + " ");
				node = node.next;
			}
			System.out.println();
			map.keySet().stream().map(key -> key + " ").forEach(System.out::print);
			System.out.println();
			System.out.println();
		}
	}

	static class Node {
		String key;
		Node next;
		Node prev;

		Node(String key) {
			this.key = key;
		}

		public String toString() {
			return key;
		}
	}
}
