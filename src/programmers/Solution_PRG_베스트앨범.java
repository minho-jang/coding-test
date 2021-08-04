package programmers;

import java.util.*;

public class Solution_PRG_베스트앨범 {
	public static void main(String[] args) {
		Solution_PRG_베스트앨범 sol = new Solution_PRG_베스트앨범();
		String[] genres = {"classic", "pop", "classic", "classic", "pop"};
		int[] plays = {500, 600, 150, 800, 2500};
		System.out.println(Arrays.toString(sol.solution(genres, plays)));
	}

	public int[] solution(String[] genres, int[] plays) {
		List<Genre> genreList = new ArrayList<>();
		HashMap<String, Node> map = new HashMap<>();
		int N = plays.length;

		for (int i = 0; i < N; i++) {
			if (map.containsKey(genres[i])) {
				Node hit = map.get(genres[i]);
				hit.playlist.add(new Music(i, plays[i]));
				genreList.get(hit.idx).sum += plays[i];

			} else {
				Genre genre = new Genre(genres[i], 0);
				genreList.add(genre);
				int genreIdx = genreList.size() - 1;

				Node newNode = new Node(genreIdx);
				newNode.playlist.add(new Music(i, plays[i]));

				genre.sum += plays[i];

				map.put(genres[i], newNode);
			}
		}

		ArrayList<Integer> answerList = new ArrayList<>();
		Collections.sort(genreList, (o1, o2) -> Integer.compare(o1.sum, o2.sum) * (-1));
		for (Genre genre : genreList) {
			int[] result = map.get(genre.genre).getTopTwoMusic();
			if (result != null) {
				for (int r : result)
					answerList.add(r);
			}
		}

		int[] answer = new int[answerList.size()];
		for (int i = 0; i < answer.length; i++)
			answer[i] = answerList.get(i);
		return answer;
	}

	class Genre {
		String genre;
		int sum;

		Genre(String genre, int sum) {
			this.genre = genre;
			this.sum = sum;
		}
	}

	class Music {
		int idx, plays;

		Music(int idx, int plays) {
			this.idx = idx;
			this.plays = plays;
		}
	}

	class Node {
		List<Music> playlist;
		int idx;

		Node(int idx) {
			this.idx = idx;
			this.playlist = new ArrayList<>();
		}

		int[] getTopTwoMusic() {
			if (playlist.size() >= 2) {
				Collections.sort(playlist, (o1, o2) -> Integer.compare(o1.plays, o2.plays) * (-1));
				return new int[]{playlist.get(0).idx, playlist.get(1).idx};

			} else if (playlist.size() == 1) {
				return new int[]{playlist.get(0).idx};

			} else {
				return null;
			}
		}
	}
}
