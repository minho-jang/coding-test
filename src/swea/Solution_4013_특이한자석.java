package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution_4013_특이한자석 {
	static class Wheel {
		int[] magnetic;
		int N;
		int scoreCursor;
		int rightWheelCursor;
		int leftWheelCursor;
		public Wheel(int[] magnetic, int scoreCursor, int rightWheelCursor, int leftWheelCursor) {
			this.magnetic = magnetic;
			this.N = magnetic.length;
			this.scoreCursor = scoreCursor;
			this.rightWheelCursor = rightWheelCursor;
			this.leftWheelCursor = leftWheelCursor;
		}
		
		void ccw() {	// 반시계방향
			scoreCursor = scoreCursor+1 >= N ? scoreCursor+1-N : scoreCursor+1;
			if (rightWheelCursor >= 0)
				rightWheelCursor = rightWheelCursor+1 >= N ? rightWheelCursor+1-N : rightWheelCursor+1;
			if (leftWheelCursor >= 0)
				leftWheelCursor = leftWheelCursor+1 >= N ? leftWheelCursor+1-N : leftWheelCursor+1; 
		}
		void cw() {	// 시계방향
			scoreCursor = scoreCursor-1 < 0 ? scoreCursor-1+N : scoreCursor-1;
			if (rightWheelCursor >= 0)
				rightWheelCursor = rightWheelCursor-1 < 0 ? rightWheelCursor-1+N : rightWheelCursor-1;
			if (leftWheelCursor >= 0)
				leftWheelCursor = leftWheelCursor-1 < 0? leftWheelCursor-1+N : leftWheelCursor-1; 
		}
		int getRightState() {
			return magnetic[rightWheelCursor];
		}
		int getLeftState() {
			return magnetic[leftWheelCursor];
		}
		int getScore() {
			return magnetic[scoreCursor];
		}
		public String toString() {
			return Arrays.toString(magnetic) + " r=" + rightWheelCursor + " l=" + leftWheelCursor + " " + scoreCursor;
		}
	}
	
	static Wheel[] wheels;
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk;
		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			
			int K = Integer.parseInt(br.readLine());
			wheels = new Wheel[4];
			for (int i = 0; i < 4; i++) {
				stk = new StringTokenizer(br.readLine());
				int[] mag = new int[8];
				for (int j = 0; j < 8; j++) {
					mag[j] = Integer.parseInt(stk.nextToken());
				}
				if (i == 0) {
					wheels[i] = new Wheel(mag, 0, 2, -1);
				} else if (i == 3) {
					wheels[i] = new Wheel(mag, 0, -1, 6);
				} else {
					wheels[i] = new Wheel(mag, 0, 2, 6);
				}
			}
			
			for (int i = 0; i < K; i++) {
				stk = new StringTokenizer(br.readLine());
				int wheelNum = Integer.parseInt(stk.nextToken())-1;
				int direction = Integer.parseInt(stk.nextToken());
				
				dfs(wheelNum, direction, new boolean[4]);
			}
			
			int answer = 0;
			for (int i = 0; i < 4; i++) {
				if (wheels[i].getScore() == 1) {
					answer += Math.pow(2, i);
				}
			}
			
			System.out.println("#" + tc + " " + answer);
			
		}
		
	}

	private static void dfs(int num, int direction, boolean[] visited) {
		visited[num] = true;
		if (direction == 1) {	// 시계방향
			if (wheels[num].rightWheelCursor >= 0 && wheels[num+1].leftWheelCursor >= 0 &&
					wheels[num].getRightState()!=wheels[num+1].getLeftState() &&
					!visited[num+1]) {
				dfs(num+1, -1, visited);
			} 
			if (wheels[num].leftWheelCursor >= 0 && wheels[num-1].rightWheelCursor >= 0 &&
					wheels[num].getLeftState()!=wheels[num-1].getRightState() &&
					!visited[num-1]) {
				dfs(num-1, -1, visited);
			}
			
			wheels[num].cw();
			
		} else if (direction == -1) {	// 반시계방향
			if (wheels[num].rightWheelCursor >= 0 && wheels[num+1].leftWheelCursor >= 0 &&
					wheels[num].getRightState()!=wheels[num+1].getLeftState() &&
					!visited[num+1]) {
				dfs(num+1, 1, visited);
			} 
			if (wheels[num].leftWheelCursor >= 0 && wheels[num-1].rightWheelCursor >= 0 &&
					wheels[num].getLeftState()!=wheels[num-1].getRightState() &&
					!visited[num-1]) {
				dfs(num-1, 1, visited);
			}
			
			wheels[num].ccw();
		}
	}
}


/*

1
2
0 0 1 0 0 1 0 0
1 0 0 1 1 1 0 1
0 0 1 0 1 1 0 0
0 0 1 0 1 1 0 1
1 1
3 -1

*/