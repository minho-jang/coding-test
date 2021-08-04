package programmers;

import java.util.Arrays;
import java.util.PriorityQueue;

public class Solution_PRG_이중우선순위큐 {
	public static void main(String[] args) {
		Solution_PRG_이중우선순위큐 sol = new Solution_PRG_이중우선순위큐();
//    String[] operations = {"I 16","D 1"};
//    String[] operations = {"I 7","I 5","I -5","D -1"};
//    String[] operations = {"I 16", "I -5643", "D -1", "D 1", "D 1", "I 123", "D -1"};
		String[] operations = {"I 10", "I 20", "D 1", "I 30", "I 40", "D -1", "D -1"};
		System.out.println(Arrays.toString(sol.solution(operations)));
	}

	public int[] solution(String[] operations) {
		PriorityQueue<Integer> queue = new PriorityQueue<>((o1, o2) -> Integer.compare(o1, o2));
		PriorityQueue<Integer> queueRev = new PriorityQueue<>((o1, o2) -> Integer.compare(o1, o2) * (-1));

		for (String operation : operations) {
			if (operation.equals("D 1")) {
				if (queueRev.size() > 0) {
					int removed = queueRev.remove();
					queue.remove(removed);
				}

			} else if (operation.equals("D -1")) {
				if (queue.size() > 0) {
					int removed = queue.remove();
					queueRev.remove(removed);
				}

			} else {
				String[] splitted = operation.split(" ");
				int num = Integer.parseInt(splitted[1]);
				queue.add(num);
				queueRev.add(num);
			}

//      System.out.println(queue);
//      System.out.println(queueRev);
//      System.out.println();
		}

		if (queue.isEmpty() || queueRev.isEmpty())
			return new int[]{0, 0};
		else
			return new int[]{queueRev.remove(), queue.remove()};
	}
}
