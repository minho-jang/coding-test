package programmers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Solution_PRG_2019Kakao_오픈채팅방 {
	public static void main(String[] args) {
		Solution_PRG_2019Kakao_오픈채팅방 sol = new Solution_PRG_2019Kakao_오픈채팅방();
		String[] record = {
				"Enter uid1234 Muzi",
				"Enter uid4567 Prodo",
				"Leave uid1234",
				"Enter uid1234 Prodo",
				"Change uid4567 Ryan"
		};
		System.out.println(Arrays.toString(sol.solution(record)));
	}

	public String[] solution(String[] record) {
		ArrayList<Node> answerList = new ArrayList<>();
		HashMap<String, String> map = new HashMap<>();

		for (String rec : record) {
			String[] splitted = rec.split(" ");

			if (splitted[0].equals("Enter")) {
				map.put(splitted[1], splitted[2]);
				answerList.add(new Node(splitted[1], "님이 들어왔습니다."));

			} else if (splitted[0].equals("Leave")) {
				answerList.add(new Node(splitted[1], "님이 나갔습니다."));

			} else if (splitted[0].equals("Change")) {
				map.put(splitted[1], splitted[2]);

			}
		}

		String[] answer = new String[answerList.size()];
		for (int i = 0; i < answerList.size(); i++) {
			Node node = answerList.get(i);
			answer[i] = map.get(node.uid) + node.msg;
		}

		return answer;
	}

	class Node {
		String uid;
		String msg;

		Node(String uid, String msg) {
			this.uid = uid;
			this.msg = msg;
		}
	}
}
