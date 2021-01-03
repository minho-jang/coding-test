package minho_jang.coding_test;
/**
 * 프로그래머스 코딩 테스트 연습
 * 2021.01.03.
 * 
 * LEVEL 2
 * 위장     
 * 출처: https://programmers.co.kr/learn/courses/30/lessons/42578
 * 
 * @author minho
 *
 */
import java.util.*;
public class Solution18 {
	public int solution(String[][] clothes) {
        int answer = 0;
        
        ArrayList<Clothes> clothesList = new ArrayList<>();
        for (String[] cloth : clothes) {
            String name = cloth[0];
            String type = cloth[1];
            int clothesIndex = hasClothesType(clothesList, type);
            
            if (clothesIndex >= 0) {
                Clothes c = clothesList.get(clothesIndex);
                c.count += 1;
            } else {
                Clothes c = new Clothes(type);
                clothesList.add(c);
            }
        }
        
        int cases = 1;
        for (Clothes c : clothesList) {
            cases *= (c.count + 1);
        }
        answer = cases - 1;
        
        return answer;
    }
    
    int hasClothesType(ArrayList<Clothes> clothesList, String type) {
        for (int i = 0; i < clothesList.size(); i++) {
            Clothes c = clothesList.get(i);
            if (c.type.equals(type)) {
                return i;
            }
        }
        
        return -1;
    }
    
    class Clothes {
        String type;
        int count;
        
        Clothes (String t) {
            this.type = t;
            this.count = 1;
        }
    }
}