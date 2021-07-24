package programmers;

import java.util.HashSet;

public class Solution_PRG_전화번호목록 {

    public boolean solution(String[] phone_book) {
        HashSet<String> set = new HashSet<>();
        for (String phone : phone_book) {
            set.add(phone);
        }

        for (String phone : phone_book) {
            int len = phone.length();
            for (int i = 1; i < len; i++) {
                if (set.contains(phone.substring(0, i))) {
                    return false;
                }
            }
        }

        return true;
    }

    public static void main(String[] args) {
        Solution_PRG_전화번호목록 sol = new Solution_PRG_전화번호목록();

//    String[] phone_book = {"119", "97674223", "1195524421"};
//    String[] phone_book = {"123", "456", "789"};
        String[] phone_book = {"12", "123", "1235", "567", "88"};
        System.out.println(sol.solution(phone_book));
    }
}
