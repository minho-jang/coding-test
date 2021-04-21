package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_BOJ_13460_구슬탈출2 {
	static class Pos {
		int r,c;
		boolean finish;
		public Pos(int r, int c) {
			this.r = r;
			this.c = c;
			this.finish = false;
		}
		@Override
		public String toString() {
			return "[" + r + ", " + c + "]";
		}
		
	}
	private static char[][] map;
	private static int[] dr = {-1, 1, 0, 0};
	private static int[] dc = {0, 0, -1, 1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk;
		
		stk = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(stk.nextToken());
		int M = Integer.parseInt(stk.nextToken());
		
		Pos red=null, blue=null;
		map = new char[N][M];
		for (int i = 0; i < N; i++) {
			map[i] = br.readLine().toCharArray();
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 'R') {
					red = new Pos(i,j);
					map[i][j] = '.';
				} else if (map[i][j] == 'B') {
					blue = new Pos(i,j);
					map[i][j] = '.';
				} 
			}
		}
		
		Queue<Pos> queue = new LinkedList<>();
		queue.offer(red);
		queue.offer(blue);
		
		int level = 1;
		while(!queue.isEmpty()) {
			if (level > 10)
				break;
			
			int size = queue.size() / 2;
			for (int i = 0; i < size; i++) {
				Pos r = queue.poll();
				Pos b = queue.poll();
				
				for (int d = 0; d < 4; d++) {
					Pos[] nextRedBlue = go(r, b, d);
					
					Pos nr = nextRedBlue[0];
					Pos nb = nextRedBlue[1];
					
					if (nr.r==r.r && nr.c==r.c && nb.r==b.r && nb.c==b.c) {	// 굴렸는데 두 공 모두 제자리
						continue;
					} 
					
					if (nr.finish) {
						if (nb.finish)	// 실패
							continue;
						else { 			// 성공
							System.out.println(level);
							return;
						}
					} else {	
						if (nb.finish)	// 실패
							continue;
						else { 			// 더 탐색
							queue.offer(nr);
							queue.offer(nb);
						}
					}
					
				}
			}
			
			level++;
		}
		
		System.out.println(-1);
	}

	private static Pos[] go(Pos red, Pos blue, int d) {
		Pos nRed = new Pos(red.r, red.c);
		Pos nBlue = new Pos(blue.r, blue.c);
		
		boolean redOK = true;
		boolean blueOK = true;
		while(true) {
			if (!redOK && !blueOK)	// 둘 다 이동할 수 없는 상태
				break;
			
			int nextRedR = nRed.r, nextRedC = nRed.c;
			int nextBlueR = nBlue.r, nextBlueC = nBlue.c;
			// 빨간공 이동 & 파란공 이동
			if (redOK) {
				nextRedR += dr[d];	
				nextRedC += dc[d];
			}
			if (blueOK) {
				nextBlueR += dr[d];
				nextBlueC += dc[d];
			}
			
			// 빨간공 판단
			if (map[nextRedR][nextRedC] == '#') 
				redOK = false;
			if (map[nextRedR][nextRedC] == 'O') {
				nRed.r = nextRedR;
				nRed.c = nextRedC;
				nRed.finish = true;
				redOK = false;
			}
			// 파란공 판단
			if (map[nextBlueR][nextBlueC] == '#') 
				blueOK = false;
			if (map[nextBlueR][nextBlueC] == 'O') { 
				nBlue.r = nextBlueR;
				nBlue.c = nextBlueC;
				nBlue.finish = true;
				blueOK = false;
			}
			
			if (!blueOK && redOK) {
				if (nextRedR==nBlue.r && nextRedC==nBlue.c) {
					redOK = false;
					blueOK = false;
				}
			}
			if (blueOK && !redOK) {
				if (nextBlueR==nRed.r && nextBlueC==nRed.c) {
					redOK = false;
					blueOK = false;
				}
			}
			
			if (redOK) {
				nRed.r = nextRedR;
				nRed.c = nextRedC;
			}
			if (blueOK) {				
				nBlue.r = nextBlueR;
				nBlue.c = nextBlueC;
			}
		}
		
		return new Pos[] {nRed, nBlue};
	}
}
