package programmers;

class Solution_PRG_2021Kakao_광고삽입 {
    public String solution(String play_time, String adv_time, String[] logs) {
        String answer = "";
        
        int videoLength = strToSec(play_time);
        int[] watch = new int[videoLength+1];
        int N = logs.length;
        
        for (int i=0; i<N; i++) {
            String[] splitted = logs[i].split("-");
            int startTimeSec = strToSec(splitted[0]);
            int endTimeSec = strToSec(splitted[1]);
            for (int t=startTimeSec; t<endTimeSec; t++) {
                watch[t]++;
            }
        }
        
        int advLength = strToSec(adv_time);
        long maxUserSum = 0L;
        int maxStartTime = 0;
        
        long userSum = 0L;
        for (int i=0; i<advLength; i++)
            userSum += watch[i];
        maxUserSum = userSum;
        
        int start = 0;
        int end = advLength-1;
        while (end < videoLength) {
            userSum -= watch[start++];
            userSum += watch[++end];

            if (maxUserSum < userSum) {
                maxUserSum = userSum;
                maxStartTime = start;
            }
        }
        
        answer = secToStr(maxStartTime);
        
        return answer;
    }
    
    int strToSec(String timeStr) {
        String[] splitted = timeStr.split(":");
        int hour = Integer.parseInt(splitted[0]);
        int min = Integer.parseInt(splitted[1]);
        int sec = Integer.parseInt(splitted[2]);
        return hour*60*60 + min*60 + sec;
    }
    
    String secToStr(int sec) {
        int hour = sec / (60*60);
        sec -= (60*60) * hour;
        int min = sec / 60;
        sec -= 60 * min;
        return String.format("%02d:%02d:%02d", hour, min, sec);
    }
}