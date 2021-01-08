package minho_jang.coding_test;
/**
 * 프로그래머스 코딩 테스트 연습
 * 2021.01.08.
 * 
 * LEVEL 3
 * 외벽 점검  
 * 출처: https://programmers.co.kr/learn/courses/30/lessons/60062
 * 
 * @author minho
 * 
 * TODO not solved. 재귀 돌면서 ArrayList를 매개변수로 넘겨주는 데에 문제가 있거나, for문 도는게 문제가 있거나
 * 내가 원하는대로 탐색이 돌지 않는다.
 */
import java.util.*;
public class Solution22 {
	final static int BIG_NUMBER = 88888888;
    public int solution(int n, int[] weak, int[] dist) {
        int answer = 0;
        
        ArrayList<Integer> weakArrlst = new ArrayList<>();
        for (int w : weak) {
            weakArrlst.add(w);
        }
        ArrayList<Integer> distArrlst = new ArrayList<>();
        for (int d : dist) {
            distArrlst.add(d);
        }
        
        int[] circle = new int[n];
        for (int i = 0; i < n; i++) {
            circle[i] = 0;
        }
        for (int i = 0; i < weak.length; i++) {
            circle[weak[i]] = 1;
        }
        
        answer = repair(circle, weakArrlst, distArrlst);
        
        return answer;
    }
    
    int repair(int[] circle, ArrayList<Integer> weak, ArrayList<Integer> dist) {
        // 재귀 종료 조건
        if (dist.isEmpty() && sum(circle) > 0) {    // 친구를 모두 투입해도 취약 지점이 남음
            return BIG_NUMBER;
        }
        if (sum(circle) == 0) {
            return 0;
        }
        
        int[] cloneCircle1 = circle.clone();
        int[] cloneCircle2 = circle.clone();
        ArrayList<Integer> cloneWeak1 = (ArrayList<Integer>) weak.clone();
        ArrayList<Integer> cloneWeak2 = (ArrayList<Integer>) weak.clone();
        ArrayList<Integer> cloneDist1 = (ArrayList<Integer>) dist.clone();
        ArrayList<Integer> cloneDist2 = (ArrayList<Integer>) dist.clone();
        int min = BIG_NUMBER;

        for (int i = 0; i < weak.size(); i++) {
            int weakValue = weak.get(i);
            
            for (int j = 0; j < dist.size(); j++) {
                int distValue = dist.get(j);
                
                // 시계방향 수리
                for (int k = 0; k <= distValue; k++) {
                    int idx = weakValue + k;
                    if (idx >= cloneCircle1.length)   idx -= cloneCircle1.length;
                    if (cloneCircle1[idx] == 1) {
                        removeElement(cloneWeak1, idx);
                        cloneCircle1[idx] = 0;
                    }
                }
                removeElement(cloneDist1, distValue);
                printArr(cloneCircle1);
                printArr(cloneWeak1);
                printArr(cloneDist1);
                int result1 = 1 + repair(cloneCircle1, cloneWeak1, cloneDist1);
                System.out.println("result1: " + result1);
                
                // 반시계방향 수리
                for (int k = 0; k <= distValue; k++) {
                    int idx = weakValue - k;
                    if (idx < 0)   idx += cloneCircle2.length;
                    if (cloneCircle2[idx] == 1) {
                        removeElement(cloneWeak2, idx);
                        cloneCircle2[idx] = 0;
                    }
                }
                removeElement(cloneDist2, distValue);
                printArr(cloneCircle2);
                printArr(cloneWeak2);
                printArr(cloneDist2);
                int result2 = 1 + repair(cloneCircle2, cloneWeak2, cloneDist2);
                System.out.println("result2: " + result2);
                
                int result = (result1 > result2 ? result2 : result1);
                if (result < min) {
                    min = result;
                }
            }
        }
        
        return min;
    }
    
    int sum(int[] arr) {
        int s = 0;
        for (int i : arr)
            s += i;
        return s;
    }
    
    void printArr(int[] arr) {
        System.out.print("[");
        for (int i : arr) {
            System.out.print(i + " ");
        }
        System.out.println("]");
    }
    
    void printArr(ArrayList<Integer> arr) {
        System.out.print("[");
        for (int i : arr) {
            System.out.print(i + " ");
        }
        System.out.println("]");
    }
    
    void removeElement(ArrayList<Integer> arr, int ele) {
        for (int i = 0; i < arr.size(); i++) {
            if (arr.get(i) == ele) {
                arr.remove(i);
                break;
            }
        }
    }
}