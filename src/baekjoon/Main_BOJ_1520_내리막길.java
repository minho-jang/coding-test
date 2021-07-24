package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_BOJ_1520_내리막길 {
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    static class Pos {
        int r, c;

        public Pos(int r, int c) {
            this.r = r;
            this.c = c;
        }

        @Override
        public String toString() {
            return "Pos [r=" + r + ", c=" + c + "]";
        }
    }

    static Pos start, end;
    static int[][] map, dp;
    static int R, C;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk;

        stk = new StringTokenizer(br.readLine());
        R = Integer.parseInt(stk.nextToken());
        C = Integer.parseInt(stk.nextToken());
        map = new int[R][C];
        for (int i = 0; i < R; i++) {
            stk = new StringTokenizer(br.readLine());
            for (int j = 0; j < C; j++) {
                map[i][j] = Integer.parseInt(stk.nextToken());
            }
        }

        start = new Pos(0, 0);
        end = new Pos(R - 1, C - 1);
        dp = new int[R][C];
        for (int i = 0; i < R; i++)
            Arrays.fill(dp[i], -1);

        int answer = dfs(start);

        for (int i = 0; i < R; i++)
            System.out.println(Arrays.toString(dp[i]));

        System.out.println(answer);
    }

    private static int dfs(Pos here) {
        if (here.r == end.r && here.c == end.c)
            return 1;

        int result = 0;
        for (int d = 0; d < 4; d++) {
            int nr = here.r + dr[d];
            int nc = here.c + dc[d];

            if (nr < 0 || nr >= R || nc < 0 || nc >= C)
                continue;
            if (map[here.r][here.c] <= map[nr][nc])
                continue;

            if (dp[nr][nc] < 0)
                result += dfs(new Pos(nr, nc));
            else
                result += dp[nr][nc];
        }

        return dp[here.r][here.c] = result;
    }
}


/*
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Algo2_서울_11반_장민호 {
	static class Pos {	// 위치를 표현하는 클래스
		int r, c;		// 행과 열
		public Pos(int r, int c) {	// 생성자
			this.r = r;		// 행
			this.c = c;		// 열
		}
	}
	static int M, N, answer = 0;		// 행, 열, 답
	static Pos start, end;				// 시작위치, 끝나는 위치
	static int[][] map;					// 산의 지형
	static int[] dr = {-1, 1, 0, 0};	// 사방탐색을 위한 행 델타
	static int[] dc = {0, 0, -1, 1};	// 사방탐색을 위한 열 델타
	
	public static void main(String[] args) throws NumberFormatException, IOException {	// main
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));	// 입력 받는 BufferedReader
		StringTokenizer stk;														// 문자열을 나누는 StringTokenizr	
		StringBuilder answerSb = new StringBuilder();								// 답을 출력하는 StringBuilder
		
		answer = 0;									// 답 초기화
		stk = new StringTokenizer(br.readLine());	// 한 줄 입력
		M = Integer.parseInt(stk.nextToken());		// 행 개수 저장
		N = Integer.parseInt(stk.nextToken());		// 열 개수 저장

		map = new int[M][N];						// map 생성
		for (int i = 0; i < M; i++) {					// 행 만큼 반복
			stk = new StringTokenizer(br.readLine());	// 한 줄 입력
			for (int j = 0; j < N; j++) {						// 열 만큼 반복 
				map[i][j] = Integer.parseInt(stk.nextToken());	// 산의 지형 저장
			}
		}
		
		start = new Pos(0,0);		// 시작은 가장 왼쪽 위 칸
		end = new Pos(M-1, N-1);	// 도착은 가장 오른족 아래 칸
		
		dfs(start);		// DFS
		
		System.out.println(answer);
	}
	
	private static void dfs(Pos here) {		// 현재 위치 here 에서 사방 탐색
		if (here.r==end.r && here.c==end.c) {		// 현재 위치가 도착이라면
			answer++;	// 개수 증가
			return;		// 종료
		}//if
		
		for (int d = 0; d < 4; d++) {	// 사방탐색
			int nr = here.r + dr[d];	// 다음 위치 행
			int nc = here.c + dc[d];	// 다음 위치 열
			
			if (nr<0 || nr>=M || nc<0 || nc>=N)	// 범위를 벗어나면
				continue; 	// 넘어간다
			
			if (map[here.r][here.c] <= map[nr][nc]) // 내리막길이 아니라면
				continue;	// 넘어간다
			
			dfs(new Pos(nr,nc));	// 도달 가능한 다음 위치로부터 다시 DFS 탐색
		}//for
		
	}//dfs
}

*/