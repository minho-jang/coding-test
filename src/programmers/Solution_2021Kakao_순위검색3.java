package programmers;

import java.util.*;

class Solution_2021Kakao_순위검색3 {
    public int[] solution(String[] info, String[] query) {
    	HashMap<String, Integer> languages = new HashMap<>();
    	languages.put("-", 0);
    	languages.put("java", 1);
    	languages.put("python", 2);
    	languages.put("cpp", 3);
        HashMap<String, Integer> jobs = new HashMap<>();
        jobs.put("-", 0);
        jobs.put("backend", 1);
        jobs.put("frontend", 2);
        HashMap<String, Integer> careers = new HashMap<>();
        careers.put("-", 0);
        careers.put("junior", 1);
        careers.put("senior", 2);        
        HashMap<String, Integer> foods = new HashMap<>();
        foods.put("-", 0);
        foods.put("chicken", 1);
        foods.put("pizza", 2);
        
        int[] answer = new int[query.length];
        
        for (int i = 0; i < query.length; i++) {
            int[] conditions = new int[5];
            String[] splitted = query[i].split(" ");
            conditions[0] = languages.get(splitted[0]);
            conditions[1] = jobs.get(splitted[2]);
            conditions[2] = careers.get(splitted[4]);
            conditions[3] = foods.get(splitted[6]);
            conditions[4] = Integer.parseInt(splitted[6]);
            // System.out.println(Arrays.toString(conditions));
            
            // 후보군. 처음에는 전부 후보.
            ArrayList<Integer> candidates = new ArrayList<>();
            for (int j = 0; j < info.length; j++) {
                candidates.add(j);
            }
            
            // 조건 확인
            for (int j = 0; j < 5; j++) {
                
                if (conditions[j] == 0)
                    continue;
                
                // 후보군 중 조건에 맞는 후보만 걸러
                ArrayList<Integer> filterred = new ArrayList<>();
                for (int k = 0; k < candidates.size(); k++) {
                	String[] splittedInfo = info[candidates.get(k)].split(" ");
                    int[] candidateInfo = {
                    		languages.get(splittedInfo[0]),
                    		jobs.get(splittedInfo[1]),
                    		careers.get(splittedInfo[2]),
                    		foods.get(splittedInfo[3]),
                    		Integer.parseInt(splittedInfo[4])};
                    if (j == 4) {
                        if (candidateInfo[j] >= conditions[j])
                            filterred.add(candidates.get(k));
                        
                    } else {
                        if (candidateInfo[j] == conditions[j]) {
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