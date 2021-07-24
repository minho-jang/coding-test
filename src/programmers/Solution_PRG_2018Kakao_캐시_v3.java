package programmers;

import java.util.HashMap;

public class Solution_PRG_2018Kakao_캐시_v3 {
    public int solution(int cacheSize, String[] cities) {
        int answer = 0;

        LRUCache lruCache = new LRUCache(cacheSize);

        for (String city : cities) {
            answer += lruCache.add(city.toLowerCase());
            lruCache.print();
        }

        return answer;
    }

    static class LRUCache {
        int size;
        int count;
        HashMap<String, Node> map;
        DoubleLinkedList linkedList;

        LRUCache(int size) {
            this.size = size;
            count = 0;
            map = new HashMap<>();
            linkedList = new DoubleLinkedList();
        }

        int add(String data) {
            if (size == 0)
                return 5;

            if (map.containsKey(data)) {
                Node node = map.get(data);
                update(node);
                return 1;
            }

            if (size <= count)
                deleteTail();

            Node node = new Node(data);
            add(node);
            count++;

            return 5;
        }

        void add(Node node) {
            node.prev = null;
            node.next = linkedList.head;

            if (linkedList.head != null)
                linkedList.head.prev = node;

            linkedList.head = node;

            if (linkedList.tail == null)
                linkedList.tail = node;

            map.put(node.key, node);
        }

        void update(Node node) {
            Node prev = node.prev;
            Node next = node.next;

            if (prev != null)
                prev.next = next;
            else
                return;

            if (next != null)
                next.prev = prev;
            else
                linkedList.tail = prev;

            add(node);
        }

        void deleteTail() {
            Node tailNode = map.get(linkedList.tail.key);

            linkedList.tail = tailNode.prev;
            if (linkedList.tail != null)
                linkedList.tail.next = null;

            map.remove(tailNode.key);
            count--;

            if (count == 0)
                linkedList.head = null;
        }

        void print() {
            Node node = linkedList.head;

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

    static class DoubleLinkedList {
        Node head;
        Node tail;
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

    public static void main(String[] args) {
        Solution_PRG_2018Kakao_캐시_v3 sol = new Solution_PRG_2018Kakao_캐시_v3();

//    int cacheSize = 3;
//    String[] cities = {"Jeju", "Pangyo", "Seoul", "NewYork", "LA", "Jeju", "Pangyo", "Seoul", "NewYork", "LA"};
//    int cacheSize = 2;
//    String[] cities = {"Jeju", "Pangyo", "NewYork", "newyork"};
        int cacheSize = 10;
        String[] cities = {"A", "B", "A", "A"};

        System.out.println(sol.solution(cacheSize, cities));
    }
}
