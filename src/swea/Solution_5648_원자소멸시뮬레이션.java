package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Solution_5648_원자소멸시뮬레이션 {
	static class Atom {
		int x, y;
		int direnction;
		int energy;
		boolean isDead;
		public Atom(int x, int y, int direnction, int energy) {
			super();
			this.x = x;
			this.y = y;
			this.direnction = direnction;
			this.energy = energy;
			this.isDead = false;
		}
		@Override
		public String toString() {
			return "(" + x + ", " + y + ", " + direnction + ", " + energy + ", " + isDead + ")";
		}
	}
	
	static int[][] map = new int[4001][4001];
	static ArrayList<Atom> atomList;
	static int answer;
	static int[] dy = {1, -1, 0, 0};
	static int[] dx = {0, 0, -1, 1};
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk;
		StringBuilder answerSb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			
			answer = 0;
			atomList = new ArrayList<>();
			for (int i = 0; i <= 4000; i++) {
                for (int j = 0; j <= 4000; j++) {
                    map[i][j] = 0;
                }
            }
			
			int N = Integer.parseInt(br.readLine());
			for (int i = 0; i < N; i++) {
				stk = new StringTokenizer(br.readLine());
				int x = (Integer.parseInt(stk.nextToken()) + 1000) * 2;
				int y = (Integer.parseInt(stk.nextToken()) + 1000) * 2;
				int d = Integer.parseInt(stk.nextToken());	// 방향
				int e = Integer.parseInt(stk.nextToken());	// 에너지
				atomList.add(new Atom(x,y,d,e));
			}
			
			int totalEnergy = 0;
			int time = 0;
			int atomCount = atomList.size();
			while(atomCount > 0) {
				if (time > 4000)
					break;
				int size = atomList.size();
				
				// 각 원자 이동
				for (int i = 0; i < size; i++) {
					Atom atom = atomList.get(i);
					if (!atom.isDead)
						moveAtom(atom);
				}
				
				// 충돌 확인
				// 1. 각 원자 위치 map 초기화
				for (int i = 0; i < size; i++) {
					Atom atom = atomList.get(i);
					if (!atom.isDead)
						map[atom.y][atom.x] = 0;
				}
				// 2. 각 원자 위치 map 에너지 합산
				for (int i = 0; i < size; i++) {
					Atom atom = atomList.get(i);
					if (!atom.isDead)
						map[atom.y][atom.x] += atom.energy;
				}
				// 3. 각 원자 위치 map에서 에너지 확인
				for (int i = 0; i < size; i++) {
					Atom atom = atomList.get(i);
					if (!atom.isDead && map[atom.y][atom.x] != atom.energy) {	
						atom.isDead = true;
						totalEnergy += map[atom.y][atom.x];
						map[atom.y][atom.x] = 0;
					}
				}
				
				time++;
			}
			
			answerSb.append("#").append(tc).append(" ").append(totalEnergy).append("\n");
		}
		
		System.out.print(answerSb.toString());
	}

	private static void moveAtom(Atom atom) {
		int ny = atom.y + dy[atom.direnction];
		int nx = atom.x + dx[atom.direnction];
		
		if (ny < 0 || ny > 4000 || nx < 0 || nx > 4000) {
			atom.isDead = true;
			return;
		}
		
		atom.y = ny;
		atom.x = nx;
	}
}


/*

2
4
-1000 0 3 5
1000 0 2 3
0 1000 1 7
0 -1000 0 9
4
-1 1 3 3
0 1 1 1
0 0 2 2
-1 0 0 9

*/