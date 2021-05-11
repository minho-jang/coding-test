package programmers;

import java.util.*;

class Solution_PRG_2020Kakao_기둥과보설치 {
    int[][] mapBo;
    int[][] mapGidung;
    int N;
    
    public int[][] solution(int n, int[][] build_frame) {
        N = n+1;
        mapBo = new int[N][N];
        mapGidung = new int[N][N];
        
        for (int[] bf : build_frame) {
            int x = bf[0];   // x 좌표
            int y = bf[1];   // y 좌표
            int a = bf[2];   // 0=기둥, 1=보
            int b = bf[3];   // 0=삭제, 1=설치
            
            build(x, y, a, b);
        }
        
        ArrayList<int[]> answerList = new ArrayList<>();
        for (int i=0; i<N; i++) {
            for (int j=0; j<N; j++) {
                if (mapGidung[i][j] == 1) 
                    answerList.add(new int[] {i,j,0});
            }
        }
        
        for (int i=0; i<N; i++) {
            for (int j=0; j<N; j++) {
                if (mapBo[i][j] == 1) 
                    answerList.add(new int[] {i,j,1});
            }
        }
        
        Collections.sort(answerList, (o1, o2) -> {
            if (o1[0] != o2[0])
                return Integer.compare(o1[0], o2[0]);
            else if (o1[1] != o2[1])
                return Integer.compare(o1[1], o2[1]);
            else
                return Integer.compare(o1[2], o2[2]);
        });
        
        int[][] answer = new int[answerList.size()][3];
        for (int i=0; i<answerList.size(); i++) {
            int[] ans = answerList.get(i);
            for (int j=0; j<3; j++) {
                answer[i][j] = ans[j];
            }
        }
        
        // for (int i=0; i<answer.length; i++)
        //     System.out.println(Arrays.toString(answer[i]));
        
        return answer;
    }
    
    void build(int x, int y, int what, int how) {
        if (how == 0)               // 삭제
            delete(x, y, what);
        else if (how == 1)          // 설치
            install(x, y, what);
    }
    
    void install(int x, int y, int what) {
        if (what == 0) {            // 기둥
            mapGidung[x][y] = 1;
            if (!isValid()) {
                mapGidung[x][y] = 0;
            }
        } else if (what == 1) {     // 보
            mapBo[x][y] = 1;
            if (!isValid()) {
                mapBo[x][y] = 0;
            }
        }
    }
    
    void delete(int x, int y, int what) {
        if (what == 0) {            // 기둥
            mapGidung[x][y] = 0;
            if (!isValid()) {
                mapGidung[x][y] = 1;
            }
        } else if (what == 1) {     // 보
            mapBo[x][y] = 0;
            if (!isValid()) {
                mapBo[x][y] = 1;
            }
        }
    }
    
    // 현재 map이 괜찮은가?
    boolean isValid() {
        
        // 기둥 조건
        for (int i=0; i<N; i++) {
            for (int j=0; j<N; j++) {
                if (mapGidung[i][j] == 1) {
                    
                    if (j == 0)     // 바닥 위에 있거나
                        continue;
                    else if (isGidungOnBo(i, j))  // 보의 한 쪽 끝에 있거나
                        continue;
                    else if (isGidungOnGidung(i, j))  // 다른 기둥 위에 있거나
                        continue;
                    
                    return false;
                }
            }
        }
        
        // 보 조건
        for (int i=0; i<N; i++) {
            for (int j=0; j<N; j++) {
                if (mapBo[i][j] == 1) {
                    
                    if (isBoOnGidung(i, j))     // 보의 한 쪽 끝부분이 기둥 위에 있거나
                        continue;
                    else if (isConnectOtherBo(i, j))  // 다른 보와 연결되어 있거나
                        continue;
                    
                    return false;
                }
            }
        }
        
        return true;
        
    }
    
    boolean isGidungOnBo(int x, int y) {
        return (x-1>=0 && mapBo[x-1][y]==1) || mapBo[x][y]==1;
    }
    boolean isGidungOnGidung(int x, int y) {
        return y-1>=0 && mapGidung[x][y-1]==1;
    }
    boolean isBoOnGidung(int x, int y) {
        if (y-1 < 0)
            return false;
        return mapGidung[x][y-1]==1 || (x+1<N && mapGidung[x+1][y-1]==1);
    }
    boolean isConnectOtherBo(int x, int y) {
        if (x-1 < 0 || x+1 >= N)
            return false;
        
        return mapBo[x-1][y]==1 && mapBo[x+1][y]==1;
    }
}