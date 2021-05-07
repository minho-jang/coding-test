package programmers;

import java.util.*;

class Solution_PRG_2019KakaoWinterIntern_호텔방배정 {
    
    HashMap<Long, Long> map;
    
    public long[] solution(long k, long[] room_number) {
        int len = room_number.length;
        long[] answer = new long[len];
        
        map = new HashMap<>();
        for (int i=0; i<len; i++) {
            // System.out.println(map);
            long rn = room_number[i];
            answer[i] = findEmptyRoom(rn);
        }
        
        return answer;
    }
    
    long findEmptyRoom(long roomNumber) {
        if (!map.containsKey(roomNumber)) {
            map.put(roomNumber, roomNumber+1);
            return roomNumber;
        }
        
        long nextRoom = map.get(roomNumber);
        long empty = findEmptyRoom(nextRoom);
        map.put(roomNumber, empty);
        return empty;
    }
}