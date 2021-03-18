package programmers;

import java.util.*;

// https://programmers.co.kr/learn/courses/30/lessons/72411
public class Solution_PRG_2021Kakao_메뉴리뉴얼{
    static ArrayList<CombinationCount> combinationSet;
        
    public String[] solution(String[] orders, int[] course) {
        String[] answer = {};
        ArrayList<String> answerAL = new ArrayList<>();
        
        char[][] ordersChar = new char[orders.length][];
        for (int i = 0; i < orders.length; i++) {
            ordersChar[i] = orders[i].toCharArray();
            Arrays.sort(ordersChar[i]);
        }
        
        for (int menuNum : course) {
            combinationSet = new ArrayList<>();
                
            for (int i = 0; i < ordersChar.length; i++) {
                if (ordersChar[i].length >= menuNum)
                    makeCombination(0, 0, ordersChar[i], menuNum, new char[menuNum], i);
            }
            
            Collections.sort(combinationSet, (o1, o2) -> {
                if (o1.count == o2.count)
                    return o1.comb.compareTo(o2.comb);
                else 
                    return o2.count - o1.count;
            });
            
            if (combinationSet.isEmpty())   continue;
            
            int max = combinationSet.get(0).count;
            for (CombinationCount cc : combinationSet) {
                if (max < 2)            break;
                if (cc.count < max)     break;
                answerAL.add(cc.comb);
            }
        }
        
        Collections.sort(answerAL);
        
        answer = new String[answerAL.size()];
        for (int i = 0; i < answerAL.size(); i++) {
            answer[i] = answerAL.get(i);
        }
        return answer;
    }
    
    static void makeCombination(int cnt, int start, char[] arr, int R, char[] res, int guestNum) {
        if (cnt == R) {
            // System.out.println(Arrays.toString(res));
            
            for (CombinationCount cc : combinationSet) {
                if (cc.comb.equals(new String(res))) {
                    cc.count++;
                    return;
                }
            }
            
            combinationSet.add(new CombinationCount(new String(res)));
            
            return;
        }
        
        for (int i = start; i < arr.length; i++) {
            res[cnt] = arr[i];
            makeCombination(cnt+1, i+1, arr, R, res, guestNum);
        }
    }
    
    static class CombinationCount {
        String comb;
        int count;
        
        CombinationCount(String comb) {
            this.comb = comb;
            this.count = 1;
        }
        
        public String toString() {
            return comb + "(" + count + ")";
        }
    }
}