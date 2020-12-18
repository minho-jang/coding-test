package minho_jang.coding_test;
/**
 * 프로그래머스 코딩 테스트 연습
 * 2020.12.18.
 * 
 * LEVEL 2
 * 카카오프렌즈 컬러링북  
 * 출처: https://programmers.co.kr/learn/courses/30/lessons/1829
 * 
 * @author minho
 *
 */
import java.util.*;
public class Solution14 {
	int[][] visited;
    int[][] globalPicture;
    
    public int[] solution(int m, int n, int[][] picture) {
        int numberOfArea = 0;
        int maxSizeOfOneArea = 0;
        
        globalPicture = picture;
        visited = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                visited[i][j] = 0;
            }
        }
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (visited[i][j] != 0) {
                    continue;
                }
                
                int color = picture[i][j];
                if (color == 0) {
                    visited[i][j] = 1;
                    continue;
                }
                
                int size = spreadFromHere(i, j, color);
                if (size > maxSizeOfOneArea) {
                    maxSizeOfOneArea = size;
                }
                numberOfArea++;
            }
        }

        int[] answer = new int[2];
        answer[0] = numberOfArea;
        answer[1] = maxSizeOfOneArea;
        return answer;
    }
    
    int spreadFromHere(int x, int y, int color) {
        int size = 1;
        int boundRow = globalPicture.length;
        int boundCol = globalPicture[0].length;
        
        if (visited[x][y] == 0) {
            visited[x][y] = 1;
            
            if (x-1 >= 0 && globalPicture[x-1][y] == color) {
                size += spreadFromHere(x-1, y, color);
            }
            if (x+1 < boundRow &&  globalPicture[x+1][y] == color) {
                size += spreadFromHere(x+1, y, color);
            }
            if (y-1 >= 0 && globalPicture[x][y-1] == color) {
                size += spreadFromHere(x, y-1, color);
            }
            if (y+1 < boundCol && globalPicture[x][y+1] == color) {
                size += spreadFromHere(x, y+1, color);
            }
        } else {
            return 0;
        }
        return size;
    }
}