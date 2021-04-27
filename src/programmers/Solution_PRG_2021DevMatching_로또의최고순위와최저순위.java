package programmers;

class Solution_PRG_2021DevMatching_로또의최고순위와최저순위 {
    public int[] solution(int[] lottos, int[] win_nums) {
        int[] answer = {};
        
        int zeroCount = 0;
        int winCount = 0;
        for (int i=0; i<6; i++) {
            if (lottos[i] == 0) {
                zeroCount++;
                continue;
            }
            
            for (int j=0; j<6; j++) {
                if (lottos[i] == win_nums[j]) {
                    winCount++;
                }
            }
        }
        
        int max = 7-winCount-zeroCount;
        int min = 7-winCount;
        
        answer = new int[2];
        answer[0] = max > 5 ? 6 : max;
        answer[1] = min > 5 ? 6 : min;
        
        return answer;
    }
}