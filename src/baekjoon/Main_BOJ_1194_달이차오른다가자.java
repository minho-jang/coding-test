package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main_BOJ_1194_달이차오른다가자 {
	static int[] dr = {1, 0, -1, 0};
	static int[] dc = {0, 1, 0, -1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk;

		stk = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(stk.nextToken());
		int M = Integer.parseInt(stk.nextToken());

		Pos start = null;
		char[][] map = new char[N][M];
		for (int i = 0; i < N; i++) {
			String line = new String(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = line.charAt(j);
				if (map[i][j] == '0') {
					start = new Pos(i, j, 0, 0);
				}
			}
		}

		int answer = -1;
		// 키상태에 따른 visit배열 => boolean[행][열][키상태]
		// 0  0  0  0  0  0
		// a  b  c  d  e  f		1이면 키를 가지고 있음.
		boolean[][][] visited = new boolean[N][M][64];
		// BFS
		Deque<Pos> queue = new ArrayDeque<>();
		visited[start.r][start.c][0] = true;
		queue.offer(start);
		while (!queue.isEmpty()) {
			Pos p = queue.poll();
			// 출구를 만나면
			if (map[p.r][p.c] == '1') {
				answer = p.moveCount;
				break;
			}

			for (int d = 0; d < 4; d++) {
				int nr = p.r + dr[d];
				int nc = p.c + dc[d];

				// 범위를 벗어나면, 벽이면,
				if (nr < 0 || nr >= N || nc < 0 || nc >= M || map[nr][nc] == '#')
					continue;
				// 방문한 적 있으면
				if (visited[nr][nc][p.keyState])
					continue;

				// 문을 발견하면
				if (map[nr][nc] >= 'A' && map[nr][nc] <= 'F') {
					if ((p.keyState >> (5 - (map[nr][nc] - 'a')) & 1) != 1)
						continue;
				}

				// 키를 발견하면
				if (map[nr][nc] >= 'a' && map[nr][nc] <= 'f') {
					int tmp = p.keyState | 1 << (5 - (map[nr][nc] - 'a'));
					visited[nr][nc][tmp] = true;
					queue.offer(new Pos(nr, nc, tmp, p.moveCount + 1));
				} else {
					visited[nr][nc][p.keyState] = true;
					queue.offer(new Pos(nr, nc, p.keyState, p.moveCount + 1));
				}
			}

		}

		System.out.println(answer);
	}

	static class Pos {
		int r, c, keyState, moveCount;

		public Pos(int r, int c, int k, int m) {
			super();
			this.r = r;
			this.c = c;
			this.keyState = k;
			this.moveCount = m;
		}
	}
}

/*

public class Main_BOJ_1194_달이차오른다가자 {
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	static class Pos {
		int r,c, moveCount;
		int keyState;
		public Pos(int r, int c, int moveCount, int keyState) {
			this.r = r;
			this.c = c;
			this.moveCount = moveCount;
			this.keyState = keyState;
		}
		@Override
		public String toString() {
			return "[" + r + ", " + c + ", " + keyState + "]";
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk;
		
		stk = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(stk.nextToken());
		int M = Integer.parseInt(stk.nextToken());
		
		// 입력 받으면서 시작점을 찾고, 시작점은 '.'으로 바꿔준다.
		Pos start = null;
		char[][] map = new char[N][M];
		for (int i = 0; i < N; i++) {
			map[i] = br.readLine().toCharArray();
			for (int j = 0; j < M; j++) {
				if (map[i][j] == '0') {
					start = new Pos(i, j, 0, 0);
					map[i][j] = '.';
				}
			}
		}
		
		// BFS
		boolean[][][] visited = new boolean[N][M][64];
		Queue<Pos> queue = new LinkedList<>();
		
		queue.add(start);
		visited[start.r][start.c][0] = true;
		
		int answer = -1;
		while(!queue.isEmpty()) {
			Pos polled = queue.poll();
			
			for (int d = 0; d < 4; d++) {
				int nr = polled.r + dr[d];
				int nc = polled.c + dc[d];
				
				if (nr<0 || nr>=N || nc<0 || nc>=M)
					continue;
				if (visited[nr][nc][polled.keyState])
					continue;
				
				if (map[nr][nc] == '1') {
					answer = polled.moveCount + 1;
					queue.clear();
					break;
				}
				
				else if (map[nr][nc] == '#') {
					continue;
				} else if (map[nr][nc] == '.') {
					visited[nr][nc][polled.keyState] = true;
					queue.offer(new Pos(nr, nc, polled.moveCount+1, polled.keyState));
				}
				
				// 문 (A~F)
				else if (map[nr][nc] == 'A') {
					if ((polled.keyState & (1<<5)) == 1<<5) {		// 키 확인
						visited[nr][nc][polled.keyState] = true;	// 방문처리
						queue.offer(new Pos(nr, nc, polled.moveCount+1, polled.keyState)); // 큐에 삽입하여 계속 탐색
					}
				} else if (map[nr][nc] == 'B') {
					if ((polled.keyState & (1<<4)) == 1<<4) {
						visited[nr][nc][polled.keyState] = true;
						queue.offer(new Pos(nr, nc, polled.moveCount+1, polled.keyState));
					}
				} else if (map[nr][nc] == 'C') {
					if ((polled.keyState & (1<<3)) == 1<<3) {
						visited[nr][nc][polled.keyState] = true;
						queue.offer(new Pos(nr, nc, polled.moveCount+1, polled.keyState));
					}
				} else if (map[nr][nc] == 'D') {
					if ((polled.keyState & (1<<2)) == 1<<2) {
						visited[nr][nc][polled.keyState] = true;
						queue.offer(new Pos(nr, nc, polled.moveCount+1, polled.keyState));
					}
				} else if (map[nr][nc] == 'E') {
					if ((polled.keyState & (1<<1)) == 1<<1) {
						visited[nr][nc][polled.keyState] = true;
						queue.offer(new Pos(nr, nc, polled.moveCount+1, polled.keyState));
					}
				} else if (map[nr][nc] == 'F') {
					if ((polled.keyState & (1<<0)) == 1<<0) {
						visited[nr][nc][polled.keyState] = true;
						queue.offer(new Pos(nr, nc, polled.moveCount+1, polled.keyState));
					}
				}
				// 열쇠 (a~f)
				else if (map[nr][nc] == 'a') {
					int nKeyState = polled.keyState | (1<<5);		// 키 상태 변경
					visited[nr][nc][nKeyState] = true;				// 방문처리
					queue.offer(new Pos(nr, nc, polled.moveCount+1, nKeyState));	// 큐에 삽입하여 계속 탐색
					
				} else if (map[nr][nc] == 'b') {
					int nKeyState = polled.keyState | (1<<4);
					visited[nr][nc][nKeyState] = true;
					queue.offer(new Pos(nr, nc, polled.moveCount+1, nKeyState));
					
				} else if (map[nr][nc] == 'c') {
					int nKeyState = polled.keyState | (1<<3);
					visited[nr][nc][nKeyState] = true;
					queue.offer(new Pos(nr, nc, polled.moveCount+1, nKeyState));
					
				} else if (map[nr][nc] == 'd') {
					int nKeyState = polled.keyState | (1<<2);
					visited[nr][nc][nKeyState] = true;
					queue.offer(new Pos(nr, nc, polled.moveCount+1, nKeyState));
					
				} else if (map[nr][nc] == 'e') {
					int nKeyState = polled.keyState | (1<<1);
					visited[nr][nc][nKeyState] = true;
					queue.offer(new Pos(nr, nc, polled.moveCount+1, nKeyState));
					
				} else if (map[nr][nc] == 'f') {
					int nKeyState = polled.keyState | (1<<0);
					visited[nr][nc][nKeyState] = true;
					queue.offer(new Pos(nr, nc, polled.moveCount+1, nKeyState));
				}
			}
		}
		
		System.out.println(answer);
	}
}


*/