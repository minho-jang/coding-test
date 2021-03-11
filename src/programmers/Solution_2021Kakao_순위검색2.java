package programmers;

import java.util.*;

class Solution_2021Kakao_순위검색2 {
    static final int CONDITION_NUM = 5;
    
    public int[] solution(String[] info, String[] query) {
        int[] answer = new int[query.length];
        
        for (int i = 0; i < query.length; i++) {
            String[] conditions = new String[CONDITION_NUM];
            String[] splitted = query[i].split(" and ");
            for (int j = 0; j < CONDITION_NUM-1; j++) {
                if (j == CONDITION_NUM-2) {
                    String[] tmp = splitted[j].split(" ");
                    conditions[j] = tmp[0];
                    conditions[j+1] = tmp[1];
                } else {
                    conditions[j] = splitted[j];
                }
            }
            // System.out.println(Arrays.toString(conditions));
            
            // 후보군. 처음에는 전부 후보.
            ArrayList<Integer> candidates = new ArrayList<>();
            for (int j = 0; j < info.length; j++) {
                candidates.add(j);
            }
            
            // 조건 확인
            for (int j = 0; j < CONDITION_NUM; j++) {
                
                if (conditions[j].equals("-"))
                    continue;
                
                // 후보군 중 조건에 맞는 후보만 걸러
                ArrayList<Integer> filterred = new ArrayList<>();
                for (int k = 0; k < candidates.size(); k++) {
                    String[] candidateInfo = info[candidates.get(k)].split(" ");
                    if (j == CONDITION_NUM-1) {
                        int candidateScore = Integer.parseInt(candidateInfo[j]);
                        int conditionScore = Integer.parseInt(conditions[j]);
                        if (candidateScore >= conditionScore) {
                            filterred.add(candidates.get(k));
                        } 
                    } else {
                        if (candidateInfo[j].equals(conditions[j])) {
                            filterred.add(candidates.get(k));
                        }  
                    } 
                }
                
                candidates = filterred;
                
            }
            
            answer[i] = candidates.size();
        }
        
        return answer;
    }
}