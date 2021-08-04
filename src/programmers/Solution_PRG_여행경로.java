package programmers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Solution_PRG_여행경로 {
	String path;
	int N;
	HashMap<String, List<Ticket>> map;

	public static void main(String[] args) {
		Solution_PRG_여행경로 sol = new Solution_PRG_여행경로();
//    String[][] tickets = {
//        {"ICN", "SFO"},
//        {"ICN", "ATL"},
//        {"SFO", "ATL"},
//        {"ATL", "ICN"},
//        {"ATL", "SFO"}
//    };
		String[][] tickets = {
				{"ICN", "AAA"},
				{"ICN", "BBB"},
				{"BBB", "ICN"}
		};
		System.out.println(Arrays.toString(sol.solution(tickets)));
	}

	public String[] solution(String[][] tickets) {
		this.path = new String();
		this.N = tickets.length;
		this.map = new HashMap<>();

		for (int i = 0; i < N; i++) {
			String[] ticket = tickets[i];
			String from = ticket[0];
			String to = ticket[1];
			if (map.containsKey(from))
				map.get(from).add(new Ticket(from, to));
			else {
				List<Ticket> list = new ArrayList<>();
				list.add(new Ticket(from, to));
				map.put(from, list);
			}
		}

		dfs("ICN", 0);

		String[] answer = new String[path.length() / 3];
		for (int i = 0; i < path.length() / 3; i++) {
			answer[i] = path.substring(3 * i, 3 * i + 3);
		}
		return answer;
	}

	private void dfs(String result, int count) {
		if (count == N) {
			if (path.length() == 0 || result.compareTo(path) < 0)
				path = result;
			return;
		}

		String now = result.substring(result.length() - 3);
		List<Ticket> tickets = map.get(now);
		if (tickets == null)
			return;

		for (Ticket ticket : tickets) {
			if (!ticket.used) {
				ticket.used = true;
				dfs(result.concat(ticket.to), count + 1);
				ticket.used = false;
			}
		}
	}

	class Ticket {
		boolean used;
		String from, to;

		Ticket(String from, String to) {
			this.from = from;
			this.to = to;
			this.used = false;
		}
	}
}
