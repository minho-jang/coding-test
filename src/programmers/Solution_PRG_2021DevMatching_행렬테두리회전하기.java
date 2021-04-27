package programmers;

class Solution_PRG_2021DevMatching_행렬테두리회전하기 {
    static int[][] arr;
    
    public int[] solution(int rows, int columns, int[][] queries) {
        int[] answer = new int[queries.length];
        
        int cnt = 1;
        arr = new int[rows][columns];
        for(int i=0; i<rows; i++) {
            for(int j=0; j<columns; j++) {
                arr[i][j] = cnt++;
            }
        }
        
        for (int i=0; i<queries.length; i++) {
            int[] q = queries[i];
            int result = rotate(q);
            answer[i] = result;
        }
        
        return answer;
    }
    
    int rotate(int[] query) {
        int min = 987654321;
        
        int x1 = query[0]-1;
        int y1 = query[1]-1;
        int x2 = query[2]-1;
        int y2 = query[3]-1;
        
        int tmp = arr[x1][y1];
        int curX = x1;
        int curY = y1;
        while(++curX <= x2) {
            arr[curX-1][curY] = arr[curX][curY];
            min = Math.min(min, arr[curX][curY]);
        }
        curX--;
        while(++curY <= y2) {
            arr[curX][curY-1] = arr[curX][curY];
            min = Math.min(min, arr[curX][curY]);
        }
        curY--;
        while(--curX >= x1) {
            arr[curX+1][curY] = arr[curX][curY];
            min = Math.min(min, arr[curX][curY]);
        }
        curX++;
        while(--curY > y1) {
            arr[curX][curY+1] = arr[curX][curY];
            min = Math.min(min, arr[curX][curY]);
        }
        curY++;
        
        arr[curX][curY] = tmp;
        min = Math.min(min, arr[curX][curY]);
        
        return min;
    }
}