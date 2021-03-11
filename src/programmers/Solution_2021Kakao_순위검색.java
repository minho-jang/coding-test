package programmers;

import java.util.*;

class Solution_2021Kakao_순위검색 {
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
        
        int[][] applicants = new int[info.length][5];
        for (int i = 0; i < applicants.length; i++) {
        	String[] splitted = info[i].split(" ");
        	applicants[i] = new int[] {
        			languages.get(splitted[0]), 
        			jobs.get(splitted[1]),
        			careers.get(splitted[2]), 
        			foods.get(splitted[3]),
        			Integer.parseInt(splitted[4]) };
		}
        
        for (int i = 0; i < query.length; i++) {
            String[] splitted = query[i].split(" ");
            int[] conditions = new int[] {
            	languages.get(splitted[0]),
            	jobs.get(splitted[2]),
            	careers.get(splitted[4]),
            	foods.get(splitted[6]),
            	Integer.parseInt(splitted[7]) };
            // System.out.println(Arrays.toString(conditions));
            
            int count = 0;
            for (int[] applicant : applicants) {
            	boolean flag = false;
            	for (int j = 0; j < 4; j++) {
					if (conditions[j] != 0 && conditions[j] != applicant[j]) {
						flag = true;
						break;
					}
				}
            	if (!flag && conditions[4] > applicant[4])	flag = true;
            	if (flag) {
            		continue;
            	}
            	count++;
            }
            answer[i] = count;
        }
        
        return answer;
    }
}