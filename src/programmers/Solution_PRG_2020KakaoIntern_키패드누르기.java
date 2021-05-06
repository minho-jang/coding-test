package programmers;

import java.util.HashMap;

class Solution_PRG_2020KakaoIntern_키패드누르기 {
    static class Pos {
        int r,c;
        Pos (int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
    static String handInput;
    static Pos lpos, rpos;
    static HashMap<Character, Pos> map;
    static StringBuilder answerSb;
    public String solution(int[] numbers, String hand) {
        handInput = hand;
        answerSb = new StringBuilder();
        
        map = new HashMap<>();
        map.put('1', new Pos(0,0));
        map.put('2', new Pos(0,1));
        map.put('3', new Pos(0,2));
        map.put('4', new Pos(1,0));
        map.put('5', new Pos(1,1));
        map.put('6', new Pos(1,2));
        map.put('7', new Pos(2,0));
        map.put('8', new Pos(2,1));
        map.put('9', new Pos(2,2));
        map.put('0', new Pos(3,1));
        map.put('*', new Pos(3,0));
        map.put('#', new Pos(3,2));
        
        lpos = map.get('*');
        rpos = map.get('#');
        for(int i=0; i<numbers.length; i++) {
            int num = numbers[i];
            move(num);
        }
        
        return answerSb.toString();
    }
    
    void move(int num) {
        if (num==1 || num==4 || num==7) {
            moveLeft(num);
            
        } else if (num==3 || num==6 || num==9) {
            moveRight(num);
            
        } else if (num==2 || num==5 || num==8 || num==0) {
            int distanceL = getDistance(lpos, map.get((char)(num+'0')));
            int distanceR = getDistance(rpos, map.get((char)(num+'0')));
            if (distanceL < distanceR) {
                moveLeft(num);
                
            } else if (distanceL > distanceR) {
                moveRight(num);
                
            } else {
                if (handInput.equals("left"))
                    moveLeft(num);
                else if (handInput.equals("right"))
                    moveRight(num);
            }
            
        }
    }
    
    void moveLeft(int num) {
        Pos target = map.get((char)(num+'0'));
        lpos = target;
        answerSb.append("L");
    }
    
    void moveRight(int num) {
        Pos target = map.get((char)(num+'0'));
        rpos = target;
        answerSb.append("R");
    }
    
    int getDistance(Pos start, Pos end) {
        return Math.abs(end.r-start.r) + Math.abs(end.c-start.c);
    }
}