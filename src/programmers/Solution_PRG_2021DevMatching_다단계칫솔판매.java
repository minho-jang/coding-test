package programmers;

import java.util.*;

class Solution_PRG_2021DevMatching_다단계칫솔판매 {
    static class Node {
        String ref;
        int profit;
        Node(String ref, int profit) {
            this.ref = ref;
            this.profit = profit;
        }
    }
    static HashMap<String, Node> map;
    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        
        map = new HashMap<>();
        int N = enroll.length;
        for(int i=0; i<N; i++) 
            map.put(enroll[i], new Node(referral[i], 0));
        
        N = seller.length;
        for(int i=0; i<N; i++) {
            String s = seller[i];
            int a = amount[i];
            dfs(map.get(s), a*100);
        }
        
        N = enroll.length;
        int[] answer = new int[N];
        for(int i=0; i<N; i++) {
            answer[i] = map.get(enroll[i]).profit;
        }
        
        return answer;
    }
    
    void dfs(Node seller, int profit) {
        if (profit / 10 == 0) {
            seller.profit += profit;
            return;
        }
        
        int p = profit / 10;
        seller.profit += profit - p;
        if (!"-".equals(seller.ref))
            dfs(map.get(seller.ref), p);
    }
}